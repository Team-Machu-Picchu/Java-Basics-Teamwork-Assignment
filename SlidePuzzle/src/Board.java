import java.awt.Color;
import java.awt.GridLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

// The main game board.
public class Board extends JPanel {
	// Set up a 2D array of all tiles.
	Tile[][] buttons;
 
	// Make the board with the assigned number of rows and columns.
	public Board(int rows, int cols) {
		// Make a grid and a placeholder tile.
		super(new GridLayout(rows, cols));
		buttons = new Tile[rows][cols];
		
		// Put our main image in a buffer and get its dimensions.
		BufferedImage puzzle = null;
		try {
			puzzle = ImageIO.read(new File("Images/pic1.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		int width = puzzle.getWidth();
		int height = puzzle.getHeight();
		
 		for(int row = 0; row < rows; row++){
			for(int col = 0; col < cols; col++){
				// Initialize the tile according to its position.
				Tile tile = new Tile(row, col);
				// For every tile except the first, crop the corresponding
				// part of the picture and assign it as the tile's icon.
				if (!(row == 0 && col == 0)) {
					BufferedImage cropped = puzzle.getSubimage(
							col * width / cols, row * height / rows, width / cols, height / rows);
					tile.setIcon(new ImageIcon(cropped));
				}
				
				// Make the tiles gray when they have no icon.
				tile.setBackground(Color.GRAY);
				// Add our own listener to the tile so it reacts to clicks.
				tile.addActionListener(new TileListener());
				buttons[row][col] = tile;
				this.add(tile);
			}
 		}
	}
	
	// Go through all the tiles and if they all
	// have their original icons end the game.
	public void checkIfComplete() {
		for (Tile[] tiles : buttons) {
			for (Tile tile : tiles) {
				if (!tile.hasRightImage()) {
					return;
				}
			}
		}
		Window.endGame();
	}
 
}