package reversi;
import javax.swing.*;
import javax.swing.Timer;

import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class ReversiGameGUI {
	
	private static MainController controller;
	private static ReversiGameBoard board;
	private static GridPanel gridPanel;
	private static ScorePanel scorePanel;
	private static StatusPanel statusPanel;
	private static Timer timer;
	private static Timer delayTimer;
	
	public ReversiGameGUI(MainController mainController, ReversiGameBoard board) {
		this.controller = mainController;
		this.board = board;

		JFrame frame = new JFrame();
		
		JPanel mainPanel = new JPanel();
		mainPanel.setBackground(new Color(0, 204, 204));
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
	    mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
	    
	    scorePanel = new ScorePanel();
	    gridPanel = new GridPanel(board, controller);
	    gridPanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
	    gridPanel.setOpaque(true);
	    statusPanel = new StatusPanel(controller);
	    
	    mainPanel.add(scorePanel);
	    mainPanel.add(gridPanel);
	    mainPanel.add(statusPanel);
	    
		frame.add(mainPanel);
		frame.setVisible(true);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public void aiPlayGame() {
		    timer = new Timer(3000, new ActionListener() {
		        @Override
		        public void actionPerformed(ActionEvent e) {
		            if (!board.isPlayerTurn()) {
		                controller.handleAITurn();
		            }
		        }
		    });
		    
		    timer.setRepeats(false);
		    timer.start();
	}
	
	public void updateGamePanel(String[][] grid, AIPlayer aiPlayer, HumanPlayer humanPlayer) {
		gridPanel.updateBoard(grid);
		scorePanel.updateScoreLabels(aiPlayer, humanPlayer);
		delayTimer = new Timer(1500, new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	            gridPanel.resetBoardBackground();
	            board.changeTurn();
	            statusPanel.updateTurnLabel(board.isPlayerTurn());
	        }
	    });
	    
	    delayTimer.setRepeats(false);
	    delayTimer.start();
	}
	
	public void updateCurrentTile(Position pos, String tileType) {
		gridPanel.updateTile(pos, tileType);
	}
	
	public void updateTurnPanel(boolean isPlayerTurn) {
		statusPanel.updateTurnLabel(isPlayerTurn);
	}
	
	public void showInvalidMove() {
		JOptionPane.showMessageDialog(null, "Invalid move! Try again.");
	}
	
	public void showNoValidMovesMessage() {
		JOptionPane.showMessageDialog(null, "No valid moves available. Turn passed to you.");
	}
	
	public void showInvalidPassMessage() {
		JOptionPane.showMessageDialog(null, "It's not your turn to pass.");
	}
	
	public void showCannotPassMessage() {
	    JOptionPane.showMessageDialog(null, "You have valid moves available. You cannot pass your turn.");
	}

	public void showGameEnds() {
		JOptionPane.showMessageDialog(null, "Oops! No valid moves available for both the players. Game Over!");
	}
	
	public void displayGameOutcome(GameOutcome outcome, int scoreDiff) {
		switch (outcome) {
		    case PLAYER_WON:
		    	JOptionPane.showMessageDialog(null, "Congratulations! You won the game by " + scoreDiff + " points.");
		        break;
		    case AI_WON:
		    	JOptionPane.showMessageDialog(null, "Sorry! You lost the game.");
		        break;
		    case DRAW:
		    	JOptionPane.showMessageDialog(null, "The game ended in a draw. Well played!");
		        break;
		}

	}
}
