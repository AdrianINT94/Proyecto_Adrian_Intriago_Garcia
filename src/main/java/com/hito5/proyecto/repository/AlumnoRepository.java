package com.hito5.proyecto.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hito5.proyecto.model.Alumno;

@Repository
public interface AlumnoRepository extends JpaRepository<Alumno,Integer> {
	 
	    List<Alumno> findByEmpresaId(Integer empresaId);
}
