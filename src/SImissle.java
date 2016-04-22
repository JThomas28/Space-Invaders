import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

public class SImissle extends SIthing {
	private Color color;
	public SImissle(int x, int y, Color c){
		super(null, x, y, new Dimension(2, 10));
		setVisible(false);
		color = c;
	}
	
	@Override
	public void drawImage(Graphics g) {
		if(getVisibility()){
			g.setColor(color);
			g.fillRect(getX(), getY(), 2, 10);
		}
	}

	@Override
	public void moveLeft() {}

	@Override
	public void moveRight() {}
	
	@Override
	public void moveUp() {
		if(getVisibility()){
			if(getY() > 0){
				setY(getY() - 15);
			}
			else{
				setVisible(false);
			}
		}
	}
	@Override
	public void moveDown() {
		if(getY() < 390){
			setY(getY() + 15);
		}
		else{
			setVisible(false);
		}
	}
}
