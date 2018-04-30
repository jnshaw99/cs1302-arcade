package cs1302.arcade;

import javafx.scene.shape.Circle;
import javafx.scene.paint.Color;

enum PieceColor{BLACK, WHITE, CLEAR}

public class CheckerPiece extends Circle {

    private int xPos;
    private int yPos;
    private PieceColor color;
    public static boolean isPressed=false;
    public static PieceColor whoseTurn=PieceColor.WHITE;
    public static int indexOfSelected=-1;
    public static PieceColor colorOfSelected=PieceColor.CLEAR;
    public static boolean selectedIsKing=false;
    boolean isKing=false;
    public static int blackCount=0;
    public static int whiteCount=0;

    public CheckerPiece(int x, int y, PieceColor setting){
        super(20);
        xPos=x;
        yPos=y;
        color=setting;
        if(color==PieceColor.BLACK){
            setFill(Color.GRAY);
        }
        else if(color==PieceColor.WHITE){
            setFill(Color.LINEN);
        }
        else
            setFill(Color.TRANSPARENT);
    }

    public void setxPos(int xPos) {
        this.xPos = xPos;
    }

    public void setyPos(int yPos) {
        this.yPos = yPos;
    }

    public int getxPos(){return xPos;}

    public int getyPos() {return yPos;}
    
    public void toggleKing(){
	isKing=!isKing;
    }
    
    public boolean getIsKing(){
	return isKing;
    }

    public void setColor(PieceColor newColor){
	color=newColor;
	if(color==PieceColor.CLEAR)
	    setFill(Color.TRANSPARENT);
	else if(color==PieceColor.WHITE && isKing)
	    setFill(Color.YELLOW);
	else if(color==PieceColor.BLACK && isKing)
	    setFill(Color.BROWN);
	else if(color==PieceColor.WHITE)
	    setFill(Color.LINEN);
	else if(color==PieceColor.BLACK)
	    setFill(Color.GRAY);
    }

    public PieceColor getColor(){
        return color;
    }
}