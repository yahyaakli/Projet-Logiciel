package Interface.g;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import handled.objects.*;
public class KeyInput extends KeyAdapter{

	private Cmd commande=Cmd.released;;

	public KeyInput() {
	}
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		switch(key) {
		case KeyEvent.VK_UP:
			commande=Cmd.up;
			break;
		case KeyEvent.VK_DOWN:
			commande=Cmd.down;
			break;
		case KeyEvent.VK_LEFT:
			commande=Cmd.left;
			break;
		case KeyEvent.VK_RIGHT:
			commande=Cmd.right;
			break;
		default:
			commande=Cmd.released;
		}
	/*	for(int i=0;i<handler.object.size();i++) {
			CharacterObject tempObject = handler.object.get(i);
			if(tempObject.getId()==ID.player) {
				switch(key) {
				case KeyEvent.VK_UP:
					tempObject.setSpeedY(-5);
					break;
				case KeyEvent.VK_DOWN:
					tempObject.setSpeedY(5);
					break;
				case KeyEvent.VK_LEFT:
					tempObject.setSpeedX(-5);
					break;
				case KeyEvent.VK_RIGHT:
					tempObject.setSpeedX(5);
					break;
				}

			}
		}*/
		if(key==KeyEvent.VK_ESCAPE) System.exit(1);
	}
	public void keyReleased(KeyEvent e) {
		commande=Cmd.released;
	}
	public Cmd getCommande() {
		return commande;
	}	
	

}
