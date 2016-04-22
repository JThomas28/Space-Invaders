import java.applet.AudioClip;
import java.awt.Dimension;
import java.util.Random;

/**
 * SImystery is the class responsible for creating and moving the mystery ship
 * 
 * @author JonathanThomas
 * @version 4/22/16
 */
public class SImystery extends SIinvader {
	/**
	 * Fields used in this class. Rand is a random number generator used to
	 * choose a point value, movingRight is a boolean which returns true if the
	 * mystery ship is moving right, and sound is an audioclip which holds the
	 * mystery ship's sound
	 */
	private static Random rand = new Random();
	private static boolean movingRight;
	private AudioClip sound;

	/**
	 * Constructor for mystery ships
	 */
	public SImystery() {
		super("SImystery.gif", chooseX(), 20, getPoints(), new Dimension(30, 20));
	}

	/**
	 * Returns the point value the mystery ship is worth
	 * 
	 * @return point value of the ship
	 */
	private static int getPoints() {
		int x = rand.nextInt(4);
		if (x == 0) {
			return 50;
		}
		else if (x == 1) {
			return 100;
		}
		else if (x == 2) {
			return 150;
		}
		else {
			return 300;
		}
	}

	/**
	 * uses random number generator to choose whether the mystery ship should
	 * move right or left
	 * 
	 * @return movingRight - true if random number 0 generated, false if 1
	 *         generated. (Only possible to generate 0 or 1)
	 */
	private static int chooseX() {
		int a = rand.nextInt(2);
		if (a == 0) {
			movingRight = true;
			return 0;
		}
		else {
			movingRight = false;
			return 460;
		}
	}

	/**
	 * 
	 * @return
	 */
	public boolean getMovingRight() {
		return movingRight;
	}

	/**
	 * returns the sound the mystery ship makes
	 * 
	 * @return mystery ship's sound
	 */
	public AudioClip getSound() {
		sound = getSound("SImystery.wav");
		return sound;
	}

	/**
	 * Responsible for moving the ship across the screen
	 */
	public void moveMystery() {
		if (!getMovingRight()) {
			if (getX() > -5) {
				setX(getX() - 10);
			}
			else {
				setVisible(false);
			}
		}
		else {
			if (getX() < 500) {
				setX(getX() + 10);
			}
			else {
				setVisible(false);
			}
		}
	}
}
