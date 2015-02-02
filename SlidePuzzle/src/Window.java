import java.awt.Dimension;
import java.awt.SplashScreen;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractButton;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;



public class Window extends JFrame implements ActionListener {

	static Window window;
	static Board board;
	private Sound music;	
	private JPanel panel;
 
		
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
			// Frame cannot be resized by the user.
			this.setResizable(false);
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
		JMenu info = new JMenu("Help");
		
		JMenuItem about = new JMenuItem("About");
		info.add(about);
		about.setActionCommand("info");
		about.addActionListener(this);
		
     	JMenu set = new JMenu("Settings");
		set.setIcon(iconSettings);

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
		soundOn.setActionCommand("sound_on");
		soundOff.setActionCommand("sound_off");
		soundOn.addActionListener(this);
		soundOff.addActionListener(this);

		setSounds.add(soundOn);
		setSounds.add(soundOff);

		JMenuItem fileNew = new JMenuItem("New Game", iconNew);
		JMenuItem fileOpen = new JMenuItem("Open", iconOpen);
		JMenuItem fileSave = new JMenuItem("Save", iconSave);
		JMenuItem fileExit = new JMenuItem("Exit", iconExit);
		fileExit.setActionCommand("exit");  
		fileExit.addActionListener(this);                

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
		
		board = new Board(5, 5);
		JPanel content = board;
		// Size of the pane.
		content.setPreferredSize(new Dimension(500, 500));
		this.setContentPane(content);
		

    }
		


	//Set up the menu listener behavior
		@Override
		public void actionPerformed(ActionEvent event) {
			//Get the source of the click signal. Returns a raw object so
			//cast as an AbstractButton - works with all buttons and menus.
			AbstractButton target = (AbstractButton)event.getSource();
			switch (target.getActionCommand()) {
			//Exit the application
			case "exit":
				System.exit(0); break;
			//Settings sounds On
			case "sound_on":			
				music = new Sound();
				music.start(); break;
			//Settings sounds Off
			case "sound_off":			
				music.close(); break;
			case "info":			
				panel = new JPanel();
                JOptionPane.showMessageDialog(panel, "Game Developers:\n\nFilip Filipov - Sitting Bit\nNikola Hristov - Crazy code\nVelislav Nikiforov - .Net Hawk\nPatrik - Java Cloud\nMariya Hadzhipetrova - Inka Loop\nDaniela Petrova - Red Bug",
		                        "Developers info", JOptionPane.INFORMATION_MESSAGE);
			default:
				break;
			}			
		}
		
		
		

	public static void main(String[] args) {

		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				//Try to show the splash screen.
				final SplashScreen splash = SplashScreen.getSplashScreen();
				if (splash == null) {
					System.err.println("Your splash image is missing!");
				}
				else {
					try {
						//Hold the splash screen for 3 seconds.
						Thread.sleep(3000);
					}
					catch(InterruptedException e) {
						System.err.println("Thread was interrupted!");
					}
					splash.close();
				}
				//Create an instance of the Window and resize it to fit all the content.
				window = new Window();
				//window.add (new Board());
				window.pack();
				// Puts frame to center of the screen.
				window.setLocationRelativeTo(null);
				//Make it visible on the screen
				window.setVisible(true);
				
			}
		});
	}
	
	public static void endGame() {
		FinalPane finished = new FinalPane();
		window.setContentPane(finished);
		window.revalidate();
	}
	
}