package com.banksimulator.modelo;

import java.util.ArrayList;
import java.util.List;

public class GestorCuentas {

    private List<CuentaBancaria> cuentas;

    public GestorCuentas() {
        cuentas = new ArrayList<>();
    }

    public void añadirCuenta(CuentaBancaria cuenta){
        cuentas.add(cuenta);
        System.out.println("Cuenta añadida: " + cuenta);
    }

    public CuentaBancaria buscarPorNumero (String numeroCuenta){
        for (CuentaBancaria cuenta: cuentas){
            if (cuenta.getNumeroCuenta().equalsIgnoreCase(numeroCuenta)){
                return cuenta;
            }
        }
        return null;
    }

    public void mostrarTodas(){
        for (CuentaBancaria cuenta: cuentas){
            System.out.println(cuenta);
        }
    }

    public List<CuentaBancaria> getCuentas() {
        return cuentas;
    }
}
