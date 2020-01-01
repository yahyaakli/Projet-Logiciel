package objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import graphics.SpriteSheet;

public class Bullet extends GameObject{

	private Handler handler;
	public Bullet(int x, int y, ID id, Handler handler,int mx, int my,  SpriteSheet ss) {
		super(x,y,id, ss);
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
		g.fillOval(x, y, 8, 8);
	}
	@Override
	public Rectangle getbounds() {
		return new Rectangle(x,y,8,8);
	}
}	
