package SomeGraphs;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

public class SoundPlayer implements Runnable{
	@Override
	public void run() {
			try {
				new Player(new FileInputStream(getClass().getClassLoader().getResource("res//sound.mp3").getPath())).play();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JavaLayerException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
	}

}
