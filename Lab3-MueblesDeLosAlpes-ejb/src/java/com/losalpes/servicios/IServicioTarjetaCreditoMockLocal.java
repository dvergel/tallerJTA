/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.losalpes.servicios;

import com.losalpes.entities.Mueble;
import com.losalpes.entities.TarjetaCreditoAlpes;
import com.losalpes.excepciones.CupoInsuficienteException;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Dev
 */
@Local
public interface IServicioTarjetaCreditoMockLocal {
    public void agregarTarjeta(TarjetaCreditoAlpes tarjeta) throws Exception ;
    
    public void eliminarTarjeta(String id) throws Exception ;
    
    public void compra(List<Mueble> carrito,TarjetaCreditoAlpes tarjeta) throws CupoInsuficienteException;
}
