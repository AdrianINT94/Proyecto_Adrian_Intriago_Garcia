package com.hito5.proyecto.controller;

import org.springframework.stereotype.Controller;

import com.hito5.proyecto.config.StageManager;
import com.hito5.proyecto.model.Alumno;
import com.hito5.proyecto.service.AlumnoService;
import com.hito5.proyecto.view.FxmlView;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

@Controller
public class AlumnoFormController {

    @FXML private TextField txtNombre;
    @FXML private TextField txtEmail;
    @FXML private TextField txtCiclo;
    @FXML private TextField txtCurso;

    private static Alumno alumnoActual;

    private final AlumnoService alumnoService;
    private final StageManager stageManager;

    public AlumnoFormController(AlumnoService alumnoService ,StageManager stageManager) {
        this.alumnoService = alumnoService;
        this.stageManager = stageManager;
    }

    
    public static void setAlumnoActual(Alumno alumno) {
        alumnoActual = alumno;
    }

    @FXML
    public void initialize() {
        if (alumnoActual != null) {
            txtNombre.setText(alumnoActual.getNombre());
            txtEmail.setText(alumnoActual.getEmail());
            txtCiclo.setText(alumnoActual.getCiclo());
            txtCurso.setText(String.valueOf(alumnoActual.getCurso()));
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
        
        
        try {
        	alumnoActual.setCurso(Integer.parseInt(txtCurso.getText()));
        }catch(NumberFormatException e){
        	mostrarAlerta("Error de formato","El curso debe ser un numero entero");
        	return;
        }
        

        alumnoService.save(alumnoActual);
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

