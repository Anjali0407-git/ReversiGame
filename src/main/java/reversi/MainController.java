package reversi;

public class MainController {

	private static ReversiGameGUI gui;
	private static AIPlayer aiPlayer;
	private static HumanPlayer humanPlayer;
	private static ReversiGameBoard board;
	
	public MainController() {
		aiPlayer = new AIPlayer();
		humanPlayer = new HumanPlayer();
		board = new ReversiGameBoard();
		gui = new ReversiGameGUI(this, board);
	}
	
	public void handlePlayerTurn(Position pos) {
		int row = pos.getX();
		int col = pos.getY();
		
		if(humanPlayer.isValidMove(row, col, board)) {
			gui.updateCurrentTile(pos, humanPlayer.getTileType());
			String grid[][] = board.flipTiles(humanPlayer, aiPlayer, row, col, "W");
			
			gui.updateGamePanel(grid, aiPlayer, humanPlayer);
			gui.aiPlayGame();
		}
		else {
			gui.showInvalidMove();
		}
		
	}
	
	public void handleAITurn() {
		if(board.isBoardFull(aiPlayer, humanPlayer)) {
			gui.displayGameOutcome(determineGameOutcome(), Math.abs(aiPlayer.getScore() - humanPlayer.getScore()));
			return;
		}
		
		Position pos = aiPlayer.findBestMove(board);
		
		if(pos != null) {
			gui.updateCurrentTile(pos, aiPlayer.getTileType());
			String grid[][] = board.flipTiles(aiPlayer, humanPlayer, pos.getX(), pos.getY(), aiPlayer.getTileType());
			gui.updateGamePanel(grid, aiPlayer, humanPlayer);
			
			if(board.isBoardFull(aiPlayer, humanPlayer)) {
				gui.displayGameOutcome(determineGameOutcome(), Math.abs(aiPlayer.getScore() - humanPlayer.getScore()));
				return;
			}
		}
		else {
			if(humanPlayer.findValidMoves(board).size() == 0) {
				gui.showGameEnds();
				gui.displayGameOutcome(determineGameOutcome(), Math.abs(aiPlayer.getScore() - humanPlayer.getScore()));
				return;
			}
			else {
				gui.showNoValidMovesMessage();
				board.changeTurn();
				gui.updateTurnPanel(board.isPlayerTurn());
			}
		}
	}

	public void passGame() {
		if(humanPlayer.findValidMoves(board).size() != 0) {
			gui.showCannotPassMessage();
			return;
		}
		
	    if (board.isPlayerTurn()) {
	        // Handle the case where the player passes their turn
	        board.changeTurn();
	        gui.updateTurnPanel(board.isPlayerTurn());

	        if(aiPlayer.findBestMove(board) == null) {
	        	gui.showGameEnds();
				gui.displayGameOutcome(determineGameOutcome(), Math.abs(aiPlayer.getScore() - humanPlayer.getScore()));
				return;
	        }
	        else {
	        	// Call AI's turn immediately after player pass
		        handleAITurn();
	        }
	    } else {
	        // Inform the player that it's not their turn to pass
	        gui.showInvalidPassMessage();
	    }
	}
	
	public GameOutcome determineGameOutcome() {
		if (humanPlayer.getScore() > aiPlayer.getScore()) {
            return GameOutcome.PLAYER_WON;
        } else if (aiPlayer.getScore() > humanPlayer.getScore()) {
            return GameOutcome.AI_WON;
        } else {
            return GameOutcome.DRAW;
        }
	}
}
