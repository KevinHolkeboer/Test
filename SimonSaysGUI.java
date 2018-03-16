import java.awt.*;
import javax.swing.*;

/**
 * Creates the window and adds a gamePanel to it.
 * 
 * @author Kevin Holkeboer
 *@version 1.0
 *@since 1.0
 */
public class SimonSaysGUI extends JFrame {
	/**
	 * Constructs a window with default values.
	 */
	public SimonSaysGUI() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		
		SimonSaysPanel gamePanel = new SimonSaysPanel();
		//gamePanel.setPreferredSize(new Dimension(25,25));
		add(gamePanel);
		
		//JLabel title = new JLabel("Simon Says");
		//add(title, BorderLayout.NORTH);
		//pack();
	
		setSize(800,800);
		setTitle("Simon Says");
		setResizable(true);
		setVisible(true);
	}
}
