import java.util.Scanner;

/**
*This program is a Nim game where two players can take 1-3 sticks from four randomly generated
*piles and the user to take the last stick will lose.
*
*@author Ethan Muir
*@version Friday, March 29
*
*/

public class NimGame
{
	//Declaration of variables
	static int pile1;
	static int pile2; 
	static int pile3;
	static int pile4;
	
	static String user1;
	static String user2;
	static String currentPlayer;
	
	static int sticksRemoved = 0;
	
	static int playAgain = 0;
	
	static int user1Score;
	static int user2Score;
	
	static int stickTotal;
	static int numSticks;
	static int currentPile;
	
	static char playAgainAns;
	static boolean repeat1;
	static boolean repeat2;
	static boolean repeat3;
	
	//Object for input
	static Scanner in = new Scanner(System.in);
	
	//Main method
	public static void main(String[]args)
	{

	
	//Program intro
	System.out.println("Welcome to the nim game! In this game, there will be four piles of sticks and each player will");
	System.out.println("decide what pile to take sticks from (1-3 sticks). The player to take the last stick out loses." + "\n");
	playerNames();
		
	//loop so the whole game can repeat when necessary
	do
	{
		//Assigning the piles their stick values using the randomizing method
		pile1 = pileRandomizer();
		pile2 = pileRandomizer();
		pile3 = pileRandomizer();
		pile4 = pileRandomizer();	
		
		//Starting the game with player one
		currentPlayer = user1;
		
		//This code is one turn that will repeat until the game is over
		do
		{
			pilePrinter();
			pileChooser();
			pileRemover(currentPile);
			playerSwap();
			stickTotal = (pile1 + pile2 + pile3 + pile4);
		}
		while(stickTotal > 0);
		 
		//This if statement will print out the winner and award them a point 
		if(currentPlayer == user1)
		{
			System.out.println("CONGRATS " + user1 + " YOU WIN!!");
			user1Score++;
		}
		else
		{
			System.out.println("CONGRATS " + user2 + " YOU WIN!!");
			user2Score++;
		}
		
		//Outputting the current score
		System.out.println(user2 + " has " + user2Score + " win(s)");
		System.out.println(user1 + " has " + user1Score + " win(s)");
		
		//Option to play the game again
		do
		{
			repeat3 = false;
			System.out.println("Would you like to play again? (y/n)");
			playAgainAns = in.next().charAt(0);
			if(playAgainAns == 'y')
			{
			}
			else if (playAgainAns == 'n')
			{
				playAgain ++;
			}
			else
			{
				System.out.println("Not a valid answer");
				repeat3 = true;
			}
		}
		while(repeat3 == true);
	}
	while(playAgain == 0);
	    
	}
	
	/**Method to give a random number of sticks from 4-8
	 * 
	 * @param none
	 * @return numsticks -This is the random number from 4-8
	 */
	public static int pileRandomizer()
	{
		numSticks = (int)(Math.random() * 5 + 4);
		return numSticks;
	}
	
	/**Method to get the users names
	 * 
	 * @param none
	 * @return none
	 */
	public static void playerNames()
	{
		System.out.println("Please enter your name player one:");
		user1 = in.next();
		System.out.println("Please enter your name player two:");
		user2 = in.next();
	}
	
	/**Method to print the piles with their sticks
	 * 
	 * @param none
	 * @return none
	 */
	public static void pilePrinter()
	{
		System.out.print("\n" + "Pile 1:" + "\t");
		for(int i = 0;i < pile1; i++)
		{
			System.out.print("|");
		}
		System.out.println("(" + pile1 + " Sticks )");
		
		System.out.print("Pile 2:" + "\t");
		for(int i = 0;i < pile2; i++)
		{
			System.out.print("|");
		}
		System.out.println("(" + pile2 + " Sticks )");
		
		System.out.print("Pile 3:" + "\t");
		for(int i = 0;i < pile3; i++)
		{
			System.out.print("|");
		}
		System.out.println("(" + pile3 + " Sticks )");
		
		System.out.print("Pile 4:" + "\t");
		for(int i = 0;i < pile4; i++)
		{
			System.out.print("|");
		}
		System.out.println("(" + pile4 + " Sticks )" + "\n");
	}
	
