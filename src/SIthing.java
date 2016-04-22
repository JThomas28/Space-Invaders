import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.net.URL;

import javax.swing.ImageIcon;

public abstract class SIthing {
	/**
	 * The fields common to all things in the frame. an image location, x and y
	 * position, a size (width and height), and a bolean telling whether the
	 * thing is visible.
	 */
	private String imageLocation;
	private int x, y;
	private Dimension size;
	private boolean isVisible;

	/**
	 * Creates a new SIthing with the given image, position, and size
	 * 
	 * @param image
	 *            - image location of the thing
	 * @param xPos
	 *            - desired x position of the thing
	 * @param yPos
	 *            - desired y position of the thing
	 * @param size
	 *            - the desired size of the thing
	 */
	public SIthing(String image, int xPos, int yPos, Dimension size) {
		this.imageLocation = image;
		this.size = size;
		this.x = xPos;
		this.y = yPos;
		new Rectangle(xPos, yPos, size.width, size.height);
		isVisible = true;
	}

	/**
	 * Creation of the drawImage method. Is overidden in each of the different
	 * "thing's" classes
	 * 
	 * @param g
	 *            - graphics
	 */
	public abstract void drawImage(Graphics g);

	/**
	 * these are all the move methods used to move things.
	 */
	public abstract void moveLeft();

	public abstract void moveRight();

	public abstract void moveUp();

	public abstract void moveDown();

	/**
	 * getSound returns the audio clip with the given name
	 * 
	 * @param filename
	 *            - name of the audio clip
	 * @return the audio clip with that name
	 */
	public AudioClip getSound(String filename) {
		URL url = getClass().getResource(filename);
		return Applet.newAudioClip(url);
	}

	/**
	 * returns the size of the thing's image. (Width and height)
	 * 
	 * @return
	 */
	public Dimension getSize() {
		return size;
	}

	/**
	 * Assigns the given boolean to visible variable
	 * 
	 * @param b
	 *            - boolean value to set to visible
	 */
	public void setVisible(boolean b) {
		isVisible = b;
	}

	/**
	 * Tells whether the given thing is visible or not
	 * 
	 * @return true if the "thing" is visible, false otherwise
	 */
	public boolean getVisibility() {
		return isVisible;
	}

	/**
	 * returns the string or location of the image associated with the "thing"
	 * 
	 * @return imageLocation - location of the image
	 */
	public String getImageLocation() {
		return imageLocation;
	}

	/**
	 * Sets the imageLocation variable to the value passed in
	 * 
	 * @param imageLocation
	 *            - value to be assigned to imageLocation
	 */
	public void setImageLocation(String imageLocation) {
		this.imageLocation = imageLocation;
	}

	/**
	 * returns the x value of the thing
	 * 
	 * @return x -x position of this "thing"
	 */
	public int getX() {
		return x;
	}

	/**
	 * Sets the x value of this "thing" to the value passed in
	 * 
	 * @param x
	 *            - value to be assigned to x
	 */
	public void setX(int x) {
		this.x = x;
	}

	/**
	 * returns the y value of the thing
	 * 
	 * @return y -y position of this "thing"
	 */
	public int getY() {
		return y;
	}

	/**
	 * Sets the y value of this "thing" to the value passed in
	 * 
	 * @param y
	 *            -value to be assigned to y
	 */
	public void setY(int y) {
		this.y = y;
	}

	/**
	 * Returns the image associated with the name passed in
	 * 
	 * @param filename
	 *            - string/name of the image
	 * @return image associated with the name string
	 */
	public Image getImage(String filename) {
		URL url = getClass().getResource(filename);
		ImageIcon image = new ImageIcon(url);
		return image.getImage();
	}
}
