/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.losalpes.servicios;

import com.losalpes.entities.Vendedor;
import com.losalpes.excepciones.OperacionInvalidaException;
import java.util.Properties;
import javax.naming.InitialContext;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Administrador
 */


public class PersistenciaCMTMockTest {
    
    /**
     * Interface con referencia al servicio de vendedores en el sistema
     */
    private IServicioVendedoresMockRemote servicio;
    
    /**
     * Método que se ejecuta antes de comenzar la prueba unitaria
     * Se encarga de inicializar todo lo necesario para la prueba
     */
    @Before
    public void setUp() throws Exception
    {
        try
        {
            Properties env = new Properties();
            env.put("java.naming.factory.initial", "com.sun.enterprise.naming.SerialInitContextFactory");
            env.put("java.naming.factory.url.pkgs", "com.sun.enterprise.naming");
            env.put("org.omg.CORBA.ORBInitialPort", "3700");
            InitialContext contexto;
            contexto = new InitialContext(env);
            servicio = (IServicioVendedoresMockRemote) contexto.lookup("com.losalpes.servicios.IServicioVendedoresMockRemote");
        } 
        catch (Exception e)
        {
            throw new Exception(e.getMessage());
        }
    }
    
    /**
     * Método de prueba para agregar un vendedor al sistema 
     */
    @Test(expected=OperacionInvalidaException.class)
    public void testAgregarVendedor() throws Exception
    {
        boolean thrown = false;
        Vendedor vendedor = null;
        
        try {
            servicio.agregarVendedor(vendedor);
        } catch (OperacionInvalidaException e) {
            thrown = true;
        }
        
        assertFalse(thrown);
    }
    
     /**
     * Método de prueba para eliminar un vendedor al sistema
     */
    @Test(expected=OperacionInvalidaException.class)
    public void testEliminarVendedor() throws Exception
    {
        boolean thrown = false;
        
        try {
            servicio.eliminarVendedor(1L);
        } catch (OperacionInvalidaException e) {
            thrown = true;
        }
        
        assertFalse(thrown);
    }
}
