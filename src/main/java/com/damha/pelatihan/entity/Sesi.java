/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.damha.pelatihan.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.GenericGenerator;

@Entity
public class Sesi implements Serializable {
     @Id @GeneratedValue(generator = "uuid")
    @GenericGenerator(name="uuid", strategy="uuid2")
    private String id;
    
    @Temporal(TemporalType.DATE)
    private Date mulai;
    
    @Temporal(TemporalType.DATE)
    private Date sampai;
    
    @ManyToOne
    @JoinColumn(name="id_materi", nullable = false)
    private Materi materi;
    
    @ManyToMany
    @JoinTable(name="peserta_pelatihan",
            joinColumns = @JoinColumn(name="id_sesi"),
            inverseJoinColumns = @JoinColumn(name ="id_peserta"))
    private List<Peserta> daftarpeserta = new ArrayList<>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getMulai() {
        return mulai;
    }

    public void setMulai(Date mulai) {
        this.mulai = mulai;
    }

    public Date getSampai() {
        return sampai;
    }

    public void setSampai(Date sampai) {
        this.sampai = sampai;
    }

    public Materi getMateri() {
        return materi;
    }

    public void setMateri(Materi materi) {
        this.materi = materi;
    }

    public List<Peserta> getDaftarpeserta() {
        return daftarpeserta;
    }

    public void setDaftarpeserta(List<Peserta> daftarpeserta) {
        this.daftarpeserta = daftarpeserta;
    }
    
    
    
}
