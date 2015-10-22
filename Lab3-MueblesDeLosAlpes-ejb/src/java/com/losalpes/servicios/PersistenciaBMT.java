/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.losalpes.servicios;

import com.losalpes.entities.Vendedor;
import javax.annotation.Resource;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.sql.DataSource;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

/**
 *
 * @author za.garcia10
 */
@TransactionManagement(TransactionManagementType.BEAN)
public class PersistenciaBMT {
    
    @Resource 
    private UserTransaction ut;
    
    @Resource(mappedName = "jdbc/derbyDatasource") 
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
        
    }
    
    public void deleteRemoteDatabase(Vendedor vendedor){
        
    }
    
    
    
}
