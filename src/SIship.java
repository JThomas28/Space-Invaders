import java.applet.AudioClip;
import java.awt.Dimension;
import java.awt.Image;

public abstract class SIship extends SIthing {
	private int xPos;
	private int yPos;
	private boolean hit;
	private AudioClip explosion;
	private Image destroyedBase;
	private Image destroyedAlien;

	public SIship(String string, int x, int y, Dimension size) {
		super(string, x, y, size);
		destroyedBase = getImage("SIbaseBlast.gif");
		destroyedAlien = getImage("SIinvaderBlast.gif");
	}

	public boolean testShipHit(SImissle missle) {
		if (missle.getX() == getX() + 24 && missle.getY() == getY()) {
			hit = true;
			destroyShip();
			explosion.play();
		}
		else {
			hit = false;
		}

		return hit;
	}
	
	
	public AudioClip invaderHitSound(){
		explosion = getSound("SIshipHit.wav");
		return explosion;
	}
	
	public void destroyShip(){
		setVisible(false);
	}
}
