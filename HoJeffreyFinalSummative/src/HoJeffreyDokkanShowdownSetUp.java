/**
 * @(#)HoJeffreyDokkanShowdownSetUp.java
 *
 *
 * @Jeffrey Ho
 * @version 3.00 2019/6/10
 *
 * This is the set up phase of the game, it allows the user to read the instructions, and pick the character they want to fight with (After clicking Play) or They can also choose to exit the game)
 */
//importing methods from these libraries
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import javax.sound.sampled.*;
import javax.swing.*;

public class HoJeffreyDokkanShowdownSetUp extends JPanel implements ActionListener{
	//declaring instance variables
	int z, y;
	static JFrame Selection;
	JFrame StartingMenu;
	JLabel DokkanShowdown, DokkanShowdownTitle, Player1CharacterTitle, Player2CharacterTitle;
	JButton[] MenuButtons,Characters, Characters2;
	HoJeffreyDokkanShowdown Character;
	public static int CharacterAttack, CharacterAttack2;
	private Clip audioClip, audioClip2;
	
	
    public HoJeffreyDokkanShowdownSetUp(){
    	//Sets up the starting menu
    	StartingMenu();
    	
    	//brings up the selection screen
    	Selection();
    	
    }
    //method that plays a song
    public void playIntro() throws UnsupportedAudioFileException,IOException, LineUnavailableException{
    //selects the file name
    	File audioFile = new File("IntroTheme.wav");
    	//assigns the file the name using pre made audio methods
    	AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
    	//gets the format of the audio for it to be played appropriately
    	AudioFormat format = audioStream.getFormat();
    	DataLine.Info info = new DataLine.Info(Clip.class, format);
    	
    	audioClip = (Clip) AudioSystem.getLine(info);
    	//opens the audio variable as a set up
    	audioClip.open(audioStream);
    	//makes the song play infinately until something tells it stop
    	audioClip.loop(Clip.LOOP_CONTINUOUSLY);;
    	//starts the song
    	audioClip.start();    	
    }
    
    
    
    
    //method for the second song to play when a character is selected
    public void playIntroFight() throws UnsupportedAudioFileException,IOException, LineUnavailableException{
	    //selects the file name
	    	File audioFile = new File("UltraInstinctTheme.wav");
	    	//assigns the file the name using pre made audio methods
	    	AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
	    	//gets the format of the audio for it to be played appropriately
	    	AudioFormat format = audioStream.getFormat();
	    	DataLine.Info info = new DataLine.Info(Clip.class, format);
	    	
	    	audioClip2 = (Clip) AudioSystem.getLine(info);
	    	//opens the audio variable as a set up
	    	audioClip2.open(audioStream);
	    	//makes the song play infinately until something tells it stop
	    	audioClip2.loop(Clip.LOOP_CONTINUOUSLY);;
	    	//starts the song
	    	audioClip2.start();
	    	
	    }
  //method that starts the song
    public void startSong() {
    	audioClip.start();
    }
    	//method that stops the song 
    public void pauseSong() {
    	audioClip.stop();
    }
    
    //method that changes the logo in the character selection menu when called
    public void P2Title() {
    	//sets properties of the Title
    	Player1CharacterTitle.setVisible(false);
    	Player2CharacterTitle = new JLabel();
    	Player2CharacterTitle.setBounds(250,-10,1000,100);
    	Player2CharacterTitle.setIcon(new ImageIcon("PlayerTwoCharacter.png"));
    	Player2CharacterTitle.setVisible(true);
    	Selection.add(Player2CharacterTitle);
    	
    }
    //method that creates the actual menu and plays a song
    
