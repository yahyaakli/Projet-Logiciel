package objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import graphics.SpriteSheet;

public class Crate extends GameObject {

	private BufferedImage crate_image;

	public Crate(int x, int y, ID id,  SpriteSheet ss) {
		super(x, y, id, ss);
		size=32;
		crate_image = ss.grabImage(6, 2, size, size);
	}

	public void tick() {
		
	}

	public void render(Graphics g) {
		g.drawImage(crate_image, (int)x,(int) y, null);
	}

	public Rectangle getbounds() {
		return new Rectangle((int)x, (int)y, size, size);
	}

	@Override
	public Rectangle getbounds2() {
		// TODO Auto-generated method stub
		return null;
	}

}
