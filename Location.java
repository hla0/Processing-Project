
public class Location {
	int row;
	int col;
	public Location(int x, int y) {
		row = x;
		col = y;
	}
	
	public int getRow() {
		return row;
	}
	
	public int getCol() {
		return col;
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
}
