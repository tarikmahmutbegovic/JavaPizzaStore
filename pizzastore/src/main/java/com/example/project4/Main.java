package com.example.project4;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Main file for initializing and starting up the RU Pizzeria GUI.
 * @author Aayush Jha, Tarik Mahmutbegovic
 */
public class Main extends Application {
    public static final int v = 600;
    public static final int v1 = 1000;

    /**
     * Function responsible for loading up and showing Main View window.
     * @param stage
     * @throws IOException
     */
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("MainView.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), v, v1);
        stage.setTitle("RU Pizzeria");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Main method that starts up GUI
     * @param args
     */
    public static void main(String[] args) {
        launch();
    }
}