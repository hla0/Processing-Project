
public class Character {
	private Location loc;
	private int health;
	private int speed;
	//level of enemy does not change with character
	private int level;
	private int speedConstant;
	
	public Character(Location loc1) {
		loc = loc1;
	}
	
	public Character(Location loc1, int l, int s) {
		loc = loc1;
		level = l;
		speedConstant = s;
	}
	
	public Character(Location loc1, int l, int h, int s) {
		loc = loc1;
		level = l;
		health = h;
		speedConstant = s;
	}
	
	
	//changes
	
	
	//other character
	public Character collisionCheck(Character a, int rad) {
		if (this instanceof Bullet) {
			int x = this.getLocation().getRow();
			int y = this.getLocation().getCol();
			int pointX;
			int pointY;
			for (double r = 0; r < 360; r++) {
				pointX = (int)(x + Math.sin(r));
				pointY = (int)(y + Math.cos(r));
			}
		}
		else if (this instanceof Token) {
			int x = this.getLocation().getRow();
			int y = this.getLocation().getCol();
			int pointX;
			int pointY;
			for (double r = 0; r < 360; r++) {
				pointX = (int)(x + Math.sin(r));
				pointY = (int)(y + Math.cos(r));
			}
			//if (collision player)
			
			//else {
			//a = null;
		}
		return a;
	}
	//wall
	public boolean collisionCheck(int rad, boolean[][][] map,int mapLoc) {
		if (this instanceof Bullet) {
			int x = this.getLocation().getRow();
			int y = this.getLocation().getCol();
			int pointX;
			int pointY;
			for (double r = 0; r < 360; r++) {
				pointX = (int)(x + Math.sin(r));
				pointY = (int)(y + Math.cos(r));
			}
		}
		return true;
	}
	
	public boolean plaEneCollisionCheck(Character a,Location loc) {
		return true;
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
		
		if(this instanceof Enemy) {
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
	}
	
	public int getLevel() {
		return level;
	}
	
	public int getSpeedConstant() {
		return speedConstant;
	}
}

