package com.example.pelardo.jdbc.models;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class UsuarioTest {

    @Test
    public void testUsuarioConstructorAndGetters() {
        Usuario usuario = new Usuario(1, "testuser", "password123", "admin");
        assertEquals("admin", usuario.getRol());
    }
}
