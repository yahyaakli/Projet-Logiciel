package graphics;

import java.util.Timer;
import java.util.TimerTask;

import graphics.Game.STATE;

public class CountDown {
	
	private int timecounter;
	private Timer time;
	private Game game;
	public CountDown(Game game) {
		this.game=game;
		this.timecounter = 101;
		 
	}
	
	
	public int gettimecounter() {
		return timecounter;
	} 


	public void tick() {
		time = new Timer();
		time.schedule(new TimerTask() {
			public void run() {
				if (game.gameState!=STATE.Pause)timecounter--;
				
			}
		}, 1000, 1000);
			
	}
	

}
