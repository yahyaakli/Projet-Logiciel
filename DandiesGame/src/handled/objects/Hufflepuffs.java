package handled.objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;
import Interface.g.Game;
public class Hufflepuffs extends CharacterObject {
	
	Random r;
	private final int COUNT_MAX = 20;
	public int count=COUNT_MAX;

	public Hufflepuffs(int x, int y, ID id) {
		super(x, y, id);
		r = new Random();
	}
	public Rectangle getBounds() {
		return new Rectangle(x,y,18,18);
	}
	
	public void tick() {
		x+=speedX;
		y+=speedY;
		x=Game.clamp(x, 0, Game.WIDTH - 42);
		y=Game.clamp(y, 0, Game.HEIGHT - 64);
		double direction=r.nextDouble();
		if (direction<0.25 && count==COUNT_MAX) { 
			setSpeedX(-1);
			count=0;
		}
		else if(direction < 0.50 && count==COUNT_MAX) {
			setSpeedX(1);
			count=0;
		}
		else if (direction <0.75&& count==COUNT_MAX) {
			setSpeedY(-1);
			count=0;
		}
		else if (direction <1 && count==COUNT_MAX) {
			setSpeedY(1);
			count=0;
		}
		count++;
		x=Game.clamp(x, 0,Game.WIDTH-32);
		y=Game.clamp(y, 0, Game.HEIGHT-32);
	}

	
	public void render(Graphics g) {
		// TODO Auto-generated method stub
		g.setColor(Color.BLUE);
		g.fillOval(x, y, 20, 20);
	}

}
