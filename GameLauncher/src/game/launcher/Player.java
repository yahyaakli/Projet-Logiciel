package game.launcher;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

public class Player extends GameObject{

	public Player(int x, int y, ID id) {
		super(x, y, id);

	}

	public void tick() {
		x+=speedX;
		y+=speedY;
	}

	public void render(Graphics g) {
		if(id == ID.player) g.setColor(Color.white);
		if(id == ID.player2) g.setColor(Color.red);
		g.fillRect(x, y, 32, 32);
	}

}
