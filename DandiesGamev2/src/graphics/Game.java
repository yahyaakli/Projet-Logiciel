package graphics;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
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

	public BufferedImage level = null;
	private BufferedImage sprite_sheet = null;
	private BufferedImage floor = null;
	private Camera camera;
	private SpriteSheet ss;
	private HUD hud;
	public int ammo = 0;
	private Menu menu;
    
	
	public enum STATE {
		Menu,
		Help,
		Game
	};
	public STATE gameState=STATE.Menu;

	public Game() {
		new Window(1000,563,"GAME",this);
		start();
		hud=new HUD();
		handler = new Handler();
		camera = new Camera(0,0);
		menu=new Menu(this, handler);
		this.addKeyListener(new KeyInput(handler));
		this.addMouseListener(menu);


		BufferedImageLoader loader = new BufferedImageLoader();
		//level = loader.loadImage("/wizard_level.png");
		level = loader.loadImage("/wizard_level_2.png");
		sprite_sheet = loader.loadImage("/sprite_sheet.png");
		ss= new SpriteSheet(sprite_sheet);

		floor = ss.grabImage(4, 2, 32, 32);
		this.addMouseListener(new MouseInput(handler,camera,this, ss));


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
		if(gameState==STATE.Game) {
			for(int i=0; i<handler.object.size();i++) {
				if(handler.object.get(i).getId() == ID.player) {
					camera.tick(handler.object.get(i));
				}
			}
			handler.tick();

			hud.tick();
		}else {
			if(gameState==STATE.Menu) {
				menu.tick();
			}
		}
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


		g2d.translate(-camera.getX(), -camera.getY());

		for (int xx = 0; xx < 32*72 ; xx+=32) {
			for (int yy = 0; yy < 32*72 ; yy+=32) {
				g.drawImage(floor, xx, yy, null);
			}
		}
		handler.render(g);

		g2d.translate(camera.getX(), camera.getY());
		if(gameState==STATE.Game) {

			hud.render(g);

			Font fnt = new Font("Courier",1,20);
			g.setFont(fnt);
			g.setColor(Color.white);
			g.drawString("Ammo: "+ammo,240,35);
		}else if(gameState==STATE.Menu || gameState==STATE.Help) {
				menu.render(g);
		}
		////

		g.dispose();
		bs.show();
	}
	//loading the level
	public void loadLevel(BufferedImage image) {
		int w = image.getWidth();
		int h = image.getHeight();

		for(int xx=0;xx<w;xx++) {
			for(int yy=0;yy<h;yy++) {
				int pixel = image.getRGB(xx, yy);
				int red = (pixel>>16) & 0xff;
				int green = (pixel>>8) & 0xff;
				int blue = (pixel) & 0xff;
				if(red == 255 && blue == 0 && green ==0)
					handler.addObject(new Border(xx*32,yy*32,ID.border, ss));
				if(red == 255 && blue == 0 && green ==255)
					handler.addObject(new Wall(xx*32,yy*32,ID.wall, ss));
				if(red == 0 && blue == 255 && green == 0)
					handler.addObject(new Hero(xx*32,yy*32,ID.player,handler,hud,this, ss));
				if(red == 0 && blue == 0 && green == 255)
					handler.addObject(new Griffindors(xx*32,yy*32,ID.griffindors,handler, ss));
				if(red == 255 && blue == 255 && green == 0)
					handler.addObject(new Hufflepuffs(xx*32,yy*32,ID.hufflepuffs,handler, ss));
				if (red == 0 && blue == 255 && green == 255)
					handler.addObject(new Crate(xx*32, yy*32, ID.crate, ss));
			}
		}
	}


	public static int clamp(int var, int min, int max) {
		if(var >= max) return var = max;
		else if(var<=min) return var = min;
		else return var;
	}
}