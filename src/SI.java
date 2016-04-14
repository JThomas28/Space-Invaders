import javax.swing.JFrame;

public class SI extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public SI(){
		super("Space Invaders");
		
		SIPanel panel = new SIPanel();
		panel.setFocusable(true);
		add(panel);
		
		
		setResizable(false);
		setFocusable(true);
		setSize(500, 450);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}

	public static void main(String [] args){
		JFrame si = new SI();
		si.setVisible(true);
	}
}
