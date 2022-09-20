package edu.pacific.comp55.starter;

import acm.graphics.GObject;
import java.awt.Color;
import acm.graphics.GPoint;
import acm.graphics.GPolygon;

public class Player {
	int health;
	private MainApplication program;
	private SomePane game;
	double x, y, dx;
	boolean isDead = false;
	GPolygon ship;
	boolean isIdle = true;
	
	
	Player(MainApplication app, SomePane pane){
		this.game = pane;
		program = app;
		x = 300;
		y = 750;
		dx = 0;
		health = 4;
		GPoint left = new GPoint(285, 530);
		GPoint right = new GPoint(315, 530);
		GPoint center = new GPoint(300, 500);
		GPoint[] points = {left, right, center, left};
		ship = new GPolygon(points);
		ship.setFilled(true);
		ship.setFillColor(Color.blue);
	}
	public GObject getShip() {
		return ship;
	}
	public int getHealth() {
		return health;
	}
	public void takeDamage(int d) {
		health -= d;
		if(health < 1) {
			isDead = true;
		}
	}
	public boolean isDead() {
		return isDead;
	}
	public void update() {
		if(dx > 25) {
			dx = 25;
		}else if(dx < -25) {
			dx = -25;
		}
		if(ship.getBounds().getX() + dx < 0) {
			dx = 0;
		}else if(ship.getBounds().getX() + ship.getWidth() + dx > 800) {
			dx = 0;
		}
		if(isIdle && dx != 0) {
			if(dx > 2) {
				dx += -2;
			}else if(dx < -2) {
				dx += 2;
			}else {
				dx = 0;
			}
		}
		
		ship.move(dx, 0);
		isIdle = true;
	}
	public void updateDx(double x) {
		if(dx == 0 && x > 0) {
			dx = 15;
		}else if(dx ==0 && x < 0) {
			dx = -15;
		}else {
			dx += x;	
		}
		
		
	}
	public void show() {
		program.add(ship);
	}
	public void hide() {
		program.remove(ship);
	}
	public void setIdle() {
		isIdle = false;
	}
	public void addBullet() {
		// TODO Auto-generated method stub
		double bX = ship.getBounds().getX() + (ship.getWidth()/2);
		double bY = ship.getBounds().getY() - 5;
		game.addBullet(bX, bY, 5, 0, -10);
		
	}
	
		
}
