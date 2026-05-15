package com.hito5.proyecto.model;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "alumnos")
public class Alumno extends Usuario {

    private String ciclo;
    private int curso;
    private String responsable;
    private String  tutorDocente;
    
    @ManyToOne
    @JoinColumn(name = "empresa_id")
    private Empresa empresa;

    public Alumno() {}

    
    public Alumno(String ciclo, int curso, String responsable, String tutorDocente, Empresa empresa) {
		super();
		this.ciclo = ciclo;
		this.curso = curso;
		this.responsable = responsable;
		this.tutorDocente = tutorDocente;
		this.empresa = empresa;
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
    

    public String getResponsable() {
		return responsable;
	}

	public void setResponsable(String responsable) {
		this.responsable = responsable;
	}

	public String getTutorDocente() {
		return tutorDocente;
	}

	public void setTutorDocente(String tutorDocente) {
		this.tutorDocente = tutorDocente;
	}

	@Override
    public String toString() {
        return "Alumno: " + nombre + " (" + ciclo + " - Curso " + curso + ")";
    }
}