    public void StartingMenu(){
    	//plays the intro theme
    	
    	try {
			playIntro();
		} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	
    	//Creates gui and dimensions
    	StartingMenu = new JFrame("Dokkan Showdown");
    	StartingMenu.setLayout(null);
    	StartingMenu.setSize(1200,500);
    	StartingMenu.setResizable(false);
    	StartingMenu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	
    	//Creates a JLabel and adds it to the Starting Menu, it is the title of the game "Dokkan Showdown"
    	DokkanShowdownTitle = new JLabel();
    	DokkanShowdownTitle.setBounds(300,-10,700,100);
    	DokkanShowdownTitle.setIcon(new ImageIcon("DokkanShowdownTitle.png"));
    	DokkanShowdownTitle.setVisible(true);
    	StartingMenu.add(DokkanShowdownTitle);

    	//Makes an array capable of holding 3 JButtons
    	MenuButtons = new JButton[3]; 
    	//This loop fills the array with buttons and its properties and puts them onto the JFrame (The Starting Menu)
    	for (int a = 0; a < MenuButtons.length; a++){ 
    		MenuButtons[a] = new JButton();
    		MenuButtons[a].setSize(300, 100);
    		//Makes it so the buttons don't overlap one another
    		MenuButtons[a].setLocation(475 ,80 + (a*130)); 
    		MenuButtons[a].setVisible(true);
    		MenuButtons[a].addActionListener(this); 
    		//takes grey box away 
    		MenuButtons[a].setOpaque(false); 
    		MenuButtons[a].setContentAreaFilled(false);
    		MenuButtons[a].setBorderPainted(false);
    		StartingMenu.add(MenuButtons[a]);
    	
    	//adds a picture to each button on the intro screen
    		if (a == 0)
    			MenuButtons[0].setIcon(new ImageIcon("PlayButton.png")); //The first button will have "Play" on it
    		else if (a == 1)
    			MenuButtons[1].setIcon(new ImageIcon("InstructionsButton.png")); //The second will have "Instructions"
    		else if (a == 2)
    			MenuButtons[2].setIcon(new ImageIcon("QuitButton.png"));	//and the third has "Exit"		
    	}
    	//sets the background pictre of the starting gui
    	DokkanShowdown = new JLabel();
    	DokkanShowdown.setVisible(true);
    	DokkanShowdown.setLocation(0,-50);
    	DokkanShowdown.setSize(1200,700);
    	DokkanShowdown.setIcon(new ImageIcon("LoadingScreen.png"));
    	StartingMenu.add(DokkanShowdown);
    	
    	StartingMenu.setVisible(true);
    }
  	
    //method for the first player's character selection
    public void Selection(){	
    	//Creates an invisible JFrame with the title Choose Your Character and can't be resized (This JFrame is intended to be a Selection Screen)
    	Selection = new JFrame("Choose Your Character"); 
    	Selection.setLayout(null);
    	Selection.setSize(1200,500);
    	Selection.setResizable(false);
    	Selection.setVisible(false);
    	Selection.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	
    //label to tell the it's player one's turn
    	Player1CharacterTitle = new JLabel();
    	Player1CharacterTitle.setBounds(250,-10,1000,100);
    	Player1CharacterTitle.setIcon(new ImageIcon("PlayerOneCharacter.png"));
    	Player1CharacterTitle.setVisible(true);
    	Selection.add(Player1CharacterTitle);
    	
    	//Makes an array capable of holding 4 JButtons
    	Characters = new JButton[4]; 
    	//loop that sets properties
    	for (int i = 0; i < Characters.length; i++){
    		Characters[i] = new JButton();
    		Characters[i].setSize(200, 200);
    	//Makes it so that the buttons don't overlap one another
    		Characters[i].setLocation(150 + (i*225),150);
    		Characters[i].setVisible(true); 
    		Characters[i].addActionListener(this);
    		Selection.add(Characters[i]);
    	
    	//sets a picture for each button to show the player the characters available
    		if(i == 0)
    			Characters[i].setIcon(new ImageIcon("FriezaPic.png"));
    		else if(i == 1)
    			Characters[i].setIcon(new ImageIcon("GogetaPic.png")); 
    		else if(i == 2)
    			Characters[i].setIcon(new ImageIcon("MightyMaskPic.png")); 
    		else
    			Characters[i].setIcon(new ImageIcon("SSJ4VegetaPic.png")); 
    	}
    //creates an array of 4 buttons
    	Characters2= new JButton[4];
    	//loop to set properties
    	for (int i = 0; i < Characters2.length; i++){
    		Characters2[i] = new JButton();
    		Characters2[i].setSize(200, 200);
    		//spaces buttons apart so the don't overlap
    		Characters2[i].setLocation(150 + (i*225),150);
    		Characters2[i].setVisible(false);
    		Characters2[i].addActionListener(this);
    		Selection.add(Characters2[i]);
    	
    		//sets the picture of the buttons for player two to pic their character
    		if(i == 0)
    			Characters2[i].setIcon(new ImageIcon("FriezaPic.png")); 
    		else if(i == 1)
    			Characters2[i].setIcon(new ImageIcon("GogetaPic.png")); 
    		else if(i == 2)
    			Characters2[i].setIcon(new ImageIcon("MightyMaskPic.png")); 
    		else
    			Characters2[i].setIcon(new ImageIcon("SSJ4VegetaPic.png")); 
    	}
    	
    }
    
