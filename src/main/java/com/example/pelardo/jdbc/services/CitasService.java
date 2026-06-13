package com.example.pelardo.jdbc.services;

import com.example.pelardo.jdbc.models.CitaTbl;
import com.example.pelardo.jdbc.repositories.CitasRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CitasService {
    private static final Logger LOGGER = Logger.getLogger(CitasService.class.getName());

    public List<CitaTbl> mostrarCita(String fecha) {
        try {
            return CitasRepository.mostrarCita(fecha);

        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error al mostrar las citas: {0}", e.getMessage());
            return new ArrayList<>();
        }
    }

    public void insertCita(String cliente, String fecha, String hora, String descripcion) throws Exception {
        if (cliente.isBlank() || fecha.isBlank() || hora.isBlank() || descripcion.isBlank()) {
            throw new Exception("Todos los campos deben ir informados");
        }
        CitasRepository.insertCita(cliente, fecha, hora, descripcion);
    }

    public void updateCita(CitaTbl cita) throws Exception {
        if (cita.getCliente().isBlank() || cita.getFecha().isBlank() || cita.getHora().isBlank()
                || cita.getDescripcion().isBlank()) {
            throw new Exception("Todos los campos deben ir informados");
        }
        CitasRepository.updateCita(cita);
    }

    public void deleteCita(CitaTbl cita) throws Exception {
        CitasRepository.deleteCita(cita);
    }
}
