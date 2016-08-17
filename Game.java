import java.util.*;
import processing.core.*;

public class Game extends PApplet {
	int score;
	int speedConstant;
	int timeCount;
	final int KEY_SPACE = 32;
	double x = 10;
	double y = 10;
	int startX;
	int startY;
	int rectWidth;
	int rectHeight;
	Player player;
	int passCount;
	Controller controller;
	GameSetup gS;
	int colorValue;
	int bulletRadius;
	int width1;
	int height1;
	
	public void setup() {
		
		size(400, 400);
		gS = new GameSetup(x,y,width,height,player);
		//changes
		width1 = width;
		height1 = height;
		colorValue = 0;
		score = 0;
		bulletRadius = (int)(((width + height) / 2) / ((x + y) / 2)) / 2;
		startX = (int) (width / 2);
		startY = (int) ((height / 2) + height / y / 2);
		speedConstant = (int)((height + width) / 80 / x);
		player = new Player(new Location(startX, startY),speedConstant);
		controller = new Controller(player,gS);
		stroke(10, 100, 40);
		gS.setSquares(0);
		gS.setupTokens();
		gS.setEnemies(4);
		frameRate(60);
	}

	public void draw() {
		speedConstant = (int)((height + width) / 80 / x);
		rectWidth = (int) (width / x);
		rectHeight = (int) (height / y);
		

		controller.checkKeyboard((int)x,(int)y,width,height);
		if (width1 != width || height1 != height) {
            gS.resetLocations(width,height,width1,height1);
            width1 = width;
            height1 = height;
        }
		player = controller.setPlayer();
		gridDraw();
		tokenDraw();
		bulletDraw();
		enemyDraw();
		playerDraw();
		
		timeCount++;
	}
	
	public void gridDraw() {
		for (int row = 0; row < x; row++) {
			for (int col = 0; col < y; col++) {
				rectMode(CORNER);
				if (gS.getMaps()[gS.getMapLoc()][row][col]) {
					fill(0, 140, 100);
				}
				else {
					fill(0);
				}
				rect((int) ((row) * (width / x)), (int) ((col) * (height / y)),
						(int) ((width / x)), (int) ((height / y)));
				// rect((int)((row) * (width / x)), (int)((col) * (height / y)),
				// rectWidth, rectHeight);

				
			}
		}
	}
	
	public void bulletDraw() {
		for(int a = gS.getBullets().size() - 1; a >= 0; a--) {
			Bullet b = gS.getBullets().get(a);
			//changes
			//if(!bulletCollisionCheck(b,a)) { 
				
				b.move();
				//draw bullet
				fill(255 - colorValue);
				ellipse(b.getLocation().getRow(),b.getLocation().getCol(), bulletRadius, bulletRadius);
			//}
			
		}
		
	}

	public void tokenDraw() {
		for (int a = gS.getTokens().size() - 1; a >= 0; a--) {
			Token t = gS.tokens.get(a);
			if (t.getMap() == gS.getMapLoc()) {
				//draw token
				if(t.getTokenType().equals("MainToken")) {
					//changes
					if (colorValue > 255) {
						colorValue = 0;
					}
					else {
						colorValue += 5; 
					}
					fill(colorValue);
				}
				if(t.getTokenType().equals("BulletToken")){
					fill(15);
				}
				if(t.getTokenType().equals("HealthToken")) {
					fill(60,240,240);
				}
				//changes
				Character s = t.collisionCheck(player, (rectWidth / 2 + rectHeight / 2) / 2);
				if (s instanceof Player) {
					//check type and act
					if(t.getTokenType().equals("MainToken")) {
						
					}
					if(t.getTokenType().equals("BulletToken")){
						
					}
					if(t.getTokenType().equals("HealthToken")) {
						
					}
					//removeToken
					//gS.removeToken(a);
				}
				ellipse(t.getLocation().getRow(),t.getLocation().getCol(), rectWidth / 2, rectHeight / 2);
			}
		}
	}
	
	public void enemyDraw() {
		for (int e = gS.getEnemies().size() - 1; e >= 0; e--) {
			Enemy a = gS.getEnemies().get(e);
			a.setPlayerLoc(player.getLocation());
			if (a.getHealth() <= 0) {
				gS.removeEnemy(e);
				player.gainExp(a.getLevel());
				score = score + (a.getLevel() * 10);
			}
			if (a.getLevel() == 1) {
				fill(200,220,240);
			}
			else if (a.getLevel() == 2) {
				fill(30,40,150);
			}
			else if (a.getLevel() == 3) {
				fill(140,20,40);
			}
			a.move();
			
			//temporary
			int enRadius = rectWidth / 4;
			triangle(a.getLocation().getRow(),a.getLocation().getCol() - rectHeight / 3,
					a.getLocation().getRow() - rectWidth / 3,a.getLocation().getCol() + rectHeight / 4,
					a.getLocation().getRow() + rectWidth / 3,a.getLocation().getCol() + rectHeight / 4);
			//ellipse(a.getLocation().getRow(),a.getLocation().getCol(), rectWidth / 2, rectHeight / 2);
			
			//changes
			Character s = a.collisionCheck(player,enRadius);
			if (s instanceof Player) {
				//decrease player health
				//player.changeHealth(-1 * a.getDamage());
				//move player back in enemy direction by certain enemy speed
				//check boundaries by using the statements in the controller
				//
			}
		}
	}
	
