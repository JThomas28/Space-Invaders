import java.awt.Graphics;
import java.awt.Image;
import java.net.URL;

import javax.swing.ImageIcon;

public class SIbase extends SIship{
	private Image baseImage;
	public SIbase(String imageLoc, int xPos, int yPos) {
		super(imageLoc, xPos, yPos);
		baseImage = 
	}
	
	@Override
	public void drawImage(Graphics g) {
		g.drawImage(SIthing.getImage(getImageLocation()), getX(), getY(), null);
	}

	@Override
	public void moveImage(int xUnits, int yUnits) {
		
	}
	
	public Image getImage(String filename){
		URL url = getClass().getResource(filename);
		ImageIcon image = new ImageIcon(url);
		return image.getImage();
	}
	
}
