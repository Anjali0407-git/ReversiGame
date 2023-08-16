package reversi;
import java.awt.Color;
import java.awt.Component;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ScorePanel extends JPanel{

	private static JLabel aiScoreLabel;
	private static JLabel humanScoreLabel;
	
	public ScorePanel() {
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
	    this.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
	    this.setBackground(new Color(255, 102, 102));
	    
	    aiScoreLabel = new JLabel("AI Score: 2");
	    aiScoreLabel.setAlignmentX(Component.RIGHT_ALIGNMENT);
	    this.add(aiScoreLabel);
	     
	     humanScoreLabel = new JLabel("Your Score: 2");
	     humanScoreLabel.setAlignmentX(Component.RIGHT_ALIGNMENT);
	     this.add(humanScoreLabel);
	}
	
	
	public static void updateScoreLabels(AIPlayer aiPlayer, HumanPlayer humanPlayer) {
		aiScoreLabel.setText(String.valueOf("AI Score: " + aiPlayer.getScore()));
		humanScoreLabel.setText(String.valueOf("Your Score: " + humanPlayer.getScore()));
	}
}
