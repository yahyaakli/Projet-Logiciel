package handled.objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;
import Interface.g.Game;
public class Hufflepuffs extends CharacterObject {
	
	Random r;
	public int count=20;

	public Hufflepuffs(int x, int y, ID id) {
		super(x, y, id);
		r = new Random();
	}
	public Rectangle getBounds() {
		return new Rectangle(x,y,18,18);
	}
	
	public void tick() {
		// TODO Auto-generated method stub
		double direction=r.nextDouble();
		if (direction<0.25 && count==20) { x-=10;
		count=0;
		}
		else if(direction < 0.50 && count==20) {
			x+=10; count=0;
		}
		else if (direction <0.75&& count==20) {
			y-=10; count=0;
		}
		else if (direction <1 && count==20) {
			y+=10; count=0;
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
