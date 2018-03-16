import java.util.ArrayList;
import java.util.Random;
import javax.swing.JButton;

/**
 * Controls the non-GUI logic.
 * <p>
 * Creates and stores the play order and checks and records the player's action.
 * 
 * @author Kevin Holkeboer
 * @version 1.0
 * @since 1.0
 *
 */
public class GameController {
	/**
	 * The pattern the player must match.
	 */
	ArrayList<JButton> pattern = new ArrayList();
	/**
	 * The number correct buttons the player has selected each round.
	 */
	int playerLoc;
	/**
	 * A random number used to add a button to the pattern at the end of each round.
	 */
	Random num = new Random();
	/**
	 * Flag to say to if the player selected the correct button.
	 */
	boolean correctColor;
	/**
	 * Constructor initializes the game pattern and player location.
	 * 
	 * @param starter
	 * 			The first button that the player must press.
	 */
	public GameController (JButton starter) {
		pattern.add(starter);
		playerLoc = 0;
		
	}
	
	/**
	 * Compares the button the player clicked to the game pattern.
	 * 
	 * @param clickedButton
	 * 			The button the player click.
	 * @return true if the button matches the pattern at the current location otherwise return false.
	 */
	public boolean checkColor (JButton clickedButton) {
		
		if(clickedButton == pattern.get(playerLoc)) {
			correctColor = true;
		}
		else {
			correctColor = false;
		}
		return correctColor;
	}
	
	/**
	 * Adds a new button to the end of the pattern.
	 * 
	 * @param colors
	 * 			An array containing the four possible buttons of the pattern.
	 */
	public void newColor (JButton[] colors) {
		pattern.add(colors[num.nextInt(4)]);
		
	}
	
}
