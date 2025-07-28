package com.banksimulator.modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CuentaBancaria implements Serializable {

    private String numeroCuenta;
    private double saldo;
    private Cliente cliente;

    private static final long serialVersionUID = 1L;

    private List <String> listaMovimientos;

    public CuentaBancaria(String numeroCuenta, double saldo, Cliente cliente) {
        this.numeroCuenta = numeroCuenta;
        this.saldo = saldo;
        this.cliente = cliente;
        listaMovimientos = new ArrayList<>();
    }

    public void depositar (double cantidad){
        saldo += cantidad;
    }

    public boolean retirar (double cantidad){
        if (cantidad <= saldo){
            saldo -= cantidad;
            return true;
        }
       return false;
    }

    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    public double getSaldo() {
        return saldo;
    }

    public Cliente getCliente() {
        return cliente;
    }

    @Override
    public String toString() {
        return "Cuenta de " + cliente.getNombre() + " (" + numeroCuenta + ") - Saldo: " + saldo + "€" + " - " + " DNI: " + cliente.getDni();
    }

    public void setNumeroCuenta(String numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }


    //Métodos de la lista de movimientos
    public void agregarMovimiento(String movimiento){
        listaMovimientos.add(movimiento);
    }

    public String consultarMovimientos(){
        if (listaMovimientos.isEmpty()) {
            return "No hay movimientos registrados.";
        }

        StringBuilder sb = new StringBuilder();
        for (String mov : listaMovimientos) {
            sb.append(mov).append("\n");
        }
        return sb.toString();
    }

    //Necesarios a la hora de cargar el archivo de cuentas bancarias mediante el Stream
    public List<String> getListaMovimientos() {
        return listaMovimientos;
    }

    public void setListaMovimientos(List<String> listaMovimientos) {
        this.listaMovimientos = listaMovimientos;
    }
}

