//-------------------------------------------------------
//Assignment 4 - Driver class
//Written by: Cédric Paradis - student ID: 40158491
//For COMP 248 Section U 2204 – April 19th, Winter 2021
//--------------------------------------------------------
package assignment4; 
import java.util.Scanner;

/**
 * 
 * This program is a battle game program where the user needs to input the number of creatures competing in the game as well as their names. The program will generate all the creatures
 * with random food, health and fire power units. Then, the program will choose a random creature to start and will prompt the user to choose between several options at each creature's turn to play.
 * The goal of the program is that the creatures fight each other in a turn base game until only one creature is left alive which will terminate the program and it will be game over. 
 *
 */



public class BATTLEGAME {

	public static void main(String[] args) {

		// Display the welcome message

		System.out.println("[------------------------------------------------]" );
		System.out.println("[ Welcome to the Battle Game ] ");
		System.out.println("[------------------------------------------------]" );



		// Prompt the user for the number of creature 

		Scanner scanner = new Scanner(System.in); // declare a scanner 

		int numOfCreatures = 0; // initialize the number of creature to zero to enter the while loop

		// While loop to ask the user for correct input 

		while (numOfCreatures <2 || numOfCreatures >8) // if the user says a number less than 2 higher than 8 it will loop again and repeat the question
		{
			System.out.print("How many creatures would you like to have (minimum 2, maximum 8)? "); //prompt the user to input number of creatures
			numOfCreatures = scanner.nextInt();  // input number of creatures
			if (numOfCreatures <2 || numOfCreatures >8) // if the user says a number less than 2 higher than 8, display a warning message 
			{
				System.out.println("*** Illegal number of creatures requested ***");
			}
		}
		scanner.nextLine(); // nextline so the scanner does not skip a line when it needs to enter the names


		// Create an array of Creatures objects

		Creature creatureArray[] = new Creature[numOfCreatures]; // array of creatures objects


		// Name each Creature objects and display their stats

		String name = ""; // initialize to an empty string 

		for (int i =0; i < creatureArray.length; i++) // for loop to assign name to every creature object created and display their descriptive message
		{

			creatureArray[i] = new Creature(null); // create a new object at each index of the array and initialize parameter to null
			System.out.print("\nWhat is the name of creature " + (i+1) + "? "); // prompt the user for the creature's name and i+1 to not start at 0

			name = scanner.nextLine(); // input the name 
			creatureArray[i].setName(name); // call the set name method of the creature object to have the name input
			System.out.println("\n" + creatureArray[i].toString()); // call the to string method to display the descriptive message of the new creature
		}



		/*
		 * Play the game
		 */

		// Randomly decide which creature starts


		int currentCreature = (int)(Math.random() * creatureArray.length); // assign a random number between the number of creature (0 - number of creature) to decide which creature starts

		// initialize a count by calling the method to decrement each time a creature dies

		int aliveCount = creatureArray[currentCreature].getNumStillAlive(); 

		// while loop to shuffle through the array after the first creature is chosen

		while (true) {

			// Display the different options to the player

			int numOption = 0;	//initialize the options to 0 to enter while loop

			while (numOption < 1 || numOption > 6)	// loop again if the user put wrong choice which is less than one and higher than 6
			{
				System.out.print("\nCreature #" + (currentCreature + 1) + ": " + creatureArray[currentCreature].getName() + ", what do you want to do?");
				System.out.println("\n\t1. How many creatures are alive?");
				System.out.println("\t2. See my status");
				System.out.println("\t3. See status of all players");
				System.out.println("\t4. Change my name");
				System.out.println("\t5. Work for some food");
				System.out.println("\t6. Attack another creature (Warning! may turn against you)");

				System.out.print("\nYour Choice please > "); // prompt user for input
				numOption = scanner.nextInt(); // input the choice for the options







				// option 1 selected; display number of creatures alive

				if (numOption == 1) // if condition to go inside if the user input the option 1
				{
					System.out.println("\tNumber of creatures alive " + aliveCount ); //display number of creatures alive
					numOption = 0; // initialize to go back the while loop because it is still player current turn
				}
				// option 2 selected; display creature information

				if (numOption == 2) // if condition to go inside if the user input the option 2
				{
					System.out.println("\n" + creatureArray[currentCreature].toString()); // display the descriptive message by calling the toString method
					numOption = 0; // initialize to go back the while loop because it is still player current turn
				}

				// option 3 selected; display all players information

				if (numOption == 3)// if condition to go inside if the user input the option 3
				{
					for (int i = 0; i < creatureArray.length; i++) // for loop to go through each creature and display their descriptive message
					{
						System.out.println("\n" + creatureArray[i].toString()); // display by calling the toString of each creature
						numOption = 0; // initialize to go back the while loop because it is still player current turn
					}
				}
				//option 4 selected; change name 

				scanner.nextLine(); // nextLine so the program does not skip a line
				if (numOption == 4)// if condition to go inside if the user input the option 4
				{
					System.out.println("Your name is currently " + "\"" + creatureArray[currentCreature].getName() + "\""); // display the current name of the creature
					System.out.print("What is the new name: ");// prompt user for input
					creatureArray[currentCreature].setName(name = scanner.nextLine()); // call the set name function of the current creature to input a new name 
					numOption = 0; // initialize to go back the while loop because it is still player current turn
				}

				// option 5 selected; earn food

				if (numOption == 5) // if condition to go inside if the user input the option 5
				{
					System.out.println("\nYour status before working for food: " + creatureArray[currentCreature].showStatus()); // display the status before earning food by calling the showStatus method
					creatureArray[currentCreature].earnFood(); // call the earnFood method to randomly earn food units and normalize the players food, health and fire units
					System.out.println("\nYour status after working for food: " + creatureArray[currentCreature].showStatus()); // display the status after the method has been called to update the new units
				}

				//option 6 selected; attack other player



				if (numOption == 6) // if condition to go inside if the user input the option 6
				{
					int numAttack = 0; // initialize the number of the creature attacked to 0

					// while loop if wrong option selected

					while (numAttack < 1 || numAttack > creatureArray.length || creatureArray[numAttack-1].getDateDied() != null || numAttack == currentCreature+1) // if the player inputs a number less than 1 or higher than the number of creatures or himself or the number of a creature that died, it loops again
					{
						System.out.print("\nWho do you want to attack? (enter a number from 1 to " + creatureArray.length + " other than yourself(" + (currentCreature+1) + "): "); // prompt input to attack 
						numAttack = scanner.nextInt(); // input number of the creature 
						if (numAttack < 1 || numAttack > creatureArray.length) // if condition to display warning message for the wrong option 
						{
							System.out.println("That creature does not exist. Try again ...");
						}

						else if (numAttack == currentCreature+1) // if condition to display warning message for choosing himself
						{
							System.out.println("Can't attack yourself silly! Try again ...");
						}

						else if (creatureArray[numAttack-1].getDateDied() != null) // if condition to display warning message for choosing a dead creature
						{
							System.out.println("This creature is dead. Try again ...");
						}



					} // end of while loop

					// Create a option of 1/3 chance of being attacked


					int chanceAttack = (int)(Math.random() * 3); // randomly choosing a number for a 1/3 chance of being attack, between 0-3 exclusive

					if (chanceAttack == 0 || creatureArray[currentCreature].getFirePowerUnits() <2) // if conditon if the random number is 0 or if the creature that attacks does not have enough fire power, it is being attacked
					{
						if (creatureArray[currentCreature].getFirePowerUnits() <2) // nested if, display a warning message for the fire power option
						{
							System.out.println("That was not a good idea... you only had " + creatureArray[currentCreature].getFirePowerUnits() + " Fire Power units!!!");
						}
						if (creatureArray[numAttack-1].getFirePowerUnits() <2) // nested if, display a message if the creature that was suppose to attack does not have enough fire power, it exits the if condition and no one is attack
						{
							System.out.println("Lucky you, the odds were that the other player attacks you, but " + creatureArray[numAttack-1].getName() + " doesn't have enough fire power to attack you! So is status quo!!");
						}
						else { // else attached to the previous if, if creature attacking has enough power go inside else
							System.out.println("....... Oh No!!! You are being attacked by " + creatureArray[numAttack-1].getName()); // display attacked message
							System.out.println("Your status before being attacked: " + creatureArray[currentCreature].showStatus()); // show status before being attacked
							creatureArray[numAttack-1].attacking(creatureArray[currentCreature]); // call the attacking method and the creature suppose to being attacked attacks the current one 
							System.out.println("Your status after being attacked: " + creatureArray[currentCreature].showStatus()); // show status after being attacked
						}
					}
					else // if the creature has enough power and has another chance number than 0, go inside the condition
					{
						System.out.println("\n.... You are attacking " + creatureArray[numAttack-1].getName() + "!"); // display attacking message 
						System.out.println("Your status before attacking: " + creatureArray[currentCreature].showStatus()); // display status before attacking 
						creatureArray[currentCreature].attacking(creatureArray[numAttack-1]); // call the attacking method to attack the chosen creature
						System.out.println("Your status after attacking: " + creatureArray[currentCreature].showStatus()); // display status after attacking
					}


					if (creatureArray[currentCreature].isAlive() == false ) // if condition to check if the creature that was attacking is still alive by calling the isAlive method
					{
						System.out.println("\n---->" + creatureArray[currentCreature].getName() + " is dead"); // display this message if the current creature died 
						aliveCount--; // decrement the number of creature alive
					}

					if (creatureArray[numAttack-1].isAlive() == false ) // if condition to check if the attacked creature is still alive by calling the isAlive method
					{
						System.out.println("\n---->" + creatureArray[numAttack-1].getName() + " is dead"); // display this message if the attacked creature died 
						aliveCount--; // decrement the number of creature alive 
					}

				} // end of option 6

			} // end of user choice while loop

			// Game over 

			if (aliveCount == 1) // go inside if only one creature is alive in the game 
			{
				System.out.println("\nGAME OVER!!!"); // display a game over message 
				for (int i = 0; i < creatureArray.length; i++) // for loop to display the descriptive message of every creature in the game 
				{
					System.out.println("\n" + creatureArray[i].toString()); // call to toString method to display
				}
				break; // break to exit the shuffling while loop and terminate the program
			}

			// Set array back to 0 to not go over index length

			if (currentCreature == creatureArray.length-1) // if the current creature that played its turn is the last one of the array, set the array to 0 to have the next creature's turn
			{currentCreature = 0;} // set current creature to 0 to start the turn over 
			else // if the creature is not the last
			{
				currentCreature++; //increment the creature object in the array by one
			}
			//while loop to skip dead creatures

			while (creatureArray[currentCreature].getDateDied() != null)  // if the next creature in the array is dead, go inside the loop
			{
				currentCreature++; // increment by one to go to the next one until it finds a alive creature

				if (currentCreature == creatureArray.length) // if the loop increments until we have one more than the number of creature, it sets back to 0 to continue to increment without going out of bounds
				{currentCreature = 0;} // set back to 0

			}




		} // end of shuffling while loop


		// Display a closing message

		System.out.println("\n[------------------------------------------------]" );
		System.out.println("[Thank you for playing Battle Game! ] ");
		System.out.println("[------------------------------------------------]" );


		scanner.close(); // always close the scanner





	}
}
