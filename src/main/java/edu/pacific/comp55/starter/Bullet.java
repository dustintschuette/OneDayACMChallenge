package edu.pacific.comp55.starter;

import java.awt.Color;
import acm.graphics.GOval;

public class Bullet {
	MainApplication program;
	SomePane pane;
	GOval bullet;
	double x, y, r, dx, dy;
	
	
	Bullet(MainApplication app, SomePane pane, double x, double y, double r, double dx, double dy){
		program = app;
		this.pane = pane;
		this.x = x;
		this.y = y;
		this.dx = dx;
		this.dy = dy;
		bullet = new GOval(x, y, r, r);
		bullet.setFilled(true);
		bullet.setFillColor(Color.yellow);
	}
	public GOval getBullet() {
		return bullet;
	}
	public void update() {
		bullet.move(dx, dy);
	}
	public void show() {
		program.add(bullet);
	}
	public void hide() {
		program.remove(bullet);
	}
}
