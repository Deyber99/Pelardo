package com.example.pelardo.jdbc.models;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CitaTblTest {

    @Test
    public void testCitaTblConstructorAndGetters() {
        CitaTbl cita = new CitaTbl(10, "Juan Perez", "2026-06-01", "10:30", "Consulta general");

        assertEquals(10, cita.getId_Cita());
        assertEquals("Juan Perez", cita.getCliente());
        assertEquals("2026-06-01", cita.getFecha());
        assertEquals("10:30", cita.getHora());
        assertEquals("Consulta general", cita.getDescripcion());
    }
}
