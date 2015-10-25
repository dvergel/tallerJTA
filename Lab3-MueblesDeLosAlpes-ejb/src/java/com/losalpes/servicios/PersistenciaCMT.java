/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.losalpes.servicios;

import com.losalpes.entities.Mueble;
import com.losalpes.entities.TarjetaCreditoAlpes;
import com.losalpes.entities.Usuario;
import com.losalpes.entities.Vendedor;
import com.losalpes.entities.Vendedores;
import com.losalpes.excepciones.CupoInsuficienteException;
import com.losalpes.excepciones.OperacionInvalidaException;
import java.io.Serializable;
import java.util.List;
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
public class PersistenciaCMT implements IServicioPersistenciaDerbyMockLocal,IServicioPersistenciaDerbyMockRemote, Serializable{
    @PersistenceContext(unitName="Lab3-MueblesDeLosAlpes-ejbDebyPU")
    private EntityManager derby;
    
    
    @Override
    public void insertRemoteDatabase(Vendedores vendedor) throws Exception,RuntimeException{
        Vendedores exist=derby.find(Vendedores.class, vendedor.getIdentificacion());
        if(exist!=null){
            derby.persist(vendedor);
        }else{
            derby.merge(vendedor);
        }     
    }
    
    @Override
    public void deleteRemoteDatabase(Vendedores vendedor) throws Exception,RuntimeException{
        Vendedor exist=derby.find(Vendedor.class, vendedor.getIdentificacion());
        if(exist!=null){
            derby.remove(vendedor);
        }
    }
    
    @Override
    public Vendedores classCast(Vendedor obj) throws Exception,RuntimeException{
        Vendedores cast=new Vendedores();
        cast.setIdentificacion(String.valueOf(obj.getIdentificacion()));
        cast.setNombres(obj.getNombres());
        cast.setApellidos(obj.getApellidos());
        return cast;
    }
    
     //-----------------------------------------------------------
    // Métodos
    //-----------------------------------------------------------
    
    /**
     * Permite crear un objeto dentro de la persistencia del sistema.
     * @param obj Objeto que representa la instancia de la entidad que se quiere crear.
     */
    @Override
    public void create(Object obj) throws OperacionInvalidaException
    {
      derby.persist(obj);
    }

    /**
     * Permite modificar un objeto dentro de la persistencia del sistema.
     * @param obj Objeto que representa la instancia de la entidad que se quiere modificar.
     */
    @Override
    public void update(Object obj)
    {
       derby.merge(obj);
    }

    /**
     * Permite borrar un objeto dentro de la persistencia del sistema.
     * @param obj Objeto que representa la instancia de la entidad que se quiere borrar.
     */
    @Override
    public void delete(Object obj) throws OperacionInvalidaException
    {
        derby.remove(obj);

    }

    /**
     * Retorna la lista de todos los elementos de una clase dada que se encuentran en el sistema.
     * @param c Clase cuyos objetos quieren encontrarse en el sistema.
     * @return list Listado de todos los objetos de una clase dada que se encuentran en el sistema.
     */
    /*@Override
    public List findAll(Class c)
    {

        return derby.createQuery("select O from " + c.getSimpleName() + " as O").getResultList();
    }*/
    @Override
    public List findAll(Class c) {
        javax.persistence.criteria.CriteriaQuery cq = derby.getCriteriaBuilder().createQuery();
        cq.select(cq.from(c));
        return derby.createQuery(cq).getResultList();
    }

    /**
     * Retorna la instancia de una entidad dado un identificador y la clase de la entidadi.
     * @param c Clase de la instancia que se quiere buscar.
     * @param id Identificador unico del objeto.
     * @return obj Resultado de la consulta.
     */
    @Override
    public Object findById(Class c, Object id)
    {

     return derby.find(c, id);
    
    }
    
    
    public void comprar(List<Mueble> muebles, Usuario usuario, 
            TarjetaCreditoAlpes tarjetaCreditoAlpes) throws CupoInsuficienteException{
        double cupo = tarjetaCreditoAlpes.getCupo();
        double total = totalizar(muebles);
        
        if (cupo - total < 0) {
            throw  new CupoInsuficienteException("Cupo insuficiente para la compra");
        }
        
        tarjetaCreditoAlpes.setCupo(cupo - total);
        
        derby.merge(tarjetaCreditoAlpes);
        
    }
    
     private double totalizar(List<Mueble> muebles) {
        double total = 0.0;
        for (Mueble mueble: muebles) {
            total += mueble.getPrecio();
        }
        return total;
    }
}
