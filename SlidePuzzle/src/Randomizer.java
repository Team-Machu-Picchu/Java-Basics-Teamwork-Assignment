import java.util.Random;
 
// Provide a random algorithm to scramble 
// the board in a way that won't leave
// the puzzle impossible to solve.
public class Randomizer {
	private Tile target;
	private Tile empty;
	boolean targetFound;
	private Tile[][] buttons;
	private Random rnd;
	private int lastDirection;

	// Get the boards tiles and start from
	// empty tile in the top left corner.
	public Randomizer() {
		this.buttons = Window.board.buttons;
		this.rnd = new Random();
		this.targetFound = false;
		this.empty = buttons[0][0];
	}
	
	// Perform the given number
	// of moves in random directions.
	public void Shuffle(int scramble) {		
		for (int i = 0; i < scramble; i++) {
			int emptyRow = empty.getRow();
	 		int emptyCol = empty.getCol();
			while (!targetFound) {
				if (pickTile(buttons, emptyRow, emptyCol)) {
					TileListener.makeMove(target, empty);
					empty = target;
					targetFound = false;
					break;
				}		
			}
		}		
	}
 
    // Map the random numbers to the four directions.
	// Never follow a with one in the opposite direction,
	// as those are wasted cycles.
	private boolean pickTile(Tile[][] buttons, int emptyRow, int emptyCol) {
		int direction = rnd.nextInt(4);
		
		switch (direction) {
		// If were not on the top row move up.
		case 0:			
			if (emptyRow != 0 && lastDirection != 1) {
				target = buttons[emptyRow - 1][emptyCol];
				targetFound = true;
				lastDirection = 0;
				return true;		
			}
			break;
		// If were not on the bottom row move down.
		case 1:
			if (emptyRow != buttons.length - 1 && lastDirection != 0) {
				target = buttons[emptyRow + 1][emptyCol];
				targetFound = true;
				lastDirection = 1;
				return true;		
			}
			break;
		// If were not on the leftmost column move left.
		case 2:
			if (emptyCol != 0 && lastDirection != 3) {
				target = buttons[emptyRow][emptyCol - 1];
				targetFound = true;
				lastDirection = 2;
				return true;		
			}
			break;
		// If were not on the rightmost column move right.
		case 3:
			if (emptyCol != buttons[emptyCol].length - 1 && lastDirection != 2) {
				target = buttons[emptyRow][emptyCol + 1];
				targetFound = true;
				lastDirection = 3;
				return true;		
			}
			break;
		}
		return false;
	}
}