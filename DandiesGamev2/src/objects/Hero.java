package objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import graphics.Game;


public class Hero extends GameObject{

	Handler handler;
	HUD hud;
	Game game;
	public Hero(int x, int y, ID id, Handler handler, HUD hud, Game game) {
		super(x, y, id);
		this.handler = handler;
		this.hud = hud;
		this.game = game;
	}

	public void tick() {
		x+=velX;
		y+=velY;
		
		collision();
		if(handler.isUp()) velY = -5;
		else if(!handler.isDown()) velY = 0;
		
		if(handler.isDown()) velY = +5;
		else if(!handler.isUp()) velY = 0;
		
		if(handler.isRight()) velX = 5;
		else if(!handler.isLeft()) velX = 0;
		
		if(handler.isLeft()) velX = -5;
		else if(!handler.isRight()) velX = 0;
	}
	private void collision() {
		for(int i=0;i<handler.object.size();i++) {
			GameObject tempObject = handler.object.get(i);
			if(tempObject.getId() == ID.block || tempObject.getId() == ID.enemy) {
				if(getbounds().intersects(tempObject.getbounds())) {
					x+= velX*-1;
					y+= velY*-1;
				}
			}
			if(tempObject.getId() == ID.enemy) {
				if(getbounds().intersects(tempObject.getbounds())) {
					hud.HP--;
				}	
			}
			
			if(tempObject.getId() == ID.crate) {
				if(getbounds().intersects(tempObject.getbounds())) {
					game.ammo += 10;
					handler.removeObject(tempObject);
				}	
			}
		}
	}
	public void render(Graphics g) {
		g.setColor(Color.BLUE);
		g.fillRect(x, y, 32, 32);
	}

	public Rectangle getbounds() {
		return new Rectangle(x,y,32,32);
	}

}
