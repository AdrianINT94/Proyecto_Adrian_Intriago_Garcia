package com.hito5.proyecto.model;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tutor_empresa")
public class TutorEmpresa extends Usuario {

    @ManyToOne
    @JoinColumn(name = "empresa_id")
    private Empresa empresa;

    protected TutorEmpresa() {
        
    }

    public TutorEmpresa(Integer id, String nombre, String email, String contrasena, Empresa empresa) {
        super(id, nombre, email, contrasena);
        this.empresa = empresa;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    @Override
    public String toString() {
        return "Tutor Empresa: " + nombre + " (" + empresa.getNombre() + ")";
    }
}
