package com.hito4.proyecto.controller;

import org.springframework.stereotype.Controller;

import com.hito4.proyecto.config.StageManager;
import com.hito4.proyecto.view.FxmlView;

import javafx.fxml.FXML;

@Controller
public class AdminController {

    private final StageManager stageManager;

    public AdminController(StageManager stageManager) {
        this.stageManager = stageManager;
    }

    @FXML
    private void irAlumnos() {
        stageManager.switchScene(FxmlView.ALUMNOS);
    }
    
    @FXML
    private void salir(javafx.event.ActionEvent event) {
        javafx.application.Platform.exit();
      
    }
}
   
    
