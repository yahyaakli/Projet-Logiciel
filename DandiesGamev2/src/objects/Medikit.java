package objects;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import graphics.SpriteSheet;

public class Medikit extends GameObject {
	
	private BufferedImage medikit_image;
	
	public Medikit(int x, int y, ID id,  SpriteSheet ss) {
		super(x, y, id, ss);
		size=32;
		medikit_image = ss.grabImage(3, 1, size, size);
	}
	public void tick() {
		// TODO Auto-generated method stub
		
	}

	
	public void render(Graphics g) {
		// TODO Auto-generated method stub
		g.drawImage(medikit_image, (int)x,(int) y, null);
	}

	
	public Rectangle getbounds() {
		// TODO Auto-generated method stub
		return new Rectangle((int)x, (int)y, size, size);
	}

	
	public Rectangle getbounds2() {
		// TODO Auto-generated method stub
		return null;
	}

}
