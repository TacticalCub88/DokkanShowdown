/**
 * @(#)HoJeffreyDokkanShowdownCharacter.java
 *
 *
 * @Jeffrey Ho
 * @@version 1.00 2019/6/10
 *
 * This class creates the Labels of the Characters and their Projectile to be used in the class HoJeffreyDokkanShowdown
 */

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class HoJeffreyDokkanShowdownCharacter {
//initializing jlabels
	static JLabel P1Character, P2Character, P1Projectile, P2Projectile, P1Attack, P2Attack;
	
	HoJeffreyDokkanShowdownCharacter() {
    	//creates new objects of these labels that will be used to fight
    	P1Character = new JLabel();
    	P2Character = new JLabel();
    	
    	P1Projectile = new JLabel();
    	P2Projectile = new JLabel();
    }
  
	//Default Constructor
	HoJeffreyDokkanShowdownCharacter(int z, int y){
		//Creates the Labels and its Properties of P1Character, P2Character, P1Projectile, and P2Projectile
    	SetUp();
    	//Modifies the Labels based on Player One's Choices
    	PlayerOneProperties(z);
    	//Modifies the Labels based on Player Two's Choices
    	PlayerTwoProperties(y);	
    }
	

	//method that sets the invisible labels
	public void SetUp(){
    	//invisible label for the character of player one
    	P1Character = new JLabel();
    	P1Character.setSize(100,100);
    	P1Character.setLocation(250,225); 
    	P1Character.setVisible(false);
    	HoJeffreyDokkanShowdown.Background.add(P1Character);
    	
    	//invisible label with the same purpose but for player two instead
    	P2Character = new JLabel();
    	P2Character.setSize(100,100);
    	P2Character.setLocation(800,225);
    	P2Character.setVisible(false);
    	HoJeffreyDokkanShowdown.Background.add(P2Character);

    	//invisible label for player one's projectile
    	P1Projectile = new JLabel();
    	P1Projectile.setSize(40,40);
    	P1Projectile.setVisible(false);
    	HoJeffreyDokkanShowdown.Background.add(P1Projectile);
    	
    	//same thing but for player two instead
    	P2Projectile = new JLabel();
    	P2Projectile.setSize(40,40);
    	P2Projectile.setVisible(false);
    	HoJeffreyDokkanShowdown.Background.add(P2Projectile);
    }
    
	//assigns player one the character and ability for the game based z which is assigned in the SetUpclass
    public void PlayerOneProperties(int z){
    	//sets icons for the chracter and ability for frieza
    	if (z == 1){ 
    		P1Projectile.setIcon(new ImageIcon("DeathBeamL.png")); 
    		P1Character.setIcon(new ImageIcon("FriezaL.png")); 
    		P1Character.setVisible(true); 
    	}
    	//sets icons for the chracter and ability for gogeta
    	else if (z == 2){
    		P1Projectile.setIcon(new ImageIcon("BigBangAttackL.png"));
    		P1Character.setIcon(new ImageIcon("GogetaL.png"));
    		P1Character.setVisible(true);
    	}
    	//sets icons for the chracter and ability for mightymask
    	else if (z == 3){
    		P1Projectile.setIcon(new ImageIcon("PowerBlastL.png"));
    		P1Character.setIcon(new ImageIcon("MightyMaskL.png"));
    		P1Character.setVisible(true);
    	}
    	//sets icons for the chracter and ability for Vegeta
    	else if (z == 4){
    		P1Projectile.setIcon(new ImageIcon("FinalFlashL.png"));
    		P1Character.setIcon(new ImageIcon("SSJ4VegetaL.png"));
    		P1Character.setVisible(true);
    	}
    }
    
    
    public void PlayerTwoProperties(int y){
    	//sets the icons for the ability and character for player based on the value of y which is assigned in the SetUp class
    	if (y == 1){ 
    		P2Projectile.setIcon(new ImageIcon("DeathBeamR.png")); 
    		P2Character.setIcon(new ImageIcon("FriezaR.png")); 
    		P2Character.setVisible(true); 
    	}
    	
    	else if (y == 2){
    		P2Projectile.setIcon(new ImageIcon("BigBangAttackR.png"));
    		P2Character.setIcon(new ImageIcon("GogetaR.png"));
    		P2Character.setVisible(true);
    	}
    	
		else if (y == 3){
			P2Projectile.setIcon(new ImageIcon("PowerBlastR.png"));
    		P2Character.setIcon(new ImageIcon("MightyMaskR.png"));
    		P2Character.setVisible(true);
    	}
    	else if (y == 4){
    		P2Projectile.setIcon(new ImageIcon("FinalFLashR.png"));
    		P2Character.setIcon(new ImageIcon("SSJ4VegetaR.png"));
    		P2Character.setVisible(true);
    	}
    }
}


