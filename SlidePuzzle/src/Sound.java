import javazoom.jl.player.*;
import java.io.FileInputStream;

//added JLayer libary

public class Sound {
	
	public static void playBackgroundMusic(){

		while(true){
		
	    try{

	    FileInputStream fis = new FileInputStream("sounds/wipala.mp3");
	    Player playMP3 = new Player(fis);

	    playMP3.play();

	    }catch(Exception e){System.out.println(e);}
	    
		}
	}
}