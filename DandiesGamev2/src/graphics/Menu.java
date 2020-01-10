package graphics;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import graphics.Game.STATE;
import objects.*;



public class Menu extends MouseAdapter{

	private Game game;
	private BufferedImageLoader loader;
	public Menu(Game game) {
		this.game=game;
		this.loader = new BufferedImageLoader();
	}


	public void mousePressed(MouseEvent e) {
		int mx =e.getX();
		int my=e.getY();


		//Play button
		if(game.gameState==STATE.Menu) {
			if(mouseOver(mx,my,400,200,200,64)) {
				game.gameState=STATE.init;
				game.init_game();
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

		if(game.gameState==STATE.GameOver) {
			if(mouseOver(mx,my,380, 260, 240, 64)) {
				game.gameState=STATE.init;
				game.init_game();
			}
			if (mouseOver(mx,my,380, 360, 240, 64)) {
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
			BufferedImage logo = loader.loadImage("/dandiesLogo.png");
			
			Font fnt=new Font("arial",1,20);
			Font fnt1=new Font("arial",1,30);
			g.setColor(Color.BLACK);
			g.fillRect(0, 0, Game.WIDTH, Game.HEIGHT);
			g.drawImage(logo, 405,50 , null);
			g.setColor(Color.WHITE);
			g.setFont(fnt1);
			g.drawRect(400, 200, 200, 64);
			g.drawString("Play", 470, 240);

			g.drawRect(400, 300, 200, 64);
			g.drawString("Help", 470, 340);

			g.drawRect(400, 400, 200, 64);
			g.drawString("Quit", 470, 440);

		}else if(game.gameState ==STATE.init) {
			Font fnt=new Font("Courier",1,50);
			g.setFont(fnt);
			g.setColor(Color.WHITE);
			g.drawString("loading", 400, 170);
		}
		else if(game.gameState ==STATE.Help) {
			Font fnt=new Font("arial",1,50);
			Font fnt1=new Font("arial",1,30);
			g.setColor(Color.BLACK);
			g.fillRect(0, 0, Game.WIDTH, Game.HEIGHT);
			g.setFont(fnt);
			g.setColor(Color.WHITE);
			g.drawString("Help", 430, 90);

			g.setFont(fnt1);
			g.drawString("# Launch the game",60,130);
			g.drawString("# Move your dandy by using arrow keys",60,180)	;	
			g.drawString("# Use arms. There are arms scattered on the maze, they will help",60,230)	;		 
			g.drawString("you hitting your enemies, click on the enemy using the mouse ",60,280);
			g.drawString("and shoot at him with your best bullet!",60,330);
			g.drawString("# Avoid ghosts. Avoid differents enemies in the maze. Once you ",60,380)			;
			g.drawString("reach 0 hit points, it's game over!",60,430);


			g.setFont(fnt1);
			g.drawRect(400, 450, 200, 64);
			g.drawString("Back", 460, 490);
		}
		else if(game.gameState ==STATE.GameOver) {

			Font fnt=new Font("arial",1,50);
			Font fnt1=new Font("arial",1,30);
			Font fnt2=new Font("arial",1,20);
			
			g.setColor(Color.BLACK);
			g.fillRect(0, 0, Game.WIDTH, Game.HEIGHT);
			g.setFont(fnt);
			g.setColor(Color.WHITE);
			g.drawString("Game Over", 370, 150);
			g.setFont(fnt2);
			g.setColor(Color.WHITE);
			g.drawString("You lost with a score of "+HUD.score+" !", 380, 200);

			g.setFont(fnt1);
			g.drawRect(380, 260, 240, 64);
			g.drawString("Try again", 430, 300);

			g.drawRect(380, 360, 240, 64);
			g.drawString("Back", 460, 400);

		}

	}

}