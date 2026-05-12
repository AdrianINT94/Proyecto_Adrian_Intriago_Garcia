package com.hito5.proyecto.config;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import com.hito5.proyecto.view.FxmlView;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

@Lazy
@Component
public class StageManager {

    private Stage primaryStage;
    private final SpringFXMLLoader springFXMLLoader;

    public StageManager(SpringFXMLLoader springFXMLLoader) {
        this.springFXMLLoader = springFXMLLoader;
    }

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public void switchScene(FxmlView view) {
        Parent rootNode = springFXMLLoader.load(view.getFxmlFile());
        Scene scene = new Scene(rootNode);

        
        scene.getStylesheets().add(
                getClass().getResource("/styles/Styles.css").toExternalForm()
        );

        primaryStage.setScene(scene);
        primaryStage.setTitle(view.getTitle());

        
        switch (view) {
            case LOGIN:
                configurarVentana(400, 300);
                break;

            case ADMIN:
                configurarVentana(800, 500);
                break;

            case ALUMNOS:
                configurarVentana(900, 600);
                break;

            case ALUMNO_FORM:
                configurarVentana(450, 500);
                break;
        }

        primaryStage.show();
    }

   
    private void configurarVentana(double width, double height) {
        primaryStage.setWidth(width);
        primaryStage.setHeight(height);
        primaryStage.setResizable(false);
    }
}