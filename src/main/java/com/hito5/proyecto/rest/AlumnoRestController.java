package com.hito5.proyecto.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

import com.hito5.proyecto.model.Alumno; 
import com.hito5.proyecto.service.AlumnoService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class AlumnoRestController {
 
 @Autowired
 private AlumnoService alumnoService;

 @GetMapping("/alumnos")
 public List<Alumno> getAlumnoParaMovil(){
     return alumnoService.findAll();
 }
}
