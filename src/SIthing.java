import java.awt.Graphics;

public abstract class SIthing {
	protected String imageLocation;
	protected int x;
	protected int y;
	
	private boolean isVisible;
	
	public SIthing(String image, int xPos, int yPos){
		this.imageLocation = image;
		this.x = xPos;
		this.y = yPos;
		isVisible = true;
	}
	
	public abstract void drawImage(Graphics g);
	public abstract void moveImage(int xUnits, int yUnits);
	
	public void setVisible(boolean b){
		isVisible = b;
	}
	
	public boolean getVisibility(){
		return isVisible;
	}
	
	public int getXPos(){
		return x;
	}
	
	public String getImageLocation() {
		return imageLocation;
	}

	public void setImageLocation(String imageLocation) {
		this.imageLocation = imageLocation;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
}
