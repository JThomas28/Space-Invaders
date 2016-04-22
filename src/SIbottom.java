import java.awt.Dimension;

/**
 * SIbottom initializes bottom aliens.
 * 
 * @author JonathanThomas
 * @version 4/22/16
 *
 */
public class SIbottom extends SIinvader {

	/**
	 * Constructor for initializing bottom aliens at given position
	 * 
	 * @param x
	 *            - is the x coordinate of the alien
	 * @param y
	 *            - is the y coordinate of the alien
	 */
	public SIbottom(int x, int y) {
		super("SIbottom0.gif", x, y, 10, new Dimension(30, 20));
	}
}
