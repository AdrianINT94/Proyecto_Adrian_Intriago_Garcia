package com.hito5.proyecto.controller;

import java.util.List;
import org.springframework.stereotype.Controller;

import com.hito5.proyecto.config.StageManager;
import com.hito5.proyecto.model.Alumno;
import com.hito5.proyecto.service.AlumnoService;
import com.hito5.proyecto.view.FxmlView;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

@Controller
public class AdminController {

    
    @FXML private TableView<Alumno> tablaAlumnos;
    @FXML private TableColumn<Alumno, String> colNombre;
    @FXML private TableColumn<Alumno, String> colEmail;
    @FXML private TableColumn<Alumno, String> colCiclo;
    @FXML private TableColumn<Alumno, Integer> colCurso;

    private final StageManager stageManager;
    private final AlumnoService alumnoService; 

   
    public AdminController(StageManager stageManager, AlumnoService alumnoService) {
        this.stageManager = stageManager;
        this.alumnoService = alumnoService;
    }

    
    @FXML
    public void initialize() {
        if (tablaAlumnos != null) {
            colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
            colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
            colCiclo.setCellValueFactory(new PropertyValueFactory<>("ciclo"));
            colCurso.setCellValueFactory(new PropertyValueFactory<>("curso"));

            cargarAlumnos();
        }
    }

    private void cargarAlumnos() {
        List<Alumno> alumnos = alumnoService.findAll();
        tablaAlumnos.setItems(FXCollections.observableArrayList(alumnos));
    }

    

    @FXML
    private void irAlumnos() {
        stageManager.switchScene(FxmlView.ALUMNOS);
    }

    @FXML
    private void nuevoAlumno() {
        AlumnoFormController.setAlumnoActual(null); 
        stageManager.switchScene(FxmlView.ALUMNO_FORM);
    }

    @FXML
    private void editarAlumno() {
    	Alumno seleccionado = tablaAlumnos.getSelectionModel().getSelectedItem();
        
        if (seleccionado != null) {
            
            AlumnoFormController.setAlumnoActual(seleccionado);
            
            stageManager.switchScene(FxmlView.ALUMNO_FORM);
        } else {
        	mostrarAlerta("Atención", "Por favor, selecciona un alumno de la tabla para editar.");
    }
    }
    
    @FXML
    private void eliminarAlumno() {
        Alumno seleccionado = tablaAlumnos.getSelectionModel().getSelectedItem();
        if (seleccionado != null) {
            try {
                alumnoService.delete(seleccionado.getId()); 
                cargarAlumnos(); 
            } catch (Exception e) {
                mostrarAlerta("Error", "No se pudo eliminar al alumno.");
            }
        } else {
            mostrarAlerta("Atención", "Selecciona un alumno para eliminar.");
        }
    }
    
    
    @FXML 
    private void exportarCSV() {
        System.out.println("Click en exportar CSV");
    }
    
    @FXML
    private void volver() {
        stageManager.switchScene(FxmlView.LOGIN);
    }

    @FXML
    private void irEmpresas(ActionEvent event) {
        stageManager.switchScene(FxmlView.EMPRESA_VIEW);
    }
    
    @FXML
    private void salir(javafx.event.ActionEvent event) {
        javafx.application.Platform.exit();
    }
    private void mostrarAlerta(String titulo, String contenido) {
        javafx.scene.control.Alert alert = new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.WARNING);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(contenido);
        alert.showAndWait();
}
    }
    
