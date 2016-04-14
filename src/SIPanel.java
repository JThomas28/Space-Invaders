import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class SIPanel extends JPanel {
	private SIbase base;
	private static final long serialVersionUID = 1L;
	public SIPanel(){
		setBackground(Color.BLACK);
		setFocusable(true);
		initializeBase();
	}
	
	public void initializeBase(){
		base = new SIbase("SIbase.gif", 250, 400);
	}
	
	@Override
	protected void paintComponent(Graphics g){
		super.paintComponent(g);
		base.drawImage(g);
	}

	public Image getImage(String filename){
		URL url = getClass().getResource(filename);
		ImageIcon image = new ImageIcon(url);
		return image.getImage();
	}
}
