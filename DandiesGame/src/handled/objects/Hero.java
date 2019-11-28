package handled.objects;

import java.awt.Color;

import java.awt.Graphics;
import java.awt.Rectangle;

import Interface.g.Cmd;
import Interface.g.Game;

public class Hero extends CharacterObject{
	Resources resources;
	public Hero(int x, int y, ID id,Resources resources) {
		super(x, y, id);
		this.resources=resources;

	}
	public Rectangle getBounds() {
		return new Rectangle(x,y,32,32);
	}
	public void tick(Cmd commande) {
		x+=speedX;
		y+=speedY;
		x=Game.clamp(x, 0, Game.WIDTH - 42);
		y=Game.clamp(y, 0, Game.HEIGHT - 64);
		switch(commande) {
		case up:
			setSpeedY(-5);
			break;
		case down:
			setSpeedY(5);
			break;
		case left:
			setSpeedX(-5);
			break;
		case right:
			setSpeedX(5);
			break;
		default:
			setSpeedX(0);
			setSpeedY(0);
		}
		collision();
	}
	public void collision() {
		for (int i=0;i<resources.object.size();i++) {
			CharacterObject tempObject=resources.object.get(i);
			if (tempObject.getId() ==ID.Enemy) {
				if(getBounds().intersects(tempObject.getBounds())) {
					HUD.HP--;
				}
			}
		}
	}

	public void render(Graphics g) {
		g.setColor(Color.black);
		g.fillRect(x, y, 32, 32);
	}

}