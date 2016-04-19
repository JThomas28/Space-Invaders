import java.awt.Graphics;
import java.awt.Image;

public abstract class SIinvader extends SIship {
	private int pointVal;
	private Image invaderHit;
	private Image alienType;

	public SIinvader(String img, int x, int y, int point) {
		super(img, x, y);
		alienType = getImage(img);
		invaderHit = getImage(getImageLocation());
		pointVal = point;
	}

	@Override
	public void drawImage(Graphics g) {
		g.drawImage(alienType, getX(), getY(), null);
	}
	public void moveInvaders(){
		if(getX() > 425){
			
		}
	}

	@Override
	public void moveLeft() {
		// TODO Auto-generated method stub

	}

	@Override
	public void moveRight() {
		// TODO Auto-generated method stub

	}

	@Override
	public void moveUp() {
		// TODO Auto-generated method stub

	}

	@Override
	public void moveDown() {
		// TODO Auto-generated method stub

	}
	
	public Image getInvaderHit(){
		return invaderHit;
	}
	public int getPointVal(){
		return pointVal;
	}

}
