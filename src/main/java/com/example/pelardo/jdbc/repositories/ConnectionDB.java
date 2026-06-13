package com.example.pelardo.jdbc.repositories;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionDB {
    private static String url;
    private static String username;
    private static String password;
    private static Connection connection = null;

    static {
        Properties properties = new Properties();
        try (InputStream input = ConnectionDB.class.getClassLoader().getResourceAsStream("db.properties")) {
            if (input != null) {
                properties.load(input);
                url = properties.getProperty("db.url");
                username = properties.getProperty("db.username");
                password = properties.getProperty("db.password");
            }
        } catch (IOException exception) {
            System.err.println("Error al cargar db.properties: " + exception.getMessage());
        }

        // Fallback to environment variables if not loaded from properties file
        if (url == null || url.isBlank()) {
            url = System.getenv("DB_URL");
        }
        if (username == null || username.isBlank()) {
            username = System.getenv("DB_USERNAME");
        }
        if (password == null || password.isBlank()) {
            password = System.getenv("DB_PASSWORD");
        }
    }

    public static Connection connect() throws Exception {
        if (connection == null) {
            try {
                connection = DriverManager.getConnection(url, username, password);
            } catch (SQLException exception) {
                throw new Exception("Error en la conecion con la base de datos. \n" +
                        "Informacion del error: " + exception.getMessage());
            }
        }
        return connection;
    }
}

