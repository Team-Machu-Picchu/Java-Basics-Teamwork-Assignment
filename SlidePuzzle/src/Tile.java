import javax.swing.ImageIcon;
import javax.swing.JButton;

// A tile build on top of a standard button.
// It knows its position on the board (which
// does not change) and where its current icon
// originated from.
public class Tile extends JButton{
	private int row, col, iconRowIndex, iconColIndex;
	private ImageIcon icon;
	
	public int getIconRowIndex() {
		return iconRowIndex;
	}
	public void setIconRowIndex(int iconRowIndex) {
		this.iconRowIndex = iconRowIndex;
	}
	public int getIconColIndex() {
		return iconColIndex;
	}
	public void setIconColIndex(int iconColIndex) {
		this.iconColIndex = iconColIndex;
	}
	public int getRow() {
		return row;
	}
	public int getCol() {
		return col;
	}
	@Override
	public ImageIcon getIcon() {
		return icon;
	}
	public void setIcon(ImageIcon icon) {
		this.icon = icon;
	}
	
//	The tile is always initialized with
//	the icon matching its physical position.
//	The actual image is assigned later.
	public Tile(int iconRowIndex, int iconColIndex) {
		super();
		this.iconRowIndex = iconRowIndex;
		this.iconColIndex = iconColIndex;
		this.row = iconRowIndex;
		this.col = iconColIndex;
		this.icon = null;
	}
	
//	Check if the current icon matches the starting
//  one by comparing the buttons two index pairs.
	public boolean hasRightImage() {
		return (row == iconRowIndex && col == iconColIndex) ? true : false;
	}
		
}
