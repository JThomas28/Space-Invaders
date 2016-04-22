import java.applet.AudioClip;
import java.awt.Dimension;

public abstract class SIship extends SIthing {

	/**
	 * Common ship fields. Hit is true if ship hit, explosion is an audio clip
	 * played if a ship is hit, and destroyedAlien is the image of a destroyed
	 * alien
	 */
	private boolean hit = false;
	private AudioClip explosion;

	/**
	 * Ship's constructor. Creates a new ship with the specified image,
	 * position, and size
	 * 
	 * @param string
	 *            - location/name of the image
	 * @param x
	 *            - x coordinate of ship
	 * @param y
	 *            - y coordinate of the ship
	 * @param size
	 *            - size of the ship
	 */
	public SIship(String string, int x, int y, Dimension size) {
		super(string, x, y, size);
		shipHitSound();
	}

	/**
	 * This method tests if a given missle hits the ship
	 * 
	 * @param missle
	 *            - missle to test
	 * @return true - if missle intersects with ship's location, false otherwise
	 */
	public boolean testShipHit(SImissile missle) {
		if (this.getVisibility() && missle.getVisibility()) {
			if (missle.getX() >= this.getX() && missle.getX() <= (this.getX() + this.getSize().getWidth())
					&& missle.getY() >= this.getY() && missle.getY() <= (this.getY() + this.getSize().getHeight())) {
				hit = true;
				explosion.play();
				missle.setVisible(false);
			}
			else {
				hit = false;
			}
		}
		return hit;
	}

	/**
	 * Sets the hit boolean to t
	 * 
	 * @param t
	 *            - boolean to set value of hit to
	 */
	public void setHit(boolean t) {
		hit = t;
	}

	/**
	 * Return hit's value
	 * 
	 * @return hit- true if ship is hit, false otherwise
	 */
	public boolean getHit() {
		return hit;
	}

	/**
	 * Returns the ship hit sound
	 * 
	 * @return explosion - sound a ship makes when hit by missle
	 */
	public AudioClip shipHitSound() {
		explosion = getSound("SIshipHit.wav");
		return explosion;
	}
}
