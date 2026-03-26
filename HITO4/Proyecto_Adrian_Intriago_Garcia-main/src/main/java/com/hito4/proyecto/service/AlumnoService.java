package com.hito4.proyecto.service;

import java.io.FileWriter;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hito4.proyecto.model.Alumno;
import com.hito4.proyecto.repository.AlumnoRepository;

@Service
@Transactional
public class AlumnoService {

    private final AlumnoRepository alumnoRepository;

    public AlumnoService(AlumnoRepository alumnoRepository) {
        this.alumnoRepository = alumnoRepository;
    }

    public List<Alumno> findAll() {
        return alumnoRepository.findAll();
    }

    public Alumno findById(Integer id) {
        return alumnoRepository.findById(id).orElse(null);
    }

    public Alumno save(Alumno alumno) {
        return alumnoRepository.save(alumno);
    }

    public void delete(Integer id) {
        alumnoRepository.deleteById(id);
    }

    public List<Alumno> findByEmpresaId(Integer empresaId) {
        return alumnoRepository.findByEmpresaId(empresaId);
    }
    
    public void exportarAlumnosCSV(String ruta) {

        List<Alumno> alumnos = alumnoRepository.findAll();

        try (FileWriter writer = new FileWriter(ruta, java.nio.charset.StandardCharsets.UTF_8)) {

            
            writer.write("ID,Nombre,Email,Ciclo,Curso\n");

            for (Alumno a : alumnos) {
                writer.write(
                    a.getId() + "," +
                    a.getNombre() + "," +
                    a.getEmail() + "," +
                    a.getCiclo() + "," +
                    a.getCurso() + "\n"
                );
            }

        } catch (Exception e) {
            System.out.println("Error al exportar CSV");
        }
    }
}