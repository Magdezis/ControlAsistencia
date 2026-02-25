/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mlhs.view.register;

import com.mlhs.controller.EditInfoController;
import com.mlhs.controller.RegisterController;
import com.mlhs.model.JornadaModel;
import com.mlhs.model.SeccionModel;
import javafx.animation.PauseTransition;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

/**
 *
 * @author Magno Solis
 */
public class RegisterAlumnoView {

    private final StackPane root = new StackPane();
    private final VBox modal;
    private final Label title = new Label("Registrar nuevo estudiante");
    private final Label labelNombre = new Label("Nombre estudiante");
    private final Label labelApellido = new Label("Apellido estudiante");
    private final Label labelJornada = new Label("jornada asignada");
    private final Label labelSeccion = new Label("sección asignada");
    private final Label labelCodigoTarjeta = new Label("Acerca o escribe el codigo de tarjeta del estudiante");
    private final Label labelCorreo = new Label("correo institucional");
    private final Label labelCarne = new Label("carne del estudiante");
    private final TextField txtNombre = new TextField();
    private final TextField txtApellido = new TextField();
    private final TextField txtCodigoTarjeta = new TextField();
    private final TextField txtCorreo = new TextField();
    private final TextField txtCarne = new TextField();

    private final ComboBox<JornadaModel> comboBoxJornada = new ComboBox();
    private final ComboBox<SeccionModel> comboBoxSeccion = new ComboBox();

    private final Button btnCerrar = new Button("Cerrar");
    private final Button btnRegistrar = new Button("Finalizar registro");
    private RegisterController registerController;

    public RegisterAlumnoView(RegisterController registerController) {
        this.registerController = registerController;
        this.modal = new VBox(10);
        buildUi();
        setEventHandler();
    }    
    public RegisterAlumnoView(){
    this.modal = new VBox(10);
    }

    

    private void buildUi() {

        // Overlay oscuro
        root.setStyle("-fx-background-color: rgba(0,0,0,0.4);");
        // root.setPrefSize(250, 250);
        root.setVisible(false);

        // 🧩 Contenido visible
        modal.setPadding(new Insets(20));
        modal.setStyle("-fx-background-color: white;");

        modal.setPrefWidth(300);
        modal.setMaxWidth(300);

        modal.setPrefHeight(180);
        modal.setMaxHeight(180);
        //evita que el modal crezca verticalmente. 
        modal.setFillWidth(true);

        txtNombre.setPromptText("Nombre");

        modal.getChildren().addAll(
                title,
                labelNombre,
                txtNombre,
                labelApellido,
                txtApellido,
                labelJornada,
                comboBoxJornada,
                labelSeccion,
                comboBoxSeccion,
                labelCodigoTarjeta,
                txtCodigoTarjeta,
                labelCorreo,
                txtCorreo,
                labelCarne,
                txtCarne,
                btnCerrar,
                btnRegistrar
        );

        // Agregar modal al overlay
        root.getChildren().add(modal);
        StackPane.setAlignment(modal, javafx.geometry.Pos.CENTER);
    }

    public void setEventHandler() {
        btnCerrar.setOnAction(e -> registerController.cerrar());
        
        btnRegistrar.setOnAction(e -> {
            
        registerController.handleRegisterAlumno
        (txtNombre.getText(),
         txtApellido.getText(),
         comboBoxJornada.getValue().getIdJornada() , 
         comboBoxSeccion.getValue().getIdseccion(),
         txtCodigoTarjeta.getText(), 
         txtCorreo.getText(), 
         txtCarne.getText());

        });

    }

    public Parent getRoot() {
        return root; 
    }

    public ComboBox<SeccionModel> getComboBoxSeccion() {
        return comboBoxSeccion;
    }

    public ComboBox<JornadaModel> getComboBoxJornada() {
        return comboBoxJornada;
    }
    
    public void mostrarToast(Stage owner, String mensaje) {
    Stage toast = new Stage();
    toast.initOwner(owner);
    toast.initStyle(StageStyle.UNDECORATED);
    toast.setAlwaysOnTop(true);

    Label label = new Label(mensaje);
    label.setStyle("""
        -fx-background-color: #323232;
        -fx-text-fill: white;
        -fx-padding: 10 20 10 20;
        -fx-background-radius: 5;
    """);

    StackPane root = new StackPane(label);
    root.setPadding(new Insets(10));

    Scene scene = new Scene(root);
    toast.setScene(scene);
    toast.show();

    PauseTransition delay = new PauseTransition(Duration.seconds(2));
    delay.setOnFinished(e -> toast.close());
    delay.play();
}

    

}
