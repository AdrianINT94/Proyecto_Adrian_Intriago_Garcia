package com.hito5.proyecto.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hito5.proyecto.model.Alumno;
import com.hito5.proyecto.model.Empresa;
import com.hito5.proyecto.model.FE;
import com.hito5.proyecto.repository.FERepository;

@Service
public class FEService {
	
	private final FERepository repo;
	
	@Autowired
	public FEService (FERepository repo) {
		this.repo =repo;
	}
	
	public List<FE> findAll(){
		return repo.findAll();
	}
	
	public FE save (FE fe) {
		return repo.save(fe);
	}
	
	public void delete (Integer id) {
		repo.deleteById(id);
		
		
	}
	public FE findById(Integer id) {
		return repo.findById(id).orElse(null);
	}
	
	public FE findbyAlumno(Alumno alumno) {
		return repo.findByAlumno(alumno);
	}
	
	public List<FE> findByEmpresa(Empresa empresa) {
	    return repo.findByEmpresa(empresa);
	
	    
	
	}
	
}

