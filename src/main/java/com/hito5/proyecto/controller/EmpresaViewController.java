package com.hito5.proyecto.controller;

import java.io.Serializable;
import java.net.URL;
import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;

import com.hito5.proyecto.config.StageManager;
import com.hito5.proyecto.model.Empresa;
import com.hito5.proyecto.service.EmpresaService;
import com.hito5.proyecto.view.FxmlView;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;


@Controller
public class EmpresaViewController  implements Initializable{
	
		
		@FXML private TableView<Empresa> tablaEmpresas;
		@FXML private TableColumn<Empresa, Long> colId;
		@FXML private TableColumn<Empresa,String> colNombre;
		@FXML private TableColumn<Empresa,String> colDireccion;
		@FXML private TableColumn<Empresa,String> colTelefono;
		@FXML private TableColumn<Empresa, String> colResponsable;
		
		@Autowired
		private EmpresaService empresaService;
		
		@Lazy
		@Autowired
		private StageManager stageManager;
		private ObservableList<Empresa> listaEmpresas = FXCollections.observableArrayList();
		
		
		@Override
		public void initialize(URL location,ResourceBundle resources) {
			tablaEmpresas.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
			setColumnProperties();
			cargarEmpresas();
			
		}
		
		private void setColumnProperties() {
			colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
			colDireccion.setCellValueFactory(new PropertyValueFactory<>("direccion"));
			colTelefono.setCellValueFactory(new PropertyValueFactory<>("telefono"));
			colResponsable.setCellValueFactory(new PropertyValueFactory<>("responsable"));
		}
		private void cargarEmpresas() {
			listaEmpresas.clear();
			listaEmpresas.addAll(empresaService.findAll());
			tablaEmpresas.setItems(listaEmpresas);
			
		}
		
		@FXML 
		private void nuevaEmpresa() {
			EmpresaFormController.setEmpresaActual(null);
			stageManager.switchScene(FxmlView.EMPRESA_FORM);
			
		}
		@FXML
		private void editarEmpresa() {
			Empresa seleccionada = tablaEmpresas.getSelectionModel().getSelectedItem();
			if(seleccionada != null) {
				EmpresaFormController.setEmpresaActual(seleccionada);
				stageManager.switchScene(FxmlView.EMPRESA_FORM);
			}
		}
		
		@FXML
		private void eliminarEmpresa() {
			Empresa seleccionada = tablaEmpresas.getSelectionModel().getSelectedItem();
			if ( seleccionada != null) {
				empresaService.delete(seleccionada.getId());
				cargarEmpresas();
				
				System.out.println("Empresa eliminada correctamente"+seleccionada.getNombre());
			}else {
				System.out.println("Selecione una empresa primero ");
			}
		}
		@FXML
		private void volver() {
			stageManager.switchScene(FxmlView.ADMIN);
		}
}
