package com.example.pelardo.jdbc.services;

import com.example.pelardo.jdbc.models.CitaTbl;
import com.example.pelardo.jdbc.repositories.CitasRepository;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class CitasServiceTest {

    private final CitasService citasService = new CitasService();

    @Test
    public void testMostrarCitaSuccess() throws Exception {
        List<CitaTbl> mockList = new ArrayList<>();
        mockList.add(new CitaTbl(1, "Cliente A", "2026-06-01", "10:00", "Desc A"));

        try (MockedStatic<CitasRepository> mockedRepository = mockStatic(CitasRepository.class)) {
            mockedRepository.when(() -> CitasRepository.mostrarCita("2026-06-01"))
                    .thenReturn(mockList);

            List<CitaTbl> result = citasService.mostrarCita("2026-06-01");

            assertNotNull(result);
            assertEquals(1, result.size());
            assertEquals("Cliente A", result.get(0).getCliente());
            mockedRepository.verify(() -> CitasRepository.mostrarCita("2026-06-01"), times(1));
        }
    }

    @Test
    public void testMostrarCitaExceptionReturnsEmptyList() throws Exception {
        try (MockedStatic<CitasRepository> mockedRepository = mockStatic(CitasRepository.class)) {
            mockedRepository.when(() -> CitasRepository.mostrarCita("2026-06-01"))
                    .thenThrow(new RuntimeException("DB connection error"));

            List<CitaTbl> result = citasService.mostrarCita("2026-06-01");

            assertNotNull(result);
            assertTrue(result.isEmpty());
        }
    }

    @Test
    public void testInsertCitaBlankFieldsThrowsException() {
        Exception exception = assertThrows(Exception.class, () ->
                citasService.insertCita("", "2026-06-01", "10:00", "Desc")
        );
        assertEquals("Todos los campos deben ir informados", exception.getMessage());
    }

    @Test
    public void testInsertCitaSuccess() throws Exception {
        try (MockedStatic<CitasRepository> mockedRepository = mockStatic(CitasRepository.class)) {
            assertDoesNotThrow(() ->
                    citasService.insertCita("Cliente B", "2026-06-01", "10:00", "Desc")
            );

            mockedRepository.verify(() ->
                            CitasRepository.insertCita("Cliente B", "2026-06-01", "10:00", "Desc"),
                    times(1)
            );
        }
    }

    @Test
    public void testUpdateCitaBlankFieldsThrowsException() {
        CitaTbl invalidCita = new CitaTbl(1, "   ", "2026-06-01", "10:00", "Desc");
        Exception exception = assertThrows(Exception.class, () -> citasService.updateCita(invalidCita));
        assertEquals("Todos los campos deben ir informados", exception.getMessage());
    }

    @Test
    public void testUpdateCitaSuccess() throws Exception {
        CitaTbl validCita = new CitaTbl(1, "Cliente C", "2026-06-01", "10:00", "Desc");

        try (MockedStatic<CitasRepository> mockedRepository = mockStatic(CitasRepository.class)) {
            assertDoesNotThrow(() -> citasService.updateCita(validCita));

            mockedRepository.verify(() -> CitasRepository.updateCita(validCita), times(1));
        }
    }

    @Test
    public void testDeleteCitaSuccess() throws Exception {
        CitaTbl citaToDelete = new CitaTbl(1, "Cliente D", "2026-06-01", "10:00", "Desc");

        try (MockedStatic<CitasRepository> mockedRepository = mockStatic(CitasRepository.class)) {
            assertDoesNotThrow(() -> citasService.deleteCita(citaToDelete));

            mockedRepository.verify(() -> CitasRepository.deleteCita(citaToDelete), times(1));
        }
    }
}
