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
Character s;
boolean collected ;
boolean stop;
boolean delayed;
int count = 0;
int enemyCount = 0;
int addRedValue = 0;
boolean enter;
boolean boss;
int timer;
int bossRad = rectWidth;
int width1;
int height1;
int hitCount = 0;
boolean hit;
int playerCount = 0;
boolean gameOver;
boolean startGame;
 
public void setup() {
  size(400, 400);
  set();
 
  frameRate(60);
}
 
public void set() {
 
  width1 = width;
  height1 = height;
 
  //changes
  colorValue = 0;
  bulletRadius = (int)(((width + height) / 2) / ((x + y) / 2)) / 2 / 2;
  startX = (int) (width / 2);
  startY = (int) ((height / 2) + height / y / 2);
  speedConstant = (int)((height + width) / 80 / x);
  player = new Player(new Location(startX, startY), speedConstant);
  gS = new GameSetup(x, y, width, height, player);
  controller = new Controller(player, gS);
  stroke(10, 100, 40);
 
  gS.setSquares(0);
  gS.setupTokens();
  gS.setEnemies(4);
 
  score = 0;
  gameOver = false;
  startGame = true;
  hit = false;
  enter = true;
  boss = false;
  collected = false;
  stop = false;
  delayed = false;
  s = null;
  timer = 0;
}
 
public void draw() {
  if (startGame) {
    background(255);
    fill(0);
    textAlign(CENTER);
    text("Press any key to begin", width / 2, height / 2);
    if (keyPressed) {
      startGame = false;
    }
  }
  else if (!startGame && !gameOver) {
    if (width1 != width || height1 != height) {
      gS.resetLocations(width, height, width1, height1);
      width1 = width;
      height1 = height;
    }
    controller.resetPlayer(player);
    gS.setPlayer(player);
    speedConstant = (int)((height + width) / 80 / x);
    rectWidth = (int) (width / x);
    rectHeight = (int) (height / y);
 
    gridDraw();
    if (!delayed) {
      controller.checkKeyboard((int)x, (int)y, width, height);
      player = controller.setPlayer();
      tokenDraw();
      bulletDraw();
      enemyDraw();
      bossDraw();
    }
    playerDraw();
    if (!stop) {
      collected = checkUnlock();
    }
    if (gS.getMapLoc() == 3 && enter) {
      gS.resetEnemies();
      enter = false;
    }
    if (collected) {
      gS.changeMapBoolean(2, 4, 0);
      gS.changeMapBoolean(2, 5, 0);
      stop = true;
      collected = false;
    }
    if (gS.getMapLoc() == 3 && ((player.getLocation().convertToGrid(player.getLocation().getRow(), width, x) == 4  && player.getLocation().convertToGrid(player.getLocation().getCol() + rectHeight / 4, height, y) == 8) || (player.getLocation().convertToGrid(player.getLocation().getRow(), width, x) == 5  && player.getLocation().convertToGrid(player.getLocation().getCol() + rectHeight / 4, height, y) == 8))) {     
      delayed = true;
    }
    if (delayed && count <= 180) {
      count++;
    }
    if (delayed && count == 90) {
      player.changeNumBullets(player.getNumBullets());
      gS.changeMapBoolean(3, 4, 9);
      gS.changeMapBoolean(3, 5, 9);
    }
    if (delayed && count == 150) {
      gS.setBoss();
      boss = true;
    }
    if (count >= 180) {
      delayed = false;
    }
 
    if (checkEnemyRespawn()) {
      gS.setEnemies(3);
    }
 
    if (colorValue > 255) {
      colorValue = 0;
    }
    else {
      colorValue += 4;
    }
    if (hit) {
      hitCount++;
    }
    if (hitCount == 60) {
      hitCount = 0;
      hit = false;
    }
    timeCount++;
    textAlign(LEFT);
    text("Score: " + score, rectWidth / 9, height - rectHeight / 9);
    textAlign(RIGHT);
    text("Bullet: " + player.getNumBullets(), (int)(width / x * 10 - rectWidth / 9), (int)(height - rectHeight / 9));
  }
  else {
    background(0);
    fill(255);
    textAlign(CENTER);
    text("GAME OVER", width / 2, height / 2);
    text("Score: " + score, width/2, height/2 + 20);
    timer = 0;
 
    if (timer <= 120) {
      timer++;
    }
    if (keyPressed && timer >= 120) {
      timer = 0;
      set();
      gameOver = false;
    }
  }
}
 
