package com.example.pelardo.jdbc.services;

import com.example.pelardo.jdbc.models.Usuario;
import com.example.pelardo.jdbc.repositories.LoginRepository;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class LoginServicesTest {

    private final LoginServices loginServices = new LoginServices();

    @Test
    void testLoginBlankFieldsThrowsException() {
        Exception exception1 = assertThrows(Exception.class, () -> loginServices.login("", "password"));
        assertEquals("Todos los campos deben ir informados", exception1.getMessage());

        Exception exception2 = assertThrows(Exception.class, () -> loginServices.login("user", "   "));
        assertEquals("Todos los campos deben ir informados", exception2.getMessage());
    }

    @Test
    void testLoginSuccess() throws Exception {
        Usuario mockUsuario = new Usuario(1, "john_doe", "password123", "usuario");

        try (MockedStatic<LoginRepository> mockedRepository = mockStatic(LoginRepository.class)) {
            mockedRepository.when(() -> LoginRepository.login("john_doe", "password123"))
                    .thenReturn(mockUsuario);

            Usuario result = loginServices.login("john_doe", "password123");

            assertNotNull(result);
            assertEquals("usuario", result.getRol());
            mockedRepository.verify(() -> LoginRepository.login("john_doe", "password123"), times(1));
        }
    }

    @Test
    void testRegisterBlankFieldsThrowsException() {
        Exception exception = assertThrows(Exception.class, () -> loginServices.register("", "pass", "pass"));
        assertEquals("Todos los campos deben ir informados", exception.getMessage());
    }

    @Test
    void testRegisterPasswordsDoNotMatch() {
        Exception exception = assertThrows(Exception.class, () -> loginServices.register("user", "password123", "different"));
        assertEquals("Las contraseñas no coinciden", exception.getMessage());
    }

    @Test
    void testRegisterPasswordTooShort() {
        Exception exception = assertThrows(Exception.class, () -> loginServices.register("user", "short", "short"));
        assertEquals("La contraseña debe tener 8 caracteres minimo", exception.getMessage());
    }

    @Test
    void testRegisterUserAlreadyExists() {
        try (MockedStatic<LoginRepository> mockedRepository = mockStatic(LoginRepository.class)) {
            mockedRepository.when(() -> LoginRepository.userExists("existing_user"))
                    .thenReturn(true);

            Exception exception = assertThrows(Exception.class, () ->
                    loginServices.register("existing_user", "password123", "password123")
            );
            assertEquals("El usuario ya existe", exception.getMessage());
        }
    }

    @Test
    void testRegisterSuccess() throws Exception {
        try (MockedStatic<LoginRepository> mockedRepository = mockStatic(LoginRepository.class)) {
            mockedRepository.when(() -> LoginRepository.userExists("new_user"))
                    .thenReturn(false);

            assertDoesNotThrow(() -> loginServices.register("new_user", "password123", "password123"));

            mockedRepository.verify(() -> LoginRepository.insertUser("new_user", "password123"), times(1));
        }
    }
}
