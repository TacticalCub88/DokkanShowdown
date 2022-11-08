/**
 * @(#)HoJeffreyDokkanShowdownTest.java
 *
 *
 * @Jeffrey Ho
 * @version 1.00 2019/6/10
 *
 * This is where the code begins, it tests the creation of the set up phase and the actual gameplay of the program. 
 */
 

public class HoJeffreyDokkanShowdownTest {

    public static void main(String [] args) {
    	//SetUp Object is created from The HoJeffreyDokkanShowdownSetUp class (creates the set up phase of the game)
    	HoJeffreyDokkanShowdownSetUp SetUp = new HoJeffreyDokkanShowdownSetUp();
    	
    	//TheShowdown Object is created from the HoJeffreyDokkanShowdown class (creates the actual game)
    	HoJeffreyDokkanShowdown TheShowdown = new HoJeffreyDokkanShowdown();
 		
    }
    
    //NOTE: When Player One selects their character A NullPointerException would pop up. However, this doesn't affect the functionality of the game
    
}


