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
		
	}

	
	public void render(Graphics g) {
		g.drawImage(medikit_image, (int)x,(int) y, null);
	}

	
	public Rectangle getbounds() {
		return new Rectangle((int)x, (int)y, size, size);
	}

	
	public Rectangle getbounds2() {
		return null;
	}

}
