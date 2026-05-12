package com.hito5.proyecto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;

import com.hito5.proyecto.config.StageManager;
import com.hito5.proyecto.model.Empresa;
import com.hito5.proyecto.service.EmpresaService;
import com.hito5.proyecto.view.FxmlView;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;


@Controller
public class EmpresaFormController {

		@FXML private TextField txtNombre;
		@FXML private TextField txtDireccion;
		@FXML private TextField txtTelefono;
		@FXML private TextField txtResponsable;
		
		
		@Autowired
		private EmpresaService empresaService;
		@Lazy
		@Autowired
		private StageManager stageManager;
		
		private static Empresa empresaActual;
		public static void setEmpresaActual(Empresa empresa) {
			empresaActual = empresa;
		}
		
		@FXML 
		public void initialize() {
			if(empresaActual != null) {
				txtNombre.setText(empresaActual.getNombre());
				txtDireccion.setText(empresaActual.getDireccion());
				txtTelefono.setText(empresaActual.getTelefono());
				txtResponsable.setText(empresaActual.getResponsable());
			}
		}
		@FXML
		private void guardarEmpresa() {
			if(validarCampos()) {
				if(empresaActual == null)empresaActual = new Empresa();
				
				empresaActual.setNombre(txtNombre.getText());
				empresaActual.setDireccion(txtDireccion.getText());
				empresaActual.setTelefono(txtTelefono.getText());
				empresaActual.setResponsable(txtResponsable.getText());
				
				empresaService.save(empresaActual);
				cancelar();
			}
		}
		
		private boolean validarCampos() {
			if(txtNombre.getText().isEmpty() || txtDireccion.getText().isEmpty()) {
				mostrarAlerta("Error de validacion" , "El nombre y la direccion son obligatorios");
				return false;
			}
			return true;
		}
		
		@FXML 
		private void cancelar() {
			empresaActual = null;
			stageManager.switchScene(FxmlView.EMPRESA_VIEW);
		}
		
		private void mostrarAlerta(String titulo, String contenido) {
			Alert alert = new Alert(Alert.AlertType.WARNING);
			alert.setTitle(titulo);
			alert.setHeaderText(null);
			alert.setContentText(contenido);
			alert.showAndWait();
		}
}
