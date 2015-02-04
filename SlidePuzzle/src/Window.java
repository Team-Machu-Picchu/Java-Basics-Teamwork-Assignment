import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.SplashScreen;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.AbstractButton;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.SwingUtilities;

// Generates our main game window and can take some player input.
public class Window extends JFrame implements ActionListener, ItemListener, FocusListener {

	int rows = 5, cols = 5, scramble = 25;
	static Window window;
	static Board board;
	private Sound music;
	private JPanel content;
	private Randomizer random;

	public Window() {
		// Sets the title
		this.setTitle("Machu Picchu Puzzle Game");	
		// Frame cannot be resized by the user.
		this.setResizable(false);
		// Exit the application when user close frame.
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		createMenus();

		// Create a two-part panel to hold the control elements.
		JPanel selectorPanel = new JPanel();
		selectorPanel.setLayout(new GridLayout(2, 0));
		selectorPanel.setPreferredSize(new Dimension(500, 50));
		
		// Create the top part that replicates
		// the menu functions with buttons.
		JPanel upperPanel = createControlButtons();
		
		// Create the bottom part that adds drop-down
		// selectors for the rows, columns and randomizer.
		JPanel lowerPanel = createControlBoxes();	

		selectorPanel.add(upperPanel);
		selectorPanel.add(lowerPanel);

		content = new JPanel(new BorderLayout());		
		setupBoard();

		// Put the control panel over the game board.
		content.add(selectorPanel, BorderLayout.NORTH);

		// Put the content pane in the window.
		this.setContentPane(content);		

	}

	private void createMenus() {
		// Create a menubar.
		JMenuBar menubar = new JMenuBar();	

		// Set up the menus.
		JMenu file = new JMenu("Menu");
		JMenu info = new JMenu("Help");

		// Create icons for the menu.
		ImageIcon iconNew = new ImageIcon("Images/puzzle.jpg");
		ImageIcon iconExit = new ImageIcon("Images/exit.png");
		ImageIcon iconSoundOn = new ImageIcon("Images/soundOn.jpg");
		ImageIcon iconSoundOff = new ImageIcon("Images/soundOff.jpg");

		// Help menus items.
		JMenuItem aboutMenu = new JMenuItem("About");		
		aboutMenu.setActionCommand("info");
		aboutMenu.addActionListener(this);

		info.add(aboutMenu);

		// File menu items.
		JMenuItem fileNewMenu = new JMenuItem("New Game", iconNew);
		fileNewMenu.setActionCommand("reset");
		fileNewMenu.addActionListener(this);

		JMenuItem soundOnMenu = new JMenuItem("Sound On", iconSoundOn);
		JMenuItem soundOffMenu = new JMenuItem("Sound Off", iconSoundOff);
		soundOnMenu.setActionCommand("sound_on");
		soundOffMenu.setActionCommand("sound_off");
		soundOnMenu.addActionListener(this);
		soundOffMenu.addActionListener(this);

		JMenuItem fileExitMenu = new JMenuItem("Exit", iconExit);				
		fileExitMenu.setActionCommand("exit");
		fileExitMenu.addActionListener(this);           

		file.add(fileNewMenu);
		file.addSeparator();
		file.add(soundOnMenu);
		file.add(soundOffMenu);
		file.addSeparator();
		file.add(fileExitMenu);

		menubar.add(file);
		menubar.add(info);

		setJMenuBar(menubar);
	}

	private JPanel createControlButtons() {
		JPanel upperPanel = new JPanel(new GridLayout(1, 0));	

		JButton reset = new JButton("Reset Game");
		reset.setActionCommand("reset");
		reset.addActionListener(this);

		JToggleButton musicSwitch = new JToggleButton("Music On/Off", true);
		musicSwitch.addItemListener(this);

		JButton about = new JButton("About");
		about.setActionCommand("info");
		about.addActionListener(this);

		JButton exit = new JButton("Exit");
		exit.setActionCommand("exit");
		exit.addActionListener(this);

		upperPanel.add(reset);
		upperPanel.add(musicSwitch);
		upperPanel.add(about);
		upperPanel.add(exit);

		return upperPanel;
	}
	
