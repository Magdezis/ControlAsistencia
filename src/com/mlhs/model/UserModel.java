/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mlhs.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import com.mlhs.config.DataBaseConnection;
import java.sql.SQLException;

/**
 *
 * @author Magno Solis
 */
public class UserModel {

    public static String getHashUser(String nombre) {
        String user = "SELECT contrasena FROM USUARIOS where usuario = ?";
        try (Connection conn = DataBaseConnection.getConnection(); PreparedStatement pstm = conn.prepareStatement(user)) {
            pstm.setString(1, nombre);
            ResultSet rs = pstm.executeQuery();

            if (rs.next()) {
                return rs.getString("contrasena");
            }

        } catch (SQLException e) {
        }
        return null; 
    }
    
    public static String getCarneUser(String carnetUser){
    String carne = "SELECT no_carne FROM USUARIOS where no_carne = ?";
    try(Connection conn = DataBaseConnection.getConnection(); PreparedStatement pstm = conn.prepareStatement(carne)){
    pstm.setString(1, carnetUser);
    ResultSet rs = pstm.executeQuery(); 
    if (rs.next()){
        //System.out.println("carne enconbtrado");
    return rs.getString("no_carne");  
    }
    }catch(SQLException e){
     }
    return null; 
    }
    
}

