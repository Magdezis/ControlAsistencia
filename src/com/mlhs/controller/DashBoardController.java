/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mlhs.controller;

import com.mlhs.model.AlumnosAsistenciaModel;
import com.mlhs.model.EstadoAsistencia;
import com.mlhs.model.JornadaModel;
import com.mlhs.model.SeccionModel;
import com.mlhs.util.SceneManager;
import com.mlhs.view.dashboard.DashBoardView;
import com.mlhs.view.edit.EditInfoView;
import java.time.LocalDate;
import javafx.collections.ObservableList;

/**
 *
 * @author magde
 */
public class DashBoardController {

    private DashBoardView view;
    private final SceneManager sceneManager;
    private JornadaModel jornadaModel;
    private SeccionModel seccionModel;
    private AlumnosAsistenciaModel alumnosModel;
  

    public DashBoardController(SceneManager sceneManager) {
        this.jornadaModel = new JornadaModel();
        this.seccionModel = new SeccionModel();
        this.sceneManager = sceneManager;

    }

    public void handleLogout() {

    }

    public void loadComboBox() {
        
        view.getComboBoxJornada().setItems(jornadaModel.obtenerJornadas());
        view.getComboBoxJornada().getSelectionModel().selectLast();
        //view.getComboBoxJornada().setValue(jornadaModel.obtenerJornadas().get(0));
       view.getComboBoxSeccion().setItems(seccionModel.obtenerSecciones());
       view.getComboBoxSeccion().getSelectionModel().selectFirst();
    }

    public void setDashBoard(DashBoardView view) {
        this.view = view;
        loadComboBox();
        actualizarTabla();
        configurarEventos();
    }

    public void cargarEstudiantes(String jornada, String seccion, LocalDate fecha) {
        ObservableList<AlumnosAsistenciaModel> datosAlumnos = AlumnosAsistenciaModel.obtenerAsistencias(jornada, seccion, fecha);
        System.out.println("alumnos registrados: " + datosAlumnos.size());
        view.getTableViewAsistencias().setItems(datosAlumnos);
    }

    public void actualizarTabla() {
        JornadaModel jornada = view.getComboBoxJornada().getValue();
        SeccionModel seccion = view.getComboBoxSeccion().getValue(); 

       cargarEstudiantes(
               jornada.getTipoJornada(),
               seccion.getCodigo_seccion(),
               view.getDatePickerAsistencia().getValue());

    }
    

    private void configurarEventos() {

        view.getComboBoxJornada().setOnAction(e -> actualizarTabla());

        view.getComboBoxSeccion().setOnAction(e -> actualizarTabla());

        view.getDatePickerAsistencia()
                .valueProperty()
                .addListener((obs, oldVal, newVal) -> actualizarTabla());
        view.getButtonEditar().setOnAction(e -> editarAlumno());
    }

    private void editarAlumno() {

        AlumnosAsistenciaModel alumnoSeleccionado
                = view.getTableViewAsistencias()
                        .getSelectionModel()
                        .getSelectedItem();

        if (alumnoSeleccionado == null) {
            System.out.println("⚠️ No hay alumno seleccionado");
            return;
        }
        sceneManager.showEditView();
        EditInfoView.getTxtNombre().setText(view.getTableViewAsistencias().getSelectionModel().getSelectedItem().getNombre());
        EditInfoView.getTxtApellido().setText(view.getTableViewAsistencias().getSelectionModel().getSelectedItem().getApellido());
        EditInfoView.getTxtCarne().setText(view.getTableViewAsistencias().getSelectionModel().getSelectedItem().getCarneEstudiante());
        EditInfoView.getTxtCodigoTarjeta().setText(view.getTableViewAsistencias().getSelectionModel().getSelectedItem().getCodigoTarjeta());
        EditInfoView.getTxtCorreo().setText(view.getTableViewAsistencias().getSelectionModel().getSelectedItem().getCorreoInstitucional());
        EditInfoView.getComboBoxJornada().setItems(jornadaModel.obtenerJornadas());
        EditInfoView.getComboBoxJornada().getSelectionModel().selectFirst();
        EditInfoView.getComboBoxSeccion().setItems(seccionModel.obtenerSecciones());
        EditInfoView.getComboBoxSeccion().getSelectionModel().selectFirst();
    }

    public void handleRegisterAlumno() {
        sceneManager.showRegisterAlumnoView();
    }

public void marcarAsistencia(String carne) {

    if (carne == null || carne.isBlank()) return;

    JornadaModel jornada = view.getComboBoxJornada().getValue();
    SeccionModel seccion = view.getComboBoxSeccion().getValue();

    if (jornada == null || seccion == null) {
        System.out.println("⚠️ Jornada o sección no seleccionada");
        return;
    }

    boolean marcado = AlumnosAsistenciaModel.marcarPresente(
            carne,
            jornada.getIdJornada(),
            seccion.getIdseccion()
    );

    if (!marcado) {
        System.out.println("ℹ️ Asistencia ya marcada o alumno no encontrado");
        System.out.println("carne" + carne);
        return;
    }

    actualizarTabla();
    System.out.println("✅ Asistencia marcada: " + carne);
}

  
}
