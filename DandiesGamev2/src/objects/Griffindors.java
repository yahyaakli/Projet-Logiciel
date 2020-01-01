package objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Random;

import graphics.SpriteSheet;

public class Griffindors extends GameObject{

	Random r = new Random();
	private Handler handler;
	private BufferedImage griffindors_image;


	int choose=0;
	int hp=100;
	public Griffindors(int x, int y, ID id, Handler handler,  SpriteSheet ss) {
		super(x,y,id, ss);
		this.handler = handler;
		griffindors_image = ss.grabImage(4, 1, 32, 32);
	}
	public void tick() {
		x+=velX;
		y+=velY;

		choose=r.nextInt(10);
		for(int i=0;i<handler.object.size();i++) {
			GameObject tempObject = handler.object.get(i);
			if(tempObject.getId()==ID.block) {
				if(getboundsBig().intersects(tempObject.getbounds())){
					x +=(velX*2)*-1;
					y +=(velX*2)*-1;
					velX*=-1;
					velY*=-1;
				}else if(choose==0) {
					velX=(r.nextInt(4- -4)+-4);
					velY=(r.nextInt(4- -4)+-4);
				}
			}
			if(tempObject.getId()==ID.bullet) {
				if(getboundsBig().intersects(tempObject.getbounds())){
					hp-=50;
					handler.removeObject(tempObject);
				}
			}
		}
		if(hp<=0) handler.removeObject(this);
		
	}
	public void render(Graphics g) {
		g.drawImage(griffindors_image, x, y, null);
		
	}
	public Rectangle getbounds() {
		return new Rectangle(x,y,32,32);
	}
	public Rectangle getboundsBig() {
		return new Rectangle(x-16,y-16,64,64);
	}
}	
