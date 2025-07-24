package com.banksimulator.vista;

import com.banksimulator.modelo.Cliente;
import com.banksimulator.modelo.CuentaBancaria;
import com.banksimulator.modelo.GestorCuentas;

import javax.swing.*;
import java.awt.*;
import java.text.NumberFormat;
import java.text.ParseException;

public class VentanaPrincipal extends JFrame {

    private GestorCuentas gestor;

    public VentanaPrincipal(GestorCuentas gestor) {

        this.gestor = gestor;
        setTitle("Ventana Principal - BankSimulator");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centrar ventana

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); // Márgenes

        // Fila 0 - Número de cuenta
        gbc.gridx = 0; gbc.gridy = 0; gbc.anchor = GridBagConstraints.EAST;
        add(new JLabel("Número de cuenta:"), gbc);
        gbc.gridx = 1; gbc.anchor = GridBagConstraints.WEST;
        JTextField campoCuenta = new JTextField(10);
        add(campoCuenta, gbc);

        // Fila 0 - Titular
        gbc.gridx = 2; gbc.anchor = GridBagConstraints.EAST;
        add(new JLabel("Titular:"), gbc);
        gbc.gridx = 3; gbc.anchor = GridBagConstraints.WEST;
        JTextField campoTitular = new JTextField(10);
        add(campoTitular, gbc);

        // Fila 1 - Saldo
        gbc.gridx = 0; gbc.gridy = 1; gbc.anchor = GridBagConstraints.EAST;
        add(new JLabel("Saldo inicial:"), gbc);
        gbc.gridx = 1; gbc.anchor = GridBagConstraints.WEST;
        JFormattedTextField campoSaldo = new JFormattedTextField(NumberFormat.getNumberInstance());
        campoSaldo.setColumns(10);
        add(campoSaldo, gbc);

        // Fila 1 - DNI
        gbc.gridx = 2; gbc.anchor = GridBagConstraints.EAST;
        add(new JLabel("DNI:"), gbc);
        gbc.gridx = 3; gbc.anchor = GridBagConstraints.WEST;
        JTextField campoDni = new JTextField(10);
        add(campoDni, gbc);

        // Fila 2 - Texto de bienvenida
        gbc.gridx = 0; gbc.gridy = 2; gbc.gridwidth = 4; gbc.anchor = GridBagConstraints.CENTER;
        add(new JLabel("¡Bienvenido al servicio, David!"), gbc);

        // Fila 3 - Panel con botones centrados
        gbc.gridy = 3;
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));
        JButton botonCrear = new JButton("Crear cuenta");
        JButton botonVer = new JButton("Ver Cuentas");
        panelBotones.add(botonCrear);
        panelBotones.add(botonVer);
        add(panelBotones, gbc);

        // Acción del botón Crear cuenta
        botonCrear.addActionListener(e -> {
            String numero = campoCuenta.getText();
            String titular = campoTitular.getText();
            String dni = campoDni.getText();

            try {
                campoSaldo.commitEdit();
                Number valor = (Number) campoSaldo.getValue();
                double saldo = valor.doubleValue();
                CuentaBancaria cuenta = new CuentaBancaria(numero, saldo, new Cliente(titular, dni));
                gestor.añadirCuenta(cuenta);
                JOptionPane.showMessageDialog(this, "Cuenta creada: " + cuenta);
            } catch (NumberFormatException | ParseException ex) {
                JOptionPane.showMessageDialog(this, "Saldo inválido", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        // Acción del botón Ver cuentas
        botonVer.addActionListener(e -> new VerCuentasFrame(gestor));

        setVisible(true);
    }
}
