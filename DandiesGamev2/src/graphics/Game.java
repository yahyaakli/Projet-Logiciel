package graphics;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import objects.*;

public class Game extends Canvas implements Runnable{
	
	private static final long serialVersionUID = 7666159906818439827L;
	private boolean isRunning = false;
	private Thread thread;
	private Handler handler;
	
	private BufferedImage level = null;
	private Camera camera;
	private HUD hud;
	public Game() {
		new Window(1000,563,"GAME",this);
		start();
		hud=new HUD();
		handler = new Handler();
		camera = new Camera(0,0);
		this.addKeyListener(new KeyInput(handler));
		this.addMouseListener(new MouseInput(handler,camera));
		
		BufferedImageLoader loader = new BufferedImageLoader();
		level = loader.loadImage("/wizard_level.png");
		
		
		loadLevel(level);
	}
	
	private void start() {
		isRunning = true;
		thread = new Thread(this);
		thread.start();
	}
	private void stop() {
		isRunning = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void run() {
		this.requestFocus();
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int frames = 0;
		while(isRunning) {
			long now = System.nanoTime();
			delta += (now-lastTime)/ns;
			lastTime = now;
			while(delta>=1) {
				tick();
				delta--;
			}
			render();
			frames++;
			if(System.currentTimeMillis() - timer>1000) {
				timer+= 1000;
			//	System.out.println("FPS: "+frames);
				frames = 0;
			}
		}
		stop();
	}
	
	public void tick() {
		for(int i=0; i<handler.object.size();i++) {
			if(handler.object.get(i).getId() == ID.player) {
				camera.tick(handler.object.get(i));
			}
		}
		handler.tick();
		hud.tick();
	}

	public void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		Graphics g = bs.getDrawGraphics();
		Graphics2D g2d = (Graphics2D) g;
		
		////
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, 1000, 563);
		g2d.translate(-camera.getX(), -camera.getY());
		
		handler.render(g);

		g2d.translate(camera.getX(), camera.getY());
		hud.render(g);
		////
		
		g.dispose();
		bs.show();
	}
	//loading the level
	private void loadLevel(BufferedImage image) {
		int w = image.getWidth();
		int h = image.getHeight();
		for(int xx=0;xx<w;xx++) {
			for(int yy=0;yy<h;yy++) {
				int pixel = image.getRGB(xx, yy);
				int red = (pixel>>16) & 0xff;
				int green = (pixel>>8) & 0xff;
				int blue = (pixel) & 0xff;
				if(red == 255)
					handler.addObject(new Block(xx*32,yy*32,ID.block));
				if(blue == 255)
					handler.addObject(new Hero(xx*32,yy*32,ID.player,handler,hud));
				if(green == 255)
					handler.addObject(new Griffindors(xx*32,yy*32,ID.enemy,handler));
			}
		}
	}
	
	public static int clamp(int var, int min, int max) {
		if(var >= max) return var = max;
		else if(var<=min) return var = min;
		else return var;
	}
}
