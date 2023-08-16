package reversi;
import java.util.ArrayList;

import javax.swing.JButton;

public class ReversiGameBoard {
	private static final int BOARD_SIZE = 8;
	private static final int TOTAL_TILES = BOARD_SIZE * BOARD_SIZE;
	private boolean isPlayerTurn;
	private String[][] board = new String[BOARD_SIZE][BOARD_SIZE];
	private ArrayList<Position> flippedTiles = new ArrayList<Position>();
	
	public ReversiGameBoard(){
		initBoard();
	}
	
	public void initBoard(){
		this.isPlayerTurn = true;
		for(int row = 0; row < BOARD_SIZE; row++) {
			for(int col = 0; col < BOARD_SIZE; col++) {
				if(row == 3 || row == 4) {
					if(row == col) {
						board[row][col] = "B";
					}
					else if(col == 3 || col == 4) {
						board[row][col] = "W";
					}
					else {
						board[row][col] = null;
					}
				}
			}
		}
	}
	
	public boolean isBoardFull(AIPlayer aiPlayer, HumanPlayer humanPlayer) {
		return (aiPlayer.getScore() + humanPlayer.getScore()) == TOTAL_TILES;
	}
	
	public boolean isPlayerTurn() {
		return this.isPlayerTurn;
	}
	
	public void setPlayerTurn(boolean isPlayerTurn) {
		this.isPlayerTurn = isPlayerTurn;
	}
	
	public void changeTurn() {
		setPlayerTurn(!this.isPlayerTurn);
	}

	public void addTile(int row, int col, String tileType){
		this.board[row][col] = tileType;
	}

	public int getBoardSize() {
		return this.BOARD_SIZE;
	}
	
	public String[][] getBoard(){
		return board;
	}

	public ArrayList<Position> getFlippedTiles(){
    	return this.flippedTiles;
    }
    
    public void setFlippedTiles(Position p){
    	this.flippedTiles.add(p);
    }
	
	public String[][] flipTiles(Player currentPlayer, Player opponentPlayer, int row, int col, String tile) {
		int score = 0;
		score += findHorizontalFlips(tile, row, col);
		score += findVerticalFlips(tile, row, col);
		score += findLeftDiagonalFlips(tile, row, col);
		score += findRightDiagonalFlips(tile, row, col);
		
		board[row][col] = tile;
		this.setFlippedTiles(new Position(row, col));
		currentPlayer.addScore(score+1);
		opponentPlayer.reduceScore(score);
		return board;
	}
	
	public int findScore(int row, int col, String tile) {
		int score = 0;
		score += findHorizontalScore(tile, row, col);
		score += findVerticalScore(tile, row, col);
		score += findLeftDiagonalScore(tile, row, col);
		score += findRightDiagonalScore(tile, row, col);
		return score;
	}
	
	public int findVerticalFlips(String tile, int row, int col) {
		boolean success = false;
		int verticalScore = 0;
		
		for(int i=row; i>0; i--) {
			if(board[i-1][col] != null && !board[i-1][col].equals(tile)) {
				success = true;
			}
			else if(success && board[i-1][col]!= null && board[i-1][col].equals(tile)) {
				this.setFlippedTiles(new Position(i-1, col));
				for(int j=i; j<row; j++) {
					board[j][col] = tile;
					this.setFlippedTiles(new Position(j, col));
					verticalScore ++;
				}
				
				break;
			}
			else {
				success = false;
				break;
			}
		}
		
		success = false;

		for(int i=row; i<BOARD_SIZE-1; i++) {
			if(board[i+1][col]!=null && !board[i+1][col].equals(tile)) {
				success = true;
			}
			else if(success && board[i+1][col]!=null && board[i+1][col].equals(tile)) {
				this.setFlippedTiles(new Position(i+1, col));
				for(int j=i; j>row; j--) {
					board[j][col] = tile;
					this.setFlippedTiles(new Position(j, col));
					verticalScore ++;
				}
				
				break;
			}
			else {
				success = false;
				break;
			}
		}
		
		return verticalScore;
	}
	
