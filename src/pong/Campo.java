package pong;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class Campo {
	
	
	
	public double x, y;
	public int width, height, playerScore = 0, enemyScore = 0;

	public Campo(int x) {
		
		this.x = x;
		this.y = 5;
		this.width = 1;
		this.height = 7;
	}
	
	public void tick() {
		
		if(Game.ball.score == 3) {
			Game.ball.score = 2;
			playerScore += 1;
		}else if(Game.ball.score == 1) {
			Game.ball.score = 2;
			enemyScore +=1;
		}
	}
	
	public void render(Graphics g) {
		
		for(y = 7; y < Game.HEIGHT - 3; y += 12) {
			
			g.setColor(Color.WHITE);
			g.fillRect((int)x, (int)y, width, height);
		}	
		
		g.setFont(new Font("Arial", Font.BOLD, 40));
		g.drawString(Integer.toString(playerScore), 130, 50);
		
		g.setFont(new Font("Arial", Font.BOLD, 40));
		g.drawString(Integer.toString(enemyScore), 195, 50);
	}
}
