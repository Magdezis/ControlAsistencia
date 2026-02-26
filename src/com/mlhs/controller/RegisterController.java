package com.mlhs.controller;

import com.mlhs.util.SceneManager;
import org.mindrot.jbcrypt.BCrypt;
import com.mlhs.config.DataBaseConnection;
import com.mlhs.model.JornadaModel;
import com.mlhs.model.SeccionModel;
import com.mlhs.view.edit.EditInfoView;
import com.mlhs.view.register.RegisterAlumnoView;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.time.LocalDate;
import java.util.Date;
import javafx.stage.Stage;

/**
 *
 * @author magde
 */
public class RegisterController {

    private final SceneManager sceneManager;
    private RegisterAlumnoView viewAlumnoRegister;
    private JornadaModel jornadaModel;
    private SeccionModel seccionModel;
    private RegisterAlumnoView msjToast = new RegisterAlumnoView(); 

    public RegisterController(SceneManager sceneManager) {
        this.sceneManager = sceneManager;
        this.jornadaModel = new JornadaModel();
        this.seccionModel = new SeccionModel();
        viewAlumnoRegister = new RegisterAlumnoView(this);
    }

    public void handledReturn() {
        sceneManager.showLoginView();
    }

    public void handleRegister(String nombre, String password, String noCarne) {
        String passHash = BCrypt.hashpw(password, BCrypt.gensalt(12));
        String recordUser = "INSERT INTO USUARIOS(usuario, contrasena, no_carne) VALUES(?,?, ?)";
        try (Connection conn = DataBaseConnection.getConnection(); PreparedStatement pstm = conn.prepareStatement(recordUser);) {
            pstm.setString(1, nombre);
            pstm.setString(2, passHash);
            pstm.setString(3, noCarne);
            int rowsAffected = pstm.executeUpdate();
            if (rowsAffected == 1) {
                System.out.println("exitoso." + passHash);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void handleRegisterAlumno(String nombre, String apellido, int fk_jornada, int fk_seccion, String codigoTarjeta, String correoInstitucional, String carneEstudiante) {
        String registerAlumno = "INSERT INTO ESTUDIANTES(nombre,apellido,fk_jornada, fk_seccion, codigoTarjeta, correoInstitucional, carneEstudiante)"
                + "VALUES(?,?,?,?,?,?,?)";
        try (Connection conn = DataBaseConnection.getConnection(); PreparedStatement pstm = conn.prepareStatement(registerAlumno);) {
            pstm.setString(1, nombre);
            pstm.setString(2, apellido);
            pstm.setInt(3, fk_jornada);
            pstm.setInt(4, fk_seccion);
            pstm.setString(5, codigoTarjeta);
            pstm.setString(6, correoInstitucional);
            pstm.setString(7, carneEstudiante);

            int rowsAffected = pstm.executeUpdate();
            if (rowsAffected == 1) {
              msjToast.mostrarToast((Stage) viewAlumnoRegister.getRoot().getScene().getWindow(), "Alumno registrado ");
              cerrar(); 
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void cerrar() {
        System.out.println("boton presionado");
        sceneManager.hideRegisterAlumnoView();
    }

    public void loadComboBox() {
        viewAlumnoRegister.getComboBoxJornada().setItems(jornadaModel.obtenerJornadas());
        viewAlumnoRegister.getComboBoxJornada().getSelectionModel().selectFirst();
        viewAlumnoRegister.getComboBoxSeccion().setItems(seccionModel.obtenerSecciones());
        viewAlumnoRegister.getComboBoxSeccion().getSelectionModel().selectFirst();
    }

    public void setDashBoard(RegisterAlumnoView view) {
        this.viewAlumnoRegister = view;
        loadComboBox();

    }
    
   
}