  //When a button is clicked, this method is called (Buttons have actionlisteners)
    public void actionPerformed(ActionEvent e){ 
    	//if the starting menu is visible then:
    	if (StartingMenu.isVisible() == true){ 
    	//if the user clicks the play button, it changes to another menu
    		if (e.getSource().equals(MenuButtons[0])){ //If the user selects the Play Button
    			StartingMenu.setVisible(false); 
    			Selection.setVisible(true); 
    		}
    	//if the user selects the second button, then a JOP will appear displaying the instructions on how to play the game
    		else if (e.getSource().equals(MenuButtons[1])) 
    			JOptionPane.showMessageDialog(null, " The Objective of the game is to push your opponent off the stage until they have no Lives left." +
    												"\n\n This is a 2 Player game so get a buddy to fight against you \n\n" + "Player One controls are:" +
    												"\n W, A, and D to move and S to fire a projectile \n\n" + "Player Two controls are:" +
    												"\n Up Arrow Key, Left Arrow Key, and Right Arrow Key to move, and Down Arrow Key to fire a projectile" + "\n\n Each" +
    												" Player has 5 Lives to begin with, shoot projectiles at your Opponent in attempt to knock them out of the stage." +
    												" If you succeed to do so they'll lose a life. \n\n If a player hits the opponent's platform, they'll lose a life. \n" + 
    												"\n Projectiles travel in a straight line and can't be shot again until it hits the other side." +
    												"\n Dodge them by jumping away (you can double jump).\n\n However, if you do get hit you can still grab onto the ledge" +
    												"\n and jump back into the fight." + "\n\n The game can be paused and unpaused at any time during the fight." +
    												"\n by clicking the space bar." + "\n\n Good Luck and Have Fun!");				        
    	//closes the gui if the user hits the third button which the "quit button"
    		else if (e.getSource().equals(MenuButtons[2])) 
    			System.exit(0); 
    	}
    	 //If the Starting menu is not visible, then the Selection Screen is visible which means the Characters can be selected

		//When Player One selects any of the four buttons or Character displayed, z is equal to a number respective to the character thats displayed
    	else{
    		if (e.getSource().equals(Characters[0])){	
    			z = 1;
    			P2Title();
    		}
   			else if (e.getSource().equals(Characters[1])){
   				z = 2;
   				P2Title();
   			}
   			else if (e.getSource().equals(Characters[2])){
   				z = 3;
   				P2Title();
   			}	
   			else if (e.getSource().equals(Characters[3])){
   				z = 4;
   				P2Title();
   			}
   			
    		//When Player One had Selected their Character, the Other Array of Buttons turn visible (It is now Player Two's turn)
   			for (int i = 0; i < Characters.length; i++){
   				Characters[i].setVisible(false);
   				Characters2[i].setVisible(true);
   			}
   			
   			//assigns the chracter selection index to y who will be used globally to refer to the character for player 2 
   			if (e.getSource().equals(Characters2[0])) {
   				y = 1;
   			}
   			else if (e.getSource().equals(Characters2[1])) {
   				y = 2;
   			}
   			if (e.getSource().equals(Characters2[2])) {
   				y = 3;
   			}
   			if (e.getSource().equals(Characters2[3])) {
   				y = 4;
   			}
   		
   		//If Player Two choses a character then
   			if (y != 0) 
   		//creates a HoJeffreyDokkanShowdown object for the actual gameplay
   				Character = new HoJeffreyDokkanShowdown(z,y); 
   		//If Player One's Character is visible and Player Two's Character is also visible then:
   			if (HoJeffreyDokkanShowdownCharacter.P1Character.isVisible() == true && HoJeffreyDokkanShowdownCharacter.P2Character.isVisible() == true){ 
   				//The selection screen turns invisible
   				Selection.setVisible(false); 
   		//stops the intro song from playing
   			pauseSong();
   			//turns the actual battlefield visible
   				HoJeffreyDokkanShowdown.BattleField.setVisible(true); 
   				try {
   					//calls on the method that plays the song for the fight
   					playIntroFight();
   					//prevents the program from crashing 
   				} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e1) {
   					// TODO Auto-generated catch block
   					e1.printStackTrace();
   				}
   			
   			}
  	  	}
	} 	
}