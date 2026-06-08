package com.example.pelardo.jdbc.models;

public class Usuario {
    private int idUser;
    private String user;
    private String password;
    private String rol;

    public Usuario(int idUser, String user, String password, String rol) {
        this.idUser = idUser;
        this.user = user;
        this.password = password;
        this.rol = rol;
    }

    public String getRol() {
        return rol;
    }
}
