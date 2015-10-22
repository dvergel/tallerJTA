/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.losalpes.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author de.vergel10
 */
@Entity
@Table(name = "VENDEDORES", catalog = "", schema = "ADMINDERBY")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Vendedores.findAll", query = "SELECT v FROM Vendedores v"),
    @NamedQuery(name = "Vendedores.findByIdentificacion", query = "SELECT v FROM Vendedores v WHERE v.identificacion = :identificacion"),
    @NamedQuery(name = "Vendedores.findByNombres", query = "SELECT v FROM Vendedores v WHERE v.nombres = :nombres"),
    @NamedQuery(name = "Vendedores.findByApellidos", query = "SELECT v FROM Vendedores v WHERE v.apellidos = :apellidos")})
public class Vendedores implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "IDENTIFICACION")
    private String identificacion;
    @Size(max = 70)
    @Column(name = "NOMBRES")
    private String nombres;
    @Size(max = 70)
    @Column(name = "APELLIDOS")
    private String apellidos;

    public Vendedores() {
    }

    public Vendedores(String identificacion) {
        this.identificacion = identificacion;
    }

    public String getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (identificacion != null ? identificacion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Vendedores)) {
            return false;
        }
        Vendedores other = (Vendedores) object;
        if ((this.identificacion == null && other.identificacion != null) || (this.identificacion != null && !this.identificacion.equals(other.identificacion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.losalpes.entities.Vendedores[ identificacion=" + identificacion + " ]";
    }
    
}
