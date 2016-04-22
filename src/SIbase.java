import java.applet.AudioClip;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

/**
 * SIbase is responsible for creating the Space invader's base, moving it, and
 * drawing it
 * 
 * @author JonathanThomas
 * @version 4/22/16
 */
public class SIbase extends SIship {
	/**
	 * baseImage is the normal base image, baseShotImage is the destroyed base
	 * image
	 */
	private Image baseImage;
	private Image baseShotImage;

	/**
	 * Constructs a new base in the middle of the frame
	 */
	public SIbase() {
		super("SIbase.gif", 250, 390, new Dimension(40, 20));
		baseImage = getImage(getImageLocation());
		baseShotImage = getImage("SIbaseBlast.gif");
	}

	/**
	 * Base's drawImage method. If the base is hit, it draws the base hit image.
	 * Otherwise, it draws the regular image at the current position
	 */
	@Override
	public void drawImage(Graphics g) {
		if (getHit()) {
			g.drawImage(baseShotImage, getX(), getY(), getSize().width, getSize().height, null);
			shipHitSound().play();
		}
		else {
			g.drawImage(baseImage, getX(), getY(), getSize().width, getSize().height, null);
		}
	}

	/**
	 * moveLeft moves the base 10 units left as long as it is in the bounds of
	 * the frame
	 */
	@Override
	public void moveLeft() {
		if (getX() >= 10) {
			setX(getX() - 10);
		}
	}

	/**
	 * moveRight moves the base 10 units right as long as it's within the frame
	 * bounds
	 */
	@Override
	public void moveRight() {
		if (getX() + getSize().getWidth() <= 490) {
			setX(getX() + 10);
		}
	}

	/**
	 * baseShoot returns the sound the base makes when shooting a missle
	 * 
	 * @return si base shoot sound
	 */
	public AudioClip baseShoot() {
		return getSound("SIbaseShoot.wav");
	}

	/**
	 * base doesn't move up or down so these methods are not used
	 */
	@Override
	public void moveUp() {
	}

	@Override
	public void moveDown() {
	}
}
