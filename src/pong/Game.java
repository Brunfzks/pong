package pong;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

public class Game extends Canvas implements Runnable, KeyListener{
	
	private static final long serialVersionUID = 1L;
	public static int WIDTH = 350;
	public static int HEIGHT = 320;
	public static int SCALE = 3;
	private  boolean isRunning = true;
	
	public BufferedImage layer = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
	
	public static Player player;
	public static Enemy enemy;
	public static Ball ball;
	public static Campo campo;
	
	
	public Game() {
		
		setPreferredSize(new Dimension(WIDTH*SCALE, HEIGHT*SCALE));
		this.addKeyListener(this);
		player = new Player(0,HEIGHT/2 - 40);
		enemy = new Enemy(340, HEIGHT/2 - 40);
		ball = new Ball(WIDTH/2 - 1, HEIGHT/2 - 1);
		campo = new Campo(175);
		
	}
	
	public static void main(String[] args) {
		Game game =  new Game();
		JFrame frame = new JFrame();
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.add(game);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
		new Thread(game).start();
	}
	
	public void tick() {
		
		player.tick();
		enemy.tick();
		ball.tick();
		campo.tick();
	}
	
	public void render() {
		
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		
		
		Graphics g = layer.getGraphics();
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		
		player.render(g);
		enemy.render(g);
		ball.render(g);
		campo.render(g);
		
		g = bs.getDrawGraphics();
		g.drawImage(layer, 0, 0, WIDTH*SCALE, HEIGHT*SCALE, null);
		bs.show();
	}
	
	public void run() {
		
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		int frames = 0;
		double timer = System.currentTimeMillis();
		while(isRunning) {
			
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			if(delta >= 1) {
				tick();
				render();
				frames++;
				delta--;
			}
			
			if(System.currentTimeMillis() - timer >= 1000) {
				System.out.println("FPS: " + frames);
				frames = 0;
				timer += 1000;
			}
		}
	}

	
	public void keyTyped(KeyEvent e) {
		
		
	}

	
	public void keyPressed(KeyEvent e) {
		
		if(e.getKeyCode() == KeyEvent.VK_UP) {
			System.out.println("cima");
			player.up = true;
		}else if(e.getKeyCode() == KeyEvent.VK_DOWN) {
			System.out.println("Baixo");
			player.down = true;
		}	
	}

	
	public void keyReleased(KeyEvent e) {
		
		if(e.getKeyCode() == KeyEvent.VK_UP) {
			
			player.up = false;
		}else if(e.getKeyCode() == KeyEvent.VK_DOWN) {
			System.out.println("Baixo");
			player.down = false;
		}	
		
	}
	
	
}
