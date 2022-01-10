//-------------------------------------------------------
//Assignment 4 
//Written by: Cédric Paradis - student ID: 40158491
//For COMP 248 Section U 2204 – April 19th, Winter 2021
//--------------------------------------------------------
package assignment4;

import java.util.Date;



/**
 * 
 * This program contains all the data members as well as all the important method members that will be used in the driver to run the battle game application smoothly
 *
 */



public class Creature {


	// Creating the global constant variables

	private static final int FOOD2HEALTH = 6; 

	private static final int HEALTH2POWER = 4;

	// Create the instance private variables

	private String name = ""; //creature name

	private int foodUnits = 0; //number of food

	private int healthUnits = 0; //number of health

	private int firePowerUnits = 0; // number of fire power unit

	private Date dateCreated; // date the creature was created

	private Date dateDied; // date the creature died

	private static int numStillAlive = 0; // keeps trackof the number of creatures


	/*
	 * Methods section
	 */


	// Constructor

	public Creature (String name)
	{
		numStillAlive++; // initalizes the number of alive creature by one
		this.name = name; // intializes the name 

		foodUnits = (int) (Math.random() * 12) + 1; // initializes the food unit randomly from 1-12 inclusive

		healthUnits = (int)(Math.random() * 7) + 1; // initializes the health unit randomly from 1-7 inclusive

		firePowerUnits = (int)(Math.random() * 10); //initializes the fire unit randomly from 0-10 exclusive

		dateCreated = new Date(); // initializes the date of creation

		dateDied = null; // initializes the date of death

	}


	// Set methods 

	public void setName(String newName) // sets the name of the creature 
	{
		name = newName;
	}

	public void setHealthUnit(int n)// sets the health unit of the creature 
	{
		healthUnits = n;
	}

	public void setFoodUnit(int n) // sets the food unit of the creature 
	{
		foodUnits = n;
	}

	// Method to substract fire 

	public void reduceFirePowerUnits (int n) // reduces the fire unit by arbitrary number
	{
		firePowerUnits -= n;
	}


	// Get methods


	public String getName() // returns the name of the creature
	{
		return name;
	}

	public int getFoodUnits() // returns the food units of the creature 
	{
		return foodUnits;
	}

	public int getHealthUnits() // returns the health units of the creature 
	{
		return healthUnits;
	}

	public int getFirePowerUnits() // returns the fire power units of the creature 
	{
		return firePowerUnits;
	}

	public Date getDateCreated() // returns the date of creation of the creature 
	{
		return dateCreated;
	}

	public Date getDateDied() // returns the date of death of the creature 
	{
		return dateDied;
	}


	public static int getNumStillAlive() // returns the number of creatures alive in the game 
	{
		return numStillAlive;
	}


	//  method to check if a creature is alive 

	public boolean isAlive()
	{
		if (dateDied == null) // if condition to check if the creature is alive, if the date is null then it is alive
		{
			return true;
		}

		else
		{
			return false;
		}
	}

	// method to earn food 

	public int earnFood()
	{
		foodUnits += (int) (Math.random() * 16); // earn food between 0-15 inclusive
		normalizeUnits(); // call the method to transfer food to health and health to fire power units

		return foodUnits; // returns the total number of food units 
	}

	// attack the player method

	public void attacking(Creature player)
	{
		// Attacker gains

		foodUnits += (int)Math.round(player.foodUnits/2.0 ); // add the food gain from the attack by dividing attacked player food by double number to round the number up before casting to an integer
		healthUnits += (int)Math.round(player.healthUnits/2.0 ); // add the health gain from the attack  by dividing attacked player health by double number to round the number up before casting to an integer

		// Lost by the attacked player 

		player.foodUnits -= (int)Math.round(player.foodUnits/2.0 ); // substract attacked player food lost by dividing by double number to round the number up before casting to an integer
		player.healthUnits -= (int)Math.round(player.healthUnits/2.0 ); // substract attacked player health lost by dividing by double number to round the number up before casting to an integer

		// Cost of the attack
		if (firePowerUnits > 0) // if condition so the player can not have minus firepower

		{reduceFirePowerUnits(2);} // call the method so two power unit is lost to attack

		// call the normalize unit method to transfer food into health and health into fire power

		normalizeUnits(); 

		// call the healthfood units zero method to check if the attacked player is dead

		player.healthFoodUnitsZero();
	}





	// Health and food units method

	public boolean healthFoodUnitsZero()
	{

		if (healthUnits == 0 && foodUnits ==0 && dateDied == null) // if condition to check if the player has no more food and health but is still alive to call it dead
		{
			died(); // if it is still alive but it has no more food, then call the died() method 
			return true;
		}

		else
		{
			return false;
		}
	}

	// death method
	private void died() // method to publish the date of death of the player
	{
		dateDied = new Date(); // change the date from null to the date the player died 
	}



	// toString method

	public String toString() // method to print the descriptive message about the non-static attribute
	{
		if (dateDied == null) { // if condition to print this particular statement when the player is alive so the date died shows he is alive
			return ("Food units \t Health Units \t Fire Power Unit \t Name" +
					"\n----------- \t ------------ \t --------------- \t ----" +
					"\n" + foodUnits + "\t\t " + healthUnits + "\t\t " + firePowerUnits + "\t\t\t " + name +
					"\nDate Create: " + dateCreated +
					"\nDate died: " + "is still alive"); 
			//"\n" + foodUnits + "Health units"  + "\n-----------" + "\n" + healthUnits + "Fire power units"+ firePowerUnits + "Fire power units");
		}

		else // if the player is dead, then the descriptive message will show the date of death 
		{
			return ("Food units \t Health Units \t Fire Power Unit \t Name" +
					"\n----------- \t ------------ \t --------------- \t ----" +
					"\n" + foodUnits + "\t\t " + healthUnits + "\t\t " + firePowerUnits + "\t\t\t " + name +
					"\nDate Create: " + dateCreated +
					"\nDate died: " + dateDied); 
		}
	}


	// showStatus method

	public String showStatus()
	{
		return foodUnits + " food units, " + healthUnits + " health units, " + firePowerUnits + " fire power units"; // print all the units the player currently possess
	}


	// normalize unit method

	private void normalizeUnits() // method to transfer 6 food units into 1 health units and 4 health units into 1 fire power
	{
		//for loop to normalize the food 

		for (int i = foodUnits; i >= FOOD2HEALTH; i -= FOOD2HEALTH) // condition if food units is equals or higher than 6 because we will have minus food otherwise 
		{

			foodUnits -= FOOD2HEALTH; // substract the food taken 
			healthUnits++; // add one health unit
		} 

		//for loop to normalize the health

		for (int i = healthUnits; i >= 8; i -=HEALTH2POWER ) // health units needs to be equals or higher than 8 because we need at least 4 health units at all time
		{
			healthUnits -= HEALTH2POWER; // substract the health taken
			firePowerUnits ++; // add one fire power unit 
		}

	}


}






