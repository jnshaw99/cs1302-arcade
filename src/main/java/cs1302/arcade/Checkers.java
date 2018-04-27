package cs1302.arcade;


import javafx.scene.control.MenuItem;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.control.MenuBar;
import javafx.scene.control.Menu;
import javafx.scene.paint.Color;
import java.util.ArrayList;

public class Checkers {

    private BorderPane borderPane = new BorderPane();
    private GridPane gridPane = new GridPane();
    private ArrayList<CheckerPiece> theBoard = new ArrayList<>();
    private int i;
    private int j;

    public Checkers(Stage stage, boolean singlePlayer){
        stage.setHeight(640);
        stage.setWidth(590);
        gridPane.getStyleClass().add("gridpane");
        setUpBoard();
        borderPane.setCenter(gridPane);
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
    public void setUpBoard(){
        for(i=0; i<8;i++){
            for(j=0;j<8;j++){
                if(i==0 || i==1)
                    theBoard.add(new CheckerPiece(i,j,PieceColor.BLACK));
                else if(i==6 || i==7)
                    theBoard.add(new CheckerPiece(i,j,PieceColor.WHITE));
                else
                    theBoard.add(new CheckerPiece(i,j,PieceColor.CLEAR));
                CheckerPiece temp = theBoard.get(i*8+j);
                temp.setOnMouseClicked((event) -> {
                    if(CheckerPiece.isPressed){

                    }else{
                        if(temp.getColor()==CheckerPiece.whoseTurn) {
                            CheckerPiece.isPressed = true;
                            temp.setColor(Color.GREEN);
                        }
                    }

                });
                gridPane.add(theBoard.get((i*8)+j),j,i);
            }
        }

    }
}