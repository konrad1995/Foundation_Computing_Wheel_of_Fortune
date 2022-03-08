

import javax.swing.JOptionPane;

public class NumberPlayers {
	

	static int n = 0;
	
	static int[] player = new int[n];
	static String[] playerName = {"Red", "Green", "Yellow"};
	
	static String[] JOPtext = {	"First player name.",
								"Second player name.",
								"Third player name."};

	public int howManyP() {//simple method that give user option to choose how many players will play
		Object[] NPO = {"2 Players",
						"3 Players"};

		// n = number of players
				n = JOptionPane.showOptionDialog(null,
				"Choose number of players!",
			    "How many players?", 
			    JOptionPane.DEFAULT_OPTION,
			    JOptionPane.QUESTION_MESSAGE,
			    null,//do not use a custom Icon
			    NPO,//the titles of buttons
			    NPO[1]);//default button title);
	
			
				return n = n + 2;
				
	}

	public void whatName() {//simple method that allows players type their names
		for (int x = 0; x<n; x++) {
			String playerInput = JOptionPane.showInputDialog(null,
															"Enter your name",
															JOPtext[x], JOptionPane.QUESTION_MESSAGE);
			if (playerInput != null) {
				if (playerInput.length() == 0) {
				}
				else {
					playerName[x] = playerInput;
					
			}
			
				
			}else {
			
			}
		}		
	}
}
