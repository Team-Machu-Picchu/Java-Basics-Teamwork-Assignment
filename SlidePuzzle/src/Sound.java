import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

import javazoom.jl.player.Player;

public class Sound extends Thread {

    private String fileLocation;
    private boolean loop;
    private Player player;

    public Sound() {
        this.fileLocation = "sounds/wipala.mp3";
        this.loop = true;
    }
    
    //on-click sound
    public static void clickSound() {
        try {
        	AudioInputStream click = AudioSystem.getAudioInputStream(new File("sounds/blop.wav"));
        	Clip clip = AudioSystem.getClip();
        	clip.open(click);
        	clip.start();
        } catch(Exception ex) {
            System.out.println("Error with playing sound.");
            ex.printStackTrace();
        }
    }
    
    public void run() {
    	// Play in an infinite loop;
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

    // Close the player;
    public void close(){
        loop = false;
        player.close();
        this.interrupt();
    }
}
