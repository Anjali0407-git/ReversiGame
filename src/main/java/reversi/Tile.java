package reversi;

public class Tile {
	private int score;
	private Position pos;
	
	public Tile(Position pos, int score) {
		this.pos = pos;
		this.score = score;
	}

	public int getScore() {
		return this.score;
	}
	
	public Position getPosition() {
		return this.pos;
	}
}
