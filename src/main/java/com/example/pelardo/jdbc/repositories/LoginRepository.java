package com.example.pelardo.jdbc.repositories;

import com.example.pelardo.jdbc.models.Usuario;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LoginRepository {
    public static Usuario login(String user, String password) throws Exception {
        try (PreparedStatement loginStatement = ConnectionDB.connect()
                .prepareStatement("SELECT * FROM USUARIOS WHERE user = ? AND password = ?");) {
            // completamos los ? con los datos correspondientes
            loginStatement.setString(1, user);
            loginStatement.setString(2, password);
            // ejecutamos la sentencia SQL
            ResultSet resultSet = loginStatement.executeQuery();
            if (resultSet.next()) {
                return new Usuario(
                        resultSet.getInt("idUser"),
                        resultSet.getString("user"),
                        resultSet.getString("password"),
                        resultSet.getString("rol"));
            } else {
                throw new Exception("Usuario/Contraseña no encontrado");
            }
        } catch (Exception exception) {
            throw new Exception("Error en la base de datos. \n" +
                    "Informacion del error: " + exception.getMessage());
        }
    }

    public static void insertUser(String user, String password) throws Exception {
        try (
                PreparedStatement insertStatement = ConnectionDB.connect().prepareStatement(
                        "INSERT INTO USUARIOS (user, password, rol) VALUES (?, ?, ?)")) {
            insertStatement.setString(1, user);
            insertStatement.setString(2, password);
            insertStatement.setString(3, "usuario");
            insertStatement.executeUpdate();
        } catch (Exception exception) {
            throw new Exception("Error en la base de datos. \n" +
                    "Informacion del error: " + exception.getMessage());
        }
    }

    public static boolean userExists(String user) throws Exception {
        try (PreparedStatement loginStatement = ConnectionDB.connect()
                .prepareStatement("SELECT * FROM USUARIOS WHERE user = ?");) {
            loginStatement.setString(1, user);
            ResultSet resultSet = loginStatement.executeQuery();
            if (resultSet.next()) {
                return true;
            }
        } catch (Exception exception) {
            // Un error general de la BD
            throw new Exception("Error al comprobar: " + exception.getMessage());
        }
        return false;
    }

}
