package handled.objects;

import java.awt.Color;
import java.awt.Graphics;

import Interface.g.Game;

public class HUD {
	public static int HP =100;
	public void tick() {
		
		HP=Game.clamp(HP, 0, 100);
	}
	public void render(Graphics g) {
		g.setColor(Color.red);
		g.fillRect(15, 15, 200, 32);
		g.setColor(Color.green);
		g.fillRect(15, 15, 2*HP, 32);
	}
}
