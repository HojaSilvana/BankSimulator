package com.banksimulator.modelo;

import java.io.*;
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

    public void eliminarCuenta(CuentaBancaria cuenta){
        cuentas.remove(cuenta);
        System.out.println("La cuenta " + cuenta + " ha sido eliminada de la base de datos");
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

    public void guardarCuentasEnArcivo (String nombreArchivo){
        try(ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(nombreArchivo))){
            out.writeObject(cuentas);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    public void cargarCuentasDesdeArchivo(String nombreArchivo) {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(nombreArchivo))) {
            cuentas = (ArrayList<CuentaBancaria>) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}
