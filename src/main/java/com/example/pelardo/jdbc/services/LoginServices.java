package com.example.pelardo.jdbc.services;

import com.example.pelardo.jdbc.models.Usuario;
import com.example.pelardo.jdbc.repositories.LoginRepository;

import static com.example.pelardo.jdbc.repositories.LoginRepository.userExists;

public class LoginServices {
    public Usuario login (String user, String password) throws Exception {
        if (user.isBlank() || password.isBlank()){
            throw new Exception("Todos los campos deben ir informados");
        }
        return LoginRepository.login(user, password);
    }
    public void register (String user, String password, String password2) throws Exception{
        if (user.isBlank() || password.isBlank() || password2.isBlank()){
            throw new Exception("Todos los campos deben ir informados");
        }
        if (!password.equals(password2)){
            throw new Exception("Las contraseñas no coinciden");
        }
        if (password.length() < 8){
            throw new Exception("La contraseña debe tener 8 caracteres minimo");
        }
        // Buscamos en la base de datos y si existe, informamos y evitamos crearlo
        if (userExists(user)) {
            throw new Exception("El usuario ya existe");
        }
        LoginRepository.insertUser(user, password);
    }
}

