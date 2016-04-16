import javax.swing.JFrame;

public class SI extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SI() {
		super("Space Invaders");

		SIPanel panel = new SIPanel();
		add(panel);

		setResizable(false);
		setSize(500, 450);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}

	public static void main(String[] args) {
		JFrame si = new SI();
		si.setVisible(true);
	}
}
