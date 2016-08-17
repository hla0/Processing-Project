
public class Bullet extends Character{
	int direction;
	int damage;
	int speed;
	final int limit = 30;
	int x;
	int y;
	
	
	public Bullet(Location loc,int dir,int lev,int sc) {
		super(loc, lev, sc);
		direction = dir;
		setSpeed(8 * getSpeedConstant());
	}
	
	public void move() {
		x = 0;
		y = 0;
		if (direction > 0 && direction < 180) {
			x = 1;
		}
		if (direction > 180 && direction < 360) {
			x = -1;
		}
		if (direction > 90 && direction < 270) {
			y = 1;
		}
		if (direction > 270 || direction < 90) {
			y = -1;
		}
		//System.out.println(x + "  " + y);
		moveTo(getLocation().getRow() + getSpeed() * x,getLocation().getCol() + getSpeed() * y);
	}
}
