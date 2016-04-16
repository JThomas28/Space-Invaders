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
	private int lifeCount = 3;
	private int level = 1;
	private int score = 0;
	

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
					//implement to play sound
					//SIship.getSound().play();
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
					//implement to play sound
					//SIship.getSound().play();
					space = false;
					break;
				}
			}
		});

		Timer baseMoveTimer = new Timer(100, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				repaint();
				if (left) {// && base.getX() > 9) {
					base.moveLeft();
				}
				// this is how to make invaders move from side to side
				// else if(left && base.getX() <=9){
				// right = true;
				// left = false;
				// }

				if (right) {// base.getX() < 470){
					base.moveRight();
				}
				// this is how to make invaders move from side to side
				// else if(right && base.getX() >= 469){
				// left = true;
				// right = false;
				// }
				if(space && !missle.getVisibility()){
					newMissle();
					missle.setVisible(true);
				}
				if(missle.getVisibility()){
					missle.moveUp();
				}
			}
		});
		baseMoveTimer.start();
		setBackground(Color.BLACK);

		initializeBase();
		newMissle();
	}

	public void initializeBase() {
		base = new SIbase();
		things.add(base);
	}
	
	public void newMissle(){
		missle = new SImissle(base.getX() + 12, base.getY());
		things.add(missle);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.GREEN);
		g.drawString("Lives:  " + lifeCount, 430, 20);
		g.drawString("Level: " + level, 230, 20);
		g.drawString("Score:  " + score, 10, 20);
		for (int i = 0; i < things.size(); i++) {
			SIthing currThing = things.get(i);
			currThing.drawImage(g);
		}
	}
}
