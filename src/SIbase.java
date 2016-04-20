import java.applet.AudioClip;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

public class SIbase extends SIship {
	private Image baseImage;
	private Image baseShotImage;
	private AudioClip shotOut;
	private SImissle missle;

	//this constructor should be passed in the base's name, and it's initial position
	public SIbase(){
		super("SIbase.gif", 250, 390, new Dimension(40, 20));
		baseImage = getImage(getImageLocation());
		baseShotImage = getImage("SIbaseBlast.gif");
		baseShoot();
	}
	
	public SImissle fire(){
		if(missle == null){
			missle = new SImissle((int) ((int)getX() + getSize().getWidth() /2), getY(), Color.green);
			missle.setVisible(true);
			baseShoot().play();
		}
		else{ 
			missle.moveUp();
			if(!missle.getVisibility()){
				missle = null;
			}
		}
		return missle;
	}
	
	public Image getBaseShotImage(){
		return baseShotImage;
	}
	
	@Override
	public void drawImage(Graphics g) {
		g.drawImage(baseImage, getX(), getY(), getSize().width, getSize().height, null);
	}

	@Override
	public void moveLeft() {
		setX(getX() - 10);
	}
	
	@Override
	public void moveRight(){
		setX(getX() + 10);
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
