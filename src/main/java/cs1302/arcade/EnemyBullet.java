package cs1302.arcade;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class EnemyBullet extends ImageView{
    
    public boolean isLive=true;
    public int localBullet;
    public EnemyBullet(Image image, double xpos, double ypos){
	super(image);
	isLive=true;
	localBullet=0;
	setX(xpos+12.5);
	setY(ypos);
	setFitWidth(20);
	setFitHeight(20);
    }
}