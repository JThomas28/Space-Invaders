import java.applet.AudioClip;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

public class SIbase extends SIship {
	private Image baseImage;
	private Image baseShotImage;

	public SIbase(){
		super("SIbase.gif", 250, 390, new Dimension(40, 20));
		baseImage = getImage(getImageLocation());
		baseShotImage = getImage("SIbaseBlast.gif");
	}
	
	public Image getBaseShotImage(){
		return baseShotImage;
	}
	
	@Override
	public void drawImage(Graphics g) {
		if(getHit()){
			g.drawImage(baseShotImage, getX(), getY(), getSize().width, getSize().height, null);
			shipHitSound().play();
		}
		else{
			g.drawImage(baseImage, getX(), getY(), getSize().width, getSize().height, null);
		}
	}

	@Override
	public void moveLeft() {
		if(getX() >= 10){
			setX(getX() - 10);
		}
	}
	
	@Override
	public void moveRight(){
		if(getX() + getSize().getWidth() <= 490){
			setX(getX() + 10);
		}
	}
	
	public AudioClip baseShoot(){
		return getSound("SIbaseShoot.wav");
	}
	
	@Override
	public void moveUp() {}
	@Override
	public void moveDown() {}
}
