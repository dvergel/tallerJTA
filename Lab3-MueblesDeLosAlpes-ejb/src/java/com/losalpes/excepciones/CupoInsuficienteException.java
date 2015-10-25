/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.losalpes.excepciones;

import javax.ejb.ApplicationException;

/**
 *
 * @author Dev
 */
@ApplicationException(rollback = true)
public class CupoInsuficienteException extends Exception {

    // -----------------------------------------------
    // Constructor
    // -----------------------------------------------
    /**
     * Constructor de la clase.
     *
     * @param mensaje Mensaje de la excepción
     */
    public CupoInsuficienteException(String mensaje) {
        super(mensaje);
    }
}
