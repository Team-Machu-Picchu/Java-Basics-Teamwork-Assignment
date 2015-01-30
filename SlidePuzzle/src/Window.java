import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.SwingUtilities;



public class Window extends JFrame {
	
	private Sound music;
	
	public Window() {
		
		// Sets the title
		this.setTitle("Machu Picchu Puzzle Game");
		
		// Sets size of the frame.
		if(false) { // Full screen mode
			// Disables decorations for this frame.
		    this.setUndecorated(true);
		    // Puts the frame to full screen.
		    this.setExtendedState(this.MAXIMIZED_BOTH);
		}
		else { // Window mode
			// Size of the frame.
		    this.setSize(500, 500);
		    // Puts frame to center of the screen.
		    this.setLocationRelativeTo(null);
		    // So that frame cannot be resizable by the user.
		    this.setResizable(true);
		}
		        
		// Exit the application when user close frame.
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
        //Create a menubar	
        JMenuBar menubar = new JMenuBar();
        
        //Display icons in the menu
        ImageIcon iconNew = new ImageIcon("Images/puzzle.jpg");
        ImageIcon iconOpen = new ImageIcon("Images/open.png");
        ImageIcon iconSave = new ImageIcon("Images/save.png");
        ImageIcon iconExit = new ImageIcon("Images/exit.png");
        ImageIcon iconSettings = new ImageIcon("Images/settings.png");
        ImageIcon iconInd1 = new ImageIcon("Images/ind1.jpg");
        ImageIcon iconInd2 = new ImageIcon("Images/ind2.jpg");
        ImageIcon iconInd3 = new ImageIcon("Images/ind3.jpg");
        ImageIcon iconInd4 = new ImageIcon("Images/ind4.png");
        ImageIcon iconSoundOn = new ImageIcon("Images/soundOn.png");
        ImageIcon iconSoundOff = new ImageIcon("Images/soundOff.jpg");

        JMenu file = new JMenu("Menu");
        JMenu info = new JMenu("About");
       
        JMenu set = new JMenu("Settings");
        
        JMenu setGame = new JMenu("Set Game");
        JMenu setSounds = new JMenu("Set Sounds");
        
        set.add(setGame);
        set.add(setSounds);

        JMenuItem game1 = new JMenuItem("Puzzle 1", iconInd1);
        JMenuItem game2 = new JMenuItem("Puzzle 2", iconInd2);
        JMenuItem game3 = new JMenuItem("Puzzle 3", iconInd3);
        JMenuItem game4 = new JMenuItem("Puzzle 4", iconInd4);
            
        setGame.add(game1);
        setGame.add(game2);
        setGame.add(game3);
        setGame.add(game4);
        
        JMenuItem soundOn = new JMenuItem("Sound On", iconSoundOn);
        JMenuItem soundOff = new JMenuItem("Sound Off", iconSoundOff);
        
        setSounds.add(soundOn);
        setSounds.add(soundOff);

        JMenuItem fileNew = new JMenuItem("New Game", iconNew);
        JMenuItem fileOpen = new JMenuItem("Open", iconOpen);
        JMenuItem fileSave = new JMenuItem("Save", iconSave);
        JMenuItem fileExit = new JMenuItem("Exit", iconExit);
        JMenuItem fileSettings = new JMenuItem("Settings", iconSettings);
            
        //Exit the application
        fileExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
        		System.exit(0);
            }
        });
        
        //Settings sounds On
        soundOn.addActionListener(new ActionListener() {
         	@Override
         	public void actionPerformed(ActionEvent actionEvent) {
         		music = new Sound();
         		music.start();
           	}
         	
        });
        
         
        file.add(fileNew);
        file.add(fileOpen);
        file.add(fileSave);
        file.addSeparator();
        file.add(set);
        file.addSeparator();
        file.add(fileExit);

        menubar.add(file);
        menubar.add(info);

        setJMenuBar(menubar);
        
   
	}
	
	        
        public static void main(String[] args) {
            
        	SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                	//Create an instance of the Window and make it visible on the screen
                	Window window = new Window();
                	//Make it visible on the screen
                    window.setVisible(true);
                }
            });
        }
  
}