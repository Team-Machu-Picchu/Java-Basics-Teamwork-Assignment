import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;

import javazoom.jl.player.Player;

// A music and sound effect player,
// working in a separate thread.
public class Sound extends Thread {

    private String fileLocation;
    private boolean loop;
    private Player player;

    // Set up our song.
    public Sound() {
        this.fileLocation = "sounds/wipala.mp3";
        this.loop = true;
    }
    
    //on-click sound
    public static void clickSound() {
		try {
			AudioInputStream click = AudioSystem.getAudioInputStream(new File("sounds/blop.wav"));			
			AudioFormat format = click.getFormat();
			DataLine.Info info = new DataLine.Info(Clip.class, format);
			Clip clip = (Clip)AudioSystem.getLine(info);
			clip = AudioSystem.getClip();
			clip.open(click);
			clip.addLineListener(new LineListener() {
				@Override
				public void update(LineEvent event) {
					if (event.getType() == LineEvent.Type.STOP) {
						event.getLine().close();					
					}
				}
			});
			clip.start();
		} catch(Exception ex) {
			System.out.println("Error with playing sound.");
			ex.printStackTrace();
		}
	}
    
    public void run() {
    	// Play in an infinite loop.
        try {
        	while (loop) {
                FileInputStream fis = new FileInputStream(fileLocation);
                BufferedInputStream bis = new BufferedInputStream(fis);
                player = new Player(bis);
                player.play();
            }
        } catch (Exception ioe) {
        	System.err.printf("%s\n", ioe.getMessage());
        }
    }

    // Close the player and it thread.
    public void close(){
        loop = false;
        player.close();
        this.interrupt();
    }
}
