  public class Location {
    int row;
    int col;
    int distance;
    //boolean ignore;
    public Location(int x, int y) {
      row = x;
      col = y;
    }
 
    public Location (int x, int y, int dist) {
      row = x;
      col = y;
      distance = dist;
      //ignore = false;
    }
 
    public void setDistance(int d) {
      distance = d;
    }
    //public boolean getIgnore() {
    //return ignore;
    //}
 
    public void setIgnore() {
      //ignore = true;
    }
    public int getRow() {
      return row;
    }
 
    public int getCol() {
      return col;
    }
 
    public int getDistance() {
      return distance;
    }
 
    public void changeRow(int r) {
      row = r;
    }
 
    public void changeCol(int c) {
      col = c;
    }
 
    public Location changeLocation(int x, int y) {
      row = x;
      col = y;
      return this;
    }
 
    public String toString() {
      return row + "   " + col;
    }
 
    public int convertToGrid(int loc, int width, double x) {
      return (int)(loc / (width / x));
    }
  }