	/**Method to choose which pile to take sticks out of
	 * 
	 * @param none
	 * @return currentPile -This is what pile the user chose
	 */
	public static int pileChooser()
	{
		do
		{
			System.out.println(currentPlayer + ", which pile would you like to choose from?");
			currentPile = in.nextInt();
				//Error checking
				repeat1 = false;
				if(currentPile < 1 || currentPile > 4) //If the user chooses an invalid pile
				{
					repeat1 = true;
					System.out.println("INVALID PILE, please choose another");
				}
				else if(currentPile == 1 && pile1 == 0) //If the user chooses pile 1 but it doesn't have any more sticks left
				{
					repeat1 = true;
					System.out.println("This pile has no more sticks, please choose another."+ "\n");
				}
				else if(currentPile == 2 && pile2 == 0) //If the user chooses pile 2 but it doesn't have any more sticks left
				{
					repeat1 = true;
					System.out.println("This pile has no more sticks, please choose another."+ "\n");
				}
				else if(currentPile == 3 && pile3 == 0) //If the user chooses pile 3 but it doesn't have any more sticks left
				{
					repeat1 = true;
					System.out.println("This pile has no more sticks, please choose another."+ "\n");
				}
				else if(currentPile == 4 && pile4 == 0) //If the user chooses pile 4 but it doesn't have any more sticks left
				{
					repeat1 = true;
					System.out.println("This pile has no more sticks, please choose another." + "\n");
				}
		}
		while(repeat1 == true);
		return currentPile;
	}
	
	/**Method to remove a certain number of sitcks from a pile
	 * 
	 * @param currentPile -This is the pile that the user chose
	 * @return Returns the number of sticks left int he given pile
	 */
	public static int pileRemover(int currentPile)
	{
		do
		{
			//User inputs how many sticks they would like to remove 
			System.out.println("How many sticks would you like to remove from pile " + currentPile);
			sticksRemoved = in.nextInt();
			repeat2 = false;
			
			//Error checking
			if(sticksRemoved < 1 || sticksRemoved > 3) //If the user chooses to remove less than one or more than three sticks
			{
				repeat2 = true;
				System.out.println("You must choose a value from 1-3, try again");
			}
			else if(currentPile == 1 && sticksRemoved > pile1) //If the user chooses to take more sticks than what are left in pile 1
			{
				repeat2 = true;
				System.out.println("There are only " + pile1 + " stick(s) in this pile, you can't take out " + sticksRemoved + ".");
			}
			else if(currentPile == 2 && sticksRemoved > pile2) //If the user chooses to take more sticks than what are left in pile 2
			{
				repeat2 = true;
				System.out.println("There are only " + pile2 + " stick(s) in this pile, you can't take out " + sticksRemoved + ".");
			}
			else if(currentPile == 3 && sticksRemoved > pile3) //If the user chooses to take more sticks than what are left in pile 3
			{
				repeat2 = true;
				System.out.println("There are only " + pile3 + " stick(s) in this pile, you can't take out " + sticksRemoved + ".");
			}
			else if(currentPile == 4 && sticksRemoved > pile4) //If the user chooses to take more sticks than what are left in pile 4
			{
				repeat2 = true;
				System.out.println("There are only " + pile4 + " stick(s) in this pile, you can't take out " + sticksRemoved + ".");
			}
		}
		while(repeat2 == true);
			//If statement to remove the sticks from the given pile
			if(currentPile == 1)
			{
				pile1 -= sticksRemoved;
				return pile1;
			}
			else if(currentPile == 2)
			{
				pile2 -= sticksRemoved;
				return pile2;
			}
			else if(currentPile == 3)
			{
				pile3 -= sticksRemoved;
				return pile3;
			}
			else
			{
				pile4 -= sticksRemoved;
				return pile4;
			}
	}
	
	/**Method to change players each turn
	* 
	* @param none
	* @return none
	*/
	public static void playerSwap()
	{
		if(currentPlayer == user1)
		{
			currentPlayer = user2;
		}
		else
		{
			currentPlayer = user1;
		}
	}
}
