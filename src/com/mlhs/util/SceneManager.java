package com.mlhs.util;

import com.mlhs.controller.DashBoardController;
import com.mlhs.controller.EditInfoController;
import com.mlhs.controller.LoginController;
import com.mlhs.controller.RegisterController;
import com.mlhs.view.dashboard.DashBoardView;
import com.mlhs.view.edit.EditInfoView;
import com.mlhs.view.login.LoginView;
import com.mlhs.view.login.RegisterView;
import com.mlhs.view.register.RegisterAlumnoView;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import static javafx.scene.input.MouseEvent.MOUSE_PRESSED;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class SceneManager {

    private final Stage stage;

    // sugerencias
    private StackPane root;
    private DashBoardView dashBoardView;
    private EditInfoView editInfoView;
    private RegisterAlumnoView registerAlumnoView;

    public SceneManager(Stage stage) {
        this.stage = stage;
    }

    public void showLoginView() {
        //controlador de la vista principal 
        LoginController controller = new LoginController(this);
        LoginView view = new LoginView(controller);
        Scene scene = new Scene(view.getRoot(), 600, 600);
        stage.setScene(scene);
        stage.setTitle("Asistencia");
        stage.centerOnScreen();
    }

    public void showDashBoardView() {
        DashBoardController controller = new DashBoardController(this);
        dashBoardView = new DashBoardView(controller);
        controller.setDashBoard(dashBoardView);
        root = new StackPane();

        root.getChildren().add(dashBoardView.getRoot());
        Scene scene = new Scene(root, 600, 600);
        scene.addEventFilter(MOUSE_PRESSED, e -> {
        dashBoardView.requestFocusLector();
        });
        
        stage.setScene(scene);
        stage.setTitle("Dashboard");
        stage.setMaximized(true);
    }

    public void showRegisterView() {
        RegisterController controller = new RegisterController(this);
        RegisterView view = new RegisterView(controller);
        Scene scene = new Scene(view.getRoot(), 600, 600);
        stage.setScene(scene);
        stage.setTitle("Registro");
    }

    public void showEditView() {
        if (editInfoView == null) {
            EditInfoController controller = new EditInfoController(this);
            editInfoView = new EditInfoView(controller);
            root.getChildren().add(editInfoView.getRoot());
            System.out.println("PRESIONADO FUNCIONAL ");

        }
        editInfoView.getRoot().setVisible(true);
        editInfoView.getRoot().setManaged(true);
        editInfoView.getRoot().toFront();
        System.out.println("mostrando overlay");
    }

    public void hideEditView() {
        if (editInfoView != null) {
            editInfoView.getRoot().setVisible(false);
        }
    }

    public void showRegisterAlumnoView() {
        RegisterController controller = new RegisterController(this);
        if (registerAlumnoView == null) {
            
            registerAlumnoView = new RegisterAlumnoView(controller);
            root.getChildren().add(registerAlumnoView.getRoot());
        }
        controller.setDashBoard(registerAlumnoView);
        registerAlumnoView.getRoot().setVisible(true);
        registerAlumnoView.getRoot().setManaged(true);
        registerAlumnoView.getRoot().toFront();

    }

    public void hideRegisterAlumnoView() {
        if (registerAlumnoView != null) {
            registerAlumnoView.getRoot().setVisible(false);
        }
    }

}
