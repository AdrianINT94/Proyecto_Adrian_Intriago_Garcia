package com.hito5.proyecto.controller;

import java.util.List;
import org.springframework.stereotype.Controller;
import javafx.beans.property.SimpleStringProperty;
import com.hito5.proyecto.config.StageManager;
import com.hito5.proyecto.model.Alumno;
import com.hito5.proyecto.model.FE;
import com.hito5.proyecto.service.AlumnoService;
import com.hito5.proyecto.service.FEService;
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
    @FXML private TableColumn<Alumno,String> colEmpresa;
    @FXML private TableColumn<Alumno,String> colTutor;
    @FXML private TableColumn<Alumno,String> colProfesor;
    
    
    private final FEService feService;
    private final StageManager stageManager;
    private final AlumnoService alumnoService; 

   
    public AdminController(StageManager stageManager, AlumnoService alumnoService, FEService feService) {
        this.stageManager = stageManager;
        this.alumnoService = alumnoService;
        this.feService = feService;
    }

    
    @FXML
    public void initialize() {
        if (tablaAlumnos != null) {
            colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
            colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
            colCiclo.setCellValueFactory(new PropertyValueFactory<>("ciclo"));
            colCurso.setCellValueFactory(new PropertyValueFactory<>("curso"));
            
            
            colEmpresa.setCellValueFactory(cellData -> {
                Alumno alumno = cellData.getValue();
                String nombre;

                if (alumno.getEmpresa() != null) {
                    nombre = alumno.getEmpresa().getNombre();
                } else {
                    nombre = ("Sin Asignar");
                }

                return new SimpleStringProperty(nombre);
            });
            
            
            colTutor.setCellValueFactory(cellData -> {
               Alumno alumno = cellData.getValue();
                FE fe = feService.findbyAlumno(alumno);
                if (fe != null && fe.getTutorEmpresa() != null) {
                    return new SimpleStringProperty(fe.getTutorEmpresa().getNombre());
                } else {
                    return new SimpleStringProperty("No asignado");
                }
            });
            
            
            colProfesor.setCellValueFactory(cellData -> {
            		Alumno alumno = cellData.getValue();
                FE fe = feService.findbyAlumno(alumno);
                if (fe != null && fe.getProfesor() != null) {
                    return new SimpleStringProperty(fe.getProfesor().getNombre());
                } else {
                    return new SimpleStringProperty("Sin profesor");
                }
       });

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
    	try ( java.io.PrintWriter csv = new java.io.PrintWriter("alumnos.csv")){
    		
    		csv.println("Nombre;Email;Ciclo;Curso;Empresa;Tutor;Profesor");
    		
    		for(Alumno alumno:tablaAlumnos.getItems()) {
    			FE fe = feService.findbyAlumno(alumno);
    			
    			csv.print(alumno.getNombre()+ ";");
    			csv.print(alumno.getEmail()+ ";");
    			csv.print(alumno.getCiclo()+ ";");
    			csv.print(alumno.getCurso()+ ";");
    			
    			
    			if (alumno.getEmpresa() != null) {
                    csv.print(alumno.getEmpresa().getNombre() + ";");
                } else {
                    csv.print("Sin Empresa;");
                }

                
                if (fe != null && fe.getTutorEmpresa() != null) {
                    csv.print(fe.getTutorEmpresa().getNombre() + ";");
                } else {
                    csv.print("Sin Tutor;");
                }

                if (fe != null && fe.getProfesor() != null) {
                    csv.print(fe.getProfesor().getNombre());
                } else {
                    csv.print("Sin Profesor");
                }

                csv.println();
    		}
    	} catch (Exception e) {
            System.out.println("Error al guardar el CSV");
    	}
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
    
