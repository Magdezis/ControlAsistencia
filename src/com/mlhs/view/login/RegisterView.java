/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mlhs.view.login;

import com.mlhs.controller.RegisterController;
import javafx.scene.Parent;
import javafx.scene.layout.GridPane;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import com.mlhs.controller.RegisterController;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

/**
 *
 * @author magde
 */
public class RegisterView {

    private final StackPane root = new StackPane();
    private final RegisterController registerController;
    private final GridPane gridMain;
    private final TextField usuario = new TextField("Tag de usuario");
    private final TextField password = new TextField("Crea una contraseña");
    private final Label user = new Label("Usuario");
    private final Label contrasen = new Label("Contrasena");
    private final Button registrar = new Button("Registrar");
    private final Button volver = new Button("Regresar");
    private final TextField lectorField = new TextField();
    private final TextField validarLector = new TextField(); 
    private final Label validarLabel = new Label("Por favor verifica tu codigo de tarjeta"); 
    private final Label tarjetaLabel = new Label("Acerca tu tarjeta al lector o escrible tu código manualmente");
    private final Label labelvalidarContrasena = new Label("Verifica que tu contraseña sea la misma: ");
    private final TextField fieldvalidarContrasena = new TextField(); 

    public RegisterView(RegisterController registerController) {
        this.gridMain = new GridPane();
        this.registerController = registerController;
        buildUi();
        setEventHandler();
    }

    public void buildUi() {
        gridMain.setPrefWidth(Double.MAX_VALUE);
        gridMain.setHgap(10);
        gridMain.setVgap(10);
        gridMain.setPadding(new Insets(25));

        HBox botones = new HBox(6);
        botones.getChildren().addAll(
                registrar,
                volver
        );
        VBox datosUsuario = new VBox(6);
        datosUsuario.getChildren().addAll(
                tarjetaLabel,
                validarLector,
                validarLabel,
                lectorField,
                user,
                usuario,
                contrasen,
                password,
                labelvalidarContrasena,
                fieldvalidarContrasena,
                botones
        );
        lectorField.setEditable(true);
        lectorField.setFocusTraversable(true);
        lectorField.setPrefWidth(250);
       
        
        gridMain.add(new VBox(), 2, 0);
        gridMain.add(datosUsuario, 1, 1);
        gridMain.add(new VBox(), 2, 2);
        System.out.println("");
        Platform.runLater(() -> lectorField.requestFocus());

    }
    

    public void setEventHandler() {
        volver.setOnAction(e -> {
            registerController.handledReturn();
        });

        registrar.setOnAction(e -> {
            registerController.handleRegister(usuario.getText(), password.getText(), lectorField.getText());
        });
  

    }

    public Parent getRoot() {
        return gridMain;
    }
}
