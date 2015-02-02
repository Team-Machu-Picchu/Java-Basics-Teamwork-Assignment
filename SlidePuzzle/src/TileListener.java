import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.sun.java.swing.plaf.windows.resources.windows;

public class TileListener implements ActionListener{
	private Tile target;
	private Tile empty;

	@Override
	public void actionPerformed(ActionEvent event) {
		Tile[][] buttons = Window.board.buttons;
		target = (Tile)event.getSource();
		int targetRow = target.getRow();
		int targetCol = target.getCol();
		if (target.getIcon() != null) {
			if (findEmptyTile(buttons, targetRow, targetCol)) {
				makeMove(target, empty);
				target.repaint();
				empty.repaint();
//				Sound.makeSound();
				empty = null;
				Window.board.checkIfComplete();
			}
		}	
	}

	boolean findEmptyTile(Tile[][] buttons, int targetRow, int targetCol) {
		Tile testTile;
		if (targetRow != 0) {
			testTile = buttons[targetRow - 1][targetCol];
			if (isEmpty(testTile)) {
				return true;
			}			
		}
		if (targetRow != buttons.length - 1) {
			testTile = buttons[targetRow + 1][targetCol];
			if (isEmpty(testTile)) {
				return true;
			}
		}
		if (targetCol != 0) {
			testTile = buttons[targetRow][targetCol - 1];
			if (isEmpty(testTile)) {
				return true;
			}
		}
		if (targetCol != buttons[targetCol].length - 1) {
			testTile = buttons[targetRow][targetCol + 1];
			if (isEmpty(testTile)) {
				return true;
			}
		}
		return false;
	}

	boolean isEmpty(Tile testTile) {
		if (testTile.getIcon() == null) {
			empty = testTile;
			return true;
		}
		return false;
	}
	
	static void makeMove(Tile target, Tile empty) {
		int targetRow = target.getIconRowIndex();
		int targetCol = target.getIconColIndex();
		int emptyRow = empty.getIconRowIndex();
		int emptyCol = empty.getIconColIndex();
		target.setIconRowIndex(emptyRow);
		target.setIconColIndex(emptyCol);
		empty.setIconRowIndex(targetRow);
		empty.setIconColIndex(targetCol);
		empty.setIcon(target.getIcon());
		target.setIcon(null);
	}
	
}