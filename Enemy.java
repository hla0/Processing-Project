public class Enemy extends Character {
    private int level;
    private int damage;
    private Location playerLoc;
    private int direction;
    //private Location[][] nodes;  
    boolean bulletCollision = false;
    //int distance = 0;
    int x;
    int y;
    int count = 0;
 
    public Enemy (Location loc, int l, int sc, Player p) {
      //changes
      super(loc, l, sc);
      setFullHealth(calculateHP(l));
      setHealth(calculateHP(l));
      level = p.getLevel() + l;
      damage = calculateDamage(l);
      l *= l;
      if (l < 20) {
        setSpeed(l * getSpeedConstant() / 6 / p.getLevel() + 1);
      }
      else {
        l /= l;
        setSpeed(l * getSpeedConstant() / 2 / p.getLevel() + 1);
      }
    }
 
    public void setCollision() {
      bulletCollision = true;
    }
 
    public void changeCollision() {
      bulletCollision = !bulletCollision;
    }
    public boolean getCollision() {
      return bulletCollision;
    }
    public int calculateDamage(int l) {
      return l * 40 - 10;
    }
 
    public int getLevel() {
      return level;
    }
 
    public int getDamage() {
      return damage;
    }
 
    public void setPlayerLoc(Location loc) {
      playerLoc = loc;
    }
 
    //use grid to find shortest distance
    public void findShortest(boolean[][][] map, int mapLoc, int width, int height, double x, double y) {
    }
 
    //simple move
    public void move() {
      //int angle;
      x = 0;
      y = 0;
      double xDist = getLocation().getRow() - playerLoc.getRow();
      double yDist = getLocation().getCol() - playerLoc.getCol();
 
      //angle =  (int) Math.toDegrees(Math.atan2((getLocation().getCol() - playerLoc.getCol()),(getLocation().getRow() - playerLoc.getRow()) ));
      if (xDist > 0) {
        x = -1;
      }
      else if (xDist < 0) {
        x = 1;
      }
      if (yDist > 0) {
        y = -1;
      }
      else if (yDist < 0) {
        y = 1;
      }
      if (Math.abs(xDist) - Math.abs(yDist) > 0) {
        if (x == 1) {
          direction = 1;
        }
        else if (x == -1) {
          direction = 3;
        }
      }
      else {
        if (y == 1) {
          direction = 0;
        }
        else if (y == -1) {
          direction = 2;
        }
      }
 
      moveTo(getLocation().getRow() + getSpeed() * x, getLocation().getCol() + getSpeed() * y);
      //moveTo((int)(getLocation().getRow() - (getSpeed() * Math.sin(angle))),(int)(getLocation().getCol() + (getSpeed() * Math.cos(angle))));
    }
 
    public void reverseMove(Location loc, int rad, boolean[][][] map, int mapLoc, int width, int height, double x, double y) {
      if (collisionCheck(getLocation(), rad, map, mapLoc, width, height, x, y)) {
        moveTo(getLocation().getRow() - getSpeed() * this.x, getLocation().getCol() - getSpeed() * this.y);
      }
    }
 
 
 
    /*
    //move based on grid
        public void move(boolean[][][] map, int mapLoc,int width,int height,double x,double y) {
            int[][] mapDist = new int[map[mapLoc].length][map[mapLoc][0].length];
            int playerGridx = getLocation().convertToGrid(playerLoc.getRow(), width, x);
            int playerGridy = getLocation().convertToGrid(playerLoc.getCol(), height, y);
            int enemyGridx = getLocation().convertToGrid(getLocation().getRow(), width, x);
            int enemyGridy = getLocation().convertToGrid(getLocation().getRow(), height, y);   
             
            int xDist = getLocation().getRow() - playerLoc.getRow();
            int yDist = getLocation().getCol() - playerLoc.getCol();
            if (xDist > 0) {
                this.x = -1;
            }
            else if (xDist < 0) {
                this.x = 1;
            }
            if (yDist > 0) {
                this.y = -1;
            }
            else if (yDist < 0) {
                this.y = 1;
            }
             
            System.out.println(getLocation());
            if (map[mapLoc][enemyGridx + this.x][enemyGridy + this.y]) {
                moveTo(getLocation().getRow() + this.x * getSpeed(),getLocation().getCol() + this.y * getSpeed());
                System.out.println(this.x);
            }
            else {
                 
                if (map[mapLoc][enemyGridx + this.x][enemyGridy]) {
                    moveTo(getLocation().getRow() + this.x * getSpeed(),getLocation().getCol());
                }
                else if (map[mapLoc][enemyGridx][enemyGridy + this.y]) {
                    moveTo(getLocation().getRow(),getLocation().getCol() + this.y * getSpeed());
                }
                else {
                    if (map[mapLoc][enemyGridx + this.x][enemyGridy - this.y]) {
                        moveTo(getLocation().getRow() + this.x * getSpeed(),getLocation().getCol() - this.y * getSpeed());
                    }
                    else if (map[mapLoc][enemyGridx - this.x][enemyGridy + this.y]) {
                        moveTo(getLocation().getRow() - this.x * getSpeed(),getLocation().getCol() + this.y * getSpeed());
                    }
                    else {
                        if (map[mapLoc][enemyGridx - this.x][enemyGridy]) {
                            moveTo(getLocation().getRow() - this.x * getSpeed(),getLocation().getCol());
                        }
                        else if (map[mapLoc][enemyGridx][enemyGridy - this.y]) {   
                            moveTo(getLocation().getRow(),getLocation().getCol() - this.y * getSpeed());
                        }
                        else {
                            if (map[mapLoc][enemyGridx - this.x][enemyGridy - this.y]) {
                                moveTo(getLocation().getRow() - this.x * getSpeed(),getLocation().getCol() - this.y * getSpeed());
                            }
                            else {
                                return;
                            }
                        }
                         
                    }
                }
            }
             
             
        }
        */
 
    /*
    public void move(boolean[][][] map, int mapLoc,int width,int height,double x,double y) {
             
            oldEnemyLoc = getLocation();
            nodes = new Location[(int) height][(int) width];
            for (int a = 0; a < width; a++) {
                for (int b = 0; b < height; b++) {
                    nodes[a][b] = new Location(width,height);
                }
            }
            boolean found = findShortestPath(playerLoc,map,mapLoc,width,height,x,y);
            ArrayList<Location> adj = findAdjacentLocations();
            System.out.println(adj.size());
            if (adj.size() > 0) {
                int dist = 9999999;
                int val = 0;
                if (found) {
                    for (int a = 0; a < adj.size(); a++) {
                        if (adj.get(a).getDistance() < dist && adj.get(a).getDistance() >= 0) {
                            dist = adj.get(a).getDistance();
                            val = a;
                        }
                    }
                }
                 
                moveTo(adj.get(val));
             
            }
             
        }
        */
 
 
    /*
    public boolean findShortestPath(Location player, boolean[][][] map, int mapLoc,int width,int height,double x,double y) {
            int row = playerLoc.getRow();
            int col = playerLoc.getCol();
            if (!player.getIgnore()) {
                nodes[row][col].setIgnore();
                nodes[row][col].setDistance(distance);
                distance++;
                for (Location a: getAdjacentLocations(player, map, mapLoc, width, height, x, y, distance)) {
                    System.out.println(a);
                    if (a.getRow() == getLocation().getRow() && a.getCol() == getLocation().getCol()) {
                        System.out.println("exit");
                        return true;
                    }
                    if (map[mapLoc][(int)(a.getRow() / (width / x))][(int)(a.getCol() / (height / y))]) {
                        findShortestPath(a, map, mapLoc, width, height, x, y);
                    }
                     
                }
            }
            return false;
        }
         
        public ArrayList<Location> getAdjacentLocations(Location location,boolean[][][] map,int mapLoc,int width,int height,double x, double y, int distance) {
            ArrayList<Location> temp = new ArrayList<Location>();
            for (int x1 = location.getRow() - 1; x1 <= location.getRow() + 1; x1++) {
                for (int y1 = location.getCol() - 1; y1 <= location.getCol() + 1; y1++) {
                    if (x1 == location.getRow() && y1 == location.getCol()) {
                         
                    }
                    else {
                        if (map[mapLoc][(int)(x1 / (width / x))][(int)((y1) / (height / y))]) {
                            try {
                                if (!nodes[(int) x1][(int) y1].getIgnore()) {
                                    temp.add(new Location((int)x1,(int)y1, distance));
                                }
                            }
                            catch(Exception e) {
                                 
                            }
                        }
                    }
                }
            }
            return temp;
             
        }
         
        public ArrayList<Location> findAdjacentLocations() {
            ArrayList<Location> adjacentLoc = new ArrayList<Location>();
            for (int x = getLocation().getRow() - getSpeed(); x < getLocation().getRow() + getSpeed(); x++)
            {
                for (int y = getLocation().getCol() - getSpeed(); y < getLocation().getCol() + getSpeed();y++) {
                    if (nodes[x][y] != null) {
                        System.out.println(nodes[x][y]);
                        if (Math.abs(nodes[x][y].getRow() - getLocation().getRow()) <= getSpeed() && Math.abs(nodes[x][y].getRow() - getLocation().getRow()) <= getSpeed() ) {
                            adjacentLoc.add(nodes[x][y]);
                        }
                    }
                }
            }
            return adjacentLoc;
        }
        */
 
    public int getDirection() {
      return direction;
    }
  }
