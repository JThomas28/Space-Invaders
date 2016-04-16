import java.awt.Color;
import java.awt.Graphics;

public class SImissle extends SIthing {
	
	public SImissle(int x, int y){
		super(null, x, y);
		setVisible(false);
	}
	@Override
	public void drawImage(Graphics g) {
		if(getVisibility()){
			g.setColor(Color.WHITE);
			g.fillRect(getX(), getY(), 2, 10);
		}
	}

	@Override
	public void moveLeft() {}

	@Override
	public void moveRight() {}
	
	@Override
	public void moveUp() {
		if(getY() > 0){
			setY(getY() - 15);
		}
		else{
			setVisible(false);
			
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
