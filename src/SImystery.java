import java.awt.Dimension;
import java.util.Random;

public class SImystery extends SIinvader {
	private static Random rand = new Random();
	private static boolean moving;
	public SImystery(int x, int y) {
		super("SImystery.gif", chooseX(), 20, getPoints(), new Dimension(40, 30));
	}
	
	private static int getPoints() {
		int x = rand.nextInt(4);
		if(x == 0){
			
		}
	}

	private static int chooseX() {
		int a = rand.nextInt(2);
		if(a == 0){
			moving = true;
			return 0;
		}
		else{
			moving = true;
			return 460;
		}
	}
	
	public boolean getMoving(){
		return moving;
	}
}
