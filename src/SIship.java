import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Image;
import java.net.URL;

public abstract class SIship extends SIthing {
	private int xPos;
	private int yPos;
	private boolean hit;
	private AudioClip explosion;
	private Image destroyedBase;
	private Image destroyedAlien;

	public SIship(String string, int x, int y) {
		super(string, x, y);
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
