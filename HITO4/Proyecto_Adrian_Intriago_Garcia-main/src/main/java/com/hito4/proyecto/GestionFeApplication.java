package com.hito4.proyecto;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.hito4.proyecto.config.StageManager;
import com.hito4.proyecto.view.FxmlView;

import javafx.application.Application;
import javafx.stage.Stage;

@SpringBootApplication
@EntityScan(basePackages = "com.hito4.proyecto.model")
@EnableJpaRepositories(basePackages ="com.hito4.proyecto.repository")
@ComponentScan("com.hito3.proyecto")
public class GestionFeApplication extends Application {

    private ConfigurableApplicationContext context;

    @Override
    public void init() {
        context = new SpringApplicationBuilder(GestionFeApplication.class)
                .web(WebApplicationType.NONE) 
                .run();
    }

    @Override
    public void start(Stage primaryStage) {

        
        StageManager stageManager = context.getBean(StageManager.class);
        
        
        stageManager.setPrimaryStage(primaryStage);

        
        stageManager.switchScene(FxmlView.LOGIN);
    }

    @Override
    public void stop() {
        context.close();
    }

    public static void main(String[] args) {
        launch(args);
    }
}