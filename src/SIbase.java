import java.awt.Graphics;
import java.awt.Image;
import java.net.URL;

import javax.swing.ImageIcon;

public class SIbase extends SIship{
	private Image base;
	public SIbase() {
		super("SIbase.gif", 250, 400);
		// TODO Auto-generated constructor stub
	}
	@Override
	public void drawImage(Graphics g) {
		
	}

	@Override
	public void moveImage(int xUnits, int yUnits) {
		// TODO Auto-generated method stub
	}
	
	private Image getImage(String filename){
		URL url = getClass().getResource(filename);
		ImageIcon image = new ImageIcon(url);
		return image.getImage();
	}
}