	public int findHorizontalFlips(String tile, int row, int col) {
		boolean success = false;
		int horizontalScore = 0;
		
		for(int i=col; i>0; i--) {
			if(board[row][i-1]!=null && !board[row][i-1].equals(tile)) {
				success = true;
			}
			else if(success && board[row][i-1]!=null && board[row][i-1].equals(tile)) {
				this.setFlippedTiles(new Position(row, i-1));
				for(int j=i; j<col; j++) {
					board[row][j] = tile;
					this.setFlippedTiles(new Position(row, j));
					horizontalScore ++;
				}
				
				break;
			}
			else {
				success = false;
				break;
			}
		}
		
		success = false;

		for(int i=col; i<BOARD_SIZE-1; i++) {
			if(board[row][i+1]!=null && !board[row][i+1].equals(tile)) {
				success = true;
			}
			else if(success && board[row][i+1]!=null && board[row][i+1].equals(tile)) {
				this.setFlippedTiles(new Position(row, i+1));
				for(int j=i; j>col; j--) {
					board[row][j] = tile;
					this.setFlippedTiles(new Position(row, j));
					horizontalScore ++;
				}
				
				break;
			}
			else {
				success = false;
				break;
			}
		}
		
		return horizontalScore;
	}
	
	public int findRightDiagonalFlips(String tile, int row, int col) {
		boolean success = false;
		int rightDiagonalScore = 0;
		
		for(int i=1; row-i >=0 && col-i>=0; i++) {
			if(board[row-i][col-i]!=null && !board[row-i][col-i].equals(tile)) {
				success = true;
			}
			else if(success && board[row-i][col-i] != null && board[row-i][col-i].equals(tile)) {
				this.setFlippedTiles(new Position(row-i, col-i));
				for(int position=i-1; position!=0; position--) {
					board[row-position][col-position] = tile;
					this.setFlippedTiles(new Position(row-position, col-position));
					rightDiagonalScore ++;
				}
				
				break;
			}
			else {
				success = false;
				break;
			}
		}
		
		success = false;

		for(int i=1; row+i < this.BOARD_SIZE && col + i < this.BOARD_SIZE; i++) {
			if(board[row+i][col +i]!=null && !board[row+i][col +i].equals(tile)) {
				success = true;
			}
			else if(success && board[row+i][col +i]!=null && board[row+i][col +i].equals(tile)) {
				this.setFlippedTiles(new Position(row+i, col+i));
				for(int position=i-1; position!=0; position--) {
					board[row+position][col+position] = tile;
					this.setFlippedTiles(new Position(row+position, col+position));
					rightDiagonalScore ++;
				}
				
				break;
			}
			else {
				success = false;
				break;
			}
		}
		
		return rightDiagonalScore;
	}
	
    public int findLeftDiagonalFlips(String tile, int row, int col){
    	boolean success = false;
    	int leftDiagonalScore = 0;
    	
		for(int i=1; row+i <this.BOARD_SIZE && col-i>=0; i++) {
			if(board[row+i][col-i]!=null && !board[row+i][col-i].equals(tile)) {
				success = true;
			}
			else if(success && board[row+i][col-i] != null && board[row+i][col-i].equals(tile)) {
				this.setFlippedTiles(new Position(row+i, col-i));
				for(int position=i-1; position!=0; position--) {
					board[row+position][col-position] = tile;
					this.setFlippedTiles(new Position(row+position, col-position));
					leftDiagonalScore ++;
				}
				
				break;
			}
			else {
				success = false;
				break;
			}
		}
		
		success = false;

		for(int i=1; row-i >=0 && col + i < this.BOARD_SIZE; i++) {
			if(board[row-i][col +i]!=null && !board[row-i][col +i].equals(tile)) {
				success = true;
			}
			else if(success && board[row-i][col +i]!=null && board[row-i][col +i].equals(tile)) {
				this.setFlippedTiles(new Position(row-i, col+i));
				for(int position=i-1; position!=0; position--) {
					board[row-position][col+position] = tile;
					this.setFlippedTiles(new Position(row-position, col+position));
					leftDiagonalScore ++;
				}
				
				break;
			}
			else {
				success = false;
				break;
			}
		}
		
		return leftDiagonalScore;
    }
    
