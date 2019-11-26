package handled.objects;

import java.awt.Color;
import java.awt.Graphics;

import Interface.g.Game;

public class Hero extends CharacterObject{
	
	public Hero(int x, int y, ID id) {
		super(x, y, id);

	}

	public void tick() {
		x+=speedX;
		y+=speedY;
		x=Game.clamp(x, 0, Game.WIDTH - 42);
		y=Game.clamp(y, 0, Game.HEIGHT - 64);
	}

	public void render(Graphics g) {
		g.setColor(Color.black);
		g.fillRect(x, y, 32, 32);
	}

}