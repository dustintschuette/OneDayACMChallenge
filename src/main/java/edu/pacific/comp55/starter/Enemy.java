package edu.pacific.comp55.starter;

import java.awt.Color;

import acm.graphics.GRect;

public class Enemy {
	EnemyPack theHive;
	GRect ship;
	boolean isDead;
	long lastShot;
	
	Enemy(EnemyPack hive, double x, double y){
		theHive = hive;
		ship = new GRect(x, y, 30, 30);
		ship.setFilled(true);
		ship.setFillColor(Color.red);
		lastShot = System.nanoTime();
	}
	public GRect getShip(){
		return ship;
	}
	public void update(double dx, double dy) {
		// TODO Auto-generated method stub
		ship.move(dx, dy);
		
	}
}
