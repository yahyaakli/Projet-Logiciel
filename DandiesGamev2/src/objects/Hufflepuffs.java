package objects;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Random;

import graphics.SpriteSheet;
import graphics.*;

public class Hufflepuffs extends GameObject {
	
	Random r = new Random();
	private Handler handler;
	private BufferedImage hufflepuffs_image;
	private GameObject player;
	
	int choose=0;
	int hp=100;

	public Hufflepuffs(int x, int y, ID id, Handler handler, SpriteSheet ss) {
		super(x, y, id, ss);
		this.handler = handler;
		hufflepuffs_image = ss.grabImage(5, 1, 32, 32);
		for(int i=0; i<handler.object.size();i++) {
			if(handler.object.get(i).getId()==ID.player) {
				player = handler.object.get(i);
			}
		}
	}

	
	public void tick() {

		float diffX = x - player.getX();
		float diffY = y - player.getY();
		float diffX2 = x - player.getX();
		float diffY2 = y - player.getY();
		float distance = (float) Math.sqrt(diffX*diffX+diffY*diffY);
		
		velX = (float) ((-1.0/distance)*diffX2);
		velY = (float) ((-1.0/distance)*diffY2);
		x+=velX;
		y+=velY;
		if(y<=0 || y>=Game.HEIGHT-32) velY*=-1;
		if(x<=0 || x>=Game.WIDTH-16) velX*=-1;
		for(int i=0;i<handler.object.size();i++) {
			GameObject tempObject = handler.object.get(i);
			if(tempObject.getId()==ID.bullet) {
				if(getboundsBig().intersects(tempObject.getbounds())){
					hp-=20;
					handler.removeObject(tempObject);
				}
			}
		}
		if(hp<=0) handler.removeObject(this);
	}

	
	public void render(Graphics g) {
		g.drawImage(hufflepuffs_image,(int) x,(int) y, null);
		
	}
	public Rectangle getbounds() {
		return new Rectangle((int)x,(int)y,32,32);
	}
	public Rectangle getboundsBig() {
		return new Rectangle((int)(x)-16,(int)(y)-16,64,64);
	}

}
