import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.Box;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;

/**
 * Creates Space invader's window with all necessary components.
 * 
 * @author JonathanThomas
 * @version 4/22/16
 */
public class SI extends JFrame {

	private static final long serialVersionUID = 1L;
	private SIPanel panel;

	/**
	 * Jframe constructor. Creates window
	 */
	public SI() {
		super("Space Invaders");

		// create the game panel and add to frame
		panel = new SIPanel();
		add(panel);

		// create all menus and add to frame
		JMenuBar menus = new JMenuBar();
		JMenu help = new JMenu("Help");
		JMenu game = new JMenu("Game");
		JMenuItem about = new JMenuItem("About...");

		// Creating menu items and adding keyboard shortcuts
		JMenuItem newGame = new JMenuItem("New Game");
		newGame.setAccelerator(KeyStroke.getKeyStroke('N', Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
		JMenuItem pause = new JMenuItem("Pause");
		pause.setAccelerator(KeyStroke.getKeyStroke('P', Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
		JMenuItem resume = new JMenuItem("Resume");
		resume.setAccelerator(KeyStroke.getKeyStroke('R', Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
		JMenuItem quit = new JMenuItem("Quit");
		quit.setAccelerator(KeyStroke.getKeyStroke('Q', Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));

		// add all sub menus to game menu
		game.add(newGame);
		game.add(pause);
		game.add(resume);
		game.add(quit);
		help.add(about);

		// add menu bar to the window
		menus.add(game);
		menus.add(Box.createHorizontalGlue());
		menus.add(help);
		setJMenuBar(menus);

		// When about is pressed, displays showing name of project's author
		about.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Space Invaders\nby JonathanThomas", "About...",
						JOptionPane.INFORMATION_MESSAGE);
			}
		});

		// new game action listener. creates new game if clicked
		newGame.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int result = JOptionPane.showConfirmDialog(SI.this, "Start a new Game?", "Confirm",
						JOptionPane.YES_NO_OPTION);
				if (result == JOptionPane.YES_OPTION) {
					// start new game
					panel.newGame();
				}
			}
		});

		// pauses current game
		pause.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				panel.pause();
			}
		});

		// resumes a paused game
		resume.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// resume the game. As simple as continuing the timer?
				panel.start();
			}
		});

		// displays a confirm dialog if user clicks quit
		quit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int result = JOptionPane.showConfirmDialog(SI.this, "Dare to Quit?", "Select an Option",
						JOptionPane.YES_NO_OPTION);
				if (result == JOptionPane.YES_OPTION) {
					System.exit(0);
				}
			}
		});

		// window listener. Exits program
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});

		// make window visible
		setResizable(false);
		setSize(500, 450);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
	}

	/**
	 * Main method. Creates jframe and set it visible
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		JFrame si = new SI();
		si.setVisible(true);
	}
}
