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

    public void cargarCuentasDesdeArchivo(String rutaArchivo) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(rutaArchivo))) {
            List<CuentaBancaria> cuentasCargadas = (List<CuentaBancaria>) ois.readObject();
            for (CuentaBancaria cuenta : cuentasCargadas) {
                if (cuenta.getListaMovimientos() == null) {
                    cuenta.setListaMovimientos(new ArrayList<>()); // para evitar el null
                }
            }
            cuentas = cuentasCargadas;
            System.out.println("Cuentas cargadas correctamente desde el archivo.");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error al cargar cuentas desde archivo: " + e.getMessage());
        }
    }

    public String estadisticasGenerales() {

        int cantidad;
        double saldoBanco;
        CuentaBancaria mayorSaldo;
        CuentaBancaria menorSaldo;
        if (cuentas.isEmpty()) {
            return "No hay cuentas registradas";
        } else {

            cantidad = cuentas.size();
            saldoBanco = 0;
            mayorSaldo = cuentas.get(0);
            menorSaldo = cuentas.get(0);


            for (CuentaBancaria cuenta : cuentas) {
                double saldo = cuenta.getSaldo();
                saldoBanco += saldo;
            }

            for (CuentaBancaria cuenta : cuentas) {
                double saldoMax = 0;
                if (cuenta.getSaldo() > saldoMax) {
                    mayorSaldo = cuenta;
                }
            }

            for (CuentaBancaria cuenta : cuentas) {
                double saldoMin = 0;
                if (cuenta.getSaldo() < saldoMin) {
                    menorSaldo = cuenta;
                }
            }
        }

        return """
                Estadísticas generales:
                -----------------------
                Número total de cuentas: %d
                Saldo total del banco: %.2f €
                Cuenta con más saldo: %s (%.2f €)
                Cuenta con menos saldo: %s (%.2f €)
                """.formatted(
                cantidad,
                saldoBanco,
                mayorSaldo.getNumeroCuenta(), mayorSaldo.getSaldo(),
                menorSaldo.getNumeroCuenta(), menorSaldo.getSaldo()
        );

    }


}
