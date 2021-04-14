package pong;

import java.awt.Color;
import java.awt.Graphics;

public class Player {

	
	public boolean up, down;
	
	public int x, y;
	public int width, height;
	
	public Player(int x, int y) {
		
		this.x = x;
		this.y = y;
		this.width = 10;
		this.height = 60;
	}
	
	public void tick(){
		
		if(up) {
			
			y-=2;
		}else if(down) {
			
			y+=2;
		}
		
		if(y+height > Game.HEIGHT) {
			
			y = Game.HEIGHT - height;
		}else if(y < 0) {
			
			y = 0;
		}
	}
	
	public void render(Graphics g) {
		
		g.setColor(Color.WHITE);
		g.fillRect(x,y, width, height);
	}
}
