package com.example.pelardo.jdbc.models;

public class CitaTbl {
    private int id_Cita;
    private String cliente;
    private String fecha;
    private String hora;
    private String descripcion;

    public CitaTbl(int id_Cita, String cliente, String fecha, String hora, String descripcion) {
        this.id_Cita = id_Cita;
        this.cliente = cliente;
        this.fecha = fecha;
        this.hora = hora;
        this.descripcion = descripcion;
    }

    // getters y setters de la clase CitaTbl
    public int getId_Cita() {
        return id_Cita;
    }

    public String getCliente() {
        return cliente;
    }

    public String getFecha() {
        return fecha;
    }

    public String getHora() {
        return hora;
    }

    public String getDescripcion() {
        return descripcion;
    }
}
