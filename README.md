# BankSimulator

BankSimulator es una aplicación Java con interfaz gráfica Swing pensada para simular operaciones básicas de gestión bancaria. Actualmente permite crear cuentas bancarias con datos personalizados (número de cuenta, titular, DNI y saldo), y sirve como base para una aplicación más completa.

---

## Características actuales

- Interfaz gráfica con Java Swing
- Creación de cuentas bancarias
- Validación de saldo numérico con control de errores
- Organización por capas (modelo, vista, lógica)
- Proyecto con control de versiones (Git)

---

## Estructura del proyecto

```
com.banksimulator
│
├── modelo
│   ├── Cliente.java
│   ├── CuentaBancaria.java
│   └── GestorCuentas.java
│
├── vista
│   ├── VentanaPrincipal.java
│   └── VerCuentasFrame.java
│
└── Main.java
```

---

## Cómo ejecutar

1. Abre el proyecto en IntelliJ IDEA u otro IDE Java.
2. Ejecuta el método `main()` desde la clase `Main`.
3. Se abrirá una ventana para crear cuentas bancarias.

> Requiere Java 17 o superior.

---

## Tecnologías utilizadas

- Java 17
- Swing
- Git

---

## Autor

David Cuenca Bascuñana  
Proyecto personal de aprendizaje - 1º DAM

---

## Pendiente por implementar

- Visualización completa de cuentas
- Exportar/importar datos
- Operaciones bancarias básicas (ingresos, reintegros)
- Persistencia en MySQL o archivos
- Menú de navegación entre ventanas

