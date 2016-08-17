
public class Token extends Character{
	Player player;
	int map;
	String tokenType;
	public Token(Location loc,Player p,int m,String type) {
		super(loc);
		player = p;
		map = m;
		tokenType = type;
	}
	
	//must be called for each token
	//all tokens will have to be updated consistently then updated with this method
	public Player setPlayer() {
		return player;
	}
	
	public int getMap() {
		return map;
	}
	
	public String getTokenType() {
		return tokenType;
	}
	
}
