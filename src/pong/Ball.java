package pong;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class Ball {

	public double x, y;
	public int width, height;
	
	public double dx, dy;
	public double speed = 4;
	
	public int score = 2;
	
	public Ball(int x, int y) {
		
		this.x = x;
		this.y = y;
		this.width = 4;
		this.height = 4;
		gerarBola();
	}
	
	public void tick() {
		
		if(y+(dy*speed) + height >= Game.HEIGHT) {
			
			dy *= -1;
		}else if(y + (dy*speed) < 0) {
			dy*=-1;
		}
		
		if(x < 0) {
			//ponto do inimigo
			
			score = 1;
			
			x = Game.WIDTH/2 - 1;
			y = Game.HEIGHT/2 - 1;
			gerarBola();
		}else if(x >= Game.WIDTH) {
			//ponto do jogador
			
			score = 3;
			x = Game.WIDTH/2 - 1;
			y = Game.HEIGHT/2 - 1;
			gerarBola();
		}
		
		Rectangle bounds = new Rectangle((int)(x+(dx*speed)), (int)(y+(dy*speed)), width, height);
		
		Rectangle boubdsPlayer = new Rectangle(Game.player.x, Game.player.y, Game.player.width, Game.player.height);
		Rectangle boubdsEnemy = new Rectangle((int)(Game.enemy.x), (int)(Game.enemy.y), Game.enemy.width, Game.enemy.height);
		
		if(bounds.intersects(boubdsPlayer)) {
			
			gerarBola();
			if(dx < 0) {
				dx *= -1;
			}
		}else if(bounds.intersects(boubdsEnemy)) {
			
			gerarBola();
			if(dx > 0) {
				dx *= -1;
			}
		}
		
		x+=dx*speed;
		y+=dy*speed;
	}
	
	public void render(Graphics g) {
		
		g.setColor(Color.WHITE);
		g.fillRect((int)x,(int)y, width, height);
	}
	
	public void gerarBola() {
		
		int angle = new Random().nextInt((80 - 45) + 45);
		dx = Math.cos(Math.toRadians(angle));
		dy = Math.sin(Math.toRadians(angle));
	}
}
