import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

public class SI extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private SIPanel panel;

	public SI() {
		super("Space Invaders");

		panel = new SIPanel();
		add(panel);
		
		JMenuBar menus = new JMenuBar();
		JMenu help = new JMenu("Help");
		JMenu game = new JMenu("Game");
		JMenuItem about = new JMenuItem("About...");
		JMenuItem newGame = new JMenuItem("New Game");
		JMenuItem pause = new JMenuItem("Pause");
		JMenuItem resume = new JMenuItem("Resume");
		JMenuItem quit = new JMenuItem("Quit");

		// add all sub menus to game menu
		game.add(newGame);
		game.add(pause);
		game.add(resume);
		game.add(quit);
		help.add(about);

		// add menu bar to the window
		menus.add(game);
		menus.add(help);
		//setJMenuBar(menus);

		about.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Space Invaders\nby JonathanThomas", "About...",
						JOptionPane.INFORMATION_MESSAGE);
			}
		});
		
		// new game action listener
		newGame.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int result = JOptionPane.showConfirmDialog(SI.this, "Start a new Game?", "Confirm",
						JOptionPane.YES_NO_OPTION);
				pause.doClick();
				if (result == JOptionPane.YES_OPTION) {
					// start new game
					panel.newGame();
				}

				else {
					// continue with regular game. User chose no
				}
			}
		});

		pause.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// pause game. Could I just pause the timer? Should I print
				// "paused" message to screen?
				panel.pause();
			}
		});

		resume.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// resume the game. As simple as continuing the timer?
				panel.start();
			}
		});
		
		quit.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				int result = JOptionPane.showConfirmDialog(SI.this, "Dare to Quit?", "Select an Option",
						JOptionPane.YES_NO_OPTION);
				if (result == JOptionPane.YES_OPTION) {
					dispose();
				}
			}
		});
		
		addWindowListener(new WindowAdapter(){
			@Override
			public void windowClosing(WindowEvent e){
				super.windowClosing(e);
				dispose();
			}
		});
		
		setResizable(false);
		setSize(500, 450);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
	}

	public static void main(String[] args) {
		JFrame si = new SI();
		si.setVisible(true);
	}
}
