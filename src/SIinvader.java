import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

/**
 * SIinvader holds all the things and methods invaders have in common
 * 
 * @author JonathanThomas
 * @version 4/22/16
 */
public abstract class SIinvader extends SIship {
	/**
	 * Private fields for pointValue for the invader, image of invader hit, and
	 * image of particular invader
	 */
	private int pointVal;
	private Image invaderHit;
	private Image alienType;

	/**
	 * Invader constructor. Creates new invader based on parameters passed in
	 * 
	 * @param img
	 *            - image location of the invader
	 * @param x
	 *            - x coordinate of invader
	 * @param y
	 *            - y coordinate of the invader
	 * @param point
	 *            - point value of the invader
	 * @param size
	 *            - size of the invader
	 */
	public SIinvader(String img, int x, int y, int point, Dimension size) {
		super(img, x, y, size);
		alienType = getImage(img);
		invaderHit = getImage("SIinvaderBlast.gif");
		pointVal = point;
	}

	/**
	 * Used to move invaders across the screen. If the rightmost invader reaches
	 * the right side of the screen, we move down and start moving left and vice
	 * versa
	 * 
	 * @return true if we reach the edge of the window, false otherwise
	 */
	public boolean goOtherWay() {
		if (getX() + getSize().getWidth() > 470 || getX() < 30) {
			SIPanel.setDirection(!SIPanel.getDirection());
			return true;
		}
		return false;
	}

	/**
	 * If the invader is hit, we draw the hit image and set its visibility to
	 * false so it will disappear
	 */
	@Override
	public void drawImage(Graphics g) {
		if (getHit()) {
			g.drawImage(invaderHit, getX(), getY(), getSize().width, getSize().height, null);
			setVisible(false);
		}
		else {
			g.drawImage(alienType, getX(), getY(), getSize().width, getSize().height, null);
		}
	}

	/**
	 * Moves the invader 20 pixels left
	 */
	@Override
	public void moveLeft() {
		setX(getX() - 20);
	}

	/**
	 * moves invader 20 pixels right
	 */
	@Override
	public void moveRight() {
		setX(getX() + 20);
	}

	/**
	 * invaders don't move up
	 */
	@Override
	public void moveUp() {
	}

	/**
	 * Moves invaders 20 pixels down toward base
	 */
	@Override
	public void moveDown() {
		setY(getY() + 20);
	}

	/**
	 * Returns the image of a destroyed invader
	 * 
	 * @return invaderHit - image of destroyed invader
	 */
	public Image getInvaderHit() {
		return invaderHit;
	}

	/**
	 * Returns the point value of the invader
	 * 
	 * @return pointVal - how many points hitting the invader is worth
	 */
	public int getPointVal() {
		return pointVal;
	}
}