    public int findVerticalScore(String tile, int row, int col) {
		boolean success = false;
		int verticalScore = 0;
		
		for(int i=row; i>0; i--) {
			if(board[i-1][col] != null && !board[i-1][col].equals(tile)) {
				success = true;
			}
			else if(success && board[i-1][col]!= null && board[i-1][col].equals(tile)) {
				for(int j=i; j<row; j++) {
					verticalScore ++;
				}
				break;
			}
			else {
				success = false;
				break;
			}
		}
		
		success = false;

		for(int i=row; i<BOARD_SIZE-1; i++) {
			if(board[i+1][col]!=null && !board[i+1][col].equals(tile)) {
				success = true;
			}
			else if(success && board[i+1][col]!=null && board[i+1][col].equals(tile)) {
				for(int j=i; j>row; j--) {
					verticalScore ++;
				}
				break;
			}
			else {
				success = false;
				break;
			}
		}
		
		return verticalScore;
	}
    
    public int findHorizontalScore(String tile, int row, int col) {
		boolean success = false;
		int horizontalScore = 0;
		
		for(int i=col; i>0; i--) {
			if(board[row][i-1]!=null && !board[row][i-1].equals(tile)) {
				success = true;
			}
			else if(success && board[row][i-1]!=null && board[row][i-1].equals(tile)) {
				for(int j=i; j<col; j++) {
					horizontalScore ++;
				}
				break;
			}
			else {
				success = false;
				break;
			}
		}
		
		success = false;

		for(int i=col; i<BOARD_SIZE-1; i++) {
			if(board[row][i+1]!=null && !board[row][i+1].equals(tile)) {
				success = true;
			}
			else if(success && board[row][i+1]!=null && board[row][i+1].equals(tile)) {
				for(int j=i; j>col; j--) {
					horizontalScore ++;
				}
				break;
			}
			else {
				success = false;
				break;
			}
		}
		
		return horizontalScore;
	}
	
	public int findRightDiagonalScore(String tile, int row, int col) {
		boolean success = false;
		int rightDiagonalScore = 0;
		
		for(int i=1; row-i >=0 && col-i>=0; i++) {
			if(board[row-i][col-i]!=null && !board[row-i][col-i].equals(tile)) {
				success = true;
			}
			else if(success && board[row-i][col-i] != null && board[row-i][col-i].equals(tile)) {
				for(int position=i-1; position!=0; position--) {
					rightDiagonalScore ++;
				}
				break;
			}
			else {
				success = false;
				break;
			}
		}
		
		success = false;

		for(int i=1; row+i < this.BOARD_SIZE && col + i < this.BOARD_SIZE; i++) {
			if(board[row+i][col +i]!=null && !board[row+i][col +i].equals(tile)) {
				success = true;
			}
			else if(success && board[row+i][col +i]!=null && board[row+i][col +i].equals(tile)) {
				for(int position=i-1; position!=0; position--) {
					rightDiagonalScore ++;
				}
				break;
			}
			else {
				success = false;
				break;
			}
		}
		
		return rightDiagonalScore;
	}
	
    public int findLeftDiagonalScore(String tile, int row, int col){
    	boolean success = false;
    	int leftDiagonalScore = 0;
    	
		for(int i=1; row+i <this.BOARD_SIZE && col-i>=0; i++) {
			if(board[row+i][col-i]!=null && !board[row+i][col-i].equals(tile)) {
				success = true;
			}
			else if(success && board[row+i][col-i] != null && board[row+i][col-i].equals(tile)) {
				for(int position=i-1; position!=0; position--) {
					leftDiagonalScore ++;
				}
				break;
			}
			else {
				success = false;
				break;
			}
		}
		
		success = false;

		for(int i=1; row-i >=0 && col + i < this.BOARD_SIZE; i++) {
			if(board[row-i][col +i]!=null && !board[row-i][col +i].equals(tile)) {
				success = true;
			}
			else if(success && board[row-i][col +i]!=null && board[row-i][col +i].equals(tile)) {
				for(int position=i-1; position!=0; position--) {
					leftDiagonalScore ++;
				}
				break;
			}
			else {
				success = false;
				break;
			}
		}
		
		return leftDiagonalScore;
    }
    
	public void showBoard() {
		for(int i=0; i<BOARD_SIZE; i++) {
			for(int j=0; j<BOARD_SIZE; j++) {
				System.out.print(this.board[i][j] + "|");
			}
			System.out.println();
		}
	}
}
