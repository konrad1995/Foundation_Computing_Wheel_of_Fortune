import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import java.awt.event.ActionEvent;
import java.awt.Label;
import javax.swing.JLabel;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.JPanel;
import java.awt.Dimension;
import javax.imageio.ImageIO;
import javax.swing.JTextArea;
import java.awt.image.BufferedImage;
import java.awt.ScrollPane;
import java.awt.SystemColor;

public class JavaGameGUI {
	//Most of the JElements and variables are create here to make them possible to use them in different methods
	private JFrame firstFrame;
	
	final BufferedImage bi = ImageIO.read(new File("src/wheel.png"));
	final BufferedImage pi = ImageIO.read(new File("src/pointer.png"));
	 
	String playerPhraseGuess;
	String nl = "\n";
	String s = "";
	String ws = null;
	String userInput;
	String wheelResult;
	
	int[] score = {0,0,0};
	 
	char[] usedChars = {};
	
	char userGuess;
	
	int x = 0; //This variable store integer of random selected segment.
	int guessCount = 26; 
	int currentPlayer; 
	int sV = 0; //This variable store value of random selected segment
	int total;
	
	NumberPlayers NOP = new NumberPlayers(); 
	UserChoice UC = new UserChoice();
	
	Phrase ph = new Phrase();
	JTextArea console = new JTextArea();
	MessageConsole mc = new MessageConsole(console); //This line use MessageConsole class to redirect text from console to JTextField(console)
	
	
	JButton spinBtn = new JButton("Spin");
	JButton btnUserGuessPhrase = new JButton("Guess Phrase");
	JButton btnGuessLetter = new JButton("Guess Letter");
	
	JPanel gamePanel = new JPanel();
	JPanel wheelPnl = new JPanel();
	
	Label p1NameLbl = new Label();
	Label p2NameLbl = new Label();
	Label p3NameLbl = new Label();
	Label lblHP = new Label(Phrase.hiddenPhrase);
	Label gk = new Label();
	
	//Below JLabels are displaying players' scores
	
	JLabel player1score = new JLabel(String.valueOf(score[0]));
	JLabel player2score = new JLabel(String.valueOf(score[1]));
	JLabel player3score = new JLabel(String.valueOf(score[2]));

	/**
	 * Launch the application.
	 * @throws IOException 
	 */

