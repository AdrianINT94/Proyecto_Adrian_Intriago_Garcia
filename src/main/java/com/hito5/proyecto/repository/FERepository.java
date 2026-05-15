package com.hito5.proyecto.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hito5.proyecto.model.Alumno;
import com.hito5.proyecto.model.Empresa;
import com.hito5.proyecto.model.FE;

public interface FERepository extends JpaRepository<FE, Integer>  {

		FE findByAlumno(Alumno alumno);
		
			List<FE> findByEmpresa(Empresa empresa);
		}

