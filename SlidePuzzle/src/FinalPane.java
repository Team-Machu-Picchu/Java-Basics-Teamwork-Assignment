import java.awt.Graphics;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JComponent;

// A simple pane with the completed picture,
// which is meant to replace our board when
// the puzzle is completed.
public class FinalPane extends JComponent implements MouseListener {

	private static BufferedImage textImage;
	private static BufferedImage puzzleImage;

	public FinalPane() {
		addMouseListener(this);
		try {
			textImage = ImageIO.read(new File("Images/winner.png"));
			puzzleImage = ImageIO.read(new File("Images/pic1.jpg"));
		}
		catch (IOException error) {
			error.printStackTrace();
		}
	}
	
	protected void paintComponent(Graphics g) {
		g.drawImage(textImage, 0, 0, textImage.getWidth(), textImage.getHeight(), null);
		g.drawImage(puzzleImage, 0, 50, puzzleImage.getWidth(), puzzleImage.getHeight(), null);
	}

	// Close the game window when clicked.
	@Override
	public void mouseClicked(java.awt.event.MouseEvent e) {
		Window.window.dispose();
		System.exit(0);
	}

	@Override
	public void mousePressed(java.awt.event.MouseEvent e) {
		// Do nothing.
	}

	@Override
	public void mouseReleased(java.awt.event.MouseEvent e) {
		// Do nothing.
	}

	@Override
	public void mouseEntered(java.awt.event.MouseEvent e) {
		// Do nothing.
	}

	@Override
	public void mouseExited(java.awt.event.MouseEvent e) {
		// Do nothing.
	}

}