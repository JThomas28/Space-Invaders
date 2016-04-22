import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.net.URL;

import javax.swing.ImageIcon;

public abstract class SIthing {
	private String imageLocation;
	private int x, y;
	private Dimension size;
	private Rectangle rectangle;
	private boolean isVisible;

	public SIthing(String image, int xPos, int yPos, Dimension size) {
		this.imageLocation = image;
		this.size = size;
		this.x = xPos;
		this.y = yPos;
		rectangle = new Rectangle(xPos, yPos, size.width, size.height);
		isVisible = true;
	}

	public abstract void drawImage(Graphics g);
	public abstract void moveLeft();
	public abstract void moveRight();
	public abstract void moveUp();
	public abstract void moveDown();
	

	public AudioClip getSound(String filename){
		URL url = getClass().getResource(filename);
		return Applet.newAudioClip(url);
	}
	
	public Dimension getSize(){
		return size;
	}
	
	public void setSize(Dimension size){
		this.size = size;
	}
	
	public void setVisible(boolean b) {
		isVisible = b;
	}

	public boolean getVisibility() {
		return isVisible;
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
	
	public Image getImage(String filename) {
		URL url = getClass().getResource(filename);
		ImageIcon image = new ImageIcon(url);
		return image.getImage();
	}

	public static void changeImage() {
		
	}
}
