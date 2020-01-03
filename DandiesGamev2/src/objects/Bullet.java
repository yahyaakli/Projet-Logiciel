package objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import graphics.SpriteSheet;

public class Bullet extends GameObject{

	private Handler handler;
	public Bullet(float x, float y, ID id, Handler handler,int mx, int my,  SpriteSheet ss) {
		super(x,y,id, ss);
		size=8;
		this.handler = handler;
		velX=(mx-x)/10;
		velY=(my-y)/10;
	}
	public void tick() {
		x+=velX;
		y+=velY;
		for(int i=0;i<handler.object.size();i++) {
			GameObject tempObject = handler.object.get(i);
			if(tempObject.getId() == ID.border || tempObject.getId() == ID.wall) {
				if(getbounds().intersects(tempObject.getbounds())) {
					handler.removeObject(this);
				}
			}
		}

	}
	@Override
	public void render(Graphics g) {
		g.setColor(Color.green);
		g.fillOval((int)x, (int)y, size, size);
	}
	@Override
	public Rectangle getbounds() {
		return new Rectangle((int)x,(int)y,size,size);
	}
	@Override
	public Rectangle getbounds2() {
		// TODO Auto-generated method stub
		return null;
	}
	
}	
