package handled.objects;

import java.awt.Graphics;
import java.util.LinkedList;

public class Resources {

	public LinkedList<CharacterObject> object = new LinkedList<CharacterObject>();
	
	public void tick() {
		for(int i=0;i<object.size();i++) {
			CharacterObject tempObject = object.get(i);
			tempObject.tick();
		}
	}
	public void render(Graphics g) {
		for(int i=0;i<object.size();i++) {
			CharacterObject tempObject = object.get(i);
			tempObject.render(g);
		}
	}
	public void addObject(CharacterObject obj) {
		this.object.add(obj);
	}
	public void removeObject(CharacterObject obj) {
		this.object.remove(obj);
	}
	
}
