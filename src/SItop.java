import java.awt.Dimension;

/**
 * SItop is the class responsible for initializing top row aliens
 * 
 * @author JonathanThomas
 *
 */
public class SItop extends SIinvader {
	/**
	 * Constructor for initializing top row aliens at given position
	 * 
	 * @param x
	 *            - is the x coordinate of the alien
	 * @param y
	 *            - is the y coordinate of the alien
	 */
	public SItop(int x, int y) {
		super("SItop0.gif", x, y, 30, new Dimension(30, 20));
	}
}
