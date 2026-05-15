package com.hito5.proyecto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.hito5.proyecto.config.StageManager;
import com.hito5.proyecto.model.Alumno;
import com.hito5.proyecto.model.Empresa;
import com.hito5.proyecto.model.FE;
import com.hito5.proyecto.model.Profesor;
import com.hito5.proyecto.model.TutorEmpresa;
import com.hito5.proyecto.service.AlumnoService;
import com.hito5.proyecto.service.EmpresaService;
import com.hito5.proyecto.service.FEService;
import com.hito5.proyecto.service.ProfesorService;
import com.hito5.proyecto.service.TutorEmpresaService;
import com.hito5.proyecto.view.FxmlView;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

@Controller
public class AlumnoFormController {

    @FXML private TextField txtNombre;
    @FXML private TextField txtEmail;
    @FXML private TextField txtCiclo;
    @FXML private TextField txtCurso;
    @FXML private ComboBox<Empresa> comboEmpresa;
    @FXML private ComboBox<TutorEmpresa> comboTutor;
    @FXML private ComboBox<Profesor> comboProfesor;
    @FXML private DatePicker dpInicio;
    @FXML private DatePicker dpFin;
    
    private final TutorEmpresaService tutorService;
    private final ProfesorService profresorService;
    private final FEService feService;

    private static Alumno alumnoActual;
    private final EmpresaService empresaService;
    private final AlumnoService alumnoService;
    private final StageManager stageManager;

    
    
    public AlumnoFormController(TutorEmpresaService tutorService, ProfesorService profesorService, FEService feService,
			EmpresaService empresaService, AlumnoService alumnoService, StageManager stageManager) {
		super();
		this.tutorService = tutorService;
		this.profresorService = profesorService;
		this.feService = feService;
		this.empresaService = empresaService;
		this.alumnoService = alumnoService;
		this.stageManager = stageManager;
	}
	

    
    public static void setAlumnoActual(Alumno alumno) {
        alumnoActual = alumno;
    }

    @FXML
    public void initialize() {
    	if (tutorService != null) comboTutor.getItems().setAll(tutorService.findAll());
    	if (profresorService != null) comboProfesor.getItems().setAll(profresorService.findAll());
    	if (empresaService != null && comboEmpresa != null) {
    	comboEmpresa.getItems().setAll(empresaService.findAll());
    	}

    	if (alumnoActual != null) {
    	txtNombre.setText(alumnoActual.getNombre());
    	txtEmail.setText(alumnoActual.getEmail());
    	txtCiclo.setText(alumnoActual.getCiclo());
    	txtCurso.setText(String.valueOf(alumnoActual.getCurso()));

    	if (alumnoActual.getEmpresa() != null) {
    	comboEmpresa.setValue(alumnoActual.getEmpresa());

    	}
    }
    }
    
    @FXML
    private void guardar() {
    	try {
    		if(txtNombre.getText().isEmpty() || txtEmail.getText().isEmpty()) {
    			mostrarAlerta("Error de validacion","El nombre y el email son obligatorios");
    			return;
    		}
    	
        if (alumnoActual == null) {
            alumnoActual = new Alumno();
        }

        alumnoActual.setNombre(txtNombre.getText());
        alumnoActual.setEmail(txtEmail.getText());
        alumnoActual.setCiclo(txtCiclo.getText());
        alumnoActual.setEmpresa(comboEmpresa.getValue());
        
        alumnoActual.setEmpresa(comboEmpresa.getSelectionModel().getSelectedItem());
        
        try {
        	alumnoActual.setCurso(Integer.parseInt(txtCurso.getText()));
        }catch(NumberFormatException e){
        	mostrarAlerta("Error de formato","El curso debe ser un numero entero");
        	return;
        }
        

        alumnoService.save(alumnoActual);
        
        FE fe = feService.findbyAlumno(alumnoActual);
        if (fe == null) {
            fe = new FE();
        }
        
        fe.setAlumno(alumnoActual);
        fe.setEmpresa(comboEmpresa.getValue());
        fe.setTutorEmpresa(comboTutor.getValue());
        fe.setProfesor(comboProfesor.getValue());
        fe.setFechaInicio(dpInicio.getValue());
        fe.setFechaFin(dpFin.getValue());

        
        feService.save(fe);
        
        cerrarVentana();
    }catch (Exception e) {
    	mostrarAlerta("Error critico","No se pudo guardar el alumno " +  e.getMessage());
    }
    }

    @FXML
    private void cancelar() {
        cerrarVentana();
    }

    private void cerrarVentana() {
    	alumnoActual = null; 
        stageManager.switchScene(FxmlView.ADMIN);    
    }
private void mostrarAlerta(String titulo,String contenido) {
	javafx.scene.control.Alert alert= new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.ERROR);
	alert.setTitle(titulo);
	alert.setHeaderText(null);
	alert.setContentText(contenido);
	alert.showAndWait();
}
}

