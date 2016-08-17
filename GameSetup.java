public class GameSetup {
    double x;
    double y;
    int width;
    int height;
    int speedConstant;
    Player player;
    ArrayList<Token> tokens = new ArrayList<Token>();
    ArrayList<Bullet> bullets = new ArrayList<Bullet>();
    ArrayList<Enemy> enemies = new ArrayList<Enemy>();
    ArrayList<Boss> boss = new ArrayList<Boss>();
    int mapLoc;
    Square[][] list;
    /*
     map
          
          3
         629
         518
         407
          
         */
    boolean[][][] maps = {
      {     //map 0
        //top connects to map 1
        //left connects to map 4
        //right connects to map 7
        //top left                                      //bottom left
        {
          false, false, false, true, true, true, false, false, false, false
        }
        ,
        {
          false, true, true, true, true, true, true, false, false, false
        }
        ,
        {
          true, true, true, true, false, true, true, true, true, false
        }
        ,
        {
          true, true, true, true, true, true, false, true, true, false
        }
        ,
        {
          true, false, true, true, true, true, true, true, true, false
        }
        ,
        {
          false, false, true, true, false, true, true, true, true, false
        }
        ,
        {
          true, true, true, true, false, false, true, true, true, false
        }
        ,
        {
          false, true, true, false, false, true, true, true, true, false
        }
        ,
        {
          true, true, true, false, true, true, false, false, true, false
        }
        ,
        {
          false, false, false, false, true, true, true, false, false, false
        }
        //top right                                    //bottom right
      }
      ,
 
      { //map 1
        //bottom connects to map 0
        //top connects to map 2
        //left connects to map 5
        //right connects to map 8
        {
          false, false, false, false, false, true, true, false, false, false
        }
        ,  
        {
          false, false, true, false, false, false, true, false, true, false
        }
        ,
        {
          false, true, true, true, true, true, true, true, true, true
        }
        ,
        {
          false, true, true, true, false, true, true, true, true, true
        }
        ,
        {
          true, true, false, true, false, true, true, true, true, true
        }
        ,
        {
          true, true, true, true, true, true, true, true, true, false
        }
        ,
        {
          true, true, true, true, true, true, true, false, true, true
        }
        ,
        {
          false, true, true, false, true, false, false, false, false, false
        }
        ,
        {
          false, false, false, false, true, true, true, true, false, true
        }
        ,
        {
          false, false, false, false, false, false, false, true, false, false
        }
      }
      ,
 
      {
        //map 2
        //map before boss
        //top connects to map 3
        //bottom connects to map 1
        //left connects to map 6
        //right connects to map 9
        {
          false, true, true, true, true, true, true, true, true, true
        }
        ,
        {
          false, true, true, true, true, true, true, true, true, true
        }
        ,
        {
          false, true, true, true, true, true, true, true, true, true
        }
        ,
        {
          false, true, true, true, true, true, true, true, true, true
        }
        ,
        {
          false, true, true, true, true, true, true, true, true, true
        }
        ,
        {
          false, true, true, true, true, true, true, true, true, true
        }
        ,
        {
          false, true, true, true, true, true, true, true, true, true
        }
        ,
        {
          false, true, true, true, true, true, true, true, true, true
        }
        ,
        {
          false, true, true, true, true, true, true, true, true, true
        }
        ,
        {
          false, true, true, true, true, true, true, true, true, true
        }
      }
      ,
 
      {
        //map 3
        //boss map
        //bottom connects to map 2
        {
          false, false, false, false, false, false, false, false, false, false
        }
        ,
        {
          false, true, true, true, true, true, true, true, true, false
        }
        ,
        {
          false, true, true, true, true, true, true, true, true, false
        }
        ,
        {
          false, true, true, true, true, true, true, true, true, false
        }
        ,
        {
          false, true, true, true, true, true, true, true, true, true
        }
        ,
        {
          false, true, true, true, true, true, true, true, true, true
        }
        ,
        {
          false, true, true, true, true, true, true, true, true, false
        }
        ,
        {
          false, true, true, true, true, true, true, true, true, false
        }
        ,
        {
          false, true, true, true, true, true, true, true, true, false
        }
        ,
        {
          false, false, false, false, false, false, false, false, false, false
        }
      }
      ,
 
      {
        //map 4
        {
          false, false, false, false, false, false, false, false, false, false
        }
        ,
        {
          true, true, true, true, true, true, true, true, true, false
        }
        ,
        {
          true, true, true, true, true, true, true, true, true, false
        }
        ,
        {
          true, true, true, true, true, true, true, true, true, false
        }
        ,
        {
          true, true, true, true, true, true, true, true, true, false
        }
        ,
        {
          true, true, true, true, true, true, true, true, true, false
        }
        ,
        {
          true, true, true, true, true, true, true, true, true, false
        }
        ,
        {
          true, true, true, true, true, true, true, true, true, false
        }
        ,
        {
          true, true, true, true, true, true, true, true, true, false
        }
        ,
        {
          true, true, true, true, true, true, true, true, true, false
        }
      }
      ,
 
      {
        //map 5
        {
          false, false, false, false, false, false, false, false, false, false
        }
        ,
        {
          true, true, true, true, true, true, true, true, true, true
        }
        ,
        {
          true, true, true, true, true, true, true, true, true, true
        }
        ,
        {
          true, true, true, true, true, true, true, true, true, true
        }
        ,
        {
          true, true, true, true, true, true, true, true, true, true
        }
        ,
        {
          true, true, true, true, true, true, true, true, true, true
        }
        ,
        {
          true, true, true, true, true, true, true, true, true, true
        }
        ,
        {
          true, true, true, true, true, true, true, true, true, true
        }
        ,
        {
          true, true, true, true, true, true, true, true, true, true
        }
        ,
        {
          true, true, true, true, true, true, true, true, true, true
        }
      }
      ,
 
      {
        //map 6
        {
          false, false, false, false, false, false, false, false, false, false
        }
        ,
        {
          false, true, true, true, true, true, true, true, true, true
        }
        ,
        {
          false, true, true, true, true, true, true, true, true, true
        }
        ,
        {
          false, true, true, true, true, true, true, true, true, true
        }
        ,
        {
          false, true, true, true, true, true, true, true, true, true
        }
        ,
        {
          false, true, true, true, true, true, true, true, true, true
        }
        ,
        {
          false, true, true, true, true, true, true, true, true, true
        }
        ,
        {
          false, true, true, true, true, true, true, true, true, true
        }
        ,
        {
          false, true, true, true, true, true, true, true, true, true
        }
        ,
        {
          false, true, true, true, true, true, true, true, true, true
        }
      }
      ,
 
      {
        //map 7
        {
          true, true, true, true, true, true, true, true, true, false
        }
        ,
        {
          true, true, true, true, true, true, true, true, true, false
        }
        ,
        {
          true, true, true, true, true, true, true, true, true, false
        }
        ,
        {
          true, true, true, true, true, true, true, true, true, false
        }
        ,
        {
          true, true, true, true, true, true, true, true, true, false
        }
        ,
        {
          true, true, true, true, true, true, true, true, true, false
        }
        ,
        {
          true, true, true, true, true, true, true, true, true, false
        }
        ,
        {
          true, true, true, true, true, true, true, true, true, false
        }
        ,
        {
          true, true, true, true, true, true, true, true, true, false
        }
        ,
        {
          false, false, false, false, false, false, false, false, false, false
        }
      }
      ,
 
      {
        //map 8
        {
          true, true, true, true, true, true, true, true, true, true
        }
        ,
        {
          true, true, true, true, true, true, true, true, true, true
        }
        ,
        {
          true, true, true, true, true, true, true, true, true, true
        }
        ,
        {
          true, true, true, true, true, true, true, true, true, true
        }
        ,
        {
          true, true, true, true, true, true, true, true, true, true
        }
        ,
        {
          true, true, true, true, true, true, true, true, true, true
        }
        ,
        {
          true, true, true, true, true, true, true, true, true, true
        }
        ,
        {
          true, true, true, true, true, true, true, true, true, true
        }
        ,
        {
          true, true, true, true, true, true, true, true, true, true
        }
        ,
        {
          false, false, false, false, false, false, false, false, false, false
        }
      }
      ,
 
      {
        //map 9
        {
          false, true, true, true, true, true, true, true, true, true
        }
        ,
        {
          false, true, true, true, true, true, true, true, true, true
        }
        ,
        {
          false, true, true, true, true, true, true, true, true, true
        }
        ,
        {
          false, true, true, true, true, true, true, true, true, true
        }
        ,
        {
          false, true, true, true, true, true, true, true, true, true
        }
        ,
        {
          false, true, true, true, true, true, true, true, true, true
        }
        ,
        {
          false, true, true, true, true, true, true, true, true, true
        }
        ,
        {
          false, true, true, true, true, true, true, true, true, true
        }
        ,
        {
          false, true, true, true, true, true, true, true, true, true
        }
        ,
        {
          false, false, false, false, false, false, false, false, false, false
        }
      }
      ,
    };
 
    public GameSetup(double x1, double y1, int w, int h, Player player) {
      x = x1;
      y = y1;
      width = w;
      height = h;
      speedConstant = (int)((height + width) / 80 / x);
      this.player = player;
      list = new Square[(int)x][(int)y];
    }
 
    public void changeMapBoolean(int mapLoc, int x, int y) {
      maps[mapLoc][x][y] = !maps[mapLoc][x][y];
    }
 
    public Square[][] getList() {
      return list;
    }
 
    public boolean[][][] getMaps() {
      return maps;
    }
 
    public ArrayList<Enemy> getEnemies() {
      return enemies;
    }
 
    public ArrayList<Bullet> getBullets() {
      return bullets;
    }
 
    public ArrayList<Token> getTokens() {
      return tokens;
    }
 
    public int getMapLoc() {
      return mapLoc;
    }
 
    public void resetEnemies() {
      for (int a = enemies.size() - 1; a >= 0; a--) {
        enemies.remove(a);
      }
    }
    public void setSquares(int a) {
      for (int row = 0; row < x; row++) {
        for (int col = 0; col < y; col++) {
          if (maps[a][row][col]) {
            list[row][col] = new Square(true);
          }
          else {
            list[row][col] = new Square(false);
          }
        }
      }
      mapLoc = a;
      resetEnemies();
    }
 
    public void removeEnemy(int a) {
      enemies.remove(a);
    }
    public void removeToken(int a) {
      tokens.remove(a);
    }
    public void removeBullet(int a) {
      bullets.remove(a);
    }
 
    public void addBullet(Bullet b) {
      bullets.add(b);
    }
 
    public ArrayList<Boss> getBoss() {
      return boss;
    }
 
    public void setBoss() {
      boss.add(new Boss(new Location(width / 2, height / 2), player.getLevel() + 5, speedConstant, player));
    }
 
    public void setPlayer(Player p) {
      player = p;
    }
 
    public void setEnemies(int c) {
      for (int b = enemies.size() - 1;b >= 0; b--) {
        enemies.remove(b);
      }
      for (int s = 0; s < c;) {
        boolean occupied = false;
        int xLoc = (int)(Math.random() * x);
        int yLoc = (int)(Math.random() * y);
        if (maps[mapLoc][xLoc][yLoc]) {
          for (Enemy e : enemies) {
            if (e.getLocation().getRow() == (int)(xLoc * width / x + (width / x / 2)) && e.getLocation().getCol() ==(int)(yLoc * height / y + (height / y / 2))) {
              occupied = true;
            }
          }
          for (Token t : tokens) {
            if (t.getLocation().getRow() == (int)(xLoc * width / x + (width / x / 2)) && t.getLocation().getCol() ==(int)(yLoc * height / y + (height / y / 2))) {
              occupied = true;
            }
          }
          if (!occupied) {
            int l = (int)(Math.random() * 3 + 1);
            if (player.getLevel() < 3) {
              //System.out.println(player.getLevel());
              l = (int)(Math.random() * player.getLevel() + 1);
            }
 
            enemies.add(new Enemy(new Location((int)(xLoc * width / x + (width / x / 2)), (int)(yLoc * height / y + (height / y / 2))), l, speedConstant, player));
            s++;
          }
        }
      }
    }
 
    public void setupTokens() {
      int tokenNumber = 4; //at least one main and mix of the three
      for (int m = 0; m < maps.length; m++) {
        int bulletNum = (int)(Math.random() * (tokenNumber));
        int healthNum = (int)(Math.random() * (tokenNumber - bulletNum));
        int mainNum = (int)(Math.random() * (tokenNumber - (healthNum + bulletNum)) + 1);
        //System.out.println(mainNum + "   " + bulletNum + "     " + healthNum);
        for (int a = 0; a < tokenNumber;) {
 
          boolean occupied = false;
          int xLoc = (int)(Math.random() * x);
          int yLoc = (int)(Math.random() * y);
          if (maps[m][xLoc][yLoc]) {
            if (m != 3) {
              for (Token t : tokens) {
                if (t.getMapLoc() == m && t.getLocation().getRow() == (int)(xLoc * width / x + (width / x / 2)) && t.getLocation().getCol() ==(int)(yLoc * height / y + (height / y / 2))) {
                  occupied = true;
                }
              }
              if (!occupied) {
 
                if (healthNum > 0) {
                  tokens.add(new HealthToken((new Location((int)(xLoc * width / x + (width / x / 2)), (int)(yLoc * height / y + (height / y / 2)))), player, m));
                  healthNum--;
                }
                else if (bulletNum > 0) {
                  tokens.add(new BulletToken((new Location((int)(xLoc * width / x + (width / x / 2)), (int)(yLoc * height / y + (height / y / 2)))), player, m));
                  bulletNum--;
                }
                else if (mainNum > 0) {
                  tokens.add(new MainToken((new Location((int)(xLoc * width / x + (width / x / 2)), (int)(yLoc * height / y + (height / y / 2)))), player, m));
                  mainNum--;
                }
                a++;
              }
            }
            else {
              for (Token t : tokens) {
                if (t.getLocation().getRow() == (int)(xLoc * width / x + (width / x / 2)) && t.getLocation().getCol() ==(int)(yLoc * height / y + (height / y / 2))) {
                  occupied = true;
                }
                if ((xLoc == 4 && yLoc == 9) || (xLoc == 5 && yLoc == 9) || (xLoc == 4 && yLoc == 4) || (xLoc == 4 && yLoc == 5) || (xLoc == 5 && yLoc == 4) || (xLoc == 5 && yLoc == 5)) {
                  occupied = true;
                }
              }
              if (!occupied) {
                if (a % 2 == 0) {
                  tokens.add(new BulletToken((new Location((int)(xLoc * width / x + (width / x / 2)), (int)(yLoc * height / y + (height / y / 2)))), player, m));
                }
                if (a % 2 == 1) {
                  tokens.add(new HealthToken((new Location((int)(xLoc * width / x + (width / x / 2)), (int)(yLoc * height / y + (height / y / 2)))), player, m));
                }
                a++;
              }
            }
          }
        }
      }
    }
 
 
    public void addToken(Token t) {
      tokens.add(t);
    }
 
    public void resetLocations(int w, int h, int w1, int h1) {
      for (int a = 0; a < enemies.size(); a++) {
        enemies.get(a).resetLocation(w, h, w1, h1);
      }
      for (int a = 0; a < tokens.size(); a++) {
        tokens.get(a).resetLocation(w, h, w1, h1);
      }
      for (int a = 0; a < bullets.size(); a++) {
        bullets.get(a).resetLocation(w, h, w1, h1);
      }
      for (int a = 0; a < boss.size(); a++) {
        boss.get(a).resetLocation(w, h, w1, h1);
      }
      player.resetLocation(w, h, w1, h1);
    }
}
