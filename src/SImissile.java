import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

/**
 * SImissle is the class responsible for creating, moving, and drawing missiles
 * 
 * @author JonathanThomas
 * @version 4/22/16
 */
public class SImissile extends SIthing {
	/**
	 * Color used to distinguish invader missiles from player missiles. Player
	 * Missiles are green, invader missiles are white
	 */
	private Color color;

	/**
	 * Missile constructor. Sets missle's x, y position and its color
	 * 
	 * @param x
	 *            - Missile initial x position
	 * @param y
	 *            - Missile initial y position
	 * @param c
	 *            - missile's color
	 */
	public SImissile(int x, int y, Color c) {
		super(null, x, y, new Dimension(2, 10));
		setVisible(false);
		color = c;
	}

	/**
	 * Override of draw image from SIthing. Missile's draw method draws a 2X10
	 * rectangle of the specified color at the specified location
	 */
	@Override
	public void drawImage(Graphics g) {
		if (getVisibility()) {
			g.setColor(color);
			g.fillRect(getX(), getY(), 2, 10);
		}
	}

	/**
	 * missiles do not move left or right, so these methods are not used
	 */
	@Override
	public void moveLeft() {
	}

	@Override
	public void moveRight() {
	}

	/**
	 * Overrides and implements the moveUp method from SIthing. Moves the missle
	 * up 15 pixels at a time as long as it is visible and not off the screen
	 */
	@Override
	public void moveUp() {
		if (getVisibility()) {
			if (getY() > 0) {
				setY(getY() - 15);
			}
			else {
				setVisible(false);
			}
		}
	}

	/**
	 * Overrides and implements moveDown from SIthing. Used for invader's
	 * missiles. Missiles are moved down 15 pixels at a time as long as they do
	 * not hit the base and are not off the screen
	 */
	@Override
	public void moveDown() {
		if (getVisibility()) {
			if (getY() < 390) {
				setY(getY() + 15);
			}
			else {
				setVisible(false);
			}
		}
	}
}
