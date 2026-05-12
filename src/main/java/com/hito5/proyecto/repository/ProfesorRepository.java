package com.hito5.proyecto.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hito5.proyecto.model.Profesor;

public interface ProfesorRepository extends JpaRepository<Profesor, Integer> {
}

