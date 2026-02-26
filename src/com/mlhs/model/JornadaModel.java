package com.mlhs.model;

import com.mlhs.config.DataBaseConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.PreparedStatement;
import java.sql.Connection; 
import java.sql.SQLException;
import java.sql.ResultSet; 
/**i
 *
 * @author Magno Solis
 */
public class JornadaModel {
    
    private int idJornada; 
    private String tipoJornada; 

    public JornadaModel(int idJornada, String tipoJornada) {
        this.idJornada = idJornada;
        this.tipoJornada = tipoJornada;
    }

    public int getIdJornada() {
        return idJornada;
    }

    public void setIdJornada(int idJornada) {
        this.idJornada = idJornada;
    }

    public String getTipoJornada() {
        return tipoJornada;
    }

    public void setTipoJornada(String tipoJornada) {
        this.tipoJornada = tipoJornada;
    }
    
    public JornadaModel(){
    
    }
    
    public ObservableList<JornadaModel> obtenerJornadas(){
     ObservableList<JornadaModel> jornadas = FXCollections.observableArrayList();
     String sqlJornadas = "SELECT * FROM jornada";
     try(Connection conn = DataBaseConnection.getConnection(); 
             PreparedStatement pstm = conn.prepareStatement(sqlJornadas); 
             ResultSet rs = pstm.executeQuery() ){
         
         while(rs.next()){
          jornadas.add(new JornadaModel(
                  rs.getInt("idJornada"),
                  rs.getString("tipoDeJornada")
          
          ));
         }
     
     }catch(SQLException e){
         e.printStackTrace();
     }
     return jornadas = FXCollections.observableArrayList(jornadas); 
    }
    
    @Override 
    public String toString(){
    return tipoJornada;
    }
}
