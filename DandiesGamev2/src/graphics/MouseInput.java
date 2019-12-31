package graphics;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import objects.*;

public class MouseInput extends MouseAdapter{
	
	private Handler handler;
	private Camera camera;
	public MouseInput(Handler handler, Camera camera) {
		this.handler=handler;
		this.camera = camera;
	}
	public void mousePressed(MouseEvent e) {
		int mx = (int)(e.getX() + camera.getX());
		int my = (int)(e.getY() + camera.getY());
		for(int i=0;i<handler.object.size();i++) {
			GameObject tempObject = handler.object.get(i);
			if(tempObject.getId() == ID.player) {
				handler.addObject(new Bullet(tempObject.getX()+16,tempObject.getY()+24,ID.bullet,handler,mx,my));
			}
		}
	}
	
	
}
