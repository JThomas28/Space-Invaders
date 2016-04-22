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

public class SIPanel extends JPanel {
	
	private static final long serialVersionUID = 1L;
	private boolean left, right, space;
	private static boolean direction = true;
	
	private ArrayList<SIthing> things = new ArrayList<SIthing>();
	private ArrayList<SIinvader> aliens = new ArrayList<SIinvader>();
	private ArrayList<SImissle> missleArray = new ArrayList<SImissle>();
	private ArrayList<SImissle> removeArray = new ArrayList<SImissle>();
	
	private SIbase base;
	private SImissle missle;
	private SImystery mystery;
	private SIinvader invader;
	
	private int score = 0;
	private int pulse = 0;
	private int speed = 40;
	private int tempScore, randInt;
	
	private Timer timer;
	private Random rand = new Random();
	
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
					for (SImissle m : missleArray) {
						if (base.testShipHit(m)){
							timer.stop();
						}
						else {
							m.moveDown();
							if (!m.getVisibility()) {
								removeArray.add(m);
							}
						}
					}
					missleArray.removeAll(removeArray);
				}
				
				if (missleArray.isEmpty()) {
					alienShoot();
				}

				if (space && missle == null) {
					newBaseMissle();
				}
				
				if (missle != null) {
					for (SIinvader v : aliens) {
						if (v.testShipHit(missle)) {
							aliens.remove(v);
							score += v.getPointVal();
							break;
						}
					}
					
					if (mystery != null) {
						if (mystery.testShipHit(missle)) {
							score += mystery.getPointVal();
						}
					}
					
					missle.moveUp();
					if (!missle.getVisibility()) {
						missle = null;
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
									base.shipHitSound().play();
									timer.stop();
								}
							}
							speed *= .8;
						}
					}

					if (direction) {
						for (SIinvader v : aliens) {
							v.moveRight();
							v.changeImage();
						}
					}
					else if (!direction) {
						for (SIinvader v : aliens) {
							v.moveLeft();
							v.changeImage();
						}
					}

					pulse = 0;
				}

				if (mystery == null) {
					randInt = rand.nextInt(1000);
					if (randInt == 0 || randInt == 1 || randInt == 2) {
						mystery = new SImystery();
						mystery.getSound().play();
						things.add(mystery);
					}
				}

				if (mystery != null) {
					mystery.moveMystery();
					if (!mystery.getVisibility()) {
						things.remove(mystery);
						aliens.remove(mystery);
						//mystery.getSound().stop();
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

	public static void setDirection(boolean d) {
		// right == true, left == false
		direction = d;
	}

	public static boolean getDirection() {
		return direction;
	}

	public void newGame() {
		things.removeAll(things);
		aliens.removeAll(aliens);
		initializeBase();
		initializeAliens();
		missle = null;
		score = 0;
		timer.start();
		speed = 40;
		direction = true;
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

	public void alienShoot() {
		SImissle m;
		ArrayList <SIinvader> missleShooters = new ArrayList<SIinvader>();
		int nbr = rand.nextInt(3);
		for(SIinvader v : aliens){
			if(v.getY() >= aliens.get(aliens.size() -1).getY()){
				missleShooters.add(v);
			}
		}
		for (int i = 0; i < nbr; i++) {
			//SIinvader v = aliens.get(rand.nextInt(10));//aliens.size()));
			SIinvader v = missleShooters.get(rand.nextInt(missleShooters.size()));
			m = new SImissle((v.getX() + v.getSize().width / 2), (int) (v.getY() + v.getSize().getHeight()), Color.WHITE);
			missleArray.add(m);
			things.add(m);
			m.setVisible(true);
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
		if (base.getHit()) {
			g.drawString("Game Over. Your Score: " + score, 160, 200);
		}
		for (int i = 0; i < things.size(); i++) {
			SIthing currThing = things.get(i);
			if (!currThing.getVisibility()) {
				things.remove(currThing);
				if (aliens.isEmpty()) {
					tempScore = score;
					timer.stop();
					newGame();
					score = tempScore;
				}
			}
			else {
				currThing.drawImage(g);
			}
		}
	}
}
