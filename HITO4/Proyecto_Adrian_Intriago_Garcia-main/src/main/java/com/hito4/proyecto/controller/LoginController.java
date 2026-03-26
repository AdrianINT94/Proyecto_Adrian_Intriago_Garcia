package com.hito4.proyecto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;

import com.hito4.proyecto.config.StageManager;
import com.hito4.proyecto.view.FxmlView;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

@Controller
public class LoginController {

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Label errorLabel;

    @Autowired
    private StageManager stageManager;

    @Value("${user.admin}")
    private String adminUser;

    @Value("${user.admin.pass}")
    private String adminPass;

    @Value("${user.prof}")
    private String profUser;

    @Value("${user.prof.pass}")
    private String profPass;

    
    @FXML
    public void login() {
        String username = usernameField.getText();
        String password = passwordField.getText();

        System.out.println("Usuario introducido: " + username);
        System.out.println("Password introducida: " + password);
        System.out.println("AdminUser (properties): " + adminUser);
        System.out.println("AdminPass (properties): " + adminPass);

        if (username.equals(adminUser) && password.equals(adminPass)) {
            System.out.println("✅ LOGIN ADMIN OK");
            stageManager.switchScene(FxmlView.ADMIN);
            return;
        }

        if (username.equals(profUser) && password.equals(profPass)) {
            System.out.println("✅ LOGIN PROF OK");
            stageManager.switchScene(FxmlView.ADMIN);
            return;
        }

        errorLabel.setText("Usuario o contraseña incorrectos");
    }
}