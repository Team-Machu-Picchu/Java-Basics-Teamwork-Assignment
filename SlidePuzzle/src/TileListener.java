import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// This class intercepts any mouse clicks on
// the game board and performs the moves when appropriate.
public class TileListener implements ActionListener{
	private Tile target;
	private Tile empty;

	@Override
	public void actionPerformed(ActionEvent event) {
		// Get the board
		Tile[][] buttons = Window.board.buttons;
		// Get the clicked tile and extract its position.
		target = (Tile)event.getSource();
		int targetRow = target.getRow();
		int targetCol = target.getCol();
		
		// If the clicked tile is next to the empty
		// tile slide it to the position.
		if (target.getIcon() != null) {
			if (findEmptyTile(buttons, targetRow, targetCol)) {
				makeMove(target, empty);
				
				// Make a noise redraw the tiles, reset the listener
				// and check if the game is over.
				target.repaint();
				empty.repaint();
				// On-click sound
				Sound.clickSound();
				empty = null;
				Window.board.checkIfComplete();
			}
		}	
	}
	
	

	boolean findEmptyTile(Tile[][] buttons, int targetRow, int targetCol) {
		// Make a placeholder for the empty tile.
		Tile testTile;
		
		// If were not on the top row look up.
		if (targetRow != 0) {
			testTile = buttons[targetRow - 1][targetCol];
			if (isEmpty(testTile)) {
				return true;
			}			
		}
		
		// If were not on the bottom row look down.
		if (targetRow != buttons.length - 1) {
			testTile = buttons[targetRow + 1][targetCol];
			if (isEmpty(testTile)) {
				return true;
			}
		}		
		
		// If were not on the leftmost column look left.
		if (targetCol != 0) {
			testTile = buttons[targetRow][targetCol - 1];
			if (isEmpty(testTile)) {
				return true;
			}
		}
		
		// If were not on the rightmost column look right.
		if (targetCol != buttons[targetCol].length - 1) {
			testTile = buttons[targetRow][targetCol + 1];
			if (isEmpty(testTile)) {
				return true;
			}
		}
		return false;
	}

	// If the tile in the placeholder is the
	// empty one put it in the proper field.
	boolean isEmpty(Tile testTile) {
		if (testTile.getIcon() == null) {
			empty = testTile;
			return true;
		}
		return false;
	}
	
	static void makeMove(Tile target, Tile empty) {
		// Get both tiles' icon indexes and swap them.
		int targetRow = target.getIconRowIndex();
		int targetCol = target.getIconColIndex();
		int emptyRow = empty.getIconRowIndex();
		int emptyCol = empty.getIconColIndex();
		
		target.setIconRowIndex(emptyRow);
		target.setIconColIndex(emptyCol);
		empty.setIconRowIndex(targetRow);
		empty.setIconColIndex(targetCol);
		
		// Then swap their icons as well.
		empty.setIcon(target.getIcon());
		target.setIcon(null);
	}
	
}
