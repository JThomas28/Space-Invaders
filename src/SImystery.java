import java.applet.AudioClip;
import java.awt.Dimension;
import java.util.Random;

public class SImystery extends SIinvader {
	private static Random rand = new Random();
	private static boolean movingRight;
	private AudioClip sound;
	public SImystery() {
		super("SImystery.gif", chooseX(), 20, getPoints(), new Dimension(30, 20));
		sound = getSound("SImystery.wav");
	}
	
	private static int getPoints() {
		int x = rand.nextInt(4);
		if(x == 0){
			return 50;
		}
		else if(x == 1){
			return 100;
		}
		else if(x == 2){
			return 150;
		}
		else{
			return 300;
		}
	}

	private static int chooseX() {
		int a = rand.nextInt(2);
		if(a == 0){
			movingRight = true;
			return 0;
		}
		else{
			movingRight = false;
			return 460;
		}
	}
	
	public boolean getMovingRight(){
		return movingRight;
	}
	
	public AudioClip getSound(){
		return sound;
	}
	
	public void moveMystery(){
		if(!getMovingRight()){
			if(getX() > -5){
				setX(getX() - 10);
			}
			else{
				setVisible(false);
			}
		}
		else{
			if(getX() < 500){
				setX(getX() + 10);
			}
			else{
				setVisible(false);
			}
		}
	}
}
