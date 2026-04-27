package com.example.pelardo.jdbc.repositories;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDB {
    private final static String URL = "jdbc:mysql://localhost:3309/PelardoDB?serverTimezone=UTC";
    private final static String USERNAME = "root";
    private final static String PASSWORD = "root";
    private static Connection connection = null;

    public static Connection connect() throws Exception {
        if (connection == null) {
            try {
                connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            } catch (SQLException exception) {
                throw new Exception("Error en la conecion con la base de datos. \n" +
                        "Informacion del error" + exception.getMessage());
            }
        }
        return connection;
    }
}
