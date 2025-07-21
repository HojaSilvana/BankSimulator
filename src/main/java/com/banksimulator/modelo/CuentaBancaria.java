package com.banksimulator.modelo;

public class CuentaBancaria {

    private String numeroCuenta;
    private String titular;
    private double saldo;

    public CuentaBancaria(String numeroCuenta, String titular, double saldo) {
        this.numeroCuenta = numeroCuenta;
        this.titular = titular;
        this.saldo = saldo;
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

    public String getTitular() {
        return titular;
    }

    public double getSaldo() {
        return saldo;
    }

    @Override
    public String toString() {
        return "Cuenta de " + titular + " (" + numeroCuenta + ") - Saldo: " + saldo + "€";
    }
}

