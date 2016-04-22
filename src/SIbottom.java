import java.awt.Dimension;

public class SIbottom extends SIinvader {
	private static String bottomImageString;
	private int x = 0;
	public SIbottom(int x, int y){
		super("SIbottom0.gif", x, y, 10, new Dimension(30, 20));
	}
	
	public String chooseImage(){
		if(x == 0){
			bottomImageString = "SIbottom0.gif";
			x = 1;
			return bottomImageString;
		}
		else{
			bottomImageString = "SIbottom1.gif";
			return bottomImageString;
		}
	}
}
