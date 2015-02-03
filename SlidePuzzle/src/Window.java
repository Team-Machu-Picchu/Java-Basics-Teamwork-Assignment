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

// Generate our main game window.
public class Window extends JFrame implements ActionListener {

	int rows = 5, cols = 5;
	static Window window;
	static Board board;
	private Sound music;	
	private JPanel panel;
 		
	public Window() {

		// Sets the title
		this.setTitle("Machu Picchu Puzzle Game");	
		// Frame cannot be resized by the user.
		this.setResizable(false);
		// Exit the application when user close frame.
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Create a menubar.
		JMenuBar menubar = new JMenuBar();

		// Display icons in the menu.
		ImageIcon iconNew = new ImageIcon("Images/puzzle.jpg");
		ImageIcon iconExit = new ImageIcon("Images/exit.png");
		ImageIcon iconSoundOn = new ImageIcon("Images/soundOn.png");
		ImageIcon iconSoundOff = new ImageIcon("Images/soundOff.jpg");

		// Set up the menus.
		JMenu file = new JMenu("Menu");
		JMenu info = new JMenu("Help");
		
		// Help menus items.
		JMenuItem about = new JMenuItem("About");		
		about.setActionCommand("info");
		about.addActionListener(this);
		
		info.add(about);
		
		// File menu items.
		JMenuItem fileNew = new JMenuItem("New Game", iconNew);
		JMenuItem soundOn = new JMenuItem("Sound On", iconSoundOn);
		JMenuItem soundOff = new JMenuItem("Sound Off", iconSoundOff);
		JMenuItem fileExit = new JMenuItem("Exit", iconExit);
		soundOn.setActionCommand("sound_on");
		soundOff.setActionCommand("sound_off");
		soundOn.addActionListener(this);
		soundOff.addActionListener(this);
		fileExit.setActionCommand("exit");  
		fileExit.addActionListener(this);                

		file.add(fileNew);
		file.addSeparator();
		file.add(soundOn);
		file.add(soundOff);
		file.addSeparator();
		file.add(fileExit);

		menubar.add(file);
		menubar.add(info);

		setJMenuBar(menubar);
		
		// Create a new game board.
		board = new Board(rows, cols);
		// Set the size of the board.
		board.setPreferredSize(new Dimension(500, 500));
		// Put it inside the content pane.
		JPanel content = board;
		// Put the content pane in the window.
		this.setContentPane(content);		

    }
		


		// Set up the menu listener behavior
		@Override
		public void actionPerformed(ActionEvent event) {
			// Get the source of the click signal. Returns a raw object so
			// cast as an AbstractButton - works with all buttons and menus.
			AbstractButton target = (AbstractButton)event.getSource();
			switch (target.getActionCommand()) {
			// Exit the application.
			case "exit":
				System.exit(0); break;
			// Settings sounds On.
			case "sound_on":			
				music = new Sound();
				music.start(); break;
			// Settings sounds Off.
			case "sound_off":			
				music.close(); break;
			// Show the credits.			
			case "info":			
				panel = new JPanel();
                JOptionPane.showMessageDialog(panel,
                		"Game Developers:\n\n"
                		+ "Filip Filipov - Sitting Bit\n"
                		+ "Nikola Hristov - Crazy code\n"
                		+ "Velislav Nikiforov - .Net Hawk\n"
                		+ "Patrik - Java Cloud\n"
                		+ "Mariya Hadzhipetrova - Inka Loop\n"
                		+ "Daniela Petrova - Red Bug",
		                        "Developers info", JOptionPane.INFORMATION_MESSAGE); break;
			default:
				break;
			}			
		}			

	public static void main(String[] args) {
		// Display the window after rendering it.
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
						// Hold the splash screen for 3 seconds.
						Thread.sleep(3000);
					}
					catch(InterruptedException e) {
						System.err.println("Thread was interrupted!");
					}
					// Close the splash screen.
					splash.close();
				}
				// Create an instance of the Window
				// and resize it to fit all the content.
				window = new Window();
				window.pack();
				// Puts frame to center of the screen.
				window.setLocationRelativeTo(null);
				//Make it visible on the screen
				window.setVisible(true);
				
			}
		});
	}
	
	// Replace the board with the completed
	// puzzle image and redraw the window.
	public static void endGame() {
		FinalPane finished = new FinalPane();
		window.setContentPane(finished);
		window.revalidate();
	}
	
}