	private JPanel createControlBoxes() {
		JPanel lowerPanel = new JPanel();
		lowerPanel.setLayout(new BoxLayout(lowerPanel, BoxLayout.X_AXIS));
		
		// Setup the dropdown selections.
		Integer[] dimention = { 3, 4, 5, 6, 7, 8, 9, 10 };
		
		// Create the selection boxes with the default selections.
		JComboBox<Integer> rowList = new JComboBox<>(dimention);
		JComboBox<Integer> colList = new JComboBox<>(dimention);		
		rowList.setName("rows");
		colList.setName("cols");		
		rowList.addActionListener(this);
		colList.addActionListener(this);
		rowList.setSelectedIndex(2);
		colList.setSelectedIndex(2);
		
		// Setup the text field to enter the scramble factor.
		JTextField scrambleFactor = new JTextField("25 (default)");
		scrambleFactor.setHorizontalAlignment(JTextField.CENTER);
		scrambleFactor.setFont(new Font("", Font.BOLD, 12));
		scrambleFactor.addFocusListener(this);

		// Make labels for the boxes.
		JLabel rowLabel = new JLabel("Rows");
		JLabel colLabel = new JLabel("Columns");
		JLabel scrambleLabel = new JLabel("Scramble factor");

		// Arrange everything with spacers.
		lowerPanel.add(Box.createRigidArea(new Dimension(10, 0)));
		lowerPanel.add(rowLabel);
		lowerPanel.add(Box.createRigidArea(new Dimension(10, 0)));
		lowerPanel.add(rowList);
		lowerPanel.add(Box.createRigidArea(new Dimension(10, 0)));
		lowerPanel.add(colLabel);
		lowerPanel.add(Box.createRigidArea(new Dimension(10, 0)));
		lowerPanel.add(colList);
		lowerPanel.add(Box.createRigidArea(new Dimension(10, 0)));
		lowerPanel.add(scrambleLabel);
		lowerPanel.add(Box.createRigidArea(new Dimension(10, 0)));
		lowerPanel.add(scrambleFactor);
		lowerPanel.add(Box.createRigidArea(new Dimension(10, 0)));
		
		return lowerPanel;
	}

	private void setupBoard() {
		// if we already have a board, discard it.
		if (content.getComponents().length != 0) {
			content.remove(board);
		}
		// Create a new game board.
		board = new Board(rows, cols);
		// Set the size of the board.
		board.setPreferredSize(new Dimension(500, 500));
		
		// Scramble the board with the given number of moves.
		random = new Randomizer();
		random.Shuffle(scramble);
		
		// Add the new board to the center
		// of the content pane and refresh it.
		content.add(board, BorderLayout.CENTER);
		board.revalidate();
	}

	// Set up the menu, button and selection box behavior.
	@Override
	public void actionPerformed(ActionEvent event) {
		// Get the raw object that was the source of the click signal.
		Object target = event.getSource();
		// If it's a ComboBox, get the name
		// and store the value in the proper field.
		if (target instanceof JComboBox<?>) {
			JComboBox jcb = (JComboBox) target;
			String jcbName = jcb.getName();
			if (jcbName.equals("rows")) {
				rows = ((Integer)(jcb.getSelectedItem()));
			}
			else if (jcbName.equals("cols")) {
				cols = ((Integer)(jcb.getSelectedItem()));
			}
		}
		else {
			// If not it was a JButton or a menus,
			// so cast it to an AbstractButton.
			AbstractButton ab = (AbstractButton)target;
			// Get the ActionCommand to decide what to do.
			switch (ab.getActionCommand()) {
			// Exit the application.
			case "exit":
				System.exit(0); break;
				// Settings sounds On.
			case "reset":
				setupBoard(); break;
			case "sound_on":			
				this.playMusic(); break;
				// Settings sounds Off.
			case "sound_off":			
				music.close(); break;
				// Show the credits.			
			case "info":
				JOptionPane.showMessageDialog(new JPanel(),
						"Game Developers:\n\n"
								+ "Filip Filipov - Sitting Bit\n"
								+ "Nikola Hristov - Crazy code\n"
								+ "Velislav Nikiforov - .Net Hawk\n"
								+ "Mariya Hadzhipetrova - Inka Loop\n"
								+ "Daniela Petrova - Red Bug",
								"Developers info", JOptionPane.INFORMATION_MESSAGE); break;
			}
		}
	}

	// Set up the two-state music button behavior.
	@Override
	public void itemStateChanged(ItemEvent event) {
		if(event.getStateChange() == ItemEvent.SELECTED){
			playMusic();
		}
		else {
			music.close();
		}
	}
	
	// Set up the editable text field behavior.
	@Override
	public void focusGained(FocusEvent e) {
		// Do nothing.	
	}
	
	// Set the scramble factor when the field
	// loses focus (the reset button is clicked).
	@Override
	public void focusLost(FocusEvent e) {
		JTextField factor = (JTextField)e.getSource();
		scramble = Integer.parseInt(factor.getText());		
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
				// Start the music.
				window.playMusic();
			}
		});
	}

	private void playMusic() {
		music = new Sound();
		music.start();
	}

	// Replace the board with the completed
	// puzzle image and redraw the window.
	public static void endGame() {
		FinalPane finished = new FinalPane();
		window.setContentPane(finished);
		window.revalidate();
	}

}