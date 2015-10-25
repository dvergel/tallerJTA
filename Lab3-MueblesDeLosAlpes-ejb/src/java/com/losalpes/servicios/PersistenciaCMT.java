/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.losalpes.servicios;

import com.losalpes.entities.Vendedor;
import com.losalpes.entities.Vendedores;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author de.vergel10
 */
@TransactionManagement(TransactionManagementType.CONTAINER)
@Stateless
public class PersistenciaCMT {
    @PersistenceContext(unitName="Lab3-MueblesDeLosAlpes-ejbDebyPU")
    private EntityManager derby;
    
    
    public void insertRemoteDatabase(Vendedores vendedor) throws Exception,RuntimeException{
        Vendedores exist=derby.find(Vendedores.class, vendedor.getIdentificacion());
        if(exist!=null){
            derby.persist(vendedor);
        }else{
            derby.merge(vendedor);
        }     
    }
    
    public void deleteRemoteDatabase(Vendedores vendedor) throws Exception,RuntimeException{
        Vendedor exist=derby.find(Vendedor.class, vendedor.getIdentificacion());
        if(exist!=null){
            derby.remove(vendedor);
        }
    }
    
    public Vendedores classCast(Vendedor obj) throws Exception,RuntimeException{
        Vendedores cast=new Vendedores();
        cast.setIdentificacion(String.valueOf(obj.getIdentificacion()));
        cast.setNombres(obj.getNombres());
        cast.setApellidos(obj.getApellidos());
        return cast;
    }
            
}