	public static void main(String[] args) throws IOException {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JavaGameGUI window = new JavaGameGUI();
					window.firstFrame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
// wheelSegment method is responsible for holding information and giving back values and strings of all 24 segments
	private void wheelSegment() {

		if ((x == 1) || (x == 10) || (x == 13) || (x == 16)) {
			ws = "400";
			sV = 400;
			
		}
		else if (x == 2) {
			ws = "Lose a Turn";
			sV = 0;
			
		}
		else if ((x == 3) || (x == 21))  {
			ws = "200";
			sV = 200;
			
		}
		else if (x == 4) {
			ws = "300";
			sV = 300;
			
		}
		else if ((x == 5) || (x == 22)) {
			ws = "500";
			sV = 500;
			
		}
		else if (x == 6) {
			ws = "250";
			sV = 250;
			
		}
		else if (x == 7) {
			ws = "800";
			sV = 800;
			
		}
		else if ((x == 8) || (x == 17) || (x == 24) || (x == 0) ) {
			ws = "1000";
			sV = 1000;
			
		}
		else if ((x == 9) || (x == 20)) {
			ws = "Bankrupt";
			sV = 0;
			
		}
		else if (x == 11) {
			ws = "600";
			sV = 600;
			
		}
		else if (x == 12) {
			ws = "150";
			sV = 150;
			
		}
		else if (x == 14) {
			ws = "750";
			sV = 750;
			
		}
		else if (x == 15) {
			ws = "Free Spin";
			sV = 0;
			
		}
		else if (x == 18) {
			ws = "450";
			sV = 450;
			
		}
		else if (x == 19) {
			ws = "700";
			sV = 700;
			
		}
		else if (x == 23) {
			ws = "900";
			sV = 900;
			
		}
		else {
			wheelSegment();
		}
	}
	
	//ChooseLetter method is using action listener and action event to wait for button GuessLetter to be pressed. 
	
	private void chooseLetter() {
		btnGuessLetter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				UC.main();			//Then it call method from another class that display box with available letters.
				System.out.println("You choosen letter " + UserChoice.userGuess);
				
				ph.makeGuess(); //MakeGuess method is checking how many letters are in the phrase
			
				
				
				guessCount = guessCount - 1;
				
				gk.setText("Guess Count = " + guessCount);
				
					if (Phrase.lettersFound == 0) { //this if statement check did user found any letter
						if ((UserChoice.userGuess == 'a') 
								  || (UserChoice.userGuess == 'e') 
								  || (UserChoice.userGuess == 'i') 
								  || (UserChoice.userGuess == 'o')	
								  || (UserChoice.userGuess == 'u')){ // if user did not found any letter then it check is it was a vowel
							 score[currentPlayer] = score[currentPlayer] - 250; //if it was vowel user lose 250 points from his score
							   System.out.println("-250 docked from score for vowel use");
							   System.out.println("Sorry! You did not guess any letter");
							   NextPlayer();      
							   System.out.println("It is your turn " + NumberPlayers.playerName[currentPlayer]);
							   updateScore();
							   cannotSpin();
						}
						else { //If it is not a vowel then it just give turn to another player
							System.out.println("Sorry! You did not guess any letter");
							NextPlayer();
							System.out.println("It is your turn " + NumberPlayers.playerName[currentPlayer]);
							canSpin();
						}
					  
						
					}
					else { //if user found at least 1 letter then it goes to else statement and check did user use vovel
						 if ((UserChoice.userGuess == 'a') 
								  || (UserChoice.userGuess == 'e') 
								  || (UserChoice.userGuess == 'i') 
								  || (UserChoice.userGuess == 'o')	
								  || (UserChoice.userGuess == 'u')){
							 score[currentPlayer] = score[currentPlayer] - 250;
							   System.out.println("-250 docked from score for vowel use");
							   System.out.println("You found " + Phrase.lettersFound + " letters.");
							   updateScore();
							
							   updatePhrase();
							   canSpin();
						    }
						 else {
							 
						
						
						System.out.println("You found " + Phrase.lettersFound + " letters.");
						total = sV * Phrase.lettersFound; //here is calculate how many points user get for his guesses
						System.out.println("You won " + total + " points.");
						score[currentPlayer] = score[currentPlayer] + total; 
						updateScore(); 
						System.out.println(Phrase.hiddenPhrase);
						updatePhrase();
						canSpin();
						 }
					}
			
		}	
		});	
	}	
	
		private void guessPhrase(){ //guessPhrase method allows player make guess on the phrase 
			btnUserGuessPhrase.addActionListener(new ActionListener() { //it is use action listener as player can choose this method by clicking proper button
				public void actionPerformed(ActionEvent e) {
					ph.fullGuess(); // this method from Phrase class open window for player where he can provide his guess 
			
					if (Phrase.guessPhrase.equals(Phrase.gamePhrase)) { //this if statement check did player guessed
						Phrase.hiddenPhrase = Phrase.gamePhrase;
						System.out.println("Congratulations that is correct phrase!");
							if (guessCount > 0) {
								total = sV + (guessCount * 200);
								score[currentPlayer] = score[currentPlayer] + total;
								updateScore();
								guessCount = 26;
								gk.setText("Guess Count = " + guessCount);
							}
							else {
								score[currentPlayer] = score[currentPlayer] + sV;
								updateScore();
								guessCount = 26;
								gk.setText("Guess Count = " + guessCount);
							}
						canSpin();
						randomPhrase();
						UserChoice.resetButtons();
					}
					else {
						System.out.println("Wrong! You have lost your round.");
						guessCount = guessCount - 5;
						gk.setText("Guess Count = " + guessCount);
						canSpin();
						NextPlayer();
						System.out.println("It is your turn " + NumberPlayers.playerName[currentPlayer]);
						
					}
					
					
					
				}
			});
			
		
			
			
			
		}
	// canSpin() and cannotSpin() are most important elements of the game. Game itself is not based on do while loop. Instead it is based on three buttons which are enabled depends from different situation.
		// For example If player did not guess any letter then it is called method nextPlayer() and after that canSpin() so next player can spin wheel before guessing letter or phrase.
	
	private void canSpin() {
		spinBtn.setEnabled(true);
		btnGuessLetter.setEnabled(false);
		btnUserGuessPhrase.setEnabled(false);
	}
	private void cannotSpin() {
		spinBtn.setEnabled(false);
		btnGuessLetter.setEnabled(true);
		btnUserGuessPhrase.setEnabled(true);
	}
	private void updateScore() { //this method it call when players score need to be updated
		player1score.setText(String.valueOf(score[0]));
		player2score.setText(String.valueOf(score[1]));
		player3score.setText(String.valueOf(score[2]));
	}
