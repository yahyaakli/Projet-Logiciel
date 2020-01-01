package graphics;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import graphics.Game.STATE;
import objects.*;



public class Menu extends MouseAdapter{

	private Game game;
	private Handler handler;



	public Menu(Game game, Handler handler) {
		this.game=game;
		this.handler=handler;
	}


	public void mousePressed(MouseEvent e) {
		int mx =e.getX();
		int my=e.getY();
		
		
		//Play button
		if(game.gameState==STATE.Menu) {
			if(mouseOver(mx,my,400,200,200,64)) {
				game.gameState=STATE.Game;
				game.loadLevel(game.level);
			}
		}
		
		//Quit button
		if(mouseOver(mx,my,400,400,200,64)) {
			System.exit(1);
		}
		
		//Help button
		if(game.gameState==STATE.Menu) {
			if(mouseOver(mx,my,400,300,200,64)) {
				game.gameState=STATE.Help;
		}
		}
		
		//Back button for help
		if(game.gameState==STATE.Help) {
			if(mouseOver(mx,my,400,450,200,64)) {
				game.gameState=STATE.Menu;
			}
		}
	}

	public void MouseReleased(MouseEvent e) {

	}

	private boolean mouseOver(int mx, int my,int x, int y,int width, int height) {
		if (mx > x && mx < x+width) {
			if(my>y && my<y+height) {
				return true;
			}else return false;
		}else return false;
	}


	public void tick() {

	}

	public void render(Graphics g) {
		if(game.gameState ==STATE.Menu) {
		Font fnt=new Font("arial",1,50);
		Font fnt1=new Font("arial",1,30);

		g.setFont(fnt);
		g.setColor(Color.white);
		g.drawString("Menu", 430, 90);

		g.setFont(fnt1);
		g.drawRect(400, 200, 200, 64);
		g.drawString("Play", 470, 240);

		g.drawRect(400, 300, 200, 64);
		g.drawString("Help", 470, 340);

		g.drawRect(400, 400, 200, 64);
		g.drawString("Quit", 470, 440);
		
		}else if(game.gameState ==STATE.Help) {
			Font fnt=new Font("arial",1,50);
			Font fnt1=new Font("arial",1,30);

			g.setFont(fnt);
			g.setColor(Color.white);
			g.drawString("Help", 430, 90);
			
			g.setFont(fnt1);
			g.drawString("", 100, 100);
			
			g.setFont(fnt1);
			g.drawRect(400, 450, 200, 64);
			g.drawString("Back", 460, 490);
		}
	}

}