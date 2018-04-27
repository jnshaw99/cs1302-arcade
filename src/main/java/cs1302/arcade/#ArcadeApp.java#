package cs1302.arcade;


import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Random;

public class ArcadeApp extends Application {
    Random rng = new Random();

    @Override
    public void start(Stage stage) {
        ArcadeMenu arcadeMenu = new ArcadeMenu(stage);
        Scene scene = new Scene(arcadeMenu.getRootPane(),640, 480);
        scene.getStylesheets().add("stylesheet.css");
        stage.setTitle("cs1302-arcade!");
        stage.setScene(scene);
        stage.sizeToScene();
        stage.show();


        // the group must request input focus to receive key events
        // @see https://docs.oracle.com/javase/8/javafx/api/javafx/scene/Node.html#requestFocus--
        //group.requestFocus();

    } // start

    public static void main(String[] args) {
        try {
            Application.launch(args);
        } catch (UnsupportedOperationException e) {
            System.out.println(e);
            System.err.println("If this is a DISPLAY problem, then your X server connection");
            System.err.println("has likely timed out. This can generally be fixed by logging");
            System.err.println("out and logging back in.");
            System.exit(1);
        } // try
    } // main

} // ArcadeApp

