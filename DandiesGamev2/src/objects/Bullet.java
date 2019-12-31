package objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Bullet extends GameObject{

	private Handler handler;
	public Bullet(int x, int y, ID id, Handler handler,int mx, int my) {
		super(x,y,id);
		this.handler = handler;
		velX=(mx-x)/10;
		velY=(my-y)/10;
	}
	public void tick() {
		x+=velX;
		y+=velY;
		for(int i=0;i<handler.object.size();i++) {
			GameObject tempObject = handler.object.get(i);
			if(tempObject.getId() == ID.block) {
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
