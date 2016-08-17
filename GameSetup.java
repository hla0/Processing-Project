import java.util.ArrayList;


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
	int mapLoc;
	Square[][] list;
	boolean[][][] maps = {
								{ 	//map 0
									//top connects to map 1
									{false,false,false,true ,true ,true ,false,false,false,false},
									{false,true ,true ,true ,true ,true ,true ,false,false,false},
									{true ,true ,true ,true ,false,true ,true ,true ,true ,false},
									{true ,true ,true ,true ,true ,true ,false,true ,true ,false},
									{true ,false,true ,true ,true ,true ,true ,true ,true ,false},
									{false,false,true ,true ,false,true ,true ,true ,true ,false},
									{true ,true ,true ,true ,false,false,true ,true ,true ,false},
									{false,true ,true ,false,false,true ,true ,true ,true ,false},
									{true ,false,true ,false,true ,true ,false,false,true ,false},
									{false,false,false,false,true ,true ,true ,false,false,false}
								},
								
								{	//map 1
								  	//bottom connects to map 0
									{false,false,false,false,false,false,false,false,false,false},	
									{false,false,true ,false,false,false,true ,false,true ,false},
									{false,true ,true ,true ,true ,true, true ,true ,true ,true},
									{false,true ,true ,true ,false,true, true ,true ,true ,true},
									{true, true ,false,true ,false,true ,true ,true ,true ,true},
									{true, true ,true ,true ,true ,true ,true ,true ,true ,false},
									{true, true ,true ,true ,true ,true ,true ,false,true ,true},
									{false,true ,true ,false,true ,false,false,false,false,false},
									{false,false,false,false,true ,true ,true ,true ,false,true},
									{false,false,false,false,false,false,false,false,false,false}												
								}
						};
	
	public GameSetup(double x1,double y1,int w,int h,Player player) {
		x = x1;
		y = y1;
		width = w;
		height = h;
		speedConstant = (int)((height + width) / 80 / x);
		this.player = player;
		list = new Square[(int)x][(int)y];
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
	public void setSquares(int a) {
		for (int row = 0; row < x; row++) {
			for (int col = 0; col < y; col++) {
				if(maps[a][row][col]) {
					list[row][col] = new Square(true);
				}
				else {
					list[row][col] = new Square(false);
				}
			}
		}
		
		//System.out.println("occur 2");
		mapLoc = a;
	}
	
	public void removeEnemy(int a) {
		enemies.remove(a);
	}
	public void removeToken(int a) {
		tokens.remove(a);
	}
	public void removeBullet(int a){
		bullets.remove(a);
	}
	
	public void addBullet(Bullet b) {
		bullets.add(b);
	}
	public void setEnemies(int c) {
		for (int b = enemies.size() - 1;b >= 0; b--) {
			enemies.remove(b);
		}
		for(int s = 0; s < c;) {
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
					enemies.add(new Enemy(new Location((int)(xLoc * width / x + (width / x / 2)), (int)(yLoc * height / y + (height / y / 2))),l,speedConstant));
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
					for (Token t : tokens) {
						if (t.getLocation().getRow() == (int)(xLoc * width / x + (width / x / 2)) && t.getLocation().getCol() ==(int)(yLoc * height / y + (height / y / 2))) {
							occupied = true;
						}
					}
					if (!occupied) {
						
						if (healthNum > 0) {
							tokens.add(new HealthToken((new Location((int)(xLoc * width / x + (width / x / 2)), (int)(yLoc * height / y + (height / y / 2)))),player,m));
							healthNum--;
						}
						else if(bulletNum > 0) {
							tokens.add(new BulletToken((new Location((int)(xLoc * width / x + (width / x / 2)), (int)(yLoc * height / y + (height / y / 2)))),player,m));
							bulletNum--;
						}
						else if(mainNum > 0) {
							tokens.add(new MainToken((new Location((int)(xLoc * width / x + (width / x / 2)), (int)(yLoc * height / y + (height / y / 2)))),player,m));
							mainNum--;
						}
						a++;
					}
				}
			}
		}
	}
}
