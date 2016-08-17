public class Character {
    private Location loc;
    private int health;
    private int speed;
    //level of enemy does not change with character
    private int fullHealth;
    private int level;
    private int speedConstant;
    private int healthLimit;
    private boolean collision;
 
    public Character(Location loc1) {
      loc = loc1;
    }
 
    public Character(Location loc1, int l, int s) {
      loc = loc1;
      level = l;
      speedConstant = s;
      if (this instanceof Player) {
        healthLimit = calculateHP(l) * 4;
      }
      else {
        healthLimit = calculateHP(l);
      }
    }
 
    public Character(Location loc1, int l, int h, int s) {
      loc = loc1;
      level = l;
      health = h;
      fullHealth = h;
      speedConstant = s;
    }
    public void setCollision(boolean a) {
      collision = a;
    }
 
    public boolean getCollision() {
      return collision;
    }
    //changes
 
    //other character
    //havent been able to get outer points of bullet and token
    public Character collisionCheck(Character a, int rad, int rectWidth, int rectHeight) {
      int xLoc = -10;
      int yLoc = -10;
      int xLoc2 = -10;
      int yLoc2 = -10;
      if (a instanceof Player) {
        xLoc = (a.getLocation().getRow() + rectWidth/ 4);
        xLoc2 = (a.getLocation().getRow() - rectWidth / 4);
        yLoc = (a.getLocation().getCol() + rectHeight / 4);
        yLoc2 = (a.getLocation().getCol() - rectHeight / 4);
      }
      if (this instanceof Bullet) {
        int x = this.getLocation().getRow();
        int y = this.getLocation().getCol();
        int pointX = -10;
        int pointY = -10;
        for (double r = 0; r < 360; r += 5) {
          pointX = (int)(x + rad * Math.sin(r) / 2);
          pointY = (int)(y + rad * Math.cos(r) / 2);
        }
        //makeshift code
        if (a instanceof Enemy) {
          if (pointX >= a.getLocation().getRow() - 5 && pointX <= a.getLocation().getRow() + 5 && pointY >= a.getLocation().getCol() - 5 && pointY <= a.getLocation().getCol() + 5) {
          }
          else {
            a = null;
          }
        }
      }
      else if (this instanceof Token) {
        int x = this.getLocation().getRow();
            int y = this.getLocation().getCol();
            int pointX;
            int pointY;
            for (double r = 0; r < 360; r++) {
                pointX = (int)(x + Math.sin(r) * rad / 2);
                pointY = (int)(y + Math.cos(r) * rad / 2);
                //System.out.println(pointX + "  " + pointY);
                if(pointX >= xLoc2 && pointX <= xLoc && pointY >= yLoc2 && pointY <= yLoc) {
                    return a;
                }
                else {
                    a = null;
                }
            }
      }
      else if (this instanceof Enemy) {
        int x = this.getLocation().getRow();
        int y = this.getLocation().getCol();
        int pointX = -10;
        int pointY = -10;
        for (double r = 0; r < 360; r += 5) {
          pointX = (int)(x + rad * Math.sin(r));
          pointY = (int)(y + rad * Math.cos(r));
          if (this instanceof Boss) {
            if (pointX >= a.getLocation().getRow() - rectWidth / 2 + 10 && pointX <= a.getLocation().getRow() + rectWidth / 2 - 10 && pointY >= a.getLocation().getCol() - rectHeight / 2 + 10 && pointY <= a.getLocation().getCol() + rectHeight / 2 - 10) {
              return a;
            }
          }
          else {
            if (pointX >= a.getLocation().getRow() - rad && pointX <= a.getLocation().getRow() + rad && pointY >= a.getLocation().getCol() - rad && pointY <= a.getLocation().getCol() + rad) {
              return a;
            }
          }
        }
        a = null;
      }
      return a;
    }
    //wall
    public boolean collisionCheck(Location loc, int rad, boolean[][][] map, int mapLoc, int width, int height, double x, double y) {
      if (this instanceof Bullet || this instanceof Enemy) {
        double x1 = loc.getRow();
        double y1 = loc.getCol();
        int pointX = (int)((x1) / ((double)width / x));
        int pointY = (int)((y1) / ((double)height / y));
        //needs work to prevent sliding past parts
        if (checkLocations(pointX, pointY, (int)x, (int)y)) {
          return true;
        }
        if (map[mapLoc][pointX][pointY]) {
          return false;
        }
      }
      return true;
    }
    public void setFullHealth(int a) {
      fullHealth = a;
    }
 
    public boolean checkLocations(int x1, int y1, int x, int y) {
      if (x1 >= x) {
        return true;
      }
      if (x1 < 0) {
        return true;
      }
      if (y1 >= y) {
        return true;
      }
      if (y1 < 0) {
        return true;
      }
      return false;
    }
 
 
    public int getFullHealth() {
      return fullHealth;
    }
 
    public Location getLocation() {
      return loc;
    }
 
    public void setLocation(int x, int y) {
      loc = new Location(x, y);
    }
 
    public void moveTo(int x, int y) {
      loc = loc.changeLocation(x, y);
    }
 
    public void moveTo(Location loc1) {
      loc = loc.changeLocation(loc1.getRow(), loc1.getCol());
    }
 
    public void setSpeed(int s) {
      if (s <= 10 || speed > 0) {
        speed = s;
      }
      else {
        speed = 5;
      }
    }
 
    public int getSpeed() {
      return speed;
    }
 
    //changes
    public int calculateHP(int l) {
 
      if (this instanceof Enemy) {
        l = l * 40 + 50;
      }
      else if (this instanceof Player) {
        l = l * 50 + 50;
      }
      return l;
    }
 
    //changes
    public void setHealth(int h) {
      health = h;
    }
    public int getHealth() {
      return health;
    }
 
    public void changeHealth(int de) {
      health = health + de;
      if (health > healthLimit) {
        health = healthLimit;
      }
    }
 
    public int getLevel() {
      return level;
    }
 
    public int getSpeedConstant() {
      return speedConstant;
    }
 
    public void resetLocation(int w, int h, int w1, int h1) {
      setLocation(loc.getRow() * w / w1, loc.getCol() * h / h1);
    }
  }
 
