package reversi;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.Timer;
import javax.swing.border.EmptyBorder;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GridPanel extends JPanel{
	
	private static final int BOARD_SIZE = 8;
	private static final int BUTTON_SIZE = 50;
	private static JButton[][] buttons;
	private static ReversiGameBoard board;
	private static MainController controller;
	private static Timer delayTimer;
	
	public GridPanel(ReversiGameBoard gameBoard, MainController controller) {
		this.board = gameBoard;
		this.controller = controller;
		this.setLayout(new GridLayout(BOARD_SIZE, BOARD_SIZE));
		this.setBorder(new EmptyBorder(10, 0, 0, 0));
		
		buttons = new JButton[BOARD_SIZE][BOARD_SIZE];
		
		for(int row = 0; row < BOARD_SIZE; row++) {
			for(int col = 0; col < BOARD_SIZE; col++) {
				buttons[row][col] = new JButton();
				buttons[row][col].setPreferredSize(new Dimension(BUTTON_SIZE, BUTTON_SIZE));
				if(row == 3 || row == 4) {
					if(row == col) {
						buttons[row][col].setText("B");
					}
					else if(col == 3 || col == 4) {
						buttons[row][col].setText("W");
					}
				}
				
				this.add(buttons[row][col]);
				buttons[row][col].addActionListener(new ButtonClickListener(row, col));
			}
		}
	}
	
	// Action listener for each button
    private class ButtonClickListener implements ActionListener {
        private int row, col;

        public ButtonClickListener(int row, int col) {
            this.row = row;
            this.col = col;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
        	if(board.isPlayerTurn()) {
        		controller.handlePlayerTurn(new Position(row, col));
			}
        }
    }
	
	public static void updateBoard(String[][] grid) {
		// Highlight flipped tiles
	    ArrayList<Position> flippedTiles = board.getFlippedTiles();
	    highlightFlippedTiles(flippedTiles);
	    delayTimer = new Timer(1500, new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	            board.getFlippedTiles().clear();
	            for (int i = 0; i < BOARD_SIZE; i++) {
	                for (int j = 0; j < BOARD_SIZE; j++) {
	                    updateTile(new Position(i, j), grid[i][j]);
	                }
	            }
	        }
	    });

	    delayTimer.setRepeats(false);
	    delayTimer.start();
	}
	
	public static void updateTile(Position pos, String tile) {
		if(tile != null) {
			buttons[pos.getX()][pos.getY()].setText(tile);
		}
	}

	public static void resetBoardBackground() {
	    for (int i = 0; i < BOARD_SIZE; i++) {
	        for (int j = 0; j < BOARD_SIZE; j++) {
	            buttons[i][j].setBackground(null); // Reset background color to default (no background color)
	        }
	    }
	}
	
	public static void highlightFlippedTiles(ArrayList<Position> flippedTiles) {
		for (Position p : flippedTiles) {
	        buttons[p.getX()][p.getY()].setBackground(board.isPlayerTurn()? Color.GREEN: Color.YELLOW);  // Set the highlight color
	    }
	}
}
