package edu.pacific.comp55.starter;
import java.awt.event.MouseEvent;

import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.Timer;

import java.awt.event.KeyListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import acm.graphics.GImage;
import acm.graphics.GLabel;
import acm.graphics.GObject;
import acm.graphics.GRect;
import acm.graphics.GRectangle;

public class SomePane extends GraphicsPane implements KeyListener, ActionListener{
	private MainApplication program; // you will use program to get access to
						
	// all of the GraphicsProgram calls
	private ArrayList<Bullet> bullets;
	private Player player;
	private EnemyPack pack;
	private Timer timer;
	private GLabel pause;
	private GLabel scoreBoard;
	boolean paused = false;
	int shotDelay = 35;
	int shotCount = 0;
	boolean shot = false;
	int score = 0;
	
	
	
	public SomePane(MainApplication app) {
		this.program = app;
		player = new Player(app, this);
		bullets = new ArrayList<Bullet>();
		pause = new GLabel("PAUSED", 350, 400);
		scoreBoard = new GLabel("Score: "+ score, 375, 420);
		
	}
	public void addBullet(double x, double y, double r, double dx, double dy) {
		Bullet temp = new Bullet(program, this, x, y, r, dx, dy);
		bullets.add(temp);
		temp.show();
	}
	public boolean rectCollision(GRect boxA, GRect boxB) {
		double minX = boxA.getX();
		double minY = boxA.getY();
		double maxX = minX + boxA.getWidth();
		double maxY = minY + boxA.getHeight();
		
		
		if( boxB.getX() > maxX || minX > boxB.getX() + boxB.getWidth()) return false;
		if( boxB.getY()> maxY || minY > boxB.getY() + boxB.getHeight()) return false;
		return true;
	}
	public boolean ovalCollision(GRectangle boxA, double x, double y, double r) {
		float d = 0.0f;
		double minX = boxA.getX();
		double minY = boxA.getY();
		double maxX = minX + boxA.getWidth();
		double maxY = minY + boxA.getHeight();
		if( x < minX ) d += (x - minX)*(x - minX);
		if( x > maxX ) d += (x - maxX)*(x - maxX);
		if( y < minY ) d += (y - minY)*(y - minY);
		if( y > maxY ) d += (y - maxY)*(y - maxY);
		return d < r*r;
	}
	public void checkCollision() {
		Iterator<Bullet> iterate = bullets.iterator();
		while(iterate.hasNext()) {
			Bullet temp = iterate.next();
			double r = temp.getBullet().getHeight();
			//System.out.println("height: " + r);
			double x = temp.getBullet().getX();
			double y = temp.getBullet().getY();
			if(ovalCollision(player.getShip().getBounds(), x, y, r)) {
				player.takeDamage(1);
				temp.hide();
				iterate.remove();
				
			}else {
				if(pack.checkColission(x, y, r)) {
					temp.hide();
					iterate.remove();
				}
			}
		}
	}

	@Override
	public void showContents() {
		pack = new EnemyPack(program, this, 74);
		pack.addPack();
		if(bullets.size() > 0) {
			showBullets();
		}
		player.show();
		//System.out.println("Added pack \n");
		timer = new Timer(10, this);
		timer.start();
		//program.add(para);
	}

	private void showBullets() {
		// TODO Auto-generated method stub
		for(int i = 0; i < bullets.size(); i++) {
			bullets.get(i).show();
		}
	}
	@Override
	public void hideContents() {
		timer.stop();
		pack.hidePack();
		if(bullets.size() > 0) {
			hideBullets();
		}
		player.hide();
		//program.remove(para);
	}

	private void hideBullets() {
		// TODO Auto-generated method stub
		for(int i = 0; i < bullets.size(); i++) {
			bullets.get(i).hide();
		}
		
	}
	public void updateBullets() {
		if(bullets.size() == 0) {return;}
		
		for(int i = 0; i < bullets.size(); i++) {
			bullets.get(i).update();
		}
	}
	@Override
	public void mousePressed(MouseEvent e) {
		
	}
	@Override
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		if(key == KeyEvent.VK_LEFT) {
			player.updateDx(-5);
		}else if(key == KeyEvent.VK_RIGHT) {
			player.updateDx(5);
		}
		if(key == KeyEvent.VK_SPACE) {
			if(shot) {
				
			}else {
				player.addBullet();
				shot = true;
			}
			
		}
			player.setIdle();
		if(key == KeyEvent.VK_P) {
			paused = !paused;
			showPaused();
		}
	}
	private void showPaused() {
		// TODO Auto-generated method stub
		if(paused) {
			program.add(pause);
			scoreBoard.setLabel("Score: " + score);
			program.add(scoreBoard);
		}else {
			program.remove(pause);
			program.remove(scoreBoard);
		}
		
	}
	public void updateScore() {
		score += 10;
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
		if(paused) {
			return;
		}
		shotCount++;
		shotCount = shotCount % shotDelay;
		if(shotCount == 0) {
			shot = false;
		}
		player.update();
		pack.update();
		updateBullets();
		checkCollision();
		if(pack.getPack().size() == 0) {
			program.switchToMenu();
		}
		if(pack.checkLose()) {
			program.switchToMenu();
		}
	}
}
