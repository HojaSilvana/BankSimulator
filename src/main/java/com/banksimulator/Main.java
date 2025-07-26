package com.banksimulator;

import com.banksimulator.modelo.Cliente;
import com.banksimulator.modelo.CuentaBancaria;
import com.banksimulator.modelo.GestorCuentas;
import com.banksimulator.vista.VentanaPrincipal;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {

//        Cliente cliente1 = new Cliente("David", "09434343F");
//        Cliente cliente2 = new Cliente("Chariana", "04534534T");
//
//        CuentaBancaria cuenta1 = new CuentaBancaria("ES1001", 1000, cliente1);
//        CuentaBancaria cuenta2 = new CuentaBancaria("ES1002", 500, cliente2);
//
//        //probar datos de la cuenta
//        System.out.println(cuenta1);
//
//        //probar ingresos a la cuenta
//        cuenta1.depositar(500);
//        System.out.println("Nuevo deposito en la cuenta: " + cuenta1);


//        //probar retiradas correctas y retiradas incorrectas
//        boolean retirarbien = cuenta1.retirar(900);
//        System.out.println("Retirada existosa: " + retirarbien + " Resultado después de la operacion: " + cuenta1);
//
//        boolean retiradamal = cuenta1.retirar(2000);
//        System.out.println("Retirada existosa: " + retiradamal + " Resultado después de la operacion: " + cuenta1);


        //probar el gestor de cuentas
        GestorCuentas gestor = new GestorCuentas();

//        gestor.añadirCuenta(cuenta1);
//        gestor.añadirCuenta(cuenta2);
//
//        gestor.mostrarTodas();

//        CuentaBancaria cuentaBuscada = gestor.buscarPorNumero("ES1001");
//        System.out.println("Cuenta encontrada: " + cuentaBuscada);


        //prueba de la ventana swing
        SwingUtilities.invokeLater(() -> new VentanaPrincipal(gestor));

        
    }
}