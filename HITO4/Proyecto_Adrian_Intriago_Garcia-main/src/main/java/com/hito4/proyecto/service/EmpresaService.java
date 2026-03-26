package com.hito4.proyecto.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.hito4.proyecto.model.Empresa;
import com.hito4.proyecto.repository.EmpresaRepository;
@Service
public class EmpresaService {

    private final EmpresaRepository repo;

    public EmpresaService(EmpresaRepository repo) {
        this.repo = repo;
    }

    public List<Empresa> findAll() {
        return repo.findAll();
    }

    public Empresa save(Empresa empresa) {
        return repo.save(empresa);
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }

    public Empresa findById(Long id) {
        return repo.findById(id).orElse(null);
    }
}
