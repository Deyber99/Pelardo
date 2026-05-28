package com.example.pelardo.jdbc.repositories;

import com.example.pelardo.jdbc.models.CitaTbl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CitasRepository {
    public static List<CitaTbl> mostrarCita(String fecha) throws Exception {
        // 1. Iniciamos una Lista para ir almacenando todos los resultados de la
        // consulta
        List<CitaTbl> listaCitas = new ArrayList<>();
        try (PreparedStatement mostrarCitaStatement = ConnectionDB.connect()
                .prepareStatement("SELECT * FROM citas WHERE fecha = ?");) {
            // completamos los "?", con los datos correspondientes
            mostrarCitaStatement.setString(1, fecha);
            // ejecutamos la sentencia SQL
            ResultSet resultSet = mostrarCitaStatement.executeQuery();

            // 2. Hacemos que el while construya el objeto y lo añada a la lista
            while (resultSet.next()) {
                CitaTbl cita = new CitaTbl(
                        resultSet.getInt("id_Cita"),
                        resultSet.getString("cliente"),
                        resultSet.getString("fecha"),
                        resultSet.getString("hora"),
                        resultSet.getString("descripcion"));
                listaCitas.add(cita);
            }
        } catch (Exception exception) {
            throw new Exception("Error en la base de datos. \n" +
                    "Informacion del error: " + exception.getMessage());
        }

        // 3. Por último regresamos la lista completa de citas
        return listaCitas;
    }

    public static void insertCita(String cliente, String fecha, String hora, String descripcion) throws Exception {
        String sql = "INSERT INTO citas (cliente, fecha, hora, descripcion) VALUES (?, ?, ?, ?)";
        try (
                PreparedStatement insertStatement = ConnectionDB.connect().prepareStatement(sql)) {
            insertStatement.setString(1, cliente);
            insertStatement.setString(2, fecha);
            insertStatement.setString(3, hora);
            insertStatement.setString(4, descripcion);
            // ejecutamos el update
            insertStatement.executeUpdate();
        } catch (Exception exception) {
            throw new Exception("Error en la base de datos. \n" +
                    "Informacion del error: " + exception.getMessage());
        }
    }

    public static void updateCita(CitaTbl cita) throws Exception {
        String sql = "UPDATE citas SET cliente = ?, fecha = ?, hora = ?, descripcion = ? WHERE \"id_Cita\" = ?";
        try (
                PreparedStatement preparedStatement = ConnectionDB.connect().prepareStatement(sql)) {
            // asignamos los valores a la sentencia sql
            preparedStatement.setString(1, cita.getCliente());
            preparedStatement.setString(2, cita.getFecha());
            preparedStatement.setString(3, cita.getHora());
            preparedStatement.setString(4, cita.getDescripcion());
            preparedStatement.setInt(5, cita.getId_Cita());
            // ejecutamos el update
            preparedStatement.executeUpdate();
        } catch (SQLException exception) {
            throw new Exception("Error en la base de datos. \n" +
                    "Informacion del error: " + exception.getMessage());
        }
    }

    public static void deleteCita(CitaTbl cita) throws Exception {
        String sql = "DELETE FROM citas WHERE \"id_Cita\" = ?";
        try (PreparedStatement preparedStatement = ConnectionDB.connect().prepareStatement(sql)) {
            // asignamos los valores a la sentencia sql
            preparedStatement.setInt(1, cita.getId_Cita());
            // ejecutamos el update
            preparedStatement.executeUpdate();
        } catch (SQLException exception) {
            throw new Exception("Error en la bas3e de datos. \n" +
                    "Informacion del error: " + exception.getMessage());
        }
    }

}
