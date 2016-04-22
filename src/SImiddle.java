import java.awt.Dimension;

/**
 * SImiddle initializes middle aliens
 * 
 * @author JonathanThomas
 * @version 4/22/16
 */
public class SImiddle extends SIinvader {
	/**
	 * Constructor for initializing middle aliens at given position
	 * 
	 * @param x
	 *            - is the x coordinate of the alien
	 * @param y
	 *            - is the y coordinate of the alien
	 */
	public SImiddle(int x, int y) {
		super("SImiddle0.gif", x, y, 20, new Dimension(30, 20));
	}
}
