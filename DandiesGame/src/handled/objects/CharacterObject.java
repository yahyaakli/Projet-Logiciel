package handled.objects;

import java.awt.Graphics;
import java.awt.Rectangle;

import Interface.g.Cmd;



public abstract class CharacterObject {
	protected int x,y;
	protected ID id;
	protected int speedX,speedY;
	public CharacterObject(int x, int y, ID id) {
		this.x=x;
		this.y=y;
		this.id=id;
	}
	public abstract Rectangle getBounds();
	
	public abstract void tick(Cmd commande);
	public abstract void render(Graphics g);

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public ID getId() {
		return id;
	}

	public void setId(ID id) {
		this.id = id;
	}

	public int getSpeedX() {
		return speedX;
	}

	public void setSpeedX(int speedX) {
		this.speedX = speedX;
	}

	public int getSpeedY() {
		return speedY;
	}

	public void setSpeedY(int speedY) {
		this.speedY = speedY;
	}
}
