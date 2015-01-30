import java.io.BufferedInputStream;
import java.io.FileInputStream;
import javazoom.jl.player.Player;

public class Sound {
	
  private String filename;
  private Player player;

  //MP3 constructor;
  
  public Sound(String filename) {
    this.filename = filename;
  }

  // The player;
  
  public void play() {
    try {
      FileInputStream fis = new FileInputStream(this.filename);
      BufferedInputStream bis = new BufferedInputStream(fis);

      this.player = new Player(bis);
    } catch (Exception e) {
        System.err.printf("%s\n", e.getMessage());
    }

    new Thread() {
      @Override
      public void run() {
        try {
          player.play();
        } catch (Exception e) {
            System.err.printf("%s\n", e.getMessage());
        }
      }
    }.start();
  }

  // Close the player;
  
  public void close() {
    if (this.player != null) {
      this.player.close();
    }
  }


// Play in an infinite loop;

  public static void playBackgroundMusic() {
    Sound mp3 = new Sound("sounds/wipala.mp3");

    mp3.play();

    while (true) {
      if (mp3.player.isComplete()) {
        mp3.close();
        mp3.play();
      }
    }
  }
}