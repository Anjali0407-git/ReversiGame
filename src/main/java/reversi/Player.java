package reversi;

import java.util.ArrayList;

public class Player {
	protected int score;
	protected String tileType;
	protected final static int INTITIAL_SCORE = 2;
	
	public Player(String tileType) {
		this.tileType = tileType;
		this.score = INTITIAL_SCORE;
	}
	
	public int getScore() {
		return this.score;
	}
	
	public void setScore(int score) {
		this.score = score;
	}
	
	public String getTileType() {
		return this.tileType;
	}
	
	public void setTileType(String tileType) {
		this.tileType = tileType;
	}
	
	public void addScore(int currentScore) {
		this.score += currentScore;
	}
	
	public void reduceScore(int currentScore) {
		this.score -= currentScore;
	}
	
	public boolean isValidMove(int row, int col, ReversiGameBoard board) {
		String tileType = this.tileType;
		if(board.findHorizontalScore(tileType, row, col)!=0 || 
				board.findVerticalScore(tileType, row, col)!=0 ||
				board.findLeftDiagonalScore(tileType, row, col)!=0 ||
				board.findRightDiagonalScore(tileType, row, col)!=0) {
			return true;
		}
		
		return false;
	}
	
	public ArrayList<Tile> findValidMoves(ReversiGameBoard board){
		ArrayList<Tile> validMoves = new ArrayList<Tile>();
		
		for(int row=0; row< board.getBoardSize(); row++) {
			for(int col=0; col<board.getBoardSize(); col++) {
				if(board.getBoard()[row][col] == null) {
					int score = board.findScore(row, col, this.tileType);
					if (score > 0) {
						Tile tile = new Tile(new Position(row, col), score);
						validMoves.add(tile);
					}
				}
			}
		}
		
		return validMoves;
	}
}
