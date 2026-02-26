/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mlhs.config;

/**
 *
 * @author Magno Solis
 */
public class DataBaseConfig {
    
    private DataBaseConfig(){};
    public static final String URL = System.getenv("URL_MYSQL_DB")+"/asistencia_kinal2026";
    public static final String USER = System.getenv("USER_MYSQL_DB"); 
    public static final String PASSWORD = System.getenv("PASS_MYSQL_DB"); 
}
