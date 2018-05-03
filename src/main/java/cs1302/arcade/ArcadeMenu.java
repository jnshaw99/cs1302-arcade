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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/*
 *Arcade is the home menu of the arcade games.
 *Select each side for the games.
 *Left side is Space Invaders and Right is American Checkers
 */
public class ArcadeMenu {

    //pane for the menu
    private BorderPane borderPane = new BorderPane();

    /*
     *Constructor for Arcade Menu.
     *Sets up the stage and borderpane
     *@param The stage for the GUI
     */
    public ArcadeMenu(Stage stage) {
        stage.setHeight(320);
        stage.setWidth(480);
        HBox group = new HBox();
	Image spaceLogo = new Image("spaceInvaders.jpg");
        Image checkersLogo = new Image("checkerLogo.png");
        ImageView space = new ImageView(spaceLogo);
	space.setFitHeight(160);
	space.setFitWidth(240);
	ImageView checkers = new ImageView(checkersLogo);
	checkers.setFitHeight(160);
	checkers.setFitWidth(240);
        Rectangle r = new Rectangle(320, 480, Color.BLACK);
        space.setX(0);
        space.setY(0);
        group.getChildren().add(space);
        Rectangle r2 = new Rectangle(320, 480, Color.WHITE);
        checkers.setX(320);
        checkers.setY(0);
        group.getChildren().add(checkers);
	//setting the icons to open the other games
        space.setOnMouseClicked(event -> {
            stage.setTitle("Space Invaders Game");
            SpaceInvaders spaceinvaders = new SpaceInvaders(stage);
            stage.getScene().setRoot(spaceinvaders.getRootPane());
        });
        checkers.setOnMouseClicked(event -> {
            stage.setTitle("Checkers Game");
            CheckersMenu checkersMenu = new CheckersMenu(stage);
            stage.getScene().setRoot(checkersMenu.getRootPane());
        });
	//setting up menu to exit game
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
    //gives set up pane to source
    public Pane getRootPane() {
        return borderPane;
    }

}