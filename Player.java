
public class Player extends Character{
	private int damage;
	private int level;
	private int exp;
	private int direction;
	private int numBullet;
	private final int totalBullets = 20;
	
	public Player(Location loc,int sc) {
		//changes
		super(loc,1,sc);
		level = 1;
		setHealth(calculateHP(level));
		setSpeed(2 * getSpeedConstant());
		numBullet = 10;
	}
	
	public void levelUp() {
		level++;
	}
	
	public void checkLevelUp() {
		if (exp >= level * level * 15) {
			levelUp();
		}
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
		checkLevelUp();
	}
	public int getNumBullets() {
		return numBullet;
	}
	public void changeNumBullets(int a) {
		numBullet = numBullet + a;
		if (numBullet > totalBullets) {
			numBullet = totalBullets;
		}
	}
}
