package edu.pacific.comp55.starter;

import java.util.ArrayList;
import java.util.Iterator;

import acm.graphics.GRect;

public class EnemyPack {
	private MainApplication program;
	private SomePane game;
	ArrayList <Enemy> enemies;
	double dx, dy;
	long shotTick = 1000000000 * (2); //2 second delay between shots
	double min, max;
	
	EnemyPack(MainApplication program, SomePane pane, int num){
		game = pane;
		this.program = program;
		min = 800;
		max = 0;
		dx = 0.3;
		enemies = new ArrayList<Enemy>();
		for(int i  = 0; i < num; i++) {
			
			Enemy temp = new Enemy(this, ((i * 35) % 770), (35 * (Math.floor(i / 22))));
			enemies.add(temp);
		}
	}
	
	public ArrayList<Enemy> getPack(){
		return enemies;
	}
	
	public long getShotTick() {
		return shotTick;
	}
	public void update() {
		findBounds();
		updateSpeed();
		if(dx > 0) {
			if(max + dx > 770) {
				dx = dx * (-1);
				dy = 20;
			}
		}else {
			if (min + dx < 0) {
				dx = dx * (-1);
				dy = 20;
			}
		}
		for(int i = 0; i < enemies.size(); i++) {
			enemies.get(i).update(dx, dy);
		}
		
		dy = 0;
	}
	private void updateSpeed() {
		// TODO Auto-generated method stub
		int sign = 1;
		if(dx < 0) {sign = -1;}
		int size = enemies.size();
		if(size < 16) {
			dx = 1.0;
		}else if(size < 32) {
			dx = 0.6;
		}else {
			return;
		}
		dx = dx * sign;
		
	}

	private void findBounds() {
		min = 800;
		max = 0;
		for(int i = 0; i < enemies.size(); i++) {
			if(enemies.get(i).getShip().getLocation().getX() < min) {
				min = enemies.get(i).getShip().getLocation().getX();
			}
			if(enemies.get(i).getShip().getLocation().getX() > max) {
				max = enemies.get(i).getShip().getLocation().getX();
			}
		}
		// TODO Auto-generated method stub
		
	}

	public void addPack() {
		for(int i = 0; i < enemies.size(); i++) {
			program.add(enemies.get(i).getShip());
			//System.out.println("Enemy Added \n");
		}
	}
	public void hidePack() {
		for(int i = 0; i < enemies.size(); i++) {
			program.remove(enemies.get(i).getShip());
		}
	}

	public boolean checkColission(double x, double y, double r) {
		// TODO Auto-generated method stub
		Iterator<Enemy> iterate = enemies.iterator();
		while(iterate.hasNext()) {
			Enemy temp = iterate.next();
			if(game.ovalCollision(temp.getShip().getBounds(), x, y, r)) {
				program.remove(temp.getShip());
				iterate.remove();
				game.updateScore();
				return true;
			}
		}
		return false;
	}

	public boolean checkLose() {
		// TODO Auto-generated method stub
		for(int i = 0; i < enemies.size(); i++) {
			if(enemies.get(i).getShip().getY() + 30 > 515) {
				return true;
			}
		}
		return false;
	}
	
}
