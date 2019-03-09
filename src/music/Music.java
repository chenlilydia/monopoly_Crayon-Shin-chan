package music;

import java.applet.AudioClip;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JApplet;

import control.GameRunning;

public class Music {

	private List<AudioClip> au = new ArrayList<AudioClip>();

	private AudioClip gameMusic;

	public Music() {
		au.add(JApplet.newAudioClip(getClass().getResource("xinmusic.wav")));
	}

	public void start() {
		gameMusic = au.get(0);
		if (gameMusic != null) {
			gameMusic.loop();
		}
	}
	public void gameOver() { 
		if (gameMusic != null) {
			gameMusic.stop();
		}
	}
}
