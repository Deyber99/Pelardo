package com.example.pelardo.jdbc.repositories;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDB {
    private final static String URL = "jdbc:postgresql://aws-0-eu-west-1.pooler.supabase.com:6543/postgres?sslmode=require";
    private final static String USERNAME = "postgres.uglsmmhacjerofflvjxy";
    private final static String PASSWORD = "D3yb3rS3bas"; // Reemplaza con tu contraseña real de Supabase
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
