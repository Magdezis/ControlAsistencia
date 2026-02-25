package com.mlhs.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException; 

/**
 *
 * @author Magno Solis
 */
public class DataBaseConnection {

    private static Connection connection;

    private DataBaseConnection() {

    }

    public static Connection getConnection() throws SQLException{
        if (connection == null || connection.isClosed()) {
            connection = DriverManager.getConnection(
                    DataBaseConfig.URL,
                    DataBaseConfig.USER,
                    DataBaseConfig.PASSWORD
            );
        }
        return connection;
    }
}
