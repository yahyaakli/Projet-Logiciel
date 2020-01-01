package objects;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import graphics.SpriteSheet;

public class Border extends GameObject {

	private BufferedImage border_image; 

	public Border(int x, int y, ID id, SpriteSheet ss) {
		super(x, y, id, ss);
		border_image = ss.grabImage(5, 2, 32, 32);
	}

	public void tick() {

	}


	public void render(Graphics g) {
		g.drawImage(border_image, x, y, null);

	}

	public Rectangle getbounds() {
		return new Rectangle(x,y,32,32);
	}

}
