
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
 
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
 
public class Board extends JPanel  { 
	Tile[][] buttons;
 
	public Board(int rows, int cols) {
		super(new GridLayout(rows, cols));
		buttons = new Tile[rows][cols];
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
				Tile tile = new Tile(row, col);				
				buttons[row][col] = tile;
				if (!(row == 0 && col == 0)) {
					BufferedImage cropped = puzzle.getSubimage(
							col * width / cols, row * height / rows, width / cols, height / rows);
					tile.setIcon(new ImageIcon(cropped));
				}
				tile.setBackground(Color.GRAY);
				//tile.addActionListener(new TileListener());
				this.add(tile);
			}
		}      
	}
	
	public void checkIfComplete() {
		for (Tile[] tiles : buttons) {
			for (Tile tile : tiles) {
				if (!tile.hasRightImage()) {
					return;
				}
			}
		}
//		Window.endGame();
	}
 
}