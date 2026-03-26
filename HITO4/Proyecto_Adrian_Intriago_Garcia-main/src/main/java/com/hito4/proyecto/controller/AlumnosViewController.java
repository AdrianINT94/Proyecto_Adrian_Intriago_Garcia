package com.hito4.proyecto.controller;

import java.io.File;
import java.util.List;

import org.springframework.stereotype.Controller;

import com.hito4.proyecto.config.StageManager;
import com.hito4.proyecto.model.Alumno;
import com.hito4.proyecto.service.AlumnoService;
import com.hito4.proyecto.view.FxmlView;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;

@Controller
public class AlumnosViewController {

    @FXML
    private TableView<Alumno> tablaAlumnos;

    @FXML
    private TableColumn<Alumno, String> colNombre;

    @FXML
    private TableColumn<Alumno, String> colEmail;

    @FXML
    private TableColumn<Alumno, String> colCiclo;

    @FXML
    private TableColumn<Alumno, Integer> colCurso;

    private final AlumnoService alumnoService;
    private final StageManager stageManager;

    public AlumnosViewController(AlumnoService alumnoService, StageManager stageManager) {
        this.alumnoService = alumnoService;
        this.stageManager = stageManager;
    }

    @FXML
    public void initialize() {
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colCiclo.setCellValueFactory(new PropertyValueFactory<>("ciclo"));
        colCurso.setCellValueFactory(new PropertyValueFactory<>("curso"));

        cargarAlumnos();
    }

    private void cargarAlumnos() {
        List<Alumno> alumnos = alumnoService.findAll();
        tablaAlumnos.setItems(FXCollections.observableArrayList(alumnos));
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
        }else {
        	
        	mostrarAlerta("Atencion","Porfavor selecciona un alumno de la tabla para editar");
        }
    }

    @FXML
    private void eliminarAlumno() {
        Alumno seleccionado = tablaAlumnos.getSelectionModel().getSelectedItem();
        
        if (seleccionado == null) {
           mostrarAlerta("Atencion","Debe seleccionar un alumno para eliminarlo");
           return;
        }
      
        javafx.scene.control.Alert confirmacion = new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.CONFIRMATION);
        confirmacion.setTitle("Confirmar eliminacion");
        confirmacion.setHeaderText(null);
        confirmacion.setContentText("¿Estas seguro de que quiere eliminar a " + seleccionado.getNombre() + "?");
        
        if(confirmacion.showAndWait().get() == javafx.scene.control.ButtonType.OK) {
        	try {
        		alumnoService.delete(seleccionado.getId());
        		cargarAlumnos();
        	}catch(Exception e) {
        		mostrarAlerta("Error","No se pudo eliminar al alumno");
        	}
        }
        
    }

   
    
    @FXML
    private void exportarCSV() {
    	try {
    
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Guardar archivo CSV");
        fileChooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("Archivo CSV", "*.csv")
        );

        File file = fileChooser.showSaveDialog(null);
        

        if (file != null) {
            alumnoService.exportarAlumnosCSV(file.getAbsolutePath());
            javafx.scene.control.Alert exito = new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.INFORMATION);
            exito.setContentText("Archivo exportado correctamente");
            exito.show();
        }
    }catch(Exception e) {
    	mostrarAlerta("Hubo un error al exportar el CSV","Error");
    	}
    }
    
    @FXML
    private void volver() {
        stageManager.switchScene(FxmlView.ADMIN);
    }

    
    private void mostrarAlerta(String titulo, String contenido) {
        javafx.scene.control.Alert alert = new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.WARNING);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(contenido);
        alert.showAndWait();
    }
}