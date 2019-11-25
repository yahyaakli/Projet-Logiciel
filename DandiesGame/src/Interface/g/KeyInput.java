package Interface.g;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import handled.objects.*;
public class KeyInput extends KeyAdapter{

	private Resources handler;

	public KeyInput(Resources handler) {
		this.handler = handler;
	}
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		for(int i=0;i<handler.object.size();i++) {
			CharacterObject tempObject = handler.object.get(i);
			if(tempObject.getId()==ID.player) {
				// key event 
				switch(key) {
				case KeyEvent.VK_W:
					tempObject.setSpeedY(-5);
					break;
				case KeyEvent.VK_S:
					tempObject.setSpeedY(5);
					break;
				case KeyEvent.VK_A:
					tempObject.setSpeedX(-5);
					break;
				case KeyEvent.VK_D:
					tempObject.setSpeedX(5);
					break;
				}

			}
		}
		if(key==KeyEvent.VK_ESCAPE) System.exit(1);
	}
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		for(int i=0;i<handler.object.size();i++) {
			CharacterObject tempObject = handler.object.get(i);
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