public boolean checkEnemyRespawn() {
 
  if (timer >= 1200 && boss) {
    timer = 0;
    return true;
  }
  timer++;
  return false;
}
 
public boolean checkUnlock() {
  int count = 0;
  for (Token t: gS.getTokens()) {
    if (t instanceof MainToken) {
      count++;
    }
  }
  return count == 0;
}
 
public void bossDraw() {
  if (gS.getBoss().size() > 0) {
    Character b = gS.getBoss().get(0).collisionCheck(player, rectWidth, rectWidth, rectHeight);
    if (b instanceof Player) {
      hit = true;
      if (gS.getBoss().get(0).getDirection() == 0) {
        player.moveTo(player.getLocation().getRow(), player.getLocation().getCol() + gS.getBoss().get(0).getSpeed());
        controller.downMethod(1);
      }
      else if (gS.getBoss().get(0).getDirection() == 1) {
        player.moveTo(player.getLocation().getRow() + gS.getBoss().get(0).getSpeed(), player.getLocation().getCol());
        controller.rightMethod(1);
      }
      else if (gS.getBoss().get(0).getDirection() == 2) {
        player.moveTo(player.getLocation().getRow(), player.getLocation().getCol() - gS.getBoss().get(0).getSpeed());
 
        controller.upMethod(1);
      }
      else if (gS.getBoss().get(0).getDirection() == 3) {
        player.moveTo(player.getLocation().getRow() - gS.getBoss().get(0).getSpeed(), player.getLocation().getCol());
        controller.leftMethod(1);
      }
      //System.out.println(s.getHealth());
      //decrease player health
      if (hitCount == 0) {
        player.changeHealth(-1 * gS.getBoss().get(0).getDamage());
      }
      //move player back in enemy direction by certain enemy speed
      //check boundaries by using the statements in the controller
      //
    }
 
    if (gS.getBoss().get(0).getHealth() <= 0) {
      score += 500;
      gameOver = true;
    }
 
    if (!gameOver) {
      fill(255);
      textAlign(CENTER);
      text(gS.getBoss().get(0).getHealth(), gS.getBoss().get(0).getLocation().getRow(), gS.getBoss().get(0).getLocation().getCol() + rectHeight * 4 / 3);
    }
    if (gS.getBoss().get(0).getCollision()) {
      enemyCount++;
 
      if (enemyCount <= 120) {
        if (addRedValue <= 180) {
          addRedValue += 6;
        }
        else {
          addRedValue = 0;
        }
      }
      else {
        gS.getBoss().get(0).changeCollision();
        enemyCount = 0;
        addRedValue = 0;
      }
    }
    fill(addRedValue);
 
 
    ellipse(gS.getBoss().get(0).getLocation().getRow(), gS.getBoss().get(0).getLocation().getCol(), rectWidth * 2, rectHeight * 2);
    gS.getBoss().get(0).setPlayerLoc(player.getLocation());
 
    gS.getBoss().get(0).move();
  }
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
      if (gS.getMapLoc() == 2) {
        if (row == 4 && col == 0 || row == 5 && col == 0) {
          if (!gS.getMaps()[2][4][0] && !gS.getMaps()[2][5][0]) {
            fill(140, 0, 10);
          }
          else {
            fill(255);
          }
        }
      }
      rect((int) ((row) * (width / x)), (int) ((col) * (height / y)),
      (int) ((width / x)), (int) ((height / y)));
      // rect((int)((row) * (width / x)), (int)((col) * (height / y)),
      // rectWidth, rectHeight);
    }
  }
}
 
public void bulletDraw() {
  for (int a = gS.getBullets().size() - 1; a >= 0; a--) {
    Bullet b = gS.getBullets().get(a);
    //changes
    if (!bulletCollisionCheck(b, a)) {
 
      b.move();
      //draw bullet
      fill(255 - colorValue);
      ellipse(b.getLocation().getRow(), b.getLocation().getCol(), bulletRadius * 2, bulletRadius * 2);
    }
  }
}
 
