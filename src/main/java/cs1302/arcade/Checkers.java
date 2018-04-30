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

public class Checkers {

    private BorderPane borderPane = new BorderPane();
    private GridPane gridPane = new GridPane();
    private ArrayList<CheckerPiece> theBoard = new ArrayList<>();
    private int i;
    private int j;

    public Checkers(Stage stage, boolean singlePlayer){
        stage.setHeight(635);
        stage.setWidth(600);
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
    public boolean canMoveTo(int x, int y, PieceColor oldColor){
        int pieceX=CheckerPiece.indexOfSelected/8;
        int pieceY=CheckerPiece.indexOfSelected%8;
        if(oldColor==PieceColor.WHITE){
	    if((x-pieceX)==-1 && (y-pieceY)==-1)
                return true;
            if((x-pieceX)==-1 && (y-pieceY)==1)
                return true;
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
	return false;
    }

    public void winner(PieceColor winningColor){
	Alert alert = new Alert(AlertType.INFORMATION);
	if(winningColor==PieceColor.WHITE)
	    alert.setContentText("Congrats! White won!");
	if(winningColor==PieceColor.BLACK)
	    alert.setContentText("Congrats! Black won!");
	alert.showAndWait();
    }

    public void setUpBoard(){
	CheckerPiece.isPressed=false;
	CheckerPiece.whoseTurn=PieceColor.WHITE;
	CheckerPiece.blackCount=12;
	CheckerPiece.whiteCount=12;
        for(i=0; i<8;i++){
            for(j=0;j<8;j++){
                if(((i==0 || i==2) && ((i*8+j)%2)==0)||(i==1 && ((i*8+j)%2)==1))
                    theBoard.add(new CheckerPiece(i,j,PieceColor.BLACK));
                else if(((i==5 || i==7)&&((i*8+j)%2)==1)||((i==6) && ((i*8+j)%2)==0))
                    theBoard.add(new CheckerPiece(i,j,PieceColor.WHITE));
                else
                    theBoard.add(new CheckerPiece(i,j,PieceColor.CLEAR));
                CheckerPiece temp = theBoard.get(i*8+j);
                temp.setOnMouseClicked((event) -> {
                    if(CheckerPiece.isPressed){
			if(temp.getColor()==PieceColor.CLEAR && canMoveTo(temp.getxPos(), temp.getyPos(), CheckerPiece.colorOfSelected)){
			    theBoard.get(CheckerPiece.indexOfSelected)
				.setColor(PieceColor.CLEAR);
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
		    if(CheckerPiece.whiteCount<=0)
			winner(PieceColor.BLACK);
		    if(CheckerPiece.blackCount<=0)
			winner(PieceColor.WHITE);

                });
                gridPane.add(theBoard.get((i*8)+j),j,i);
            }
        }

    }
}