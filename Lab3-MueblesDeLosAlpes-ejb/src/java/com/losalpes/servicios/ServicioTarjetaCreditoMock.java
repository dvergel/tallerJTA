/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.losalpes.servicios;

import com.losalpes.entities.Mueble;
import com.losalpes.entities.TarjetaCreditoAlpes;
import com.losalpes.excepciones.CupoInsuficienteException;
import com.losalpes.excepciones.OperacionInvalidaException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author Dev
 */
@Stateless
public class ServicioTarjetaCreditoMock implements IServicioTarjetaCreditoMockLocal, IServicioTarjetaCreditoMockRemote {
    //-----------------------------------------------------------
    // Atributos
    //-----------------------------------------------------------

    /**
     * Interface con referencia al servicio de persistencia en el sistema
     */
    @EJB
    private IServicioPersistenciaDerbyMockLocal persistencia;

    //-----------------------------------------------------------
    // Constructor
    //-----------------------------------------------------------
    /**
     * Constructor sin argumentos de la clase
     */
    public ServicioTarjetaCreditoMock() {
    }

    //-----------------------------------------------------------
    // Métodos
    //-----------------------------------------------------------
    /**
     * Agrega un TarjetaCredito al sistema
     *
     * @param TarjetaCredito Nuevo TarjetaCredito
     */
    @Override
    public void agregarTarjeta(TarjetaCreditoAlpes tarjeta) throws Exception {
        try {
            if (persistencia.findById(TarjetaCreditoAlpes.class, tarjeta.getIdentificacion()) != null) {
                persistencia.update(tarjeta);
            } else {
                persistencia.create(tarjeta);
            }
        } catch (OperacionInvalidaException ex) {
            Logger.getLogger(ServicioCatalogoMock.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * Se elimina un TarjetaCredito del sistema dado su identificador único
     *
     * @param id Identificador único del TarjetaCredito
     */
    @Override
    public void eliminarTarjeta(String id) throws Exception {
        Mueble m = (Mueble) persistencia.findById(TarjetaCreditoAlpes.class, id);
        try {
            persistencia.delete(m);
        } catch (OperacionInvalidaException ex) {
            Logger.getLogger(ServicioCatalogoMock.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * reduce el monto de la TarjetaCredito
     *
     * @param id Identificador único del TarjetaCredito
     */
    @Override
    public void compra(List<Mueble> carrito, TarjetaCreditoAlpes tarjeta) throws CupoInsuficienteException {
        double totalCompra = 0;
        for (Mueble item : carrito) {
            totalCompra += item.getCantidad() * item.getPrecio();
        }
        if (totalCompra > tarjeta.getCupo()) {
            throw new CupoInsuficienteException("Fondos Insuficientes!!");
        } else {
            tarjeta.setCupo(tarjeta.getCupo() - totalCompra);
            try {
                persistencia.update(tarjeta);
            } catch (Exception ex) {
                throw new CupoInsuficienteException(ex.getMessage());
            }
        }
    }
}
