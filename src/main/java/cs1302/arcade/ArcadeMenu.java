package cs1302.arcade;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class ArcadeMenu {

    private BorderPane borderPane = new BorderPane();

    public ArcadeMenu(Stage stage) {
        stage.setHeight(480);
        stage.setWidth(640);
        HBox group = new HBox();
        Rectangle r = new Rectangle(320, 480, Color.BLACK);
        r.setX(0);
        r.setY(0);
        group.getChildren().add(r);
        Rectangle r2 = new Rectangle(320, 480, Color.WHITE);
        r2.setX(320);
        r2.setY(0);
        group.getChildren().add(r2);

        r.setOnMouseClicked(event -> {
            System.out.println("Space Invaders");
        });
        r2.setOnMouseClicked(event -> {
            stage.setTitle("Checkers Game");
            CheckersMenu checkersMenu = new CheckersMenu(stage);
            stage.getScene().setRoot(checkersMenu.getRootPane());
        });
        MenuItem exitApp = new MenuItem("Exit");
        exitApp.setOnAction(event -> {
            System.exit(0);
        });
        Menu menu = new Menu("File");
        menu.getItems().addAll( exitApp);
        MenuBar menuBar = new MenuBar();
        menuBar.getMenus().addAll(menu);
        borderPane.setTop(menuBar);
        borderPane.setCenter(group);
    }
    public Pane getRootPane() {
        return borderPane;
    }

}