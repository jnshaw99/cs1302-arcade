package cs1302.arcade;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.scene.layout.Pane;
import java.util.ArrayList;
import java.util.Random;

public class SpaceInvaders {

    private BorderPane borderPane = new BorderPane();
    GridPane enemies = new GridPane();
    Image alienImage;
    int enemiesPos=0;
    int counter;
    int gameLevel=1;
    ArrayList<Enemy> enemyArray = new ArrayList<>();
    
    
    public SpaceInvaders(Stage stage) {
        stage.setHeight(635);
        stage.setWidth(800);
	borderPane.setStyle("-fx-background-color: #000000;");
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
        alienImage = new Image("aliens.jpg");
        setUpEnemies();
        enemies.getStyleClass().add("enemies");
	Pane thePane = new Pane();
	thePane.getChildren().add(enemies);
	borderPane.setCenter(thePane);
	Image playerImage = new Image("player.gif", 100, 150, false, false);
	Player player = new Player(playerImage);
	borderPane.setBottom(player);
	EnemyBullet enemyBullet = new EnemyBullet(new Image("enemyBullet.png"),0,0);
	KeyFrame keyframeBullet = new KeyFrame(Duration.seconds(.1), new EventHandler<ActionEvent>(){
		public void handle(ActionEvent e){
		    Random rng=new Random();
		    int randy= rng.nextInt(55);
		    while(!enemyBullet.isLive){
			if(!enemyArray.get(randy).isDead){
			    enemyBullet.localBullet=0;
			    enemyBullet.setX(enemyArray.get(randy).getBoundsInParent().getMinX()+Enemy.xTrans);
			    enemyBullet.setY(enemyArray.get(randy).getBoundsInParent().getMaxY());
			    enemyBullet.isLive=true;
			    break;
			}
		    }
		    enemyBullet.localBullet+=15;
		    enemyBullet.setTranslateY(enemyBullet.localBullet);
		    if(enemyBullet.getBoundsInParent()
		       .intersects(player.getBoundsInParent().getMinX()+player.getTranslateX(),
						   player.getBoundsInParent().getMinY(),
						   player.getBoundsInParent().getWidth(),
						   player.getBoundsInParent().getHeight())
		       && enemyBullet.isLive || enemyBullet.localBullet>=450){
			enemyBullet.isLive=false;
		    }
		    if(enemiesPos==0){
			enemies.setTranslateX(25);
			Enemy.xTrans = 25;
			enemiesPos++;
		    }else if(enemiesPos==1){
			enemies.setTranslateX(50);
			Enemy.xTrans=50;
			enemiesPos++;
		    }else if(enemiesPos==2){
			enemies.setTranslateX(75);
			Enemy.xTrans=75;
			enemiesPos++;
		    }else if(enemiesPos==3){
			enemies.setTranslateX(50);
			Enemy.xTrans = 50;
			enemiesPos++;
		    }else if(enemiesPos==4){
			enemies.setTranslateX(25);
			Enemy.xTrans = 25;
			enemiesPos++;
		    }else if(enemiesPos==5){
			enemies.setTranslateX(0);
			Enemy.xTrans = 0;
			enemiesPos=0;
		    }
		    
		}
	    });
	Timeline timelineBullet = new Timeline();
	timelineBullet.setCycleCount(Timeline.INDEFINITE);
	timelineBullet.getKeyFrames().add(keyframeBullet);
	timelineBullet.play();
	thePane.getChildren().add(enemyBullet);
	counter=50;
	ArcadeApp.scene.setOnKeyPressed(event -> {
		switch(event.getCode()){
                case A:
		    counter-=10;
                    player.setTranslateX(counter);
                    break;
                case D:
		    counter+=10;
                    player.setTranslateX(counter);
                    break;
		case SPACE:
		    Image bullet = new Image("gameBullet.gif");
		    PlayerBullet playerBullet = new PlayerBullet(bullet, player.getTranslateX());
		    KeyFrame keyFrame = new KeyFrame(Duration.seconds(.1), new EventHandler<ActionEvent>(){
			    public void handle(ActionEvent e){
				playerBullet.localBullet-=5;
				playerBullet.setTranslateY(playerBullet.localBullet);
				for(int i = 0; i < 55; i++){
				    if(playerBullet.getBoundsInParent()
				       .intersects(enemyArray.get(i).getBoundsInParent().getMinX()+Enemy.xTrans,
						   enemyArray.get(i).getBoundsInParent().getMinY(),
						   enemyArray.get(i).getBoundsInParent().getWidth(),
						   enemyArray.get(i).getBoundsInParent().getHeight())
				       && !enemyArray.get(i).isDead && playerBullet.isLive){
					enemies.getChildren().remove(enemyArray.get(i));
					thePane.getChildren().remove(playerBullet);
					enemyArray.get(i).isDead = true;
					playerBullet.isLive = false;
						player.score += 1;
				    }//if
				}//for
				if(playerBullet.localBullet>=450){
				    thePane.getChildren().remove(playerBullet);
				}
			    }
			});
		    Timeline timeline = new Timeline();
		    timeline.setCycleCount(Timeline.INDEFINITE);
		    timeline.getKeyFrames().add(keyFrame);
		    timeline.play();

		    
		    thePane.getChildren().add(playerBullet);
		}
	    });
    }

    public void setUpEnemies() {
        for(int i=0; i<5; i++){
            for(int j=0;j<11;j++){
		Enemy temp = new Enemy(alienImage);
		enemyArray.add(temp);
                enemies.add(enemyArray.get((i*11)+j), j, i);
            }

        }
    }

    public Pane getRootPane() {
        return borderPane;
    }
}
