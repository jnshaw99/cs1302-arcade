package cs1302.arcade;

import javafx.scene.control.MenuBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Menu;
import javafx.scene.text.Text;

import java.awt.*;

public class CheckersMenu {
    private BorderPane borderPane = new BorderPane();
    public CheckersMenu(Stage stage){
        Button singlePlayer = new Button("Play!");
        singlePlayer.setOnAction(event -> {
            Checkers checkers = new Checkers(stage);
            stage.getScene().setRoot(checkers.getRootPane());
        });
	Text intructions = new Text("Multiplyer. Click a piece to select(highlighted green), then press where to move to\n"
				    +"To indicate a \"king\" piece the white will change to yellow and grey to brown.\n"
				    +"Good luck!\n");
        VBox vbox = new VBox();
        vbox.getChildren().addAll(singlePlayer, intructions);
        borderPane.setCenter(vbox);
        MenuItem exitApp = new MenuItem("Exit");
        exitApp.setOnAction(event -> {
            System.exit(0);
        });
        MenuItem backApp = new MenuItem("Back to Menu");
        backApp.setOnAction(event -> {
            stage.setTitle("cs1302-arcade!");
            ArcadeMenu arcadeMenu = new ArcadeMenu(stage);
            stage.getScene().setRoot(arcadeMenu.getRootPane());
        });
        Menu menu = new Menu("File");
        menu.getItems().addAll(backApp, exitApp);
        MenuBar menuBar = new MenuBar();
        menuBar.getMenus().addAll(menu);
        borderPane.setTop(menuBar);
    }
    public Pane getRootPane(){
        return borderPane;
    }

}