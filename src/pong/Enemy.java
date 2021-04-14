package pong;

import java.awt.Color;
import java.awt.Graphics;

public class Enemy {
	
		
		public double x, y;
		public int width, height;
	
		public Enemy(int x, int y) {
			
			this.x = x;
			this.y = y;
			this.width = 10;
			this.height = 60;
		}
		
		public void tick() {
			
			y += (Game.ball.y - y - 2) * 0.3;
			
			if(y+height > Game.HEIGHT) {
				
				y = Game.HEIGHT - height;
			}else if(y < 0) {
				
				y = 0;
			}
		}
		
		public void render(Graphics g) {
			
			g.setColor(Color.WHITE);
			g.fillRect((int)x,(int)y, width, height);
		}
}
