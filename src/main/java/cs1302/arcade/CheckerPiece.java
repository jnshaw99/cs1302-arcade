package cs1302.arcade;

import javafx.scene.shape.Circle;
import javafx.scene.paint.Color;
//possible colors of the piece locations
enum PieceColor{BLACK, WHITE, CLEAR}

/*
 *Represents each indivdual piece on the board.
 *Pieces don't move on the board they only change color to fit their new state
 *@inheirtDoc Circle
 */
public class CheckerPiece extends Circle {

    private int xPos;
    private int yPos;
    private PieceColor color;
    //static variables are needed for content of game
    public static boolean isPressed=false;
    public static PieceColor whoseTurn=PieceColor.WHITE;
    public static int indexOfSelected=-1;
    public static PieceColor colorOfSelected=PieceColor.CLEAR;
    public static boolean selectedIsKing=false;
    boolean isKing=false;
    public static int blackCount=0;
    public static int whiteCount=0;

    /*
     *Constructor for each checker piece
     *@param x x position of piece
     *@param y y position of piece
     *@param setting default color of piece
     */
    public CheckerPiece(int x, int y, PieceColor setting){
	//radius of 20
        super(20);
        xPos=x;
        yPos=y;
        color=setting;
	//setting piece color and shade
        if(color==PieceColor.BLACK){
            setFill(Color.GRAY);
        }
        else if(color==PieceColor.WHITE){
            setFill(Color.LINEN);
        }
        else
            setFill(Color.TRANSPARENT);
    }

    /*
     *Returns the pieces x position
     */
    public int getxPos(){return xPos;}
    /*
     *Returns the pieces y position
     */
    public int getyPos() {return yPos;}
    
    /*
     *toggles whether the piece is a king piece
     */
    public void toggleKing(){
	isKing=!isKing;
    }
    /*
     *returns whether the piece is a king piece
     */
    public boolean getIsKing(){
	return isKing;
    }
    /*
     *sets the color of the location
     *@param newColor the new color the piece will become
     */
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
    /*
     *returns the color of the piece
     */
    public PieceColor getColor(){
        return color;
    }
}