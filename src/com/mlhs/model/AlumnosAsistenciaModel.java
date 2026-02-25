package com.mlhs.model;

import com.mlhs.config.DataBaseConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.time.LocalDate;

public class AlumnosAsistenciaModel {

    private final int idestudiante;
    private final String nombre;
    private final String apellido;
    private final String codigoTarjeta;
    private final String correoInstitucional;
    private final String carneEstudiante;
    private final EstadoAsistencia asistencia;

    public AlumnosAsistenciaModel(
            int idestudiante,
            String nombre,
            String apellido,
            String codigoTarjeta,
            String correoInstitucional,
            String carneEstudiante,
            EstadoAsistencia asistencia) {
        this.idestudiante = idestudiante;
        this.nombre = nombre;
        this.apellido = apellido;
        this.carneEstudiante = carneEstudiante;
        this.correoInstitucional = correoInstitucional;
        this.codigoTarjeta = codigoTarjeta;
        this.asistencia = asistencia;

    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getCodigoTarjeta() {
        return codigoTarjeta;
    }

    public EstadoAsistencia getAsistencia() {
        return asistencia;
    }

    public String getCorreoInstitucional() {
        return correoInstitucional;
    }

    public String getCarneEstudiante() {
        return carneEstudiante;
    }

    public int getIdestudiante() {
        return idestudiante;
    }

    public static ObservableList<AlumnosAsistenciaModel> obtenerAsistencias(
            String jornada,
            String seccion,
            LocalDate fecha) {

        ObservableList<AlumnosAsistenciaModel> lista
                = FXCollections.observableArrayList();

        String sql = "{CALL sp_estudiantes_filtrados(?,?,?)}";

        try (Connection conn = DataBaseConnection.getConnection(); CallableStatement cs = conn.prepareCall(sql)) {

            cs.setString(1, jornada);
            cs.setString(2, seccion);
            cs.setDate(3, Date.valueOf(fecha));

            ResultSet rs = cs.executeQuery();

            while (rs.next()) {
                lista.add(new AlumnosAsistenciaModel(
                        rs.getInt("idestudiantes"),
                        rs.getString("nombre"),
                        rs.getString("apellido"),
                        rs.getString("codigoTarjeta"),
                        rs.getString("correoInstitucional"),
                        rs.getString("carneEstudiante"),
                        EstadoAsistencia.valueOf(rs.getString("asistencia"))
                ));
            }
            System.out.println("llego al metodo ");

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }

    public static boolean marcarPresente(
            String codigoTarjeta,
            int idJornada,
            int idSeccion
    ) {
        //carne, asistencia, fecha
        String sql = "{call asistencia_kinal2026.sp_marcado_asistencia(?, ?, ?)}";

        try (Connection conn = DataBaseConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, codigoTarjeta);
            ps.setString(2, EstadoAsistencia.PRESENTE.name());
            ps.setDate(3, Date.valueOf(LocalDate.now()));

            /*
        ps.setInt(4, idJornada);
        ps.setInt(5, idSeccion);
       
             */
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

}