private void spin() {  //this method simply allows player to spin the wheel
	spinBtn.setEnabled(true);
		
		spinBtn.addActionListener(new ActionListener() { 
			@SuppressWarnings("serial")
			public void actionPerformed(ActionEvent e) {
		
				Random rand = new Random();             //when button is pressed then random generator choose number from 1 to 24 as there is 24 segment on the wheel
				x = rand.nextInt((24 - 1) + 1) + 1;
				wheelPnl.add(new JPanel() {    
					 @Override
			            public Dimension getPreferredSize() {
			                return new Dimension(380, 380);
			            }
					  @Override
			            protected void paintComponent(Graphics g) {
      
						  
						  // 360 Degrees divided by 24 gives 15 degrees per segment
						  // code that rotate picture was based on radiant
						  // 15 degrees is pi/12 radiant
						  // x allows rotate picture by those radiant which is 15 degrees
						  // it allows to assign every number from 1 to 24 to concert segment 
						  // it gives player illusion that program can read from picture which segment was selected
						  // instead it rotate picture to segment that was assigned by number X
			                super.paintComponent(g);
			                Graphics2D g2 = (Graphics2D) g;
			                g2.rotate(x * (Math.PI / 12), 380 / 2, 380 / 2);
			                g2.drawImage(bi, 0, 0, null);      
			            }
				});
				spinBtn.setEnabled(false);
				wheelSegment();
				System.out.println(NumberPlayers.playerName[currentPlayer] + " pitted " + ws);
				if (ws == "Bankrupt") {
					
					score[currentPlayer] = 0;
					updateScore();
					NextPlayer();
					System.out.println("It is your turn " + NumberPlayers.playerName[currentPlayer]);
					canSpin();
				
				}
				else if (ws == "Free Spin") {
					canSpin();
			
				}
				else if (ws == "Lose a Turn") {
					NextPlayer();
					System.out.println("Now is your turn " + NumberPlayers.playerName[currentPlayer]);
					canSpin();
				
					
				}
				else {
					cannotSpin();
					System.out.println("Now Guess a Letter or guess phrase.");				
				}
				
				
				
				
			}
		});
				
		
	
	}
	
	
		
		
	
    private void updateNames() {
    	// It is simple method that update player names
    	p1NameLbl.setText(NumberPlayers.playerName[0]);
    	p2NameLbl.setText(NumberPlayers.playerName[1]);
    	p3NameLbl.setText(NumberPlayers.playerName[2]);
    	
    }
    private void howmanyp() {//this method call a method from another class that shows Option dialog with 2 buttons where user can choose how many players will be play (2 or 3)
    	NOP.howManyP();
    			for (int a = 0; a<1;) { //the option dialog shows 3 buttons (2 players, 3 players and exit) the last one did not exit game but instead continue game with variable that allow play only with one player.
    				//To avoid that loop checks did player press 2 or 3 players buttons if not, window will appear again forcing user to press one of those two buttons
    				if (NumberPlayers.n == 1) {
    					NOP.howManyP();
    				}
    				else if  (NumberPlayers.n == 2) {
    					a++;
    				}
    				else if  (NumberPlayers.n == 3) {
    					a++;
    				}
    			}
    		
    	
    	
    
    	
    }

