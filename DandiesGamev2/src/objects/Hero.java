package objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.lang.Math;

import com.sun.glass.ui.EventLoop.State;

import graphics.Game;
import graphics.Game.STATE;
import graphics.SpriteSheet;


public class Hero extends GameObject{

	Handler handler;
	HUD hud;
	Game game;
	private BufferedImage hero_image;
	private static final int speed = 3;

	public Hero(float x, float y, ID id, Handler handler, HUD hud, Game game,  SpriteSheet ss) {
		super(x, y, id, ss);
		this.handler = handler;
		this.hud = hud;
		this.game = game;
		size=32;
		hero_image = ss.grabImage(1, 1, size, 48);
	}

	public void tick() {
		x+=velX;
		y+=velY;

		collision();
		if(handler.isUp()) velY = -speed;
		else if(!handler.isDown()) velY = 0;

		if(handler.isDown()) velY = +speed;
		else if(!handler.isUp()) velY = 0;

		if(handler.isRight()) velX = speed;
		else if(!handler.isLeft()) velX = 0;

		if(handler.isLeft()) velX = -speed;
		else if(!handler.isRight()) velX = 0;

		if(hud.HP == 0 || game.countdown.gettimecounter() == -1) {
			handler.removeObject(this);
			game.gameState = STATE.GameOver;
		}
		

	}

		
	
	private void collision() {
		for(int i=0;i<handler.object.size();i++) {
			GameObject tempObject = handler.object.get(i);
			if(tempObject.getId() == ID.wall || tempObject.getId() == ID.border || tempObject.getId() == ID.griffindors || tempObject.getId() == ID.hufflepuffs) {
				if(getbounds().intersects(tempObject.getbounds())) {

					float[] pos={x,y};
					float[] limit={tempObject.x,tempObject.y,tempObject.x+tempObject.size,tempObject.y+tempObject.size};
					pos = clamp2D(pos , limit );
					x=pos[0];
					y=pos[1];

					pos[0] +=size;
					pos = clamp2D(pos , limit );
					x=pos[0]-size;
					y=pos[1];

					pos[1]+=size;
					pos = clamp2D(pos , limit );
					x=pos[0]-size;
					y=pos[1]-size;

					pos[0]-=size;
					pos = clamp2D(pos , limit );
					x=pos[0];
					y=pos[1]-size;

				}
			}
			if(tempObject.getId() == ID.griffindors || tempObject.getId() == ID.hufflepuffs) {
				if(getbounds().intersects(tempObject.getbounds2())) {
					hud.HP--;
					tempObject.velX=0;
					tempObject.velY=0;
				}	
			}

			if(tempObject.getId() == ID.crate) {
				if(getbounds().intersects(tempObject.getbounds())) {
					game.ammo += 30;
					handler.removeObject(tempObject);
				}	
			}
			if ( tempObject.getId() == ID.medikit) {
				if(getbounds().intersects(tempObject.getbounds())) {
					hud.HP += 20;
					handler.removeObject(tempObject);
					game.clamp(hud.HP,0,100);
				}	
			}
		}
	}
	public float[] clamp2D(float[] pos, float[] limit) {

		if ((pos[1]-limit[1])*(pos[1]-limit[3])<=0) {

			if ((pos[0]-limit[0])*(pos[0]-limit[2])<=0) {

				if (Math.abs(pos[0]-limit[0])<Math.abs(pos[0]-limit[2])) pos[0]=limit[0]-1;
				else pos[0]=limit[2];

				if (Math.abs(pos[1]-limit[1])<Math.abs(pos[1]-limit[3])) pos[1]=limit[1]-1;

				else pos[1]=limit[3];

			}
		}

		return pos;
	}
	public void render(Graphics g) {
		g.drawImage(hero_image, (int)x, (int)y, null);
	}

	public Rectangle getbounds() {
		return new Rectangle((int)x,(int)y,size,size);
	}

	@Override
	public Rectangle getbounds2() {
		// TODO Auto-generated method stub
		return null;
	}

}
