package objects;

import java.awt.Graphics;
import java.awt.Rectangle;

import graphics.SpriteSheet;

public abstract class GameObject {
	
	protected float x, y;
	protected float velX =0, velY=0;
	protected ID id;
	protected SpriteSheet ss;
	protected int size;
	
	public GameObject(float x, float y, ID id, SpriteSheet ss) {
		this.x = x;
		this.y = y;
		this.id = id;
		this.ss = ss;
	}
	
	public ID getId() {
		return id;
	}

	public void setId(ID id) {
		this.id = id;
	}

	public abstract void tick();
	public abstract void render(Graphics g);
	public abstract Rectangle getbounds();
	public abstract Rectangle getbounds2();
	
	public float getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public float getVelX() {
		return velX;
	}

	public void setVelX(float velX) {
		this.velX = velX;
	}

	public float getVelY() {
		return velY;
	}

	public void setVelY(float velY) {
		this.velY = velY;
	}
	
	
}
