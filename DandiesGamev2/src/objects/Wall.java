package objects;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import graphics.SpriteSheet;

public class Wall  extends GameObject {
	
	private BufferedImage wall_image; 

	public Wall(int x, int y, ID id, SpriteSheet ss) {
		super(x, y, id, ss);
		wall_image = ss.grabImage(5, 2, 32, 32);
	}

	public void tick() {
		
	}

	
	public void render(Graphics g) {
		g.drawImage(wall_image, (int)x,(int) y, null);
		
	}

	public Rectangle getbounds() {
		return new Rectangle((int)x,(int)y,32,32);
	}

}
