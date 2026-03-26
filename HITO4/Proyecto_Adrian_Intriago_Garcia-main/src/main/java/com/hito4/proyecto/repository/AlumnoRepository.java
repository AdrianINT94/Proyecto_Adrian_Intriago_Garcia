package com.hito4.proyecto.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hito4.proyecto.model.Alumno;

public interface AlumnoRepository extends JpaRepository<Alumno,Integer> {
	 
	    List<Alumno> findByEmpresaId(Integer empresaId);
}
