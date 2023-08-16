package reversi;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class StatusPanel extends JPanel {
	
	private JLabel turnLabel;
	private JButton passButton;
	
	public StatusPanel(MainController controller) {
		this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
	    this.setBorder(BorderFactory.createEmptyBorder(30, 10, 20, 10));
	    this.setBackground(new Color(255, 255, 153));
	    turnLabel = new JLabel("IT'S YOUR TURN FIRST");
	    turnLabel.setBackground(Color.GREEN);
        
        JPanel panel1 = new JPanel();
        panel1.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        panel1.setBackground(new Color(255, 255, 153));
        panel1.add(turnLabel);
        
        JPanel panel2 = new JPanel();
        panel2.setLayout(new FlowLayout(FlowLayout.RIGHT, 10, 10));
        panel2.setBackground(new Color(255, 255, 153));
        passButton = new JButton("Pass");
        passButton.setBackground(new Color(255, 128, 0));
        passButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	controller.passGame();
            }
        });
        
        panel2.add(passButton);
        
        this.add(panel1);
        this.add(panel2);
        
	}
	
	public void updateTurnLabel(boolean isPlayerTurn) {
		String firstTurn = "IT'S AI TURN";
		String secondTurn = "IT'S YOUR TURN";
		turnLabel.setText(isPlayerTurn? secondTurn: firstTurn);
	}
}
