package com.banksimulator.vista;

import com.banksimulator.modelo.CuentaBancaria;
import com.banksimulator.modelo.GestorCuentas;

import javax.swing.*;

public class VerCuentasFrame extends JFrame {

    public VerCuentasFrame (GestorCuentas gestor){

        setTitle("Listado de Cuentas");
        setSize(400, 300);
        setLocationRelativeTo(null);

        JTextArea areaCuentas = new JTextArea();
        areaCuentas.setEditable(false);

        StringBuilder texto = new StringBuilder();
        for (CuentaBancaria c : gestor.getCuentas()) {
            texto.append(c.toString()).append("\n");
        }

        areaCuentas.setText(texto.toString());

        add(new JScrollPane(areaCuentas));
        setVisible(true);

    }
}
