package com.hito4.proyecto.service;

import java.util.List;

import com.hito4.proyecto.model.FCT;
import com.hito4.proyecto.repository.FctRepository;

public class FctService {
	
	private final FctRepository repo;
	
	public FctService (FctRepository repo) {
		this.repo =repo;
	}
	
	public List<FCT> findAll(){
		return repo.findAll();
	}
	
	public FCT save (FCT fct) {
		return repo.save(fct);
	}
	
	public void delete (Integer id) {
		repo.deleteById(id);
		
		
	}
	public FCT findById(Integer id) {
		return repo.findById(id).orElse(null);
	}
}
