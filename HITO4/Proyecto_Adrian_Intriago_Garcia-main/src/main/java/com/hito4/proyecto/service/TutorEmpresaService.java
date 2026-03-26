package com.hito4.proyecto.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.hito4.proyecto.model.TutorEmpresa;
import com.hito4.proyecto.repository.TutorEmpresaRepository;

@Service
public class TutorEmpresaService {
	
	private final TutorEmpresaRepository repo;

    public TutorEmpresaService(TutorEmpresaRepository repo) {
        this.repo = repo;
    }

    public List<TutorEmpresa> findAll() {
        return repo.findAll();
    }

    public TutorEmpresa save(TutorEmpresa tutor) {
        return repo.save(tutor);
    }

    public void delete(Integer id) {
        repo.deleteById(id);
    }

    public TutorEmpresa findById(Integer id) {
        return repo.findById(id).orElse(null);
    }
}

