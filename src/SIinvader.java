import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

public abstract class SIinvader extends SIship {
	private int pointVal;
	private Image invaderHit;
	private Image alienType;

	public SIinvader(String img, int x, int y, int point, Dimension size) {
		super(img, x, y, size);
		alienType = getImage(img);
		invaderHit = getImage(getImageLocation());
		pointVal = point;
	}

	@Override
	public void drawImage(Graphics g) {
		g.drawImage(alienType, getX(), getY(), getSize().width, getSize().height, null);
	}

	@Override
	public void moveLeft() {
		if(getX() > 9){
			setX(getX() - 10);
		}
	}

	@Override
	public void moveRight() {
		if(getX() < 470){
			setX(getX() + 10);
		}
	}

	@Override
	public void moveUp() {
		// TODO Auto-generated method stub

	}

	@Override
	public void moveDown() {
		setY(getY() + 10);
		
	}
	
	public Image getInvaderHit(){
		return invaderHit;
	}
	public int getPointVal(){
		return pointVal;
	}

}
