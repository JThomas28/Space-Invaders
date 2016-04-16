import java.awt.Graphics;
import java.awt.Image;

public class SIbase extends SIship {
	private Image baseImage;

	//this constructor should be passed in the base's name, and it's initial position
	public SIbase(){
		super("SIbase.gif", 250, 400);
		baseImage = getImage(getImageLocation());
	}

	@Override
	public void drawImage(Graphics g) {
		g.drawImage(baseImage, getX(), getY(), null);
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

	@Override
	public void moveUp() {}

	@Override
	public void moveDown() {}
	
	public void shoot(){
		int x = getX();
		int y = getY();
		SImissle missle = new SImissle(x,y);
		missle.moveUp();
	}
}
