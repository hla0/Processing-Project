public class Player extends Character {
    private int damage;
    private int level;
    private int exp;
    private int direction;
    private int numBullet;
    private final int totalBullets = 100;
    private int lives;
 
    public Player(Location loc, int sc) {
      //changes
      super(loc, 1, sc);
      level = 1;
      setHealth(calculateHP(level) * 4);
      setSpeed(2 * getSpeedConstant());
      numBullet = 40;
      lives = 3;
    }
 
    public void changeLives(int a) {
      lives += a;
    }
 
    public void levelUp() {
      level = level + 1;
    }
 
    public boolean checkLevelUp() {
      return (exp >= level * level * 15);
    }
 
    public int getExp() {
      return exp;
    }
    public int getLevel() {
      return level;
    }
    public void setDirection(int a) {
      direction = a;
    }
    public int getDirection() {
      return direction;
    }
 
    public void calculateDamage() {
      damage = level * 10;
    }
    public int getDamage() {
      return damage;
    }
 
    public void gainExp(int a) {
      exp = exp + a;
    }
    public int getNumBullets() {
      return numBullet;
    }
    public void changeNumBullets(int a) {
      numBullet = numBullet + a;
      if (numBullet > totalBullets) {
        numBullet = totalBullets;
      }
      else if (numBullet < 0) {
        numBullet = 0;
      }
    }
  }
 
