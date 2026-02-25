/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mlhs.view.edit;

import com.mlhs.controller.EditInfoController;
import com.mlhs.controller.RegisterController;
import com.mlhs.model.JornadaModel;
import com.mlhs.model.SeccionModel;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

/**
 *
 * @author Magno Solis
 */
public class EditInfoView {
    
    /* reutilizable**/
     private final StackPane root = new StackPane();
    private final VBox modal;
    private final Label title = new Label("Actualizar información");
    private final Label labelNombre = new Label("Nombre estudiante");
    private final Label labelApellido = new Label("Apellido estudiante");
    private final Label labelJornada = new Label("jornada asignada");
    private final Label labelSeccion = new Label("sección asignada");
    private final Label labelCodigoTarjeta = new Label("Acerca o escribe el codigo de tarjeta");
    private final Label labelCorreo = new Label("correo institucional");
    private final Label labelCarne = new Label("carne del estudiante");
    private static final TextField txtNombre = new TextField();
    private static final TextField txtApellido = new TextField();
    private static final TextField txtCodigoTarjeta = new TextField();
    private static final TextField txtCorreo = new TextField();
    private static final TextField txtCarne = new TextField();

    private static final ComboBox<JornadaModel> comboBoxJornada = new ComboBox();
    private static final ComboBox<SeccionModel> comboBoxSeccion = new ComboBox();

    private final Button btnCerrar = new Button("Cerrar");
    private final Button btnRegistrar = new Button("Actualizar información");
    private RegisterController registerController;
    
    /*finaliza reutilizable **/

    private final EditInfoController editInfoController;

    public EditInfoView(EditInfoController editInfoController) {
        this.editInfoController = editInfoController;
        this.modal = new VBox(10);
        buildUi();
        setEventHandler();
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
    

    public static TextField getTxtNombre() {
        return txtNombre;
    }

    public static TextField getTxtApellido() {
        return txtApellido;
    }

    public static TextField getTxtCodigoTarjeta() {
        return txtCodigoTarjeta;
    }

    public static TextField getTxtCorreo() {
        return txtCorreo;
    }

    public static TextField getTxtCarne() {
        return txtCarne;
    }

    public static ComboBox<JornadaModel> getComboBoxJornada() {
        return comboBoxJornada;
    }

    public static ComboBox<SeccionModel> getComboBoxSeccion() {
        return comboBoxSeccion;
    }
    
    
    
   
    public void setEventHandler() {
        btnCerrar.setOnAction(e -> editInfoController.cerrar());
    }

    public Parent getRoot() {
        return root; // ✅ AHORA SÍ
    }
    
}
