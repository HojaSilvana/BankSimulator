package com.banksimulator.modelo;

public class CuentaBancaria {

    private String numeroCuenta;
    private double saldo;
    private Cliente cliente;

    public CuentaBancaria(String numeroCuenta, double saldo, Cliente cliente) {
        this.numeroCuenta = numeroCuenta;
        this.saldo = saldo;
        this.cliente = cliente;
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
        return "Cuenta de " + cliente.getNombre() + " (" + numeroCuenta + ") - Saldo: " + saldo + "€";
    }
}

