package com.banksimulator.modelo;

import java.io.Serializable;

public class Cliente implements Serializable {

    private String nombre;
    private String dni;
    private static final long serialVersionUID = 1L;

    public Cliente(String nombre, String dni) {
        this.nombre = nombre;
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDni() {
        return dni;
    }

    @Override
    public String toString() {
        return nombre + " (DNI: " + dni + ")";
    }
}
