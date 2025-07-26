package com.banksimulator.vista;

import com.banksimulator.modelo.Cliente;
import com.banksimulator.modelo.CuentaBancaria;
import com.banksimulator.modelo.GestorCuentas;

import javax.swing.*;
import java.awt.*;
import java.awt.font.NumericShaper;
import java.text.NumberFormat;
import java.text.ParseException;

public class VentanaPrincipal extends JFrame {

    private GestorCuentas gestor;

    public VentanaPrincipal(GestorCuentas gestor) {

        this.gestor = gestor;
        setTitle("Ventana Principal - BankSimulator");
        setSize(900, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centrar ventana

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); // Márgenes entre cada una de las celdas del Grid

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
        JButton botonIngresar = new JButton ("Ingresar dinero");
        JButton botonRetirar  = new JButton ("Retirar dinero");

        panelBotones.add(botonIngresar);
        panelBotones.add(botonRetirar);
        panelBotones.add(botonCrear);
        panelBotones.add(botonVer);
        add(panelBotones, gbc);


        gbc.gridy = 4;
        JPanel panelBotones2 = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));
        JButton botonEliminar = new JButton ("Eliminar cuenta");
        JButton buscarCuenta = new JButton ("Buscar cuenta existente");
        JButton guardarDatos = new JButton ("Guardar datos de clientes");
        JButton cargarDatos = new JButton ("Cargar datos de clientes");

        panelBotones2.add (buscarCuenta);
        panelBotones2.add(botonEliminar);
        panelBotones2.add(guardarDatos);
        panelBotones2.add(cargarDatos);
        add(panelBotones2, gbc);

        gbc.gridy = 5;
        JPanel panelBotones3 = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));
        JButton botonTransferencia = new JButton ("Hacer una transferencia");

        panelBotones3.add(botonTransferencia);
        add(panelBotones3, gbc);


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

        botonIngresar.addActionListener(e -> {
            String numeroCuenta = JOptionPane.showInputDialog(this, "Introduce el numero de cuenta");
            if (numeroCuenta == null){
                return;
            }

            CuentaBancaria cuentaBuscada = gestor.buscarPorNumero(numeroCuenta);
            if (cuentaBuscada == null){
                JOptionPane.showMessageDialog(this, "Cuenta no encontrada", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            String cantidadTexto = JOptionPane.showInputDialog(this, "Introduce la cantidad a ingresar");


            try {
                NumberFormat nf = NumberFormat.getNumberInstance();
                Number valorIngreso = nf.parse(cantidadTexto);
                double ingresoCuenta = valorIngreso.doubleValue();
                if (ingresoCuenta <= 0){
                    throw new NumberFormatException();
                }else{
                    cuentaBuscada.depositar(ingresoCuenta);
                    JOptionPane.showMessageDialog (this,"Ingreso realizado con existo. Nuevo saldo: " + cuentaBuscada.getSaldo());
                }
            } catch (ParseException ex) {
                JOptionPane.showMessageDialog(this, "Cantidad inválida", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } );

        botonRetirar.addActionListener(e -> {
            String numeroCuenta = JOptionPane.showInputDialog(this, "Introduce el numero de cuenta");
            if(numeroCuenta == null){
                return;
            }

            CuentaBancaria cuentaBuscada = gestor.buscarPorNumero(numeroCuenta);
            if(cuentaBuscada == null){
                JOptionPane.showMessageDialog(this, "No se ha encontrado la cuenta", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            String cantidadRetirar = JOptionPane.showInputDialog(this, "Ingresa la cantidad que quieres retirar");

            try{

                NumberFormat nf = NumberFormat.getNumberInstance();
                Number valorRetirada = nf.parse(cantidadRetirar);
                double retirada = valorRetirada.doubleValue();
                if (retirada > cuentaBuscada.getSaldo()){
                    throw new NumberFormatException();
                }else {
                    cuentaBuscada.retirar(retirada);
                    JOptionPane.showMessageDialog(this, "Retirada exitosa. Nuevo saldo: " + cuentaBuscada.getSaldo());
                }

            } catch (ParseException ex) {
                JOptionPane.showMessageDialog(this, "Cantidad inválida", "Error", JOptionPane.ERROR_MESSAGE);
            }


        });

        botonEliminar.addActionListener(e -> {
            String numeroCuenta = JOptionPane.showInputDialog(this, "Introduce el numero de cuenta para eliminar");
            if (numeroCuenta == null){
                return;
            }

            CuentaBancaria cuentaEliminar = gestor.buscarPorNumero(numeroCuenta);
            if(cuentaEliminar == null){
                JOptionPane.showMessageDialog(this, "No se ha encontrado ninguna cuenta asociada a ese numero o no se ha introducido ningún valor");
            }else{
                int numeroConfirmar = JOptionPane.showConfirmDialog(this, "¿Estas seguro de que quieres eliminar la cuenta? " + cuentaEliminar);

                if (numeroConfirmar == JOptionPane.YES_OPTION){
                    gestor.eliminarCuenta(cuentaEliminar);
                }
            }


        });

        buscarCuenta.addActionListener(e -> {
            String numeroCuenta = JOptionPane.showInputDialog(this, "Introduce la cuenta que quieres buscar: ");
            if(numeroCuenta == null){
                return;
            }

            CuentaBancaria cuentaBuscada = gestor.buscarPorNumero(numeroCuenta);
            if(cuentaBuscada != null){
                JOptionPane.showMessageDialog(this, "Se ha encontrado la cuenta indicada: \n" +
                        "Número de cuenta: " + cuentaBuscada.getNumeroCuenta() + "\n" +
                        "Nombre del usuario: " + cuentaBuscada.getCliente() + "\n" +
                        "Saldo : " + cuentaBuscada.getSaldo());
            }else{
                JOptionPane.showMessageDialog(this, "No hay ninguna cuenta asociada a ese numero");
            }
        });

        guardarDatos.addActionListener(e -> {
            gestor.guardarCuentasEnArcivo("cuentas bancarias.dat");
            JOptionPane.showMessageDialog(this, "Cuentas guardadas correctamente");
        });

        cargarDatos.addActionListener(e -> {
            gestor.cargarCuentasDesdeArchivo("cuentas bancarias.dat");
            JOptionPane.showMessageDialog(this, "Cuentas cargadas con exito");
        });

        botonTransferencia.addActionListener(e -> {
            String cuentaOrigen= JOptionPane.showInputDialog(this, "Introduce el numero de cuenta desde donde quieres hacer la transferencia");
            if(cuentaOrigen == null){
                return;
            }

            String cuentaDestino= JOptionPane.showInputDialog(this, "Introduce el numero de cuenta al que hacer la transferencia");
            if(cuentaDestino == null){
                return;
            }

            String cantidadTransferir = JOptionPane.showInputDialog(this, "Ingresa la cantidad que quieres transferir");

            try{

                NumberFormat nf = NumberFormat.getNumberInstance();
                Number valorRetirada = nf.parse(cantidadTransferir);
                double transferencia = valorRetirada.doubleValue();

                CuentaBancaria cuenta1 = gestor.buscarPorNumero(cuentaOrigen);
                CuentaBancaria cuenta2 = gestor.buscarPorNumero(cuentaDestino);

                if (cuenta1 == null || cuenta2 == null){
                    JOptionPane.showMessageDialog(this, "Una o varias cuentas no existen en el sistema");
                    return;
                }

                if (transferencia <= 0){
                    JOptionPane.showMessageDialog(this, "La cantidad debe ser mayor a 0");
                    return;
                }

                if(cuenta1.retirar(transferencia)){
                    cuenta2.depositar(transferencia);
                    JOptionPane.showMessageDialog(this, "Transferencia realizada con exito");
                }else{
                    JOptionPane.showMessageDialog(this, "El saldo es insuficiente");
                }


            } catch (ParseException ex) {
                JOptionPane.showMessageDialog(this, "Conversión erronea", "Error de conversión de divisa", JOptionPane.ERROR_MESSAGE);
            } catch (NumberFormatException nfe) {
                JOptionPane.showMessageDialog (this, "Cantidad invalida", "Error de saldo", JOptionPane.ERROR_MESSAGE);
            }


        });

    }
}
