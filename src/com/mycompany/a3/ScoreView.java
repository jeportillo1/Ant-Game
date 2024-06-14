package com.mycompany.a3;

import java.util.Observable;
import java.util.Observer;

import com.codename1.ui.Container;
import com.codename1.ui.Label;

public class ScoreView extends Container implements Observer {
	private GameWorld gw;
	private String trigger;
	Label cl;
	Label li;
	Label ad;
	Label so;
	
	public ScoreView(GameWorld g) {
		gw = g;
		this.cl = new Label("Time: " + gw.getClock());
		this.li = new Label(" Lives Left: " + gw.getGameLives());
		this.ad = new Label(gw.getAntData());
		
		if(gw.getSound() == true)
			trigger = "ON";
		else 
			trigger = "OFF";
		
		this.so = new Label(" Sound: " + trigger);
		
	}
	
	@Override
	public void update(Observable obs, Object arg) {
		
		if(obs == gw) {
			cl.setText("Time: " + gw.getClock());
			li.setText(" Lives Left: " + gw.getGameLives());
			ad.setText(gw.getAntData());
			if(gw.getSound() == true)
				trigger = "ON";
			else 
				trigger = "OFF";
			so.setText(" Sound: " + trigger);
		}
	}
	
	public Label myTimeLabel() {
		
		return cl;
	}
	
	public Label myLifeLabel() {
		
		return li;
	}
	
	public Label myAntLabel() {
		
		return ad;
	}
	
	public Label mySoundLabel() {
	
		return so;
	}

}
