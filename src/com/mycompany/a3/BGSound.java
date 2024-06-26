package com.mycompany.a3;

import java.io.InputStream;

import com.codename1.media.Media;
import com.codename1.media.MediaManager;
import com.codename1.ui.Display;

public class BGSound implements Runnable{
	private Media m;
	public BGSound(String fileName) {
		if (Display.getInstance().getCurrent() == null) {
			System.out.println("Error: Create sound objects after calling show()!");
			System.exit(0);
		}
		
		while(m == null) {
			try {
				InputStream is = Display.getInstance().getResourceAsStream(getClass(), "/"+fileName);
				//attach a runnable to run when media has finished playing as the last parameter
				m = MediaManager.createMedia(is, "audio/wav", this);
			}
		
			catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	//pause playing the sound
	public void pause() { 
		m.pause();
	} 
	
	//continue playing from where we have left off
	public void play() { 
		m.play();
	} 

	//entered when media has finished playing
	public void run() {
		//start playing from time zero (beginning of the sound file)
		m.setTime(0);
		m.play();
	}
	

}
