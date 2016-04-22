import java.applet.AudioClip;
import java.awt.Dimension;
import java.awt.Image;

public abstract class SIship extends SIthing {
	private boolean hit = false;
	private AudioClip explosion;
	private Image destroyedBase;
	private Image destroyedAlien;

	public SIship(String string, int x, int y, Dimension size) {
		super(string, x, y, size);
		destroyedBase = getImage("SIbaseBlast.gif");
		destroyedAlien = getImage("SIinvaderBlast.gif");
		shipHitSound();
	}

	public boolean testShipHit(SImissle missle) {
		if (this.getVisibility() && missle.getVisibility()) {
			if (missle.getX() + 2 >= this.getX() && missle.getX() <= (this.getX() + this.getSize().getWidth())
					&& missle.getY() - 10 >= this.getY() && missle.getY() -10 <= (this.getY() + this.getSize().getHeight())) {
				hit = true;
				explosion.play();
				missle.setVisible(false);
			}
			else{
				hit = false;
			}
		}
		return hit;
	}
	
	public boolean testBaseHit(SImissle m){
		if(this.getVisibility() && m.getVisibility()){
			if(m.getX() + 2 >= this.getX())
		}
	}
	
	public void setHit(boolean t){
		hit = t;
	}
	
	public boolean getHit() {
		return hit;
	}

	public Image getDestroyedAlienImage() {
		return destroyedAlien;
	}

	public Image getDestroyedBaseImage() {
		return destroyedBase;
	}

	public AudioClip shipHitSound() {
		explosion = getSound("SIshipHit.wav");
		return explosion;
	}
}
