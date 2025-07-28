package com.banksimulator.fx;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class MainFX extends Application {
    @Override
    public void start(Stage stage) {
        Label label = new Label("Hola desde JavaFX");
        Scene scene = new Scene(label, 400, 300);
        stage.setScene(scene);
        stage.setTitle("BankSimulator FX");
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
