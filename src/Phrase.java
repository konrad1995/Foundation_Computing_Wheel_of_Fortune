
import javax.swing.JOptionPane;

public class Phrase {
	
	static String gamePhrase;
	static String hiddenPhrase;
	static int lettersFound;
	static String guessPhrase;
	

	public int guessCount = 26; //guess count starts at 26 and is lowered 1 for a letter guess and 5 for an incorrect full guess
	
	/**
	 * prints the game board out to the console
	 * @return 
	 */
	public void randomGP(){ //this method select random phrase for the game
        int r = (int) (Math.random()*6);
        gamePhrase = new String[] {"a bad scene", 
				"a bargain at half the price", 
				"a bargain hunters dream", 
				"back in a flash", 
				"city that never sleeps",
				"dog and pony show"}[r];
        
        
        char [] ch = gamePhrase.toCharArray();

        for(int i=0; i<gamePhrase.length(); i++){ //this loop create hidden phrase for players
            if(' '==gamePhrase.charAt(i)){
                ch[i] = '_'; //when hidden phrase where updated with some letters it was hard to see spaces between the words so the spaces where changed with this symbol instead
            }
      
            else {
                ch[i] = '*';
            }
        }
        hiddenPhrase = String.valueOf(ch);
    }
	
	

		/**
		 * takes the letter guessed from the game class and checks it against the
		 * game phrase
		 * 
		 * @param letter the letter guessed by the player
		 * @return 
		 * @return whether that letter appears in the phrase and how many times
		 */
		
	
	public int makeGuess(){
		
		String guesses = "";
		lettersFound = 0;
		
		for (int x = 0; x<gamePhrase.length(); x++){
			if (UserChoice.userGuess == gamePhrase.charAt(x)){
				
				guesses += UserChoice.userGuess;
				lettersFound++;
							}
			else {
				guesses += hiddenPhrase.charAt(x);
			}
		}
		
		hiddenPhrase = guesses;
		return lettersFound;
		
	}
	
	/**
	 * takes the guess of the full phrase from the player and checks it
	 * against the game phrase 
	 * 
	 * @param guess phrase guess input from player
	 * @return 
	 * @return the guessCount (lowered with each guess) times 200, providing the bonus points for a correct answer
	 * or the feedback that the answer was incorrect and 5 points docked from the bonus
	 */
	public void fullGuess(){ //this method gives input dialog for the user when he can place his guess of full phrase
			String playerInput = JOptionPane.showInputDialog(null,
															"Enter your phrase",
															"Guess Phrase", JOptionPane.QUESTION_MESSAGE);
			if (playerInput != null) {
				if (playerInput.length() == 0) {
					fullGuess();
				}
				else {
					guessPhrase = playerInput;
					
			}
			
				
			}else {
			
			}
				
	}
	
	/**
	 * checks the two phrases against each other and returns a true or false
	 * 
	 * @return if the whole phrase has been guessed
	 */

	
}
