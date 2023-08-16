package reversi;

import java.util.ArrayList;

public class AIPlayer extends Player {
	
	public AIPlayer() {
		super("B");
	}
	
	public Position findBestMove(ReversiGameBoard board) {
		ArrayList<Tile> allValidMoves = findValidMoves(board);
		int bestMoveinx = 0;
		
		
		for(int i= 1; i< allValidMoves.size(); i++) {
			int bestScore = allValidMoves.get(bestMoveinx).getScore();
			if(allValidMoves.get(i).getScore() > bestScore) {
				bestScore = allValidMoves.get(i).getScore();
				bestMoveinx = i;
			}
		}
		
		return allValidMoves.size() == 0 ? null : allValidMoves.get(bestMoveinx).getPosition();
	}
}
