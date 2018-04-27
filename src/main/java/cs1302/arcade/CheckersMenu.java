package cs1302.arcade;

import javafx.scene.control.MenuBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.scene.control.Button;

import java.awt.*;

public class CheckersMenu {
    private BorderPane borderPane = new BorderPane();
    public CheckersMenu(Stage stage){
        Button singlePlayer = new Button("Single Player");
        Button mulitPlayer = new Button("Multiplayer");
        singlePlayer.setOnAction(event -> {
            Checkers checkers = new Checkers(stage,true);
            stage.getScene().setRoot(checkers.getRootPane());
        });
        mulitPlayer.setOnAction(event -> {
            Checkers checkers = new Checkers(stage, false);
            stage.getScene().setRoot(checkers.getRootPane());
        });
        HBox hbox = new HBox();
        hbox.getChildren().addAll(singlePlayer,mulitPlayer);
        borderPane.setCenter(hbox);
        javafx.scene.control.MenuItem exitApp = new javafx.scene.control.MenuItem("Exit");
        exitApp.setOnAction(event -> {
            System.exit(0);
        });
        javafx.scene.control.MenuItem backApp = new javafx.scene.control.MenuItem("Back to Menu");
        backApp.setOnAction(event -> {
            stage.setTitle("cs1302-arcade!");
            ArcadeMenu arcadeMenu = new ArcadeMenu(stage);
            stage.getScene().setRoot(arcadeMenu.getRootPane());
        });
        javafx.scene.control.Menu menu = new javafx.scene.control.Menu("File");
        menu.getItems().addAll(backApp, exitApp);
        javafx.scene.control.MenuBar menuBar = new MenuBar();
        menuBar.getMenus().addAll(menu);
        borderPane.setTop(menuBar);
    }
    public Pane getRootPane(){
        return borderPane;
    }

}