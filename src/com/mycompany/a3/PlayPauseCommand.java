package com.mycompany.a3;

import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Label;
import com.codename1.ui.events.ActionEvent;

public class PlayPauseCommand extends Command {
	Game myGame;
	GameWorld myGW;
	Button myButton;
	String pause = "Pause";
	String play = "Play";
	int myTime;
	String myS;
	boolean sound;
	boolean repeat;
	
	public PlayPauseCommand(Game g, Button b,int t, GameWorld gw, boolean mid) {
		super("Play/Pause");
		// TODO Auto-generated constructor stub
		myGame = g;
		myS = b.getText();
		myButton = b;
		myTime = t;
		myGW = gw;
		repeat = mid;
	}


	public void setGameWorld(GameWorld gw) {
		myGW = gw;
	}
	
	public void actionPerformed(ActionEvent evt) {
		if(myS.equals("Pause")) {
			myButton.setText(play);
			myS = myButton.getText();
			myGame.getTimer().cancel();
			if(myGW.getSound() == true)
				myGW.getBGSound().pause();
			
			myGame.getAccelButton().setEnabled(!myGame.getAccelButton().isEnabled());
			myGame.getBrakeButton().setEnabled(!myGame.getBrakeButton().isEnabled());
			myGame.getLeftButton().setEnabled(!myGame.getLeftButton().isEnabled());
			myGame.getRightButton().setEnabled(!myGame.getRightButton().isEnabled());
			myGame.getSoundBox().setEnabled(!myGame.getSoundBox().isEnabled());
			myGame.getPositionButton().setEnabled(true);
			myGame.getAccelCommand().setEnabled(false);
			
			myGame.removeKeyListener(97, myGame.getAccelCommand());
			myGame.removeKeyListener(98, myGame.getBrakeCommand());
			myGame.removeKeyListener(108, myGame.getLeftCommand());
			myGame.removeKeyListener(114, myGame.getRightCommand());
			
			
		}
		else if(myS.equals("Play")) {
			myButton.setText(pause);
			myS = myButton.getText();
			myGame.getTimer().schedule(myTime, repeat, myGame);
			if(myGW.getSound() == true)
				myGW.getBGSound().play();
			
			myGame.getAccelButton().setEnabled(!myGame.getAccelButton().isEnabled());
			myGame.getBrakeButton().setEnabled(!myGame.getBrakeButton().isEnabled());
			myGame.getLeftButton().setEnabled(!myGame.getLeftButton().isEnabled());
			myGame.getRightButton().setEnabled(!myGame.getRightButton().isEnabled());
			myGame.getSoundBox().setEnabled(!myGame.getSoundBox().isEnabled());
			myGame.getPositionButton().setEnabled(false);
			myGame.getAccelCommand().setEnabled(true);
			
			myGame.addKeyListener(97, myGame.getAccelCommand());
			myGame.addKeyListener(98, myGame.getBrakeCommand());
			myGame.addKeyListener(108, myGame.getLeftCommand());
			myGame.addKeyListener(114, myGame.getRightCommand());
		}
	}
	
}
