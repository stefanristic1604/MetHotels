package com.rile.methotels.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.apache.tapestry5.beaneditor.Validate;
import org.apache.tapestry5.ioc.annotations.Inject;

/**
 *
 * @author Stefan
 */
@Entity
@Table(name = "rezervacija")
@NamedQueries({
    @NamedQuery(name = "Rezervacija.findAll", query = "SELECT r FROM Rezervacija r")})
public class Rezervacija implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    
    @Validate("required")
    @Column(name = "soba_id")
    private Integer sobaId;
    @Validate("required")
    @Column(name = "ime")
    
    private String ime;
    @Validate("required")
    @Column(name = "prezime")
    private String prezime;
    
    @Validate("required, email")
    @Column(name = "email")
    private String email;
    
    @Validate("required")
    @Column(name = "broj_soba")
    private Integer brojSoba;
    
    @Validate("required")
    @Column(name = "dan_prijave")
    @Temporal(TemporalType.DATE)
    private Date danPrijave;
    
    @Validate("required")
    @Column(name = "dan_odjave")
    @Temporal(TemporalType.DATE)
    private Date danOdjave;

    @Inject
    public Rezervacija() {
    }

    public Rezervacija(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSobaId() {
        return sobaId;
    }

    public void setSobaId(Integer sobaId) {
        this.sobaId = sobaId;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getBrojSoba() {
        return brojSoba;
    }

    public void setBrojSoba(Integer brojSoba) {
        this.brojSoba = brojSoba;
    }

    public Date getDanPrijave() {
        return danPrijave;
    }

    public void setDanPrijave(Date danPrijave) {
        this.danPrijave = danPrijave;
    }

    public Date getDanOdjave() {
        return danOdjave;
    }

    public void setDanOdjave(Date danOdjave) {
        this.danOdjave = danOdjave;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Rezervacija)) {
            return false;
        }
        Rezervacija other = (Rezervacija) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.rile.methotels.entities.Rezervacija[ id=" + id + " ]";
    }

}