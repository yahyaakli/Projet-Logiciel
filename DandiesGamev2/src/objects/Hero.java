package objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import graphics.Game;
import graphics.SpriteSheet;


public class Hero extends GameObject{

	Handler handler;
	HUD hud;
	Game game;
	private BufferedImage hero_image;

	public Hero(int x, int y, ID id, Handler handler, HUD hud, Game game,  SpriteSheet ss) {
		super(x, y, id, ss);
		this.handler = handler;
		this.hud = hud;
		this.game = game;
		hero_image = ss.grabImage(1, 1, 32, 48);
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
			if(tempObject.getId() == ID.wall || tempObject.getId() == ID.border || tempObject.getId() == ID.griffindors || tempObject.getId() == ID.hufflepuffs) {
				if(getbounds().intersects(tempObject.getbounds())) {
					x+= velX*-1;
					y+= velY*-1;
				}
			}
			if(tempObject.getId() == ID.griffindors || tempObject.getId() == ID.hufflepuffs) {
				if(getbounds().intersects(tempObject.getbounds())) {
					hud.HP--;
				}	
			}
			
			if(tempObject.getId() == ID.crate) {
				if(getbounds().intersects(tempObject.getbounds())) {
					game.ammo += 30;
					handler.removeObject(tempObject);
				}	
			}
		}
	}
	public void render(Graphics g) {
		g.drawImage(hero_image, x, y, null);
	}

	public Rectangle getbounds() {
		return new Rectangle(x,y,32,32);
	}

}
