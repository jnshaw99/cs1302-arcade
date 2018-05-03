package cs1302.arcade;

import javafx.scene.image.ImageView;
import javafx.scene.image.Image;

public class Enemy extends ImageView {
    public static int xTrans = 0;
    public int xPos = 0;
    public int yPos = 0;
    public boolean isDead=false;
    public static int numAliens=55;

    public Enemy(Image image){
        super(image);

    }
}