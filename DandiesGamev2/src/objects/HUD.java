package objects;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import graphics.*;

public class HUD {
	public int HP =100;
	private int greenValue = 255;
	public static int score;
	public HUD(){
		score = 0;
	}
	public void tick() {
		HP=Game.clamp(HP, 0, 100);
		greenValue = Game.clamp(greenValue, 0, 255);
		greenValue = HP*2;
		
	}
	public void render(Graphics g) {
		g.setColor(Color.GRAY);
		g.fillRect(15, 15, 200, 32);
		g.setColor(new Color(75,greenValue,0));
		g.fillRect(15, 15, HP*2, 32);
		g.setColor(Color.WHITE);
		g.drawRect(15, 15, 200, 32);
		Font fnt = new Font("Courier",1,20);
		g.setFont(fnt);
		g.setColor(Color.white);
		g.drawString("Score:"+score, 450, 35);
		
	}
}
