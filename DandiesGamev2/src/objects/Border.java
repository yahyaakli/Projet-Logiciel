package objects;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import graphics.SpriteSheet;

public class Border extends GameObject {

	private BufferedImage border_image; 

	public Border(int x, int y, ID id, SpriteSheet ss) {
		super(x, y, id, ss);
		size= 32;
		border_image = ss.grabImage(5, 2, size, size);
		
	}

	public void tick() {

	}


	public void render(Graphics g) {
		g.drawImage(border_image,(int) x, (int)y, null);

	}

	public Rectangle getbounds() {
		return new Rectangle((int)x,(int)y,size,size);
	}

	@Override
	public Rectangle getbounds2() {
		// TODO Auto-generated method stub
		return null;
	}

	
}