private void updatePhrase() { //simple method that update phrase at the beginning of every round 
	
	lblHP.setText(Phrase.hiddenPhrase);
	
	
}
	private void randomPhrase() { //method select random phrase for the game
		ph.randomGP();
		updatePhrase();
		System.out.println("New phrase has been selected.");
	}

	/**
	 * Create the application.
	 */
	
	
	public JavaGameGUI() throws Exception {
		
		howmanyp();
		NOP.whatName();
		randomPhrase();
		initialize();
		updatePhrase();
		updateNames();
		chooseLetter();
		guessPhrase();
		spin();
		
		
		System.out.println("This is Wheel of Fortune!");
		System.out.println("There is " + NumberPlayers.n + " number of players!");
		System.out.println(NumberPlayers.playerName[currentPlayer] + " press Spin to start game.");
		

		
			}
	/**
	 * this function moves the game on so the next player can spin. 
	 * if it is at the last player then 0 is returned to start from the beginning
	 * 
	 * @param currentPlayer the player whose turn it currently is
	 * @param numberOfPlayers the total number of people playing the game
	 * @return 
	 * @return 0 or +1 depending on whose turn it is
	 */
	private void NextPlayer(){
		int cp = currentPlayer;
		int np = NumberPlayers.n;
		np = np -1;
		if (cp == np){
			currentPlayer = 0;
		}
		else {
			currentPlayer = (currentPlayer + 1);
		}
		
	}
	/*
	 * Initialize the contents of the frame.
	 */
	
	@SuppressWarnings("serial")
	private void initialize() throws Exception {
	
		firstFrame = new JFrame("Wheel of Fortune");
		firstFrame.setBounds(100, 100, 1030, 616);
		firstFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		firstFrame.setResizable(false);
				
		firstFrame.getContentPane().add(gamePanel);
		gamePanel.setLayout(null);

		player1score.setBounds(597, 455, 102, 22);
		player1score.setHorizontalAlignment(SwingConstants.CENTER);
		gamePanel.add(player1score);
		
		p1NameLbl.setBounds(411, 455, 180, 22);
		p1NameLbl.setAlignment(Label.CENTER);
		p1NameLbl.setBackground(Color.RED);
		gamePanel.add(p1NameLbl);
		
		p2NameLbl.setBackground(Color.GREEN);
		p2NameLbl.setAlignment(Label.CENTER);
		p2NameLbl.setBounds(411, 483, 180, 22);
		gamePanel.add(p2NameLbl);
		
		p3NameLbl.setBackground(Color.YELLOW);
		p3NameLbl.setAlignment(Label.CENTER);
		p3NameLbl.setBounds(411, 511, 180, 22);
		gamePanel.add(p3NameLbl);
	
		player2score.setBounds(597, 483, 102, 19);
		player2score.setHorizontalAlignment(SwingConstants.CENTER);
		gamePanel.add(player2score);

		ScrollPane sp = new ScrollPane();
		sp.setBounds(10, 46, 395, 487);
	
		gamePanel.add(sp);
	
		player3score.setHorizontalAlignment(SwingConstants.CENTER);
		player3score.setBounds(597, 511, 102, 22);
		gamePanel.add(player3score);
		
		mc.redirectOut(null, System.out);	mc.redirectOut();	mc.redirectErr(Color.RED, null);	mc.setMessageLines(100);
	
		wheelPnl.setBackground(SystemColor.menu);
		wheelPnl.setBounds(411, 26, 380, 380);
		gamePanel.add(wheelPnl);
		
		
		wheelPnl.add(new JPanel() {
			 @Override
	            public Dimension getPreferredSize() {
	                return new Dimension(380, 380);
	            }
			  @Override
	            protected void paintComponent(Graphics g) {
         super.paintComponent(g);
	                Graphics2D g2 = (Graphics2D) g;
	                g2.rotate(x * (Math.PI / 12), 380 / 2, 380 / 2);
	                g2.drawImage(bi, 0, 0, null);     
       
	            }

		});
		wheelPnl.getParent();
        wheelPnl.setVisible(true);
       
        spinBtn.setEnabled(true);
		spinBtn.setBounds(411, 416, 380, 28);
		gamePanel.add(spinBtn);
		
		JLabel info = new JLabel("Vovels cost 250 points.");
		info.setBackground(Color.GRAY);
		info.setBounds(801, 10, 290, 14);
		gamePanel.add(info);
		
	
		btnUserGuessPhrase.setBounds(411, 539, 380, 28);
		btnUserGuessPhrase.setEnabled(false);
		gamePanel.add(btnUserGuessPhrase);
		
		btnGuessLetter.setBounds(10, 539, 395, 28);
		btnGuessLetter.setEnabled(false);
		gamePanel.add(btnGuessLetter);
		
		sp.add(console);
		console.setTabSize(5);
		console.setBounds(10, 46, 391, 483);
		console.setCaretPosition(console.getDocument().getLength());
		
		lblHP.setBackground(Color.CYAN);
		lblHP.setAlignment(Label.CENTER);
		lblHP.setBounds(10, 10, 391, 30);
		
		gamePanel.add(lblHP);
		
		JPanel pointer = new JPanel();
		pointer.add(new JPanel() {
		 @Override
         public Dimension getPreferredSize() {
             return new Dimension(380, 380);
         }
		  @Override
         protected void paintComponent(Graphics g) {
			super.paintComponent(g);
             Graphics2D g2 = (Graphics2D) g;
             g2.drawImage(pi, 0, 0, null);     
		  }
		});
		pointer.setBounds(411, 10, 380, 14);
		gamePanel.add(pointer);
		
		
		gk.setText("Guess Count = " + guessCount);
		gk.setBackground(Color.LIGHT_GRAY);
		gk.setAlignment(Label.CENTER);
		gk.setBounds(797, 39, 167, 22);
		gamePanel.add(gk);
		
	}
}
