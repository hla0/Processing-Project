
public class Enemy extends Character {
	private int level;
	private int damage;
	private Location playerLoc;
	private int direction;
	
	public Enemy (Location loc, int l,int sc) {
		//changes
		super(loc, l, sc);
		setHealth(calculateHP(l));
		level = l;
		damage = calculateDamage(l);
		setSpeed(l * getSpeedConstant());
	}
	
	public int calculateDamage(int l) {
		return l * 20 - 10;
		
	}
	
	public int getDamage() {
		return damage;
	}
	
	public void setPlayerLoc(Location loc) {
		playerLoc = loc;
	}
	
	public void move() {
		
	}
	
	public int getDirection() {
		return direction;
	}
}
