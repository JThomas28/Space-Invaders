import java.applet.AudioClip;
import java.awt.Dimension;
import java.awt.Image;

public abstract class SIship extends SIthing {
	private boolean hit = false;
	private AudioClip explosion;
	private Image destroyedAlien;

	public SIship(String string, int x, int y, Dimension size) {
		super(string, x, y, size);
		// destroyedBase = getImage("SIbaseBlast.gif");
		destroyedAlien = getImage("SIinvaderBlast.gif");
		shipHitSound();
	}

	/**
	 * This method tests if a given missle hits the ship
	 * 
	 * @param missle
	 *            - missle to test
	 * @return true - if missle intersects with ship's location, false otherwise
	 */
	public boolean testShipHit(SImissle missle) {
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

	public AudioClip shipHitSound() {
		explosion = getSound("SIshipHit.wav");
		return explosion;
	}
}
