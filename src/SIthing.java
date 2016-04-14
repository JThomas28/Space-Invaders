
public abstract class SIthing {
	private String imageLocation;
	

	private int x;
	private int y;
	private int width;
	private int height;
	private boolean isVisible;
	
	public SIthing(String image, int xPos, int yPos, int width, int height){
		this.imageLocation = image;
		this.x = xPos;
		this.y = yPos;
		this.width = width;
		this.height = height;
		isVisible = true;
	}
	
	public abstract void draw();
	public abstract void move(int xUnits, int yUnits);
	
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

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}
}
