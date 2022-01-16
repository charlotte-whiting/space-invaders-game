import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class GamePanel extends JPanel{
	private final int SIZE_PANEL = 600;
	private Timer timer = new Timer(100, null);
	private Ship ship;
	private ArrayList<Laser> shots;
	private ArrayList<Alien> enemies;
	//private ArrayList<Asteroid> asteroids;
	private int counter1,counter2;
	private int score,bestScore;
	private boolean endGame;
	
//highest score:945
	
	public static void main(String[] args) {
		JFrame frame = new JFrame("Space Invaders");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(new GamePanel());
		frame.pack();
		frame.setVisible(true);
	}
	
	public GamePanel() {
		ship = new Ship(300,550);
		shots = new ArrayList<>();
		enemies = new ArrayList<>();
		//asteroids = new ArrayList<>();
		score = 0;
		endGame =false;
		bestScore = 0;
		this.setPreferredSize(new Dimension(this.SIZE_PANEL,SIZE_PANEL));
		
		timer.addActionListener(new ActionListener() {

			/**
			 * This method is called every time the timer goes off.  The Timer can be scheduled
			 * to go off at different intervals (shorter intervals makes actions go faster).
			 */
			@Override
			public void actionPerformed(ActionEvent e) {
				testCollision();
				if(counter1 ==17) {
					makeNewEnemy();
					counter1 =0;
				}
				else {
					counter1++;
				}
//				if(counter2 == 35) {
//					makeNewAsteroid();
//					counter2 = 0;
//					}
//				else {
//					counter2++;
//				}
				repaint();
			}

		});
		timer.start();
		this.addKeyListener(new KeyAdapter() {
			/**
			 * This method is called every time a key is pressed.  In this game, what
			 * happens when a key is pressed?  That behavior should be echoed in the game.
			 */
			@Override
			public void keyPressed(KeyEvent key) {
				int type = key.getKeyCode();
				//System.out.println("you just hit "  + type);
				if(type == 39) {
					ship.setRow(ship.getRow() + 50);
				}
				if(type == 37) {
					ship.setRow(ship.getRow() - 50);
				}
				if(type == 32) {
					Laser temp = new Laser(ship.getRow()+25, 550);
					shots.add(temp);
				}
				if(type == 83) {
					endGame = false;
					score = 0;
					while(enemies.size() > 0) {
						enemies.remove(0);
					}
				}
			}
		});

	}
	
	
	public void paintComponent(Graphics g) {
		//System.out.print("running");
		super.paintComponent(g);
		if(!this.hasFocus())
			this.requestFocusInWindow();
		if(endGame == false) {
		Color c = new Color(20,20,10);
		g.setColor(c);
		g.fillRect(0, 0, 600, 600);
		ship.draw(g);
		for(Laser l : shots) {
			l.setCol(l.getCol() - 30);
			l.draw(g);
		}
//		for (Asteroid s : asteroids) {
//			s.setCol(s.getCol()-30);
//			s.draw(g);
//		}
		for(Alien a : enemies) {
			a.setCol(a.getCol() + 10);
			a.draw(g);
			if(a.getType() == "Red") {
				a.setCol(a.getCol() + 15);
				a.draw(g);
			}
			if(a.getType() == "Green") {
				a.setCol(a.getCol() + 10);
				a.draw(g);
			}
			if(a.getType() == "Blue") {
				a.setCol(a.getCol() + 5);
				a.draw(g);
			}
		}
		String str = "score: " + score;
		g.setColor(Color.WHITE);
		g.drawString(str,10,10);
		}
		else {
			if(score>bestScore) {
				bestScore = score;
			}
			g.setColor(Color.white);
			g.fillRect(0, 0, 600, 600);
			String end = "Game Over! Press 's' to try again";
			g.setColor(Color.black);
			g.drawString(end, 200, 300);
			String str = "Final Score: " + score;
			g.drawString(str, 200, 330);
			String best = "Highest score ever: " + bestScore;
			g.drawString(best, 200, 360);
		}
	}
	
	public void testCollision() {
		ArrayList<Alien> aRemove = new ArrayList<>();
		ArrayList<Laser> lRemove = new ArrayList<>();
		for(Alien crash: enemies) {
			int crashMinRow = crash.getRow();
			int crashMaxRow = crashMinRow + 50;
			int crashCol = crash.getCol() + 50;
			int shipMinRow = ship.getRow();
			int shipMaxRow = shipMinRow + 50;
			int shipCol = ship.getCol();
			if(shipCol <= crashCol) {
				if((crashMinRow>=shipMinRow && crashMinRow <= shipMaxRow)||(crashMaxRow>=shipMinRow && crashMaxRow <= shipMaxRow) || (crashCol >= 600)) {
				endGame = true;
				//System.out.println("Crash");
				for(Alien a: enemies) {
					aRemove.add(a);
				}
				for(Laser l : shots) {
					lRemove.add(l);
				}
				}
			}
			}
		//System.out.println("This was called");
		if(endGame == false) {
		for(Alien a : enemies) {
			int rowMin = a.getRow();
			int rowMax = rowMin + 50;
			int colMin = a.getCol();
			int colMax = colMin + 50;
			for(Laser l : shots) {
				int lRowMin = l.getRow();
				int lRowMax = lRowMin + 10;
				int lCol = l.getCol();
				if(lCol <= colMax && lCol >= colMin) {
				if((lRowMin <= rowMax && lRowMin >= rowMin) || (lRowMax <= rowMax && lRowMax >= rowMin)) {
					aRemove.add(a);
					lRemove.add(l);
					if(a.getType() == "Red") {
						score+=20;
					}
					if(a.getType() == "Green") {
						score+=15;
					}
					if(a.getType() == "Blue") {
						score+=10;
					}
				}
				}
			}
		}
		}
//		for (Asteroid x : asteroids) {
//			int shipMinRow = ship.getRow();
//			int shipMaxRow = shipMinRow + 50;
//			int shipCol = ship.getCol();
//			int asMinRow = x.getRow();
//			int asMaxRow = asMinRow + 25;
//			int asCol = x.getCol() + 25;
//			if(shipCol <= asCol) {
//				if((asMinRow >= shipMinRow && asMinRow <=shipMaxRow) || (asMaxRow>=shipMinRow && asMaxRow <= shipMaxRow)) {
//					endGame = true;
//				}
//			}
//			if (endGame == true){
//				for(Asteroid ast: asteroids) {
//					asteroids.remove(ast);
//				}
//			}
//			
//		}
		
		for(Alien e : aRemove) {
			enemies.remove(e);
		}
		for(Laser s : lRemove) {
			shots.remove(s);
		}
	}
	
	public void makeNewEnemy() {
		int randCol = (int)(Math.random() * 550);
		int randAlien = (int)(Math.random()*3);
		if(randAlien == 0) {
			GreenAlien temp = new GreenAlien(randCol,0);
			enemies.add(temp);
		}
		else if(randAlien ==1) {
			BlueAlien temp = new BlueAlien(randCol, 0);
			enemies.add(temp);
		}
		else if(randAlien == 2) {
			RedAlien temp = new RedAlien(randCol, 0);
			enemies.add(temp);
		}
	}
	
//	public void makeNewAsteroid() {
//		int randCol = (int)(Math.random()*550);
//		Asteroid temp = new Asteroid(randCol,0);
//		asteroids.add(temp);
//	}
	
}
