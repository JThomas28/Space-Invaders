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
		invaderHit = getImage("SIinvaderBlast.gif");
		pointVal = point;
	}

	public boolean goOtherWay() {
		if (getX() + getSize().getWidth() > 470 || getX() < 30) {
			SIPanel.setDirection(!SIPanel.getDirection());
			return true;
		}
		return false;
	}

	@Override
	public void drawImage(Graphics g) {
		if (getHit()) {
			g.drawImage(invaderHit, getX(), getY(), getSize().width, getSize().height, null);
			setVisible(false);
		}
		else {
			g.drawImage(alienType, getX(), getY(), getSize().width, getSize().height, null);
		}
	}

	@Override
	public void moveLeft() {
		//if (getX() > 21) {
			setX(getX() - 20);
		//}
	}

	@Override
	public void moveRight() {
		//if (getX() + getSize().getWidth() < 479) {
			setX(getX() + 20);
		//}
	}

	@Override
	public void moveUp() {}

	@Override
	public void moveDown() {
		setY(getY() + 20);
	}

	public Image getInvaderHit() {
		return invaderHit;
	}

	public int getPointVal() {
		return pointVal;
	}
}
