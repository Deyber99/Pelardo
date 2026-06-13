package com.example.pelardo.jdbc.models;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class UsuarioTest {

    @Test
    void testUsuarioConstructorAndGetters() {
        Usuario usuario = new Usuario(1, "testuser", "password123", "admin");
        assertEquals("admin", usuario.getRol());
    }
}
