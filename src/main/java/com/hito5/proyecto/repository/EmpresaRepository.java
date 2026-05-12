package com.hito5.proyecto.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hito5.proyecto.model.Empresa;


public interface EmpresaRepository extends JpaRepository<Empresa, Long> {

}
