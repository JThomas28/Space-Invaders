import java.awt.Dimension;

public class SItop extends SIinvader {
	private static int x = 1;
	private static String img = "SItop0.gif";
	public SItop(int x, int y){
		super(getCorrectImage(), x, y, 30, new Dimension(30, 20));
	}
	@Override
	public void changeImage(){
		if(x > 0){
			img = "SItop0.gif";
		}
		else{
			img = "SItop1.gif";
		}
		x *=-1;
	}
	
	public static String getCorrectImage(){
		return img;
	}
}
