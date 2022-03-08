import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class UserChoice {
	
	

	public static char userGuess;
	 JPanel gamePanel;
	
	//Alphabet Buttons

	  static JButton[] arrABL = new JButton [26];
	  static char[] arrLetter = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'}; 
	  static String[] arrStrLetter = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"}; 
	  static boolean[] arrUsed = {false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false};
	  static int index;

	
	
		public void main(){
			
			
			 ActionListener listener = new ActionListener() {
		
			 @Override
		        public void actionPerformed(ActionEvent e) {
				 Object btnPressed = e.getSource();
		            if (btnPressed instanceof JButton) {
		            	 String text = ((JButton) e.getSource()).getText();
		            	 userGuess = text.charAt(0);
		            	 for (int i = 0; i < arrLetter.length; i++) {
		            		 if (userGuess == arrLetter[i]) {
		            			 arrUsed[i] = true;
		            		 }
		            		 else {
		            			 
		            		 }
		            	 }
		            	 
		            	 JOptionPane.getRootFrame().dispose(); 
		            	
		                
		            }
		        }
		    };
		   gamePanel = new JPanel(new GridLayout(6,6));
		    
		  
			
		    
			  for (int i = 0; i < arrABL.length; i++) { //this method create buttons with characters for players to choose
					arrABL[i] = new JButton(String.valueOf(arrLetter[i]));
			        arrABL[i].addActionListener(listener);
			        arrABL[i].setEnabled(true);
			        if (arrUsed[i] == true) { //this if method check did letter was previously chosen 
			        	arrABL[i].setEnabled(false); //if it was then player cannot guess the same letter again as the button will not be enabled
			        	
			        }
			    	gamePanel.add(arrABL[i]);
			    
			  }
			     
			  JOptionPane.showOptionDialog(null, gamePanel, "Choose a letter.", JOptionPane.DEFAULT_OPTION,JOptionPane.INFORMATION_MESSAGE, null, new Object[]{}, null);	
				
			       
		}	
	  	public static void resetButtons() {
	  		for (int i = 0; i < arrUsed.length; i++){
	  			arrUsed[i] = false;
	  		}
	  	}
}
