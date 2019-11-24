package game.launcher;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter{

	private handler handler;

	public KeyInput(handler handler) {
		this.handler = handler;
	}
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		for(int i=0;i<handler.object.size();i++) {
			GameObject tempObject = handler.object.get(i);
			if(tempObject.getId()==ID.player) {
				// key event 
				switch(key) {
				case KeyEvent.VK_W:
					if(tempObject.getY()>32) {
						tempObject.setSpeedY(-5);
					}else{
						tempObject.setSpeedY(0);
					}
					break;
				case KeyEvent.VK_S:
					if(tempObject.getY()<(Game.HEIGHT/2+64)) {
						tempObject.setSpeedY(5);
					}else{
						tempObject.setSpeedY(0);
					}
					break;
				case KeyEvent.VK_A:
					if(tempObject.getX()>32) {
						tempObject.setSpeedX(-5);
					}
					else{
						tempObject.setSpeedX(0);
					}
					break;
				case KeyEvent.VK_D:
					if(tempObject.getX()<Game.WIDTH-64) {
						tempObject.setSpeedX(5);
					}
					else{
						tempObject.setSpeedX(0);
					}
					break;
				}

			}
		}
	}
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		for(int i=0;i<handler.object.size();i++) {
			GameObject tempObject = handler.object.get(i);
			if(tempObject.getId()==ID.player) {
				switch(key) {
				case KeyEvent.VK_W:
					tempObject.setSpeedY(0);
					break;
				case KeyEvent.VK_S:
					tempObject.setSpeedY(0);
					break;
				case KeyEvent.VK_A:
					tempObject.setSpeedX(0);
					break;
				case KeyEvent.VK_D:
					tempObject.setSpeedX(0);
					break;
				}
			}
		}
	}

}
