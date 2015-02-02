import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
 
 
public class Board extends JPanel  { 
	 private JButton[][] buttons = new JButton[5][5];
 
  public Board() {
	  super(new GridLayout(5,5));     
        
       for(int row=0; row<buttons.length; row++){
           for(int col=0; col<buttons.length; col++){
           
             JButton b = new JButton ("("+row+","+col+")");      
             this.buttons[row][col] = b;
             this.add(b);
           
           }
       }      
     
   }
 
   public JButton getSpot(int x, int y) {
       return buttons[x][y];
   }
 
}
