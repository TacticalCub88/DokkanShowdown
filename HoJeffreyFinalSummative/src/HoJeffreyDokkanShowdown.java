/**
 * @(#)HoJeffreyDokkanShowdown.java
 *
 *
 * @Jeffrey Ho
 * @version 1.00 2019/6/11
 *
 * This is the actual gameplay of the program. It consists of the Battlefield (Background, Platforms etc), the life system, the win condition, and the character's functionality.
 */
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Dimension;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.sound.sampled.*;
import javax.swing.*;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class HoJeffreyDokkanShowdown extends JPanel implements KeyListener, ActionListener{ 
	
	Timer t = new Timer(15,this); //Timer uses the ActionListener implemented in this class
	//Side Note: If the game is too fast paced, you can adjust the number in the brackets. (This modifies how fast the timer will tick. (every x millisecond)
	
	int intMoveP1X, intMoveP1Y, intMoveP2X, intMoveP2Y, intP1Jump, intP1JumpCounter, intP2Jump, intP2JumpCounter;
	
	final byte GRAVITY = 13;
	final byte LEDGEFATIGUE = 1;
	final byte KNOCKBACK = 35;
	final byte PROJECTILESPEED = 30;
	final byte JUMPCOUNTER = 2;
	final int JUMPDURATION = 300;
	static JFrame BattleField;
	static JLabel Background;
	private Clip audioClip; 
	JLabel Platform, Platform2, Border, Border2, Pause;
	JLabel[] P1Hearts, P2Hearts, Win;
	HoJeffreyDokkanShowdownCharacter Character;
	
    public HoJeffreyDokkanShowdown(){
    	
    	Background();
    	//Creates the Background of the Game
    	
    	Platforms();
    	//Creates the Platforms
    	
    	Hearts();
    	//Creates the Lives of the Pokemon
    	
    	Wins();
    	//Creates the labels for whoever wins (will be displayed when game is over)
	}
	
	public HoJeffreyDokkanShowdown(int z, int y){
			
		Character = new HoJeffreyDokkanShowdownCharacter(z,y);
		//Creates a new HoJeffreyDokkanShowdownCharacter Object (The parameters are given from the HoJeffreyDokkanSetUp class) Allows the characters made in that class to be accessed in this class
	}

	
	//method that plays a song
    public void playAttack() throws UnsupportedAudioFileException,IOException, LineUnavailableException{
    //selects the file name
    	File audioFile = new File("DeathBeam.wav");
    	//assigns the file the name using pre made audio methods
    	AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
    	//gets the format of the audio for it to be played appropriately
    	AudioFormat format = audioStream.getFormat();
    	DataLine.Info info = new DataLine.Info(Clip.class, format);
    	
    	audioClip = (Clip) AudioSystem.getLine(info);
    	//opens the audio variable as a set up
    	audioClip.open(audioStream);
    	//starts the song
    	audioClip.start();
    }
	
	public void Background(){
		addKeyListener(this);
    	setFocusable (true);
    	setFocusTraversalKeysEnabled(false);
    	//Listens for keys inputted from the user and allows it to perform an action
    	
    	BattleField = new JFrame("Dokkan Showdown");
    	BattleField.setLayout(null);
    	BattleField.setSize(1200,500);
    	BattleField.setResizable(false);
    	BattleField.addKeyListener(this);
    	BattleField.setVisible(false);
    	BattleField.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	//Creates a Jframe with the title Dokkan Showdown and can't be resized (This JFrame is intended to be where the fight happens)
    	
    	Background = new JLabel();
		Background.setIcon(new ImageIcon("Tournament_of_Power_Arena.png"));
		Background.setVisible(true);
    	Background.setSize(1200,500);
    	Background.setLocation(0,0);
    	BattleField.add(Background);
    	//Adds a Jlabel on top of the Jframe (BattleField) to create a background
    	
    	Pause = new JLabel();
    	Pause.setIcon(new ImageIcon("Pause.png"));
    	Pause.setVisible(true);
    	Pause.setSize(900, 180);
    	Pause.setLocation(150, 70);
    	Background.add(Pause);
    	//Adds a Jlabel on top of the Background to indicate that the game is paused
	}
	
	
	
	public void Platforms(){
		
		Platform = new JLabel();
    	Platform.setIcon(new ImageIcon("PokemonStage.png"));
    	Platform.setVisible(true);
    	Platform.setSize(400, 25);
    	Platform.setLocation(150, 375);
    	Background.add(Platform);
    	//Creates a Jlabel used as a platform (Intended for Player One) and adds it on top of the Background
    	
    	Border = new JLabel();
    	Border.setIcon(new ImageIcon("Border.png"));
    	Border.setVisible(true);
    	Border.setSize(350, 100);
    	Border.setLocation(200, 400);
    	Background.add(Border);
    	//Creates a Jlabel used as a border under the platform (so that the player can't "be in the platform") and adds it on top of the Background
    	
    	Platform2 = new JLabel();
    	Platform2.setIcon(new ImageIcon("PokemonStage2.png"));
    	Platform2.setVisible(true);
    	Platform2.setSize(400, 25);
    	Platform2.setLocation(600, 375);
    	Background.add(Platform2);
    	//Creates a Jlabel used as a platform (Intended for Player Two) and adds it on top of the Background
    	
    	Border2 = new JLabel();
    	Border2.setIcon(new ImageIcon("Border.png"));
    	Border2.setVisible(true);
    	Border2.setSize(350, 100);
    	Border2.setLocation(600, 400);
    	Background.add(Border2);
    	//Creates a Jlabel(Black Rectangle) used as a border under the platform (so that the player can't "be in the platform") and adds it on top of the Background
	}
	
	public void Hearts(){
		
		P1Hearts = new JLabel[5]; //Creates an array capable of holding 3 Jlabels
    	for (int i = 0; i < P1Hearts.length;i++){
    		P1Hearts[i] = new JLabel();
    		P1Hearts[i].setIcon(new ImageIcon("Lives.png"));
    		P1Hearts[i].setSize(80, 80);
    		P1Hearts[i].setLocation(25 + (i*30),25); //Makes it so that they dont Overlap one another
    		P1Hearts[i].setVisible(true);
    		Background.add(P1Hearts[i]);
    	}
    	//This loop fills the Array with the Jlabels(Hearts) and its properties and adds them on top of the Background
    	
    	P2Hearts = new JLabel[5];
    	for (int a = 0; a < P2Hearts.length;a++){
    		P2Hearts[a] = new JLabel();
    		P2Hearts[a].setIcon(new ImageIcon("Lives.png"));
    		P2Hearts[a].setSize(80, 80);
    		P2Hearts[a].setLocation(1080 - (a*30),25);
    		P2Hearts[a].setVisible(true);
    		Background.add(P2Hearts[a]);
    	}
    	//Creates another set of Hearts, this time for Player 2
	}
	
	public void Wins(){
		
		Win = new JLabel[2]; //Creates an array capable of holding 2 JLabels
		for (int i = 0; i < Win.length; i++){
			Win[i] = new JLabel();
			Win[i].setSize(900, 180);
			Win[i].setLocation(150, 70);
			Win[i].setVisible(false);
			Background.add(Win[i]);
			//This loop will fill the array with two JLabels and its properties. Also adds them on top of the Background (They're invisible)
			
			if (i == 0)
				Win[i].setIcon(new ImageIcon("P1WIN.png")); //The first Jlabel is an image that indicates Player One has won
			else if (i == 1)
				Win[i].setIcon(new ImageIcon("P2WIN.png")); //The second Jlabel is an image that indicates Player Two has won
		}
	}
	
    public void keyPressed(KeyEvent e){ //When a user clicks a key on the keyboard this method is called
    
    	int code = e.getKeyCode(); //What the user clicked on is saved as "code"
       
    	if(code == KeyEvent.VK_UP && intP2JumpCounter > 0) //If the user clicked the up arrow key and they have more than 0 Jumps available then
        	intMoveP2Y = -30; //Move Player Two's Character Up
        
       	if(code == KeyEvent.VK_LEFT) //If the user clicked on the left arrow key then
        	intMoveP2X = -20; //Move Player Two's Character Left
       	
        if(code == KeyEvent.VK_RIGHT) //If the user clicked on the right arrow key then
            intMoveP2X = 20; //Move Player Two's Character Right 
       
        if(code == KeyEvent.VK_DOWN) //If the user clicked on the down arrow key then
        
       		if (HoJeffreyDokkanShowdownCharacter.P2Projectile.isVisible() == false){ //if the projectile is invisible then
       			HoJeffreyDokkanShowdownCharacter.P2Projectile.setVisible(true); //Make the projectile visible
       		  try {
         			playAttack();
         		} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e2) {
         			// TODO Auto-generated catch block
         			e2.printStackTrace();
         		}
       			HoJeffreyDokkanShowdownCharacter.P2Projectile.setLocation(HoJeffreyDokkanShowdownCharacter.P2Character.getX() - 25, HoJeffreyDokkanShowdownCharacter.P2Character.getY() + 25); //and Have it so that it's location is set to where Player Two's Pokemon is
       		}
       	   	else //If the projectile is visible
       	   	HoJeffreyDokkanShowdownCharacter.P2Projectile.setLocation(HoJeffreyDokkanShowdownCharacter.P2Projectile.getX(), HoJeffreyDokkanShowdownCharacter.P2Projectile.getY()); //Then have it set to the location it's already at
       	   	//This prevents spamming (The down arrow key can't be clicked again until the Projectile goes invisible)
       	   		
        if (code == KeyEvent.VK_SPACE){ //If the user clicks space then
        
       		if (t.isRunning() == true){ //If the time is running
       			t.stop(); //The game is paused
       			Pause.setVisible(true); //The players will be indicated that it is paused (Pause image turns visible)
       		}
       		else if (Win[0].isVisible() == true || Win[1].isVisible() == true) //If the game is won(timer stops and either player has no hearts left) then
       			System.exit(0); //Stop and exit out of the program
       		else{ //If the time is paused then
       			Pause.setVisible(false); //Get rid of the pause image and 
       			t.start(); //Resume the game by starting the timer again
       			//playSoundGameplay();
       			
       
       			
       			
       		}
       }     
   }

   public void keyTyped(KeyEvent e) { 
    
   	   char code = e.getKeyChar();
   	   
   	   if(code == 'w' && intP1JumpCounter > 0)
           intMoveP1Y = -30;
       
       if(code == 'a')
           intMoveP1X = -20;

       if(code == 'd')
           intMoveP1X = 20;
       
       if(code == 's'){
    	 //shoots projectile if one isn't there. This makes it so abilities can't be spammed
           if (HoJeffreyDokkanShowdownCharacter.P1Projectile.isVisible() == false){
        	   HoJeffreyDokkanShowdownCharacter.P1Projectile.setVisible(true);
        	  //audio placed here so sound effect will only play when the ability is there
        	   try {
          			playAttack();
          		} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e2) {
          			// TODO Auto-generated catch block
          			e2.printStackTrace();
          		}
        	   HoJeffreyDokkanShowdownCharacter.P1Projectile.setLocation(HoJeffreyDokkanShowdownCharacter.P1Character.getX() + 75, HoJeffreyDokkanShowdownCharacter.P1Character.getY() + 25);
           }
       	   else
       		HoJeffreyDokkanShowdownCharacter.P1Projectile.setLocation(HoJeffreyDokkanShowdownCharacter.P1Projectile.getX(), HoJeffreyDokkanShowdownCharacter.P1Projectile.getY());
       }
   }
   //This is the same code as above, this time being intended for Player One's Character
   
   public void keyReleased(KeyEvent e){ //When a key is released
    
      int code = e.getKeyCode(); //It is saved as code

      if(code == KeyEvent.VK_UP){ //If the up key is released then
      	intMoveP2Y = 0; //Player Two stops moving up
      	intP2JumpCounter -= 1; //A Jump Counter is taken down by 1
      }
      
      else if (code == KeyEvent.VK_LEFT || code == KeyEvent.VK_RIGHT) //If the left or right key is released then
     	 intMoveP2X = 0; //Player Two stops moving right/left
     	 
      else if (code == KeyEvent.VK_W){
      	 intMoveP1Y = 0;
     	 intP1JumpCounter -= 1;
      }
    
      else if (code == KeyEvent.VK_A || code == KeyEvent.VK_D)
      	intMoveP1X = 0;
   }
   //Same thing applies here but for Player One
 
    public void actionPerformed(ActionEvent e){ //Every time the timer ticks, this method is called (and the methods within this)
   			
			P1Move();
			//Player One's Movement
			
			P2Move();
			//Player Two's Movement
			
			P1Lives();
			//Player One's Lives 
			 
			P2Lives();
			//Player Two's Lives

			P1Projectile();
			//Player One's Projectile
			
			P2Projectile();
			//Player Two's Projectile
	}
			
	public void P1Move(){
		 	
		 	if (HoJeffreyDokkanShowdownCharacter.P1Character.getBounds().intersects(Platform.getBounds()) && HoJeffreyDokkanShowdownCharacter.P1Character.getBounds().intersects(Border.getBounds()))
		 	//if Player One is in contact with the platform and the border then
		 		HoJeffreyDokkanShowdownCharacter.P1Character.setLocation(HoJeffreyDokkanShowdownCharacter.P1Character.getLocation().x, HoJeffreyDokkanShowdownCharacter.P1Character.getLocation().y += intMoveP1Y + LEDGEFATIGUE);
			//Player One can only Move up (and it's being slowly dragged down (LEDGEFATIGUE) to prevent players from staying there)
			//PlayerOne can move up by adding intMoveP1Y to it's current y location 
		 	else if (HoJeffreyDokkanShowdownCharacter.P1Character.getBounds().intersects(Platform.getBounds())){
		 	//if Player One is in contact with only the platform then
		 		HoJeffreyDokkanShowdownCharacter.P1Character.setLocation(HoJeffreyDokkanShowdownCharacter.P1Character.getLocation().x += intMoveP1X, HoJeffreyDokkanShowdownCharacter.P1Character.getLocation().y += intMoveP1Y);
			//It can move in all direction (except down) (intMoveP1X and intMoveP1Y is added to PlayerOne's location)
				intP1Jump = JUMPDURATION; 
				intP1JumpCounter = JUMPCOUNTER;
			//Player One's Jump is refreshed and ready to use
			}
			else if (intP1Jump <= 0)
				HoJeffreyDokkanShowdownCharacter.P1Character.setLocation(HoJeffreyDokkanShowdownCharacter.P1Character.getLocation().x += intMoveP1X, HoJeffreyDokkanShowdownCharacter.P1Character.getLocation().y += GRAVITY);
			//If Player One no longer has any jump available then Gravity will pull Player One down, in the time being Player One can only move left or right
			else if (HoJeffreyDokkanShowdownCharacter.P1Character.getLocation().y < 0){
			//If Player One ever reaches the top of the BattleField
				HoJeffreyDokkanShowdownCharacter.P1Character.setLocation(HoJeffreyDokkanShowdownCharacter.P1Character.getLocation().x += intMoveP1X, HoJeffreyDokkanShowdownCharacter.P1Character.getLocation().y += GRAVITY);
			//Player One can no longer move their character up (Instead gravity will pull them down)
				intP1Jump -= 5;
			//With every tick, decrease the value of Player One's Jump duration (Eventually intP1Jump = 0)
			}
			else {
				HoJeffreyDokkanShowdownCharacter.P1Character.setLocation(HoJeffreyDokkanShowdownCharacter.P1Character.getLocation().x += intMoveP1X, HoJeffreyDokkanShowdownCharacter.P1Character.getLocation().y += intMoveP1Y + GRAVITY);
				intP1Jump -= 5;
			//During the jump, this statement is most likely to be used. As the character is in the air update their location and decrease 5 from their jump duration	
			}
	}	
		 	
	public void P1Lives(){
		
		if (HoJeffreyDokkanShowdownCharacter.P1Character.getLocation().y > 500 || HoJeffreyDokkanShowdownCharacter.P1Character.getBounds().intersects(Platform2.getBounds())){ //if Player One ever reaches the bottom of the battle field or touches the other platform then
			for (int i = P1Hearts.length-1; i >= 0; i--){ 
				if (P1Hearts[i].isVisible() == true){ //If a heart is visible then
					P1Hearts[i].setVisible(false); //Make it invisible
					HoJeffreyDokkanShowdownCharacter.P1Character.setLocation(250, 225); //and spawn Player One back in
					break; //Break the for loop
				}	
				//if a heart was invisible then loop to the next heart thats visible	
			}
		}
			
			if (P1Hearts[0].isVisible() == false){ //However, if the last heart is invisible then
				HoJeffreyDokkanShowdownCharacter.P1Character.setVisible(false); //Make player one invisible
				t.stop(); //and stop the timer
				Win[1].setVisible(true); //Set the image to being visible to indicate Player Two has won (since Player one has no hearts left)
			}
	}
		 
	public void P1Projectile(){
		
		if (HoJeffreyDokkanShowdownCharacter.P1Projectile.getBounds().intersects(HoJeffreyDokkanShowdownCharacter.P2Character.getBounds())) //If Player One's projectile hits Player Two then
			HoJeffreyDokkanShowdownCharacter.P2Character.setLocation(HoJeffreyDokkanShowdownCharacter.P2Character.getLocation().x += KNOCKBACK, HoJeffreyDokkanShowdownCharacter.P2Character.getLocation().y); //Player Two is pushed back (by adding KNOCKBACK to the x location of Player Two)
		
		if (HoJeffreyDokkanShowdownCharacter.P1Projectile.isVisible()== true) //If the projectile is visible then
			HoJeffreyDokkanShowdownCharacter.P1Projectile.setLocation(HoJeffreyDokkanShowdownCharacter.P1Projectile.getX()+ PROJECTILESPEED, HoJeffreyDokkanShowdownCharacter.P1Projectile.getY()); //The projectile will move based on it's current location and projectile speed
		
		if (HoJeffreyDokkanShowdownCharacter.P1Projectile.getX() > 1200){ //If the projectile ever gets to the other side of the battlefield then
			HoJeffreyDokkanShowdownCharacter.P1Projectile.setVisible(false); //Make the projectile invisible and
			HoJeffreyDokkanShowdownCharacter.P1Projectile.setLocation(0,500); //Set it's new location to avoid it from still hitting Player two despite it being invisible
		} 
	}
		 
	public void P2Move(){
	
		if (HoJeffreyDokkanShowdownCharacter.P2Character.getBounds().intersects(Platform2.getBounds()) && HoJeffreyDokkanShowdownCharacter.P2Character.getBounds().intersects(Border2.getBounds()))
			HoJeffreyDokkanShowdownCharacter.P2Character.setLocation(HoJeffreyDokkanShowdownCharacter.P2Character.getLocation().x, HoJeffreyDokkanShowdownCharacter.P2Character.getLocation().y += intMoveP2Y + LEDGEFATIGUE);
		else if (HoJeffreyDokkanShowdownCharacter.P2Character.getBounds().intersects(Platform2.getBounds())){
			HoJeffreyDokkanShowdownCharacter.P2Character.setLocation(HoJeffreyDokkanShowdownCharacter.P2Character.getLocation().x += intMoveP2X, HoJeffreyDokkanShowdownCharacter.P2Character.getLocation().y += intMoveP2Y);
			intP2Jump = JUMPDURATION;
			intP2JumpCounter = JUMPCOUNTER;
		}
		else if (intP2Jump <= 0)
			HoJeffreyDokkanShowdownCharacter.P2Character.setLocation(HoJeffreyDokkanShowdownCharacter.P2Character.getLocation().x += intMoveP2X, HoJeffreyDokkanShowdownCharacter.P2Character.getLocation().y += GRAVITY);
		else if (HoJeffreyDokkanShowdownCharacter.P2Character.getLocation().y < 0){
			HoJeffreyDokkanShowdownCharacter.P2Character.setLocation(HoJeffreyDokkanShowdownCharacter.P2Character.getLocation().x += intMoveP2X, HoJeffreyDokkanShowdownCharacter.P2Character.getLocation().y += GRAVITY);
			intP2Jump -= 5;
		}
		else {
			HoJeffreyDokkanShowdownCharacter.P2Character.setLocation(HoJeffreyDokkanShowdownCharacter.P2Character.getLocation().x += intMoveP2X, HoJeffreyDokkanShowdownCharacter.P2Character.getLocation().y += intMoveP2Y + GRAVITY);
			intP2Jump -= 5;
		}
	}
		 //Works similarly to P1Move()
		 
	public void P2Lives(){
		if (HoJeffreyDokkanShowdownCharacter.P2Character.getLocation().y > 500|| HoJeffreyDokkanShowdownCharacter.P2Character.getBounds().intersects(Platform.getBounds())){
			for (int i = P2Hearts.length - 1; i >= 0; i--){
				if (P2Hearts[i].isVisible() == true){
					P2Hearts[i].setVisible(false);
					HoJeffreyDokkanShowdownCharacter.P2Character.setLocation(800, 225);
					break;
				}		
			}
		}
			
		if (P2Hearts[0].isVisible() == false){
			HoJeffreyDokkanShowdownCharacter.P2Character.setVisible(false);
			t.stop();
			Win[0].setVisible(true);
		}
	}
	//Works similarly to P1Lives()
		
	public void P2Projectile(){
		if (HoJeffreyDokkanShowdownCharacter.P2Projectile.getBounds().intersects(HoJeffreyDokkanShowdownCharacter.P1Character.getBounds())){
			HoJeffreyDokkanShowdownCharacter.P1Character.setLocation(HoJeffreyDokkanShowdownCharacter.P1Character.getLocation().x -= KNOCKBACK, HoJeffreyDokkanShowdownCharacter.P1Character.getLocation().y);
		}
		if (HoJeffreyDokkanShowdownCharacter.P2Projectile.isVisible()== true){
			HoJeffreyDokkanShowdownCharacter.P2Projectile.setLocation(HoJeffreyDokkanShowdownCharacter.P2Projectile.getX()- PROJECTILESPEED, HoJeffreyDokkanShowdownCharacter.P2Projectile.getY());
		}
		if (HoJeffreyDokkanShowdownCharacter.P2Projectile.getX() < 0){
			HoJeffreyDokkanShowdownCharacter.P2Projectile.setVisible(false);
			HoJeffreyDokkanShowdownCharacter.P2Projectile.setLocation(0,500);
		}
	}
	//Works similarly to P2Lives()		
}