public void tokenDraw() {
  for (int a = gS.getTokens().size() - 1; a >= 0; a--) {
    Token t = gS.tokens.get(a);
    if (t.getMapLoc() == gS.getMapLoc()) {
      //draw token
      if (t.getTokenType().equals("MainToken")) {
        //changes
        fill(colorValue);
      }
      if (t.getTokenType().equals("BulletToken")) {
        fill(15);
      }
      if (t.getTokenType().equals("HealthToken")) {
        fill(60, 240, 240);
      }
      //changes
      Character s = t.collisionCheck(player, (rectWidth / 2 + rectHeight / 2) / 2, rectWidth, rectHeight);
      if (s instanceof Player) {
        //check type and act
        if (t.getTokenType().equals("MainToken")) {
          score += player.getLevel() * 30;
        }
        if (t.getTokenType().equals("BulletToken")) {
          player.changeNumBullets(10);
        }
        if (t.getTokenType().equals("HealthToken")) {
          player.changeHealth(50);
        }
        //removeToken
        gS.removeToken(a);
      }
      ellipse(t.getLocation().getRow(), t.getLocation().getCol(), rectWidth / 2, rectHeight / 2);
    }
  }
}
 
public void enemyDraw() {
  int makeShiftRad = 5;
  for (int e = gS.getEnemies().size() - 1; e >= 0; e--) {
    if (e < gS.getEnemies().size()) {
 
      Enemy a = gS.getEnemies().get(e);
      a.setPlayerLoc(player.getLocation());
      if (a.getCollision()) {
        enemyCount++;
        if (enemyCount <= 120) {
          if (addRedValue <= 60) {
            addRedValue += 2;
          }
          else {
            addRedValue = 0;
          }
        }
        else {
          a.changeCollision();
          enemyCount = 0;
          addRedValue = 0;
        }
      }
      if (a.getHealth() <= 0) {
        int rand = (int)(Math.random() * 100);
        if (rand <= 30) {
          gS.addToken(new BulletToken(a.getLocation(), player, gS.getMapLoc()));
        }
        else if (rand <= 45) {
          gS.addToken(new HealthToken(a.getLocation(), player, gS.getMapLoc()));
        }
        gS.removeEnemy(e);
        player.gainExp(a.getLevel());
        if (player.checkLevelUp()) {
          player.levelUp();
        }
        score += (a.getLevel() * 10);
 
        //System.out.println(player.getLevel());
      }
 
      if (a.getHealth() > 0) {
        fill(255);
        textAlign(CENTER);
        text(a.getHealth(), a.getLocation().getRow(), a.getLocation().getCol() + makeShiftRad + rectHeight / 2);
      }
      //System.out.println(a.getLevel());
      //System.out.println(player.getLevel() + "   player" );
      if (a.getLevel() == player.getLevel() + 1) {
        if (a.getCollision()) {
          fill(200 + addRedValue, 220, 240);
        }
        else {
          fill(190, 220, 240);
        }
      }
      else if (a.getLevel() == player.getLevel() + 2) {
        if (a.getCollision()) {
          fill(30 + addRedValue, 40, 150);
        }
        else {
          fill(30, 40, 150);
        }
      }
      else if (a.getLevel() == player.getLevel() + 3) {
        if (a.getCollision()) {
          fill(140 + addRedValue, 20, 40);
        }
        else {
          fill(140, 20, 40);
        }
      }
      else {
        fill(190, 220, 240);
      }
      if (!a.collisionCheck(a.getLocation(), makeShiftRad, gS.getMaps(), gS.getMapLoc(), width, height, x, y) ) {
        a.move();
      }
      else {
        a.reverseMove(a.getLocation(), makeShiftRad, gS.getMaps(), gS.getMapLoc(), width, height, x, y);
      }
 
      //temporary
      int enRadius = rectWidth / 4;
      triangle(a.getLocation().getRow(), a.getLocation().getCol() - rectHeight / 3,
      a.getLocation().getRow() - rectWidth / 3, a.getLocation().getCol() + rectHeight / 4,
      a.getLocation().getRow() + rectWidth / 3, a.getLocation().getCol() + rectHeight / 4);
      //ellipse(a.getLocation().getRow(),a.getLocation().getCol(), rectWidth / 2, rectHeight / 2);
 
      //changes
      s = a.collisionCheck(player, enRadius, rectWidth, rectHeight);
      if (s instanceof Player) {
        hit = true;
        if (a.getDirection() == 0) {
          player.moveTo(player.getLocation().getRow(), player.getLocation().getCol() + a.getSpeed());
          if (player.getLocation().getCol() >= 382) {
            player.moveTo(player.getLocation().getRow(), height + 1);
          }
          controller.downMethod(1);
        }
        else if (a.getDirection() == 1) {
          player.moveTo(player.getLocation().getRow() + a.getSpeed(), player.getLocation().getCol());
          if (player.getLocation().getRow() >= 382) {
            player.moveTo(width + 1, player.getLocation().getCol());
          }
          controller.rightMethod(1);
        }
        else if (a.getDirection() == 2) {
          player.moveTo(player.getLocation().getRow(), player.getLocation().getCol() - a.getSpeed());
          controller.upMethod(1);
        }
        else if (a.getDirection() == 3) {
          player.moveTo(player.getLocation().getRow() - a.getSpeed(), player.getLocation().getCol());
          controller.leftMethod(1);
        }
        //System.out.println(s.getHealth());
        //decrease player health
 
        if (hitCount == 0) {
          player.changeHealth(-1 * a.getDamage());
        }
        //move player back in enemy direction by certain enemy speed
        //check boundaries by using the statements in the controller
        //
      }
    }
  }
  }
 
  public void playerDraw() {
 
    if (hit) {
      playerCount += 5;
      if (playerCount > 100) {
        playerCount = 0;
      }
    }
    else {
      playerCount = 0;
    }
    //player color
    fill(30 + playerCount, 100 - playerCount, 200 - playerCount * 2);
    rectMode(CENTER);
    rect(player.getLocation().getRow(), player.getLocation().getCol(),
    rectWidth / 2, rectHeight / 2);
    if (player.getHealth() > 0) {
      fill(255);
      textAlign(CENTER);
      text(player.getHealth(), player.getLocation().getRow(), player.getLocation().getCol() + rectHeight / 2 + 4);
    }
    else {
      gameOver = true;
    }
  }
  public void keyPressed() {
    if (!delayed) {
      if (keyCode == KEY_SPACE && !controller.getKeys()[keyCode]) {
        player.changeNumBullets(-1);
        Location bulletLocation = new Location(player.getLocation().row, player.getLocation().getCol());
        //fire
        if (player.getNumBullets() > 0) {
          gS.addBullet((new Bullet(bulletLocation, controller.getAngle(), player.getLevel(), speedConstant)));
        }
        /*
            else {
                        System.out.println("require");
                    }
                    */
        //System.out.println(gS.getBullets().size());
      }
 
      if (keyCode < controller.getKeys().length) {
        // println("Key pressed == " + keyCode);
        controller.setKeys(keyCode, true);
      }
    }
  }
 
  public void keyReleased() {
    if (keyCode < controller.getKeys().length) {
      // println("Key released == " + keyCode);
      controller.setKeys(keyCode, false);
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
    }
    */
 
  //changes
  public boolean bulletCollisionCheck(Bullet b, int a) {
 
    boolean collision = false;
    if (gS.getBoss().size() > 0) {
      s = b.collisionCheck(gS.getBoss().get(0), bulletRadius, rectWidth, rectHeight);
      if (s instanceof Boss) {
        gS.getBoss().get(0).setCollision();
        gS.getBoss().get(0).changeHealth(-b.getDamage());
        gS.removeBullet(a);
        collision = true;
        return collision;
      }
    }
 
    for (int c = 0; c < gS.getEnemies().size(); c++) {
      //checks enemies
      s = b.collisionCheck(gS.getEnemies().get(c), bulletRadius, rectWidth, rectHeight);
 
      if (s instanceof Enemy) {
        gS.getEnemies().get(c).setCollision();
        gS.getEnemies().get(c).changeHealth(-b.getDamage());
        gS.removeBullet(a);
        collision = true;
        return collision;
      }
    }
    //checks walls
    if (b.collisionCheck(b.getLocation(), bulletRadius, gS.getMaps(), gS.getMapLoc(), width, height, x, y)) {
      gS.removeBullet(a);
      collision = true;
    }
    return collision;
  }
