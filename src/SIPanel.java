import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.Timer;

public class SIPanel extends JPanel {
	private SIbase base;
	private static final long serialVersionUID = 1L;
	private boolean left, right, space;
	private ArrayList<SIthing> things = new ArrayList<SIthing>();
	private SImissle missle;
	private int score = 0;
	private int pulse = 0;
	private Timer timer;
	private SIship ship;
	private SIinvader invader;
	private ArrayList<SIinvader> aliens = new ArrayList<SIinvader>();
	private boolean moveRight = true;
	private boolean moveLeft = false;
	private int max = 0;
	private int min = 550;

	public SIPanel() {
		setFocusable(true);

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

				// implement below statement to play a sound
				case KeyEvent.VK_SPACE:
					// implement to play sound
					// base.baseShoot().play();
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
					// implement to play sound
					// base.baseShoot().play();
					space = false;
					break;
				}
			}
		});

		timer = new Timer(50, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				repaint();
				
				if (left) {
					if(base.getX() >= 10){
						base.moveLeft();
					}
				}

				if (right) {
					if(base.getX() + base.getSize().getWidth() <= 490){
						base.moveRight();
					}
				}

				if (space && missle == null) {// !missle.getVisibility()) {
					newBaseMissle();
				}
				if (missle != null) {
					missle.moveUp();
					
					if (!missle.getVisibility()) {
						missle = null;
					}
				}

				// trying to move the invaders
				if (pulse % (int) SIinvader.getSpeed() == 0) {
					for (SIinvader i : aliens) {
						max = (int) Math.max(499, i.getX() + i.getSize().getWidth());
						min = (int) Math.min(30, i.getX());
					}

					if (moveRight && max > 499) {
						moveRight = false;
						moveLeft = true;
						for (SIinvader v : aliens) {
							v.moveDown();
						}
						SIinvader.increaseSpeed();
					}
					else if (moveLeft && min < 10) {
						moveLeft = false;
						moveRight = true;
						for (SIinvader v : aliens) {
							v.moveDown();
						}
						SIinvader.increaseSpeed();
					}
					else if (moveRight) {
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

				// if(ship.testShipHit(missle)){
				// ship.setVisible(false);
				
				pulse++;
			}
		});
		timer.start();

		setBackground(Color.BLACK);
		newGame();
	}

	public void newGame() {
		things.removeAll(things);
		aliens.removeAll(aliens);
		initializeBase();
		initializeAliens();
		missle = null;
		score = 0;
		timer.start();
	}

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

	public void pause() {
		timer.stop();
	}

	public void start() {
		timer.start();
	}

	private void initializeBase() {
		base = new SIbase();
		things.add(base);
	}

	public void newBaseMissle() {
		missle = new SImissle(base.getX() + base.getSize().width / 2, base.getY(), Color.green);
		things.add(missle);
		base.baseShoot().play();
		missle.setVisible(true);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.GREEN);
		g.drawString("Score:  " + score, 10, 20);
		for (int i = 0; i < things.size(); i++) {
			SIthing currThing = things.get(i);
			currThing.drawImage(g);
		}
	}
}
