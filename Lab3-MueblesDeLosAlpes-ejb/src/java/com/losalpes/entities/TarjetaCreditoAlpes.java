/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.losalpes.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Dev
 */
@Entity
@Table(name = "TARJETA_CREDITO_ALPES", catalog = "", schema = "ADMINDERBY")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TarjetaCreditoAlpes.findAll", query = "SELECT t FROM TarjetaCreditoAlpes t"),
    @NamedQuery(name = "TarjetaCreditoAlpes.findByIdentificacion", query = "SELECT t FROM TarjetaCreditoAlpes t WHERE t.identificacion = :identificacion"),
    @NamedQuery(name = "TarjetaCreditoAlpes.findByNombreTitutlar", query = "SELECT t FROM TarjetaCreditoAlpes t WHERE t.nombreTitutlar = :nombreTitutlar"),
    @NamedQuery(name = "TarjetaCreditoAlpes.findByNombreBanco", query = "SELECT t FROM TarjetaCreditoAlpes t WHERE t.nombreBanco = :nombreBanco"),
    @NamedQuery(name = "TarjetaCreditoAlpes.findByCupo", query = "SELECT t FROM TarjetaCreditoAlpes t WHERE t.cupo = :cupo"),
    @NamedQuery(name = "TarjetaCreditoAlpes.findByFechaExpedicion", query = "SELECT t FROM TarjetaCreditoAlpes t WHERE t.fechaExpedicion = :fechaExpedicion"),
    @NamedQuery(name = "TarjetaCreditoAlpes.findByFechaVencimiento", query = "SELECT t FROM TarjetaCreditoAlpes t WHERE t.fechaVencimiento = :fechaVencimiento")})
public class TarjetaCreditoAlpes implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "IDENTIFICACION")
    private String identificacion;
    @Size(max = 200)
    @Column(name = "NOMBRE_TITUTLAR")
    private String nombreTitutlar;
    @Size(max = 50)
    @Column(name = "NOMBRE_BANCO")
    private String nombreBanco;
    @Column(name = "CUPO")
    private Integer cupo;
    @Column(name = "FECHA_EXPEDICION")
    @Temporal(TemporalType.DATE)
    private Date fechaExpedicion;
    @Column(name = "FECHA_VENCIMIENTO")
    @Temporal(TemporalType.DATE)
    private Date fechaVencimiento;

    public TarjetaCreditoAlpes() {
    }

    public TarjetaCreditoAlpes(String identificacion) {
        this.identificacion = identificacion;
    }

    public String getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }

    public String getNombreTitutlar() {
        return nombreTitutlar;
    }

    public void setNombreTitutlar(String nombreTitutlar) {
        this.nombreTitutlar = nombreTitutlar;
    }

    public String getNombreBanco() {
        return nombreBanco;
    }

    public void setNombreBanco(String nombreBanco) {
        this.nombreBanco = nombreBanco;
    }

    public Integer getCupo() {
        return cupo;
    }

    public void setCupo(Integer cupo) {
        this.cupo = cupo;
    }

    public Date getFechaExpedicion() {
        return fechaExpedicion;
    }

    public void setFechaExpedicion(Date fechaExpedicion) {
        this.fechaExpedicion = fechaExpedicion;
    }

    public Date getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(Date fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
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
        if (!(object instanceof TarjetaCreditoAlpes)) {
            return false;
        }
        TarjetaCreditoAlpes other = (TarjetaCreditoAlpes) object;
        if ((this.identificacion == null && other.identificacion != null) || (this.identificacion != null && !this.identificacion.equals(other.identificacion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.losalpes.entities.TarjetaCreditoAlpes[ identificacion=" + identificacion + " ]";
    }
    
}
