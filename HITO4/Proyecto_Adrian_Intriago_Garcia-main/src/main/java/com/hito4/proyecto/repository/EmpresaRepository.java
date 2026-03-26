package com.hito4.proyecto.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hito4.proyecto.model.Empresa;


public interface EmpresaRepository extends JpaRepository<Empresa, Long> {

}
