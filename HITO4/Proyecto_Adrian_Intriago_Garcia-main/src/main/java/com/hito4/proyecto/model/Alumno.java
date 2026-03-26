package com.hito4.proyecto.model;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "alumnos")
public class Alumno extends Usuario {

    private String ciclo;
    private int curso;

    @ManyToOne
    @JoinColumn(name = "empresa_id")
    private Empresa empresa;

    public Alumno() {}

    public Alumno(Integer id, String nombre, String email, String contrasena, String ciclo, int curso) {
        super(id, nombre, email, contrasena);
        this.ciclo = ciclo;
        this.curso = curso;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public String getCiclo() {
        return ciclo;
    }

    public void setCiclo(String ciclo) {
        this.ciclo = ciclo;
    }

    public int getCurso() {
        return curso;
    }

    public void setCurso(int curso) {
        this.curso = curso;
    }

    @Override
    public String toString() {
        return "Alumno: " + nombre + " (" + ciclo + " - Curso " + curso + ")";
    }
}