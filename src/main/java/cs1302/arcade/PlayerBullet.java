  package cs1302.arcade;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class PlayerBullet extends ImageView{
    public boolean isLive = true;
    public int localBullet=0;
    public PlayerBullet(Image image, double xpos){
	super(image);
	setX(xpos+35.0);
	setY(450);
	setFitWidth(20);
	setFitHeight(20);
    }

}