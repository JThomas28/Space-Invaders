import java.applet.AudioClip;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

public class SIbase extends SIship {
	private Image baseImage;
	private Image baseShotImage;
	private AudioClip shotOut;

	//this constructor should be passed in the base's name, and it's initial position
	public SIbase(){
		super("SIbase.gif", 250, 350, new Dimension(40, 20));
		baseImage = getImage(getImageLocation());
		baseShotImage = getImage("SIbaseBlast.gif");
		baseShoot();
	}
	
	@Override
	public void drawImage(Graphics g) {
		g.drawImage(baseImage, getX(), getY(), getSize().width, getSize().height, null);
	}

	@Override
	public void moveLeft() {
		if(getX() > 9){
			setX(getX() - 10);
		}
	}
	
	@Override
	public void moveRight(){
		if(getX() < 470){
			setX(getX() + 10);
		}
	}
	
	public AudioClip baseShoot(){
		shotOut = getSound("SIbaseShoot.wav");
		return shotOut;
	}
	@Override
	public void moveUp() {}
	@Override
	public void moveDown() {}
}