	public void playerDraw() {
		//player color
		fill(30,100,200);
		rectMode(CENTER);
		rect(player.getLocation().getRow(), player.getLocation().getCol(),
				rectWidth / 2, rectHeight / 2);
	}
	public void keyPressed() {
		if (keyCode == KEY_SPACE && !controller.getKeys()[keyCode]) {
			//player.changeNumBullets(-1);
			Location bulletLocation = new Location(player.getLocation().row,player.getLocation().getCol());
			//fire
			if (player.getNumBullets() > 0) {
				
				gS.addBullet((new Bullet(bulletLocation,controller.getAngle(),player.getLevel(),speedConstant)));
				
			}
			else {
				System.out.println("require");
			}
			//System.out.println(gS.getBullets().size());
		}
			
		if (keyCode < controller.getKeys().length) {
			// println("Key pressed == " + keyCode);
			controller.setKeys(keyCode, true);
		}
	}

	public void keyReleased() {
		if (keyCode < controller.getKeys().length) {
			// println("Key released == " + keyCode);
			controller.setKeys(keyCode,false);
			
		}
	}
	/*
	//use mapLoc to check which map and use if statements to decide where to go
	public void boundaryCheck() {
		int xLoc;
		int yLoc;
		if(player.getLocation().getRow() - rectWidth / 4 < 0) {
			println("out left");
			xLoc = (int)((width - rectWidth / 4) / (width / x)) ;
			yLoc = (int)(player.getLocation().getCol() / (height / y));
			if (gS.getMaps()[gS.getMapLoc()][xLoc][yLoc]) {
				player.moveTo(width - rectWidth / 4, player.getLocation().getCol());
			}
			else {
				player.moveTo(0 + rectWidth / 4, player.getLocation().getCol());
			}
		}
		
		if(player.getLocation().getRow() + rectWidth / 4 > width) {
			println("out right");
			xLoc = (int)((0 + rectWidth / 4) / (width / x));
			yLoc = (int)(player.getLocation().getCol() / (height / y));
			if (gS.getMaps()[gS.getMapLoc()][xLoc][yLoc]) {
				player.moveTo(0 + rectWidth / 4, player.getLocation().getCol());
			}
			else {
				player.moveTo(width - rectWidth / 4, player.getLocation().getCol());
			}
		}
		
		if(player.getLocation().getCol() - rectHeight / 4 < 0) {
			println("out up");
			if (gS.getMapLoc() == 0) {
				gS.setSquares(1);
				gS.setEnemies(4);
			}
			xLoc = (int)(player.getLocation().getRow() / (width / x));
			yLoc = (int)((height - rectHeight / 4) / (height / y));
			if (gS.getMaps()[gS.getMapLoc()][xLoc][yLoc]) {
				player.moveTo(player.getLocation().getRow(), height - rectHeight / 4);
			}
			else {
				player.moveTo(player.getLocation().getRow(), 0 + rectHeight / 4);
			}
		}
		
		if(player.getLocation().getCol() + rectHeight / 4 > height) {
			println("out down");
			if (gS.getMapLoc() == 1) {
				gS.setSquares(0);
				gS.setEnemies(3);
			}
			xLoc = (int)(player.getLocation().getRow() / (width / x));
			yLoc = (int)((0 + rectHeight / 4) / (height / y));
			if (gS.getMaps()[gS.getMapLoc()][xLoc][yLoc]) {
				player.moveTo(player.getLocation().getRow(), 0 + rectHeight / 4);
			}
			else {
				player.moveTo(player.getLocation().getRow(), height - rectHeight / 4);
			}
		}
	}
	*/
	
	//changes
	public boolean bulletCollisionCheck(Bullet b,int a) {
		
		Character s = null;
		boolean collision = false;
		for (Enemy e: gS.getEnemies()) {
			//checks enemies
			s = b.collisionCheck(e,bulletRadius);
		}
		if (s instanceof Enemy) {
			//gS.removeBullet(a);
			collision = true;
		}
		//checks walls
		if (b.collisionCheck(bulletRadius, gS.getMaps(), gS.getMapLoc())) {
			//gS.removeBullet(a);
			collision = true;
		}
		return collision;
	}
}
