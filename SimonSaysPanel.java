import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 * Creates the game board and responds to player actions.
 * <p>
 * Creates the four game buttons and a play button to show the pattern. Formats the layout.
 * <p>
 * Displays the pattern to the layer. Creates a GameController object.
 * 
 * @author Kevin Holkeboer
 * @version 1.0
 * @since 1.0
 */
public class SimonSaysPanel extends JPanel implements ActionListener {
	/**
	 * The red button of the game board.
	 */
	JButton redButton = new JButton("Red");
	/**
	 * The blue button of the game board.
	 */
	JButton blueButton = new JButton("Blue");
	/**
	 * The green button of the game board.
	 */
	JButton greenButton = new JButton("Green");
	/**
	 * The yellow button of the game board.
	 */
	JButton yellowButton = new JButton("Yellow");
	/**
	 * The play button of the game board.
	 */
	JButton playButton = new JButton("Play");
	/**
	 * An array containing all the game buttons that are added to the pattern.
	 */
	JButton[] colorButtons = {redButton, blueButton, greenButton, yellowButton};
	/**
	 * The active game. starting with the red button.
	 */
	GameController game = new GameController(redButton);
	/**
	 * The dialog box that tells the player they lost.
	 */
	JDialog lostGame = new JDialog();
	
	/**
	 * Constructor that creates the layout for the buttons.
	 * <p>
	 * Adds the colors and actionListeners to the buttons.
	 * 
	 */
	public SimonSaysPanel() {
		/**
		 * Sets the layout to GridBag.
		 */
		setLayout(new GridBagLayout());
		/**
		 * Constraint to place the red button in the top center.
		 */
		GridBagConstraints red = new GridBagConstraints();
		red.gridx = 100;
		red.gridy = 0;
		red.weighty = 1;
		/**
		 * constraint to place the blue button in the middle right.
		 */
		GridBagConstraints blue = new GridBagConstraints();
		blue.gridx = 200;
		blue.gridy = 100;
		blue.weightx = 1;
		/**
		 * Constraint to place the green button in the middle left.
		 */
		GridBagConstraints green = new GridBagConstraints();
		green.gridx = 100;
		green.gridy = 200;
		green.weighty = 1;
		/**
		 * constraint to place the yellow button in the bottom center.
		 */
		GridBagConstraints yellow = new GridBagConstraints();
		yellow.gridx = 0;
		yellow.gridy = 100;
		yellow.weightx = 1;
		/**
		 * constraint to place the play button in the middle center.
		 */
		GridBagConstraints playConst = new GridBagConstraints();
		playConst.gridx = 100;
		playConst.gridy = 100;
		playConst.weightx = 1;
		/**
		 * Adds ActionListener to all five buttons.
		 */
		redButton.addActionListener(this);
		blueButton.addActionListener(this);
		greenButton.addActionListener(this);
		yellowButton.addActionListener(this);
		playButton.addActionListener(this);
		/**
		 * Sets the background colors of the four game buttons.
		 */
		redButton.setBackground(Color.RED);
		blueButton.setBackground(Color.BLUE);
		greenButton.setBackground(Color.GREEN);
		yellowButton.setBackground(Color.YELLOW);
	
		//redButton.setOpaque(true);
		//blueButton.setOpaque(true);
		//greenButton.setOpaque(true);
		//yellowButton.setOpaque(true);
		/**
		 * Adds the buttons and their constraints to the board.
		 */
		add(redButton, red);
		add(blueButton, blue);
		add(yellowButton, green);
		add(greenButton, yellow);
		add(playButton, playConst);
		
	
		
	}
	/**
	 * Responds to the buttons being clicked.
	 * <p>
	 * The play button calls the flashButtons method to display the current pattern.
	 * <p>
	 * The four colored game buttons call the GameController to check against the pattern and start a new round if the pattern is complete.
	 * <p>
	 * If the game button doesn't match the pattern, the GameController constructor is called to reset the game and a pop-up tells the player they lost.
	 */
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == playButton) {
		
			flashButtons(); 
		}
		if(e.getSource() != playButton) {
			
			if(e.getSource() instanceof JButton) {
				if(game.checkColor((JButton)e.getSource())) {
					game.playerLoc++;
					if(game.playerLoc >= game.pattern.size()) {
						game.newColor(colorButtons);
						flashButtons();
						game.playerLoc = 0;
					}
					else {
					}
				}
				else {
					JOptionPane.showInternalMessageDialog(getParent(), "Game Reset");
					game = new GameController(redButton);
				}
				
			}
			
		}
	}
	/**
	 * Alternate the background colors of the game buttons to display the current pattern.
	 * 
	 */
	private void flashButtons () {
		/**
		 * Indicates the current button to change color.
		 */
		int gameLoc = 0;
		/**
		 * Indicates if the button's color has been reset to it's original color.
		 */
		boolean resetColors = false;
		/**
		 * Used to increase the delay time of each color change to make them visible.
		 */
		int delayMulti = 0;
		/**
		 * Loops through the current pattern changing and then reseting the buttons' colors.
		 */
		while (gameLoc < game.pattern.size()) {
			/**
			 * Increases the delay by one second for each color change and reset.
			 */
			int delay = (delayMulti*1000);
			/**
			 * Indicates which button's color will be changed.
			 */
			final Integer currentButton = new Integer(gameLoc);
			/**
			 * Indicates on true that the button's color has been reset to the original.
			 */
			final Boolean innerResetColors = new Boolean(resetColors);
			
			System.out.println(delay);
			/**
			 * Changes the color of the current button and calls resetColors to reset the original color.
			 */
			ActionListener setColor = new ActionListener() {

				public void actionPerformed(ActionEvent e) {
					if(innerResetColors) {
						resetColors();
					}
					else {
						game.pattern.get(currentButton).setBackground(Color.CYAN);
						System.out.println("set");
					}
					
				}
				
			};
			/**
			 * Delays the color changes by the time of delay.
			 */
			Timer timer = new Timer(delay,setColor);
			timer.setRepeats(false);
			timer.start();
			/**
			 * Increases the delay to offset color changes from each other.
			 */
			delayMulti++;
			/**
			 * Sets the next button to be flashed.
			 */
			if(resetColors) {
				gameLoc++;
				resetColors = false;
			}
			/**
			 * Allow the color of the current button to be reset.
			 */
			else {
				resetColors = true;
			}
		}
		
		
	}
	/**
	 * Resets the four game buttons to their original color.
	 */
	private void resetColors () {
		redButton.setBackground(Color.RED);
		blueButton.setBackground(Color.BLUE);
		greenButton.setBackground(Color.GREEN);
		yellowButton.setBackground(Color.YELLOW);
		System.out.println("reset");
	}
	
	
}
