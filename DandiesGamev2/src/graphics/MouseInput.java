package graphics;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import objects.*;

public class MouseInput extends MouseAdapter{

	private Handler handler;
	private Camera camera;
	private Game game;
	private SpriteSheet ss;

	public MouseInput(Handler handler, Camera camera, Game game,  SpriteSheet ss) {
		this.handler=handler;
		this.camera = camera;
		this.game = game;
		this.ss = ss;
	}
	public void mousePressed(MouseEvent e) {
		int mx = (int)(e.getX() + camera.getX());
		int my = (int)(e.getY() + camera.getY());
		if(game.ammo > 0) {
			handler.addObject(new Bullet(game.player.getX()+16,game.player.getY()+24,ID.bullet,handler,mx,my, ss));
			game.ammo--;
		}
	}


}
