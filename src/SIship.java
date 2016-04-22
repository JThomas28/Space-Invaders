import java.applet.AudioClip;
import java.awt.Dimension;
import java.awt.Image;

public abstract class SIship extends SIthing {
	private boolean hit = false;
	private AudioClip explosion;
	//private Image destroyedBase;
	private Image destroyedAlien;

	public SIship(String string, int x, int y, Dimension size) {
		super(string, x, y, size);
		//destroyedBase = getImage("SIbaseBlast.gif");
		destroyedAlien = getImage("SIinvaderBlast.gif");
		shipHitSound();
	}

	public boolean testShipHit(SImissle missle) {
		if (this.getVisibility() && missle.getVisibility()) {
			if (missle.getX() >= this.getX() && missle.getX() <= (this.getX() + this.getSize().getWidth())
					&& missle.getY() >= this.getY()
					&& missle.getY() <= (this.getY() + this.getSize().getHeight())) {
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

	public void setHit(boolean t) {
		hit = t;
	}

	public boolean getHit() {
		return hit;
	}

//	public Image getDestroyedAlienImage() {
//		return destroyedAlien;
//	}

//	public Image getDestroyedBaseImage() {
//		return destroyedBase;
//	}

	public AudioClip shipHitSound() {
		explosion = getSound("SIshipHit.wav");
		return explosion;
	}

//	public void changeImage() {
//		
//	}
}
