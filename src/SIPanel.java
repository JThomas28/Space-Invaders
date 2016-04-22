import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JPanel;
import javax.swing.Timer;

/**
 * SIPanel contains the game's logic. Draws and updates images.
 * 
 * @author JonathanThomas
 *
 */
public class SIPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	/**
	 * left, right, and space are true if left, right, or space are pressed on
	 * the keyboard
	 */
	private boolean left, right, space;

	/**
	 * direction is true if aliens are moving right, false otherwise
	 */
	private static boolean direction = true;

	/**
	 * Arraylist to keey track of everything. Things contains everything that
	 * needs to be drawn, aliens contains all invaders, missleArray contains all
	 * missles fired by aliens, and remove array contains all missles fired by
	 * aliens that have gone off screen
	 */
	private ArrayList<SIthing> things = new ArrayList<SIthing>();
	private ArrayList<SIinvader> aliens = new ArrayList<SIinvader>();
	private ArrayList<SImissile> missleArray = new ArrayList<SImissile>();
	private ArrayList<SImissile> removeArray = new ArrayList<SImissile>();

	/**
	 * Types of things. base is the base, missle is the missle fired by the
	 * base, mystery is the mystery ship, and invader is used to initialize all
	 * invaders
	 */
	private SIbase base;
	private SImissile missile;
	private SImystery mystery;
	private SIinvader invader;

	/**
	 * Score is the player's score, pulse is the number of clock cycles we've
	 * been through so far, and speed represents the invader's speed
	 */
	private int score = 0;
	private int pulse = 0;
	private int speed = 40;
	private int tempScore, randInt;

	/**
	 * timer is the timer for the game, and rand is a random number generator
	 * used to get random aliens to fire missiles, and used to spawn the mystery
	 * ship every so often
	 */
	private Timer timer;
	private Random rand = new Random();

	/**
	 * Constructor. Initializes everything in the panel including timer and key
	 * listener. Checks conditions and acts accordingly
	 */
	public SIPanel() {
		setFocusable(true);

		// key listener
		addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				switch (e.getKeyCode()) {
				case KeyEvent.VK_LEFT:
					left = true;
					break;
				case KeyEvent.VK_RIGHT:
					right = true;
					break;
				case KeyEvent.VK_SPACE:
					space = true;
					break;
				}
			}

			public void keyReleased(KeyEvent e) {
				switch (e.getKeyCode()) {
				case KeyEvent.VK_LEFT:
					left = false;
					break;
				case KeyEvent.VK_RIGHT:
					right = false;
					break;
				case KeyEvent.VK_SPACE:
					space = false;
					break;
				}
			}
		});

		// timer created
		timer = new Timer(100, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (left) {
					base.moveLeft();
				}

				if (right) {
					base.moveRight();
				}

				if (!missleArray.isEmpty()) {
					for (SImissile m : missleArray) {
						if (base.testShipHit(m)) {
							base.drawImage(getGraphics());
							timer.stop();
						}
						else {
							m.moveDown();
							if (!m.getVisibility()) {
								removeArray.add(m);
							}
						}
					}
					// remove all disabled missiles
					missleArray.removeAll(removeArray);
				}

				// if there are no missiles fired by invaders, fire some
				if (missleArray.isEmpty()) {
					alienShoot();
				}

				// if space is pressed and there is no missile from the base on
				// the screen, fire one
				if (space && missile == null) {
					newBaseMissle();
				}

				// if there's a missile from the base on the screen, move it
				if (missile != null) {
					for (SIinvader v : aliens) {
						if (v.testShipHit(missile)) {
							aliens.remove(v);
							score += v.getPointVal();
							break;
						}
					}

					// if there's a mystery ship on the screen, check hit by
					// missile
					if (mystery != null) {
						if (mystery.testShipHit(missile)) {
							score += mystery.getPointVal();
						}
					}
					missile.moveUp();
					if (!missile.getVisibility()) {
						missile = null;
					}
				}

				// move the invaders
				if (pulse >= speed) {
					for (SIinvader i : aliens) {
						if (i.goOtherWay()) {
							for (SIinvader v : aliens) {
								v.moveDown();
								if (v.getY() >= base.getY() + base.getSize().getHeight()) {
									base.setHit(true);
									timer.stop();
								}
							}
							// speed up if invaders reach edge
							speed *= .8;
							break;
						}
					}

					if (direction) {
						for (SIinvader v : aliens) {
							v.moveRight();
						}
					}
					else {
						for (SIinvader v : aliens) {
							v.moveLeft();
						}
					}
					pulse = 0;
				}

				if (mystery == null) {
					// create mystery ship only small percentage of time
					randInt = rand.nextInt(1000);
					if (randInt == 0 || randInt == 1 || randInt == 2) {
						mystery = new SImystery();
						mystery.getSound().play();
						things.add(mystery);
					}
				}
				// move mystery ship if there is one
				if (mystery != null) {
					mystery.moveMystery();
					if (!mystery.getVisibility()) {
						aliens.remove(mystery);
						mystery = null;
					}
				}
				pulse++;
				repaint();
			}

		});

		timer.start();

		setBackground(Color.BLACK);
		newGame();
	}

	/**
	 * Sets the direction the invaders should move
	 * 
	 * @param d
	 *            - direction invaders should move. If true, move right, else
	 *            move left
	 */
	public static void setDirection(boolean d) {
		// right == true, left == false
		direction = d;
	}

	/**
	 * returns the direction invaders should move
	 * 
	 * @return direction - true if invaders moving right, false otherwise
	 */
	public static boolean getDirection() {
		return direction;
	}

	/**
	 * Creates new game. Initializes aliens, base, score, direction
	 */
	public void newGame() {
		things.removeAll(things);
		aliens.removeAll(aliens);
		initializeBase();
		initializeAliens();
		missile = null;
		score = 0;
		timer.start();
		speed = 40;
		direction = true;
	}

	/**
	 * Creates all aliens
	 */
	private void initializeAliens() {
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 10; j++) {
				if (i == 0) {

					// draw top type of alien
					invader = new SItop(50 + 30 * j, 50 + 25 * i);
					aliens.add(invader);
					things.add(invader);
				}
				if (i == 1 || i == 2) {

					// draw middle alien
					invader = new SImiddle(50 + 30 * j, 50 + 25 * i);
					aliens.add(invader);
					things.add(invader);
				}
				if (i == 3 || i == 4) {

					// draw bottom alien
					invader = new SIbottom(50 + 30 * j, 50 + 25 * i);
					aliens.add(invader);
					things.add(invader);
				}
			}
		}
	}

	/**
	 * Makes aliens shoot. Can be at most 3 missles at a time. Aliens on lowest
	 * level are only ones that can shoot
	 */
	public void alienShoot() {
		SImissile m;
		ArrayList<SIinvader> missleShooters = new ArrayList<SIinvader>();
		int nbr = rand.nextInt(3);
		for (SIinvader v : aliens) {
			if (v.getY() >= aliens.get(aliens.size() - 1).getY()) {
				missleShooters.add(v);
			}
		}
		for (int i = 0; i < nbr; i++) {
			SIinvader v = missleShooters.get(rand.nextInt(missleShooters.size()));
			m = new SImissile((v.getX() + v.getSize().width / 2), (int) (v.getY() + v.getSize().getHeight()),
					Color.WHITE);
			missleArray.add(m);
			things.add(m);
			m.setVisible(true);
		}
	}

	/**
	 * stops timer, pauses game
	 */
	public void pause() {
		timer.stop();
	}

	/**
	 * starts timer, resumes game
	 */
	public void start() {
		timer.start();
	}

	/**
	 * Creates the base. Called in the new game method
	 */
	private void initializeBase() {
		base = new SIbase();
		things.add(base);
	}

	/**
	 * Creates a new missile from the base if none already exists
	 */
	public void newBaseMissle() {
		missile = new SImissile(base.getX() + base.getSize().width / 2, base.getY(), Color.green);
		things.add(missile);
		base.baseShoot().play();
		missile.setVisible(true);
	}

	/**
	 * Paint component is responsible for painting everything on the frame.
	 * Ships, missiles, score, etc.
	 */
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.GREEN);
		g.drawString("Score:  " + score, 10, 20);
		// if base is hit, print game over to screen
		if (base.getHit()) {
			g.drawString("Game Over. Your Score: " + score, 160, 200);
		}

		/**
		 * for everything in the things array, if it is visible, paint it
		 */
		for (int i = 0; i < things.size(); i++) {
			SIthing currThing = things.get(i);
			if (!currThing.getVisibility()) {
				things.remove(currThing);
				if (aliens.isEmpty()) {
					tempScore = score;
					timer.stop();
					newGame();
					speed *= .8;
					score = tempScore;
				}
			}
			else {
				currThing.drawImage(g);
			}
		}
	}
}
