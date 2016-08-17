 public class Controller {
    Player player;
    int x;
    int y;
    int xLoc;
    int yLoc;
    int xLoc2;
    int yLoc2;
    int rectWidth;
    int rectHeight;
    int width;
    int height;
    int angle = 0;
    GameSetup gS;
    boolean off;
    public Controller(Player play, GameSetup gs) {
      player = play;
      gS = gs;
    }
    final int KEY_LEFT = 37;
    final int KEY_UP = 38;
    final int KEY_RIGHT = 39;
    final int KEY_DOWN = 40;
 
    boolean[] keyPressed = new boolean[256];
 
    public boolean[] getKeys() {
      return keyPressed;
    }
 
    public void setKeys(int key, boolean a) {
      keyPressed[key] = a;
    }
 
    //changes check with in school code
    public void checkKeyboard(int x, int y, int width, int height) {
      this.width = width;
      this.height = height;
      this.x = x;
      this.y = y;
      rectWidth = (int) (width / x);
      rectHeight = (int) (height / y);
 
      if (keyPressed[KEY_UP]) {
        player.setDirection(0);
        if (!keyPressed[KEY_LEFT] || !keyPressed[KEY_RIGHT]) {
          angle = 0;
        }
        player.moveTo(player.getLocation().getRow(), player.getLocation().getCol() - player.getSpeed());
        xLoc = player.getLocation().convertToGrid(player.getLocation().getRow() + rectWidth/ 4, width, x);
        xLoc2 = player.getLocation().convertToGrid(player.getLocation().getRow() - rectWidth / 4, width, x);
        yLoc = player.getLocation().convertToGrid(player.getLocation().getCol() - rectHeight / 4, height, y);
        checkLocations(xLoc, xLoc2, yLoc, yLoc);
        upMethod();
      }
 
      if (keyPressed[KEY_DOWN]) {
        player.setDirection(2);
        if (!keyPressed[KEY_LEFT] || keyPressed[KEY_RIGHT]) {
          angle = 180;
        }
        player.moveTo(player.getLocation().getRow(), player.getLocation().getCol() + player.getSpeed());
        xLoc = player.getLocation().convertToGrid(player.getLocation().getRow() + rectWidth/ 4, width, x);
        xLoc2 = player.getLocation().convertToGrid(player.getLocation().getRow() - rectWidth / 4, width, x);
        yLoc = player.getLocation().convertToGrid(player.getLocation().getCol() + rectHeight / 4, height, y);
        checkLocations(xLoc, xLoc2, yLoc, yLoc);
        downMethod();
      }
 
 
      if (keyPressed[KEY_LEFT]) {
        player.setDirection(3);
        if (!keyPressed[KEY_UP] || !keyPressed[KEY_DOWN]) {
          angle = 270;
        }
        player.moveTo(player.getLocation().getRow() - player.getSpeed(), player.getLocation().getCol());
        xLoc = player.getLocation().convertToGrid(player.getLocation().getRow() - rectWidth / 4, width, x);
        yLoc = player.getLocation().convertToGrid(player.getLocation().getCol() - rectHeight / 4, height, y);
        yLoc2 = player.getLocation().convertToGrid(player.getLocation().getCol() + rectHeight / 4, height, y);
        checkLocations(xLoc, xLoc, yLoc, yLoc2);
 
        leftMethod();
      }
 
      if (keyPressed[KEY_RIGHT]) {
        player.setDirection(1);
        if (!keyPressed[KEY_UP] || !keyPressed[KEY_DOWN]) {
          angle = 90;
        }
        player.moveTo(player.getLocation().getRow() + player.getSpeed(), player.getLocation().getCol());
        xLoc = player.getLocation().convertToGrid(player.getLocation().getRow() + rectWidth / 4, width, x);
        yLoc = player.getLocation().convertToGrid(player.getLocation().getCol() - rectHeight / 4, height, y);
        yLoc2 = player.getLocation().convertToGrid(player.getLocation().getCol() + rectHeight / 4, height, y);
        checkLocations(xLoc, xLoc, yLoc, yLoc2);
 
        rightMethod();
      }
      if (keyPressed[KEY_UP] && keyPressed[KEY_RIGHT]) {
        angle = 45;
      }
      if (keyPressed[KEY_UP] && keyPressed[KEY_LEFT]) {
        angle = 315;
      }
      if (keyPressed[KEY_DOWN] && keyPressed[KEY_RIGHT]) {
        angle = 135;
      }
      if (keyPressed[KEY_DOWN] && keyPressed[KEY_LEFT]) {
        angle = 225;
      }
    }
 
 
    public void upMethod() {
      if (player.getLocation().getCol() - rectHeight / 4 <= 0) {
        //println("out up");
        if (gS.getMapLoc() == 0) {
          if (gS.getMaps()[1][xLoc][(int)((height - rectHeight / 4) / (height / y))] && gS.getMaps()[1][xLoc2][(int)((height - rectHeight / 4) / (height / y))]) {
            gS.setSquares(1);
            gS.setEnemies(5);
          }
        }
        else if (gS.getMapLoc() == 1) {
          if (gS.getMaps()[2][xLoc][(int)((height - rectHeight / 4) / (height / y))] && gS.getMaps()[2][xLoc2][(int)((height - rectHeight / 4) / (height / y))]) {
            gS.setSquares(2);
            gS.setEnemies(11);
          }
        }
        else if (gS.getMapLoc() == 2) {
          if (gS.getMaps()[3][xLoc][(int)((height - rectHeight / 4) / (height / y))] && gS.getMaps()[3][xLoc2][(int)((height - rectHeight / 4) / (height / y))]) {
            gS.setSquares(3);
            //gS.setEnemies(4);
            //set boss
          }
        }
        else if (gS.getMapLoc() == 4) {
          if (gS.getMaps()[5][xLoc][(int)((height - rectHeight / 4) / (height / y))] && gS.getMaps()[5][xLoc2][(int)((height - rectHeight / 4) / (height / y))]) {
            gS.setSquares(5);
            gS.setEnemies(8);
          }
        }
        else if (gS.getMapLoc() == 5) {
          if (gS.getMaps()[6][xLoc][(int)((height - rectHeight / 4) / (height / y))] && gS.getMaps()[6][xLoc2][(int)((height - rectHeight / 4) / (height / y))]) {
            gS.setSquares(6);
            gS.setEnemies(6);
          }
        }
        else if (gS.getMapLoc() == 7) {
          if (gS.getMaps()[8][xLoc][(int)((height - rectHeight / 4) / (height / y))] && gS.getMaps()[8][xLoc2][(int)((height - rectHeight / 4) / (height / y))]) {
            gS.setSquares(8);
            gS.setEnemies(8);
          }
        }
        else if (gS.getMapLoc() == 8) {
          if (gS.getMaps()[9][xLoc][(int)((height - rectHeight / 4) / (height / y))] && gS.getMaps()[9][xLoc2][(int)((height - rectHeight / 4) / (height / y))]) {
            gS.setSquares(9);
            gS.setEnemies(7);
          }
        }
 
        yLoc = (int)((height - rectHeight / 4) / (height / y));
        if (gS.getMaps()[gS.getMapLoc()][xLoc][yLoc] && gS.getMaps()[gS.getMapLoc()][xLoc2][yLoc]) {
          player.moveTo(player.getLocation().getRow(), height - rectHeight / 4);
        }
        else {
          player.moveTo(player.getLocation().getRow(), 0 + rectHeight / 4);
        }
      }
 
      checkLocations(xLoc, xLoc2, yLoc, yLoc);
      if (!gS.getMaps()[gS.getMapLoc()][xLoc][yLoc] || !gS.getMaps()[gS.getMapLoc()][xLoc2][yLoc]) {
        player.moveTo(player.getLocation().getRow(), player.getLocation().getCol() + player.getSpeed());
      }
    }
 
    public void downMethod() {
      if (player.getLocation().getCol() + rectHeight / 4>= height) {
        //System.out.println("out down");
        if (gS.getMapLoc() == 1) {
          if (gS.getMaps()[0][xLoc][(int)((0 + rectHeight / 4) / (height / y))] && gS.getMaps()[0][xLoc2][(int)((0 + rectHeight / 4) / (height / y))]) {
            //System.out.println("occur");
            gS.setSquares(0);
            gS.setEnemies(6);
          }
        }
        else if (gS.getMapLoc() == 2) {
          if (gS.getMaps()[1][xLoc][(int)((0 + rectHeight / 4) / (height / y))] && gS.getMaps()[1][xLoc2][(int)((0 + rectHeight / 4) / (height / y))]) {
            gS.setSquares(1);
            gS.setEnemies(5);
          }
        }
        else if (gS.getMapLoc() == 5) {
          if (gS.getMaps()[4][xLoc][(int)((0 + rectHeight / 4) / (height / y))] && gS.getMaps()[4][xLoc2][(int)((0 + rectHeight / 4) / (height / y))]) {
            gS.setSquares(4);
            gS.setEnemies(6);
          }
        }
        else if (gS.getMapLoc() == 6) {
          if (gS.getMaps()[5][xLoc][(int)((0 + rectHeight / 4) / (height / y))] && gS.getMaps()[5][xLoc2][(int)((0 + rectHeight / 4) / (height / y))]) {
            gS.setSquares(5);
            gS.setEnemies(8);
          }
        }
        else if (gS.getMapLoc() == 8) {
          if (gS.getMaps()[7][xLoc][(int)((0 + rectHeight / 4) / (height / y))] && gS.getMaps()[7][xLoc2][(int)((0 + rectHeight / 4) / (height / y))]) {
            gS.setSquares(7);
            gS.setEnemies(6);
          }
        }
        else if (gS.getMapLoc() == 9) {
          if (gS.getMaps()[8][xLoc][(int)((0 + rectHeight / 4) / (height / y))] && gS.getMaps()[8][xLoc2][(int)((0 + rectHeight / 4) / (height / y))]) {
            gS.setSquares(8);
            gS.setEnemies(7);
          }
        }
        yLoc = (int)((0 + rectHeight / 4) / (height / y));
        if (gS.getMaps()[gS.getMapLoc()][xLoc][yLoc] && gS.getMaps()[gS.getMapLoc()][xLoc2][yLoc]) {
          player.moveTo(player.getLocation().getRow(), 0 + rectHeight / 4);
        }
        else {
          player.moveTo(player.getLocation().getRow(), height - rectHeight / 4);
        }
      }
 
      checkLocations(xLoc, xLoc2, yLoc, yLoc);
      if (!gS.getMaps()[gS.getMapLoc()][xLoc][yLoc] || !gS.getMaps()[gS.getMapLoc()][xLoc2][yLoc]) {
        //System.out.println(mapLoc);
        player.moveTo(player.getLocation().getRow(), player.getLocation().getCol() - player.getSpeed());
      }
    }
 
    public void leftMethod() {
      if (player.getLocation().getRow() - rectWidth / 4 <= 0) {
        if (gS.getMapLoc() == 0) {
          if (gS.getMaps()[4][(int)((width - rectWidth / 4) / (width / x))][yLoc] && gS.getMaps()[4][(int)((width - rectWidth / 4) / (width / x))][yLoc2]) {
            gS.setSquares(4);
            gS.setEnemies(5);
          }
        }
        else if (gS.getMapLoc() == 1) {
          if (gS.getMaps()[5][(int)((width - rectWidth / 4) / (width / x))][yLoc] && gS.getMaps()[5][(int)((width - rectWidth / 4) / (width / x))][yLoc2]) {
            gS.setSquares(5);
            gS.setEnemies(4);
          }
        }
        else if (gS.getMapLoc() == 2) {
          if (gS.getMaps()[6][(int)((width - rectWidth / 4) / (width / x))][yLoc] && gS.getMaps()[6][(int)((width - rectWidth / 4) / (width / x))][yLoc2]) {
            gS.setSquares(6);
            gS.setEnemies(7);
          }
        }
        else if (gS.getMapLoc() == 7) {
          if (gS.getMaps()[0][(int)((width - rectWidth / 4) / (width / x))][yLoc] && gS.getMaps()[0][(int)((width - rectWidth / 4) / (width / x))][yLoc2]) {
            gS.setSquares(0);
            gS.setEnemies(5);
          }
        }
        else if (gS.getMapLoc() == 8) {
          if (gS.getMaps()[1][(int)((width - rectWidth / 4) / (width / x))][yLoc] && gS.getMaps()[1][(int)((width - rectWidth / 4) / (width / x))][yLoc2]) {
            gS.setSquares(1);
            gS.setEnemies(6);
          }
        }
        else if (gS.getMapLoc() == 9) {
          if (gS.getMaps()[2][(int)((width - rectWidth / 4) / (width / x))][yLoc] && gS.getMaps()[2][(int)((width - rectWidth / 4) / (width / x))][yLoc2]) {
            gS.setSquares(2);
            gS.setEnemies(9);
          }
        }
        //println("out left");
        xLoc = (int)((width - rectWidth / 4) / (width / x)) ;
 
        if (gS.getMaps()[gS.getMapLoc()][xLoc][yLoc] && gS.getMaps()[gS.getMapLoc()][xLoc][yLoc2]) {
          player.moveTo(width - rectWidth / 4, player.getLocation().getCol());
        }
        else {
          player.moveTo(0 + rectWidth / 4, player.getLocation().getCol());
        }
      }
 
      checkLocations(xLoc, xLoc2, yLoc, yLoc2);
      if (!gS.getMaps()[gS.getMapLoc()][xLoc][yLoc] || !gS.getMaps()[gS.getMapLoc()][xLoc][yLoc2]) {
        player.moveTo(player.getLocation().getRow() + player.getSpeed(), player.getLocation().getCol());
      }
    }
 
    public void rightMethod() {
      if (player.getLocation().getRow() + rectWidth / 4 >= width) {
        //println("out right");
        if (gS.getMapLoc() == 4) {
          if (gS.getMaps()[0][(int)((0 + rectWidth / 4 ) / (width / x))][yLoc] && gS.getMaps()[0][(int)((0 + rectWidth / 4 ) / (width / x))][yLoc2]) {
            gS.setSquares(0);
            gS.setEnemies(6);
          }
        }
        else if (gS.getMapLoc() == 5) {
          if (gS.getMaps()[1][(int)((0 + rectWidth / 4 ) / (width / x))][yLoc] && gS.getMaps()[1][(int)((0 + rectWidth / 4 ) / (width / x))][yLoc2]) {
            gS.setSquares(1);
            gS.setEnemies(6);
          }
        }
        else if (gS.getMapLoc() == 6) {
          if (gS.getMaps()[2][(int)((0 + rectWidth / 4 ) / (width / x))][yLoc] && gS.getMaps()[2][(int)((0 + rectWidth / 4 ) / (width / x))][yLoc2]) {
            gS.setSquares(2);
            gS.setEnemies(9);
          }
        }
        else if (gS.getMapLoc() == 0) {
          if (gS.getMaps()[7][(int)((0 + rectWidth / 4 ) / (width / x))][yLoc] && gS.getMaps()[7][(int)((0 + rectWidth / 4 ) / (width / x))][yLoc2]) {
            gS.setSquares(7);
            gS.setEnemies(8);
          }
        }
        else if (gS.getMapLoc() == 1) {
          if (gS.getMaps()[8][(int)((0 + rectWidth / 4 ) / (width / x))][yLoc] && gS.getMaps()[8][(int)((0 + rectWidth / 4 ) / (width / x))][yLoc2]) {
            gS.setSquares(8);
            gS.setEnemies(4);
          }
        }
        else if (gS.getMapLoc() == 2) {
          if (gS.getMaps()[9][(int)((0 + rectWidth / 4 ) / (width / x))][yLoc] && gS.getMaps()[9][(int)((0 + rectWidth / 4 ) / (width / x))][yLoc2]) {
            gS.setSquares(9);
            gS.setEnemies(6);
          }
        }
        xLoc = (int)((0 + rectWidth / 4 ) / (width / x));
        if (gS.getMaps()[gS.getMapLoc()][xLoc][yLoc] && gS.getMaps()[gS.getMapLoc()][xLoc][yLoc2]) {
          player.moveTo(0 + rectWidth / 4, player.getLocation().getCol());
        }
        else {
          player.moveTo(width - rectWidth / 4, player.getLocation().getCol());
        }
      }
 
      checkLocations(xLoc, xLoc2, yLoc, yLoc2);
      if (!gS.getMaps()[gS.getMapLoc()][xLoc][yLoc] || !gS.getMaps()[gS.getMapLoc()][xLoc][yLoc2]) {
        player.moveTo(player.getLocation().getRow() - player.getSpeed(), player.getLocation().getCol());
      }
    }
 
 
    public void upMethod(int a) {
 
      if (player.getLocation().getCol() - rectHeight / 4 <= 0) {
        //println("out up");
        if (gS.getMapLoc() == 0) {
          if (gS.getMaps()[1][xLoc][(int)((height - rectHeight / 4) / (height / y))] && gS.getMaps()[1][xLoc2][(int)((height - rectHeight / 4) / (height / y))]) {
            gS.setSquares(1);
            gS.setEnemies(4);
          }
        }
        else if (gS.getMapLoc() == 1) {
          if (gS.getMaps()[2][xLoc][(int)((height - rectHeight / 4) / (height / y))] && gS.getMaps()[2][xLoc2][(int)((height - rectHeight / 4) / (height / y))]) {
            gS.setSquares(2);
            gS.setEnemies(5);
          }
        }
        else if (gS.getMapLoc() == 2) {
          if (gS.getMaps()[3][xLoc][(int)((height - rectHeight / 4) / (height / y))] && gS.getMaps()[3][xLoc2][(int)((height - rectHeight / 4) / (height / y))]) {
            gS.setSquares(3);
            //gS.setEnemies(4);
            //set boss
          }
        }
        else if (gS.getMapLoc() == 4) {
          if (gS.getMaps()[5][xLoc][(int)((height - rectHeight / 4) / (height / y))] && gS.getMaps()[5][xLoc2][(int)((height - rectHeight / 4) / (height / y))]) {
            gS.setSquares(5);
            gS.setEnemies(7);
          }
        }
        else if (gS.getMapLoc() == 5) {
          if (gS.getMaps()[6][xLoc][(int)((height - rectHeight / 4) / (height / y))] && gS.getMaps()[6][xLoc2][(int)((height - rectHeight / 4) / (height / y))]) {
            gS.setSquares(6);
            gS.setEnemies(7);
          }
        }
        else if (gS.getMapLoc() == 7) {
          if (gS.getMaps()[8][xLoc][(int)((height - rectHeight / 4) / (height / y))] && gS.getMaps()[8][xLoc2][(int)((height - rectHeight / 4) / (height / y))]) {
            gS.setSquares(8);
            gS.setEnemies(9);
          }
        }
        else if (gS.getMapLoc() == 8) {
          if (gS.getMaps()[9][xLoc][(int)((height - rectHeight / 4) / (height / y))] && gS.getMaps()[9][xLoc2][(int)((height - rectHeight / 4) / (height / y))]) {
            gS.setSquares(9);
            gS.setEnemies(8);
          }
        }
 
        yLoc = (int)((height - rectHeight / 4) / (height / y));
        if (gS.getMaps()[gS.getMapLoc()][xLoc][yLoc] && gS.getMaps()[gS.getMapLoc()][xLoc2][yLoc]) {
          player.moveTo(player.getLocation().getRow(), height - rectHeight / 4);
        }
        else {
          player.moveTo(player.getLocation().getRow(), 0 + rectHeight / 4);
        }
      }
 
      xLoc = player.getLocation().convertToGrid(player.getLocation().getRow() + rectWidth/ 4, width, x);
      xLoc2 = player.getLocation().convertToGrid(player.getLocation().getRow() - rectWidth / 4, width, x);
      yLoc = player.getLocation().convertToGrid(player.getLocation().getCol() - rectHeight / 4 - player.getSpeed(), height, y);
      yLoc2 = player.getLocation().convertToGrid(player.getLocation().getCol() + rectHeight / 4, height, y);
 
      checkLocations(xLoc, xLoc2, yLoc, yLoc);
      if (!gS.getMaps()[gS.getMapLoc()][xLoc][yLoc] || !gS.getMaps()[gS.getMapLoc()][xLoc2][yLoc]) {
        player.moveTo(player.getLocation().getRow(), player.getLocation().getCol() + player.getSpeed());
      }
    }
 
    public void downMethod(int a) {
 
      if (player.getLocation().getCol() + rectHeight / 4>= height) {
        System.out.println("out down");
        if (gS.getMapLoc() == 1) {
          if (gS.getMaps()[0][xLoc][(int)((0 + rectHeight / 4) / (height / y))] && gS.getMaps()[0][xLoc2][(int)((0 + rectHeight / 4) / (height / y))]) {
            //System.out.println("occur");
            gS.setSquares(0);
            gS.setEnemies(5);
          }
        }
        else if (gS.getMapLoc() == 2) {
          if (gS.getMaps()[1][xLoc][(int)((0 + rectHeight / 4) / (height / y))] && gS.getMaps()[1][xLoc2][(int)((0 + rectHeight / 4) / (height / y))]) {
            gS.setSquares(1);
            gS.setEnemies(6);
          }
        }
        else if (gS.getMapLoc() == 5) {
          if (gS.getMaps()[4][xLoc][(int)((0 + rectHeight / 4) / (height / y))] && gS.getMaps()[4][xLoc2][(int)((0 + rectHeight / 4) / (height / y))]) {
            gS.setSquares(4);
            gS.setEnemies(3);
          }
        }
        else if (gS.getMapLoc() == 6) {
          if (gS.getMaps()[5][xLoc][(int)((0 + rectHeight / 4) / (height / y))] && gS.getMaps()[5][xLoc2][(int)((0 + rectHeight / 4) / (height / y))]) {
            gS.setSquares(5);
            gS.setEnemies(9);
          }
        }
        else if (gS.getMapLoc() == 8) {
          if (gS.getMaps()[7][xLoc][(int)((0 + rectHeight / 4) / (height / y))] && gS.getMaps()[7][xLoc2][(int)((0 + rectHeight / 4) / (height / y))]) {
            gS.setSquares(7);
            gS.setEnemies(6);
          }
        }
        else if (gS.getMapLoc() == 9) {
          if (gS.getMaps()[8][xLoc][(int)((0 + rectHeight / 4) / (height / y))] && gS.getMaps()[8][xLoc2][(int)((0 + rectHeight / 4) / (height / y))]) {
            gS.setSquares(8);
            gS.setEnemies(7);
          }
        }
        yLoc = (int)((0 + rectHeight / 4) / (height / y));
        if (gS.getMaps()[gS.getMapLoc()][xLoc][yLoc] && gS.getMaps()[gS.getMapLoc()][xLoc2][yLoc]) {
          player.moveTo(player.getLocation().getRow(), 0 + rectHeight / 4);
        }
        else {
          player.moveTo(player.getLocation().getRow(), height - rectHeight / 4);
        }
      }
 
      xLoc = player.getLocation().convertToGrid(player.getLocation().getRow() + rectWidth/ 4, width, x);
      xLoc2 = player.getLocation().convertToGrid(player.getLocation().getRow() - rectWidth / 4, width, x);
      yLoc = player.getLocation().convertToGrid(player.getLocation().getCol() - rectHeight / 4, height, y);
      yLoc2 = player.getLocation().convertToGrid(player.getLocation().getCol() + rectHeight / 4 + player.getSpeed(), height, y);
 
      checkLocations(xLoc, xLoc2, yLoc, yLoc2);
      if (!gS.getMaps()[gS.getMapLoc()][xLoc][yLoc2] || !gS.getMaps()[gS.getMapLoc()][xLoc2][yLoc2]) {
        //System.out.println(mapLoc);
        player.moveTo(player.getLocation().getRow(), player.getLocation().getCol() - player.getSpeed());
      }
    }
 
    public void leftMethod(int a) {
 
      if (player.getLocation().getRow() - rectWidth / 4 <= 0) {
        if (gS.getMapLoc() == 0) {
          if (gS.getMaps()[4][(int)((width - rectWidth / 4) / (width / x))][yLoc] && gS.getMaps()[4][(int)((width - rectWidth / 4) / (width / x))][yLoc2]) {
            gS.setSquares(4);
            gS.setEnemies(5);
          }
        }
        else if (gS.getMapLoc() == 1) {
          if (gS.getMaps()[5][(int)((width - rectWidth / 4) / (width / x))][yLoc] && gS.getMaps()[5][(int)((width - rectWidth / 4) / (width / x))][yLoc2]) {
            gS.setSquares(5);
            gS.setEnemies(6);
          }
        }
        else if (gS.getMapLoc() == 2) {
          if (gS.getMaps()[6][(int)((width - rectWidth / 4) / (width / x))][yLoc] && gS.getMaps()[6][(int)((width - rectWidth / 4) / (width / x))][yLoc2]) {
            gS.setSquares(6);
            gS.setEnemies(6);
          }
        }
        else if (gS.getMapLoc() == 7) {
          if (gS.getMaps()[0][(int)((width - rectWidth / 4) / (width / x))][yLoc] && gS.getMaps()[0][(int)((width - rectWidth / 4) / (width / x))][yLoc2]) {
            gS.setSquares(0);
            gS.setEnemies(5);
          }
        }
        else if (gS.getMapLoc() == 8) {
          if (gS.getMaps()[1][(int)((width - rectWidth / 4) / (width / x))][yLoc] && gS.getMaps()[1][(int)((width - rectWidth / 4) / (width / x))][yLoc2]) {
            gS.setSquares(1);
            gS.setEnemies(3);
          }
        }
        else if (gS.getMapLoc() == 9) {
          if (gS.getMaps()[2][(int)((width - rectWidth / 4) / (width / x))][yLoc] && gS.getMaps()[2][(int)((width - rectWidth / 4) / (width / x))][yLoc2]) {
            gS.setSquares(2);
            gS.setEnemies(5);
          }
        }
        //println("out left");
        xLoc = (int)((width - rectWidth / 4) / (width / x)) ;
 
        checkLocations(xLoc, xLoc2, yLoc, yLoc);
        if (gS.getMaps()[gS.getMapLoc()][xLoc][yLoc] && gS.getMaps()[gS.getMapLoc()][xLoc][yLoc2]) {
          player.moveTo(width - rectWidth / 4, player.getLocation().getCol());
        }
        else {
          player.moveTo(0 + rectWidth / 4, player.getLocation().getCol());
        }
      }
 
      xLoc = player.getLocation().convertToGrid(player.getLocation().getRow() + rectWidth/ 4, width, x);
      xLoc2 = player.getLocation().convertToGrid(player.getLocation().getRow() - rectWidth / 4 - player.getSpeed(), width, x);
      yLoc = player.getLocation().convertToGrid(player.getLocation().getCol() - rectHeight / 4, height, y);
      yLoc2 = player.getLocation().convertToGrid(player.getLocation().getCol() + rectHeight / 4, height, y);
 
      checkLocations(xLoc, xLoc2, yLoc, yLoc2);
 
      if (!gS.getMaps()[gS.getMapLoc()][xLoc2][yLoc] || !gS.getMaps()[gS.getMapLoc()][xLoc2][yLoc2]) {
        player.moveTo(player.getLocation().getRow() + player.getSpeed(), player.getLocation().getCol());
      }
    }
 
    public void rightMethod(int a) {
 
      if (player.getLocation().getRow() + rectWidth / 4 >= width) {
        //println("out right");
        if (gS.getMapLoc() == 4) {
          if (gS.getMaps()[0][(int)((0 + rectWidth / 4 ) / (width / x))][yLoc] && gS.getMaps()[0][(int)((0 + rectWidth / 4 ) / (width / x))][yLoc2]) {
            gS.setSquares(0);
            gS.setEnemies(5);
          }
        }
        else if (gS.getMapLoc() == 5) {
          if (gS.getMaps()[1][(int)((0 + rectWidth / 4 ) / (width / x))][yLoc] && gS.getMaps()[1][(int)((0 + rectWidth / 4 ) / (width / x))][yLoc2]) {
            gS.setSquares(1);
            gS.setEnemies(6);
          }
        }
        else if (gS.getMapLoc() == 6) {
          if (gS.getMaps()[2][(int)((0 + rectWidth / 4 ) / (width / x))][yLoc] && gS.getMaps()[2][(int)((0 + rectWidth / 4 ) / (width / x))][yLoc2]) {
            gS.setSquares(2);
            gS.setEnemies(7);
          }
        }
        else if (gS.getMapLoc() == 0) {
          if (gS.getMaps()[7][(int)((0 + rectWidth / 4 ) / (width / x))][yLoc] && gS.getMaps()[7][(int)((0 + rectWidth / 4 ) / (width / x))][yLoc2]) {
            gS.setSquares(7);
            gS.setEnemies(8);
          }
        }
        else if (gS.getMapLoc() == 1) {
          if (gS.getMaps()[8][(int)((0 + rectWidth / 4 ) / (width / x))][yLoc] && gS.getMaps()[8][(int)((0 + rectWidth / 4 ) / (width / x))][yLoc2]) {
            gS.setSquares(8);
            gS.setEnemies(4);
          }
        }
        else if (gS.getMapLoc() == 2) {
          if (gS.getMaps()[9][(int)((0 + rectWidth / 4 ) / (width / x))][yLoc] && gS.getMaps()[9][(int)((0 + rectWidth / 4 ) / (width / x))][yLoc2]) {
            gS.setSquares(9);
            gS.setEnemies(5);
          }
        }
        xLoc = (int)((0 + rectWidth / 4 ) / (width / x));
        if (gS.getMaps()[gS.getMapLoc()][xLoc][yLoc] && gS.getMaps()[gS.getMapLoc()][xLoc][yLoc2]) {
          player.moveTo(0 + rectWidth / 4, player.getLocation().getCol());
        }
        else {
          player.moveTo(width - rectWidth / 4, player.getLocation().getCol());
        }
      }
      xLoc = player.getLocation().convertToGrid(player.getLocation().getRow() + rectWidth/ 4 + player.getSpeed(), width, x);
      xLoc2 = player.getLocation().convertToGrid(player.getLocation().getRow() - rectWidth / 4, width, x);
      yLoc = player.getLocation().convertToGrid(player.getLocation().getCol() - rectHeight / 4, height, y);
      yLoc2 = player.getLocation().convertToGrid(player.getLocation().getCol() + rectHeight / 4, height, y);
 
      checkLocations(xLoc, xLoc2, yLoc, yLoc2);
      if (!gS.getMaps()[gS.getMapLoc()][xLoc][yLoc] || !gS.getMaps()[gS.getMapLoc()][xLoc][yLoc2]) {
        player.moveTo(player.getLocation().getRow() - player.getSpeed(), player.getLocation().getCol());
      }
    }
 
 
    public int getAngle() {
      return angle;
    }
 
    public void checkLocations(int xLoc, int xLoc2, int yLoc, int yLoc2) {
      if (xLoc >= x) {
        this.xLoc = 0;
        off = true;
      }
      else if (xLoc < 0) {
        this.xLoc = x - 1;
        off = true;
      }
      if (xLoc2 >= x) {
        this.xLoc2 = 0;
        off = true;
      }
      else if (xLoc2 < 0) {
        this.xLoc2 = x - 1;
        off = true;
      }
 
      if (yLoc >= y) {
        this.yLoc = 0;
        off = true;
      }
      else if (yLoc < 0) {
        this.yLoc = y - 1;
        off = true;
      }
      if (yLoc2 >= y) {
        this.yLoc2 = 0;
        off = true;
      }
      else if (yLoc2 < 0) {
        this.yLoc2 = y - 1;
        off = true;
      }
    }
    /*
    public void boundaryCheck() {
            if(player.getLocation().getRow() - rectWidth / 4 < 0) {
                //println("out left");
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
                //println("out right");
                xLoc = (int)((0 + rectWidth / 4) / (width / x));
                yLoc = (int)(player.getLocation().getCol() / (height / y));
                if (gS.getMaps()[gS.getMapLoc()][xLoc][yLoc]) {
                    player.moveTo(0 + rectWidth / 4, player.getLocation().getCol());
                }
                else {
                    player.moveTo(width - rectWidth / 4, player.getLocation().getCol());
                }
            }
             
            if(player.getLocation().getCol() + rectHeight / 4 > height) {
                //println("out down");
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
            off = false;
        }
        */
    public Player setPlayer() {
      return player;
    }
 
    public void resetPlayer(Player p) {
      player = p;
    }
  }
