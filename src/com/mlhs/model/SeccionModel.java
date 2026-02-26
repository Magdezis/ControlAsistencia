package com.mlhs.model;

import com.mlhs.config.DataBaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Magno Solis
 */
public class SeccionModel {
    private int idseccion;    
    private String codigo_seccion;
    
    public SeccionModel() {
    }

    public SeccionModel(int idseccion, String codigo_seccion) {
        this.idseccion = idseccion;
        this.codigo_seccion = codigo_seccion;
    }
    
    public int getIdseccion() {
        return idseccion;
    }
    
    public void setIdseccion(int idseccion) {
        this.idseccion = idseccion;
    }
    
    public String getCodigo_seccion() {
        return codigo_seccion;
    }
    
    public void setCodigo_seccion(String codigo_seccion) {
        this.codigo_seccion = codigo_seccion;
    }
    
    public ObservableList<SeccionModel> obtenerSecciones() {
        ObservableList<SeccionModel> secciones = FXCollections.observableArrayList();
        String sqlJornadas = "SELECT * FROM seccion";
        try (Connection conn = DataBaseConnection.getConnection(); PreparedStatement pstm = conn.prepareStatement(sqlJornadas); ResultSet rs = pstm.executeQuery()) {
            
            while (rs.next()) {
                secciones.add(new SeccionModel(
                        rs.getInt("idseccion"),
                        rs.getString("codigo_seccion")
                ));
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return secciones = FXCollections.observableArrayList(secciones);        
    }
    
    @Override
    public String toString(){
    return codigo_seccion; 
    }
}
