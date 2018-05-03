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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Alert;

/*
 *The application for the Checkers content.
 *Creates the board for the checkers game and the grid to put them on
 */
public class Checkers {
    //the rootpane of application
    private BorderPane borderPane = new BorderPane();
    //the board
    private GridPane gridPane = new GridPane();
    //to store the content of the board
    private ArrayList<CheckerPiece> theBoard = new ArrayList<>();
    private int i;
    private int j;
    /*
     *Constructor of the content board
     */
    public Checkers(Stage stage){
        stage.setHeight(635);
        stage.setWidth(600);
        gridPane.getStyleClass().add("gridpane");
        setUpBoard();
        borderPane.setCenter(gridPane);
	//Menu adds ability to exit app or go back to menu
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
    /*
     *Returns rootpane of this gui
     */
    public Pane getRootPane(){
        return borderPane;
    }
    /*
     *Checks if the piece can move to a position.
     *return true if can.
     *@param x height position of desired location
     *@param y width position of desired location
     *@param oldColor gives the piece color of the piece being moved
     */
    public boolean canMoveTo(int x, int y, PieceColor oldColor){
	//finds the index of the selected
        int pieceX=CheckerPiece.indexOfSelected/8;
        int pieceY=CheckerPiece.indexOfSelected%8;
	//checking movement positions for old color
        if(oldColor==PieceColor.WHITE){
	    if((x-pieceX)==-1 && (y-pieceY)==-1)
                return true;
            if((x-pieceX)==-1 && (y-pieceY)==1)
                return true;
	    //for if the piece is capturing
	    if((x-pieceX)==-2&&(y-pieceY)==-2 && (theBoard.get((pieceX-1)*8+(pieceY-1)).getColor()==PieceColor.BLACK)){
		theBoard.get((pieceX-1)*8+(pieceY-1)).setColor(PieceColor.CLEAR);
		CheckerPiece.blackCount--;
		return true;
	    }
	    if((x-pieceX)==-2&&(y-pieceY)==2 && (theBoard.get((pieceX-1)*8+(pieceY+1)).getColor()==PieceColor.BLACK)){
		theBoard.get((pieceX-1)*8+(pieceY+1)).setColor(PieceColor.CLEAR);
		CheckerPiece.blackCount--;
		return true;
	    }		
	    //content for if the piece is black
        }else if(oldColor==PieceColor.BLACK){
	    if((x-pieceX)==1 && (y-pieceY)==1)
                return true;
	    if((x-pieceX)==1 && (y-pieceY)==-1)
                return true;
	    if((x-pieceX)==2&&(y-pieceY)==2 && (theBoard.get((pieceX+1)*8+(pieceY+1)).getColor()==PieceColor.WHITE)){
		theBoard.get((pieceX+1)*8+(pieceY+1)).setColor(PieceColor.CLEAR);
		CheckerPiece.whiteCount--;
		return true;
	    }
	    if((x-pieceX)==2&&(y-pieceY)==-2 && (theBoard.get((pieceX+1)*8+(pieceY-1)).getColor()==PieceColor.WHITE)){
		theBoard.get((pieceX+1)*8+(pieceY-1)).setColor(PieceColor.CLEAR);
		CheckerPiece.whiteCount--;
		return true;
	    }		
	}
	//extra movement for king pieces
	if(CheckerPiece.selectedIsKing){
	    if(oldColor==PieceColor.WHITE){
		if((x-pieceX)==1 && (y-pieceY)==1)
		    return true;
		if((x-pieceX)==1 && (y-pieceY)==-1)
		    return true;
		if((x-pieceX)==2&&(y-pieceY)==2 && (theBoard.get((pieceX+1)*8+(pieceY+1)).getColor()==PieceColor.BLACK)){
		    theBoard.get((pieceX+1)*8+(pieceY+1)).setColor(PieceColor.CLEAR);
		    CheckerPiece.blackCount--;
		    return true;
		}
		if((x-pieceX)==2&&(y-pieceY)==-2 && (theBoard.get((pieceX+1)*8+(pieceY-1)).getColor()==PieceColor.BLACK)){
		    theBoard.get((pieceX+1)*8+(pieceY-1)).setColor(PieceColor.CLEAR);
		    CheckerPiece.blackCount--;
		    return true;
		}		
	    }else if(oldColor==PieceColor.BLACK){
		if((x-pieceX)==-1 && (y-pieceY)==-1)
		    return true;
		if((x-pieceX)==-1 && (y-pieceY)==1)
		    return true;
		if((x-pieceX)==-2&&(y-pieceY)==-2 && (theBoard.get((pieceX-1)*8+(pieceY-1)).getColor()==PieceColor.WHITE)){
		    theBoard.get((pieceX-1)*8+(pieceY-1)).setColor(PieceColor.CLEAR);
		    CheckerPiece.whiteCount--;
		    return true;
		}
		if((x-pieceX)==-2&&(y-pieceY)==2 && (theBoard.get((pieceX-1)*8+(pieceY+1)).getColor()==PieceColor.WHITE)){
		    theBoard.get((pieceX-1)*8+(pieceY+1)).setColor(PieceColor.CLEAR);
		    CheckerPiece.whiteCount--;
		    return true;
		}		
	    }
	}
	//if none are true returns that it can't move there
	return false;
    }
    /*
     *If one piece wins it returns a winner's alert
     *@param winningColor the piece color that won the game
     */
    public void winner(PieceColor winningColor){
	Alert alert = new Alert(AlertType.INFORMATION);
	if(winningColor==PieceColor.WHITE)
	    alert.setContentText("Congrats! White won!");
	if(winningColor==PieceColor.BLACK)
	    alert.setContentText("Congrats! Black won!");
	alert.showAndWait();
    }
    /*
     *sets up the content of the board
     */
    public void setUpBoard(){
	//must reset static values in CheckerPiece in case a subsequent game
	CheckerPiece.isPressed=false;
	CheckerPiece.whoseTurn=PieceColor.WHITE;
	CheckerPiece.blackCount=12;
	CheckerPiece.whiteCount=12;
	//setting the initial board content
        for(i=0; i<8;i++){
            for(j=0;j<8;j++){
                if(((i==0 || i==2) && ((i*8+j)%2)==0)||(i==1 && ((i*8+j)%2)==1))
                    theBoard.add(new CheckerPiece(i,j,PieceColor.BLACK));
                else if(((i==5 || i==7)&&((i*8+j)%2)==1)||((i==6) && ((i*8+j)%2)==0))
                    theBoard.add(new CheckerPiece(i,j,PieceColor.WHITE));
                else
                    theBoard.add(new CheckerPiece(i,j,PieceColor.CLEAR));
                CheckerPiece temp = theBoard.get(i*8+j);
		//if a piece is pressed
                temp.setOnMouseClicked((event) -> {
			//if a piece is already selected
                    if(CheckerPiece.isPressed){
			//if it can move to position
			if(temp.getColor()==PieceColor.CLEAR && canMoveTo(temp.getxPos(), temp.getyPos(), CheckerPiece.colorOfSelected)){
			    theBoard.get(CheckerPiece.indexOfSelected)
				.setColor(PieceColor.CLEAR);
			    //makes sure the new position is set to king
			    if(CheckerPiece.selectedIsKing){
				theBoard.get(CheckerPiece.indexOfSelected).toggleKing();
				temp.toggleKing();
			    }else if(temp.getxPos()==0 || temp.getxPos()==7)
				temp.toggleKing();
			    temp.setColor(CheckerPiece.whoseTurn);
			    if(CheckerPiece.whoseTurn==PieceColor.WHITE)
				CheckerPiece.whoseTurn=PieceColor.BLACK;
			    else if(CheckerPiece.whoseTurn==PieceColor.BLACK)
				CheckerPiece.whoseTurn=PieceColor.WHITE;
			    CheckerPiece.isPressed=!CheckerPiece.isPressed;
			    //makeing sure that the piece stays king
			}else{
			    if(CheckerPiece.colorOfSelected==PieceColor.WHITE && CheckerPiece.selectedIsKing)
				theBoard.get(CheckerPiece.indexOfSelected).setFill(Color.YELLOW);
			    if(CheckerPiece.colorOfSelected==PieceColor.BLACK && CheckerPiece.selectedIsKing)
				theBoard.get(CheckerPiece.indexOfSelected).setFill(Color.BROWN);
			    if(CheckerPiece.colorOfSelected==PieceColor.WHITE)
				theBoard.get(CheckerPiece.indexOfSelected).setFill(Color.LINEN);
			    if(CheckerPiece.colorOfSelected==PieceColor.BLACK)
				theBoard.get(CheckerPiece.indexOfSelected).setFill(Color.GRAY);
			    CheckerPiece.isPressed=false;
			}
			//if no piece is selected it selects a piece
                    }else{
                        if(temp.getColor()==CheckerPiece.whoseTurn) {
                            CheckerPiece.isPressed = true;
                            temp.setFill(Color.GREEN);
			    CheckerPiece.indexOfSelected=(temp.getxPos()*8+temp.getyPos());
			    CheckerPiece.colorOfSelected=temp.getColor();
			    if(temp.getIsKing())
				CheckerPiece.selectedIsKing=true;
			    else
				CheckerPiece.selectedIsKing=false;
			    
			}
                    }
		    //checks for a winner
		    if(CheckerPiece.whiteCount<=0)
			winner(PieceColor.BLACK);
		    if(CheckerPiece.blackCount<=0)
			winner(PieceColor.WHITE);
		    });
		//adds that piece to board
                gridPane.add(theBoard.get((i*8)+j),j,i);
            }
        }

    }
}