package com.hito5.proyecto.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Empresa {
	
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nombre;
	private String direccion;
	private String telefono;
	private String responsable;
	
	 public Empresa() {
	    }
	
		public Long getId() {
		return id;
	}
	 public void setId(Long id) {
		 this.id = id;
	 }
	 public String getNombre() {
		 return nombre;
	 }
	 public void setNombre(String nombre) {
		 this.nombre = nombre;
	 }
	 public String getDireccion() {
		 return direccion;
	 }
	 public void setDireccion(String direccion) {
		 this.direccion = direccion;
	 }
	 public String getTelefono() {
		 return telefono;
	 }
	 public void setTelefono(String telefono) {
		 this.telefono = telefono;
	 }
	 public String getResponsable() {
		 return responsable;
	 }
	 public void setResponsable(String responsable) {
		 this.responsable = responsable;
	 }
		@Override
		public String toString() {
			return nombre+"("+telefono+")";
		}
	}
	
	
	
	

