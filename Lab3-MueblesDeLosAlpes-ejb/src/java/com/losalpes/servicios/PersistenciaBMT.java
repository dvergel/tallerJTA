/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.losalpes.servicios;

import com.losalpes.entities.Vendedor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.sql.DataSource;
import javax.transaction.UserTransaction;

/**
 *
 * @author za.garcia10
 */
@Stateless
@TransactionManagement(TransactionManagementType.BEAN)
public class PersistenciaBMT {
    
    @Resource 
    private UserTransaction ut;
    
    @Resource(mappedName = "jdbc/derbyDS") 
    private DataSource dataSource;
    
    public void initTransaction() throws Exception {
        ut.begin();
    }
    
    public void commitTransaction() throws Exception {
        ut.commit();
    }
    
    public void rollBackTransaction() throws Exception {
        ut.rollback();
    }
    
    public void insertRemoteDatabase(Vendedor vendedor){
        try {
            initTransaction();
            StringBuilder sql = new StringBuilder();
            sql.append("insert into vendedores (identificacion, nombres, apellidos) ");
            sql.append("values (?,?,?)");
            Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql.toString());
            statement.setString(1, String.valueOf(vendedor.getIdentificacion()));
            statement.setString(2, vendedor.getNombres());
            statement.setString(3, vendedor.getApellidos());
            statement.executeQuery();
            commitTransaction();
        } catch (Exception ex) {
            try {
                rollBackTransaction();
            } catch (Exception ex1) {
                Logger.getLogger(PersistenciaBMT.class.getName()).log(Level.SEVERE, null, ex1);
            }
        } 
    }
    
    public void deleteRemoteDatabase(Vendedor vendedor){
        try {
            initTransaction();
            final String sql = "delete from vendedores v where v.identificacion = ?";
            Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, String.valueOf(vendedor.getIdentificacion()));
            statement.executeQuery();
            commitTransaction();
        } catch (Exception ex) {
            try {
                rollBackTransaction();
            } catch (Exception ex1) {
                Logger.getLogger(PersistenciaBMT.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }
    }
    
    
    
}
