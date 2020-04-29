import java.util.InputMismatchException;
import java.util.Scanner;
import java.awt.*;

/**
 * This class sets up and runs a RaceTrack with some user input initialization data. 
 * <p><b>You should not edit this file.</b></p>
 *  
 * @author cs12b
 * @version 2.0 
 */
public class SimulationDriver {

	/**
	 * Value to signify a default stat value.
	 */
	public static final int DEFAULT_STAT_VAL = 0;
	public static int numRaceCars =-1;
	public static int numFormulaOnes =-1;
	public static int numSportsCars =-1;
	public static Car[] allCars ;
	public static int numCars;
	public static Scanner consoleRdr = new Scanner(System.in);
		
	/**
	 * Sets up and runs a RaceTrack using the helper method getSomeRaceCars(). 
	 * @param args cmd line args not used 
	 * @see #getSomeRaceCars()
	 */
	public static void main(String[] args) {
		// set up a RaceTrack with an array of RaceCars read from user input using getSomeRaceCars() 
		RaceTrack track = new RaceTrack();
		RaceCar[] allRaceCars = getSomeRaceCars();
		FormulaOne[] allFormulaOnes = getSomeFormulaOnes();
		SportsCar[] allSportsCars = getSomeSportsCars();
		numCars=numRaceCars+numFormulaOnes+numSportsCars;
		allCars= new Car[numCars];
		for(int i=0;i<numRaceCars;i++) 
			allCars[i] = allRaceCars[i];
		for(int i=0;i<numFormulaOnes;i++) 
			allCars[i+numRaceCars] = allFormulaOnes[i];
		for(int i=0;i<numSportsCars;i++) 
			allCars[i+numRaceCars+numFormulaOnes] = allSportsCars[i];
		track.setCars(allCars);
		while(FinishLine.finished()==false){
			track.tick();
		}
	} 
		
	/**
	 * This method gets some RaceCars should run the part A simulation.
	 * @return an array of RaceCars created from user input.
	 */
	public static RaceCar[] getSomeRaceCars() {
		System.out.println("Welcome to the Need for Speed Simulator!\n\nFor each RaceCar: enter the speed and strength separated by a space.\nIf you want to construct a default RaceCar, enter " + DEFAULT_STAT_VAL + " for the speed and strength.");
		boolean carNumInvalid = true;
		// loop until the user provides a valid no. of cars (nonnegative integer) 
		do {
			System.out.print("How many RaceCars would you like to enter in the race? ");
			try { // try to get no. of cars as integer 
				numRaceCars = consoleRdr.nextInt();
			} 
			catch (InputMismatchException e) { // if the input is not an integer, clear the scanner buffer and continue to keep looping 
				System.out.println("Please enter a valid integer.");
				consoleRdr.nextLine();
				continue;
			}
			if (numRaceCars >= 0) { // if nonnegative integer, then we've got a valid input (mark loop end)  
				carNumInvalid = false;
			}
			else {
				System.out.println("Please enter a nonnegative integer.");
			}
		} while (carNumInvalid);
		System.out.println();
		RaceCar[] cars = new RaceCar[numRaceCars];
		// for each car user will enter, loop until the user provides valid car initialization data (2 nonnegtive integers that are either both 0 or nonzero) 
		for (int i = 0; i < numRaceCars; i++) {
			boolean carInvalid = true;
			do {
				System.out.print("RaceCar #" + (i + 1) + ": ");
				int speed = -1;
				int strength = -1;
				try { // try to get 2 integers 
					speed = consoleRdr.nextInt();
					strength = consoleRdr.nextInt();
				}
				catch (InputMismatchException e) { // if either is not an integer, clear the scanner buffer and continue to keep looping to read input 
					System.out.println("The strength and speed should be valid integers.");
					consoleRdr.nextLine();
					continue;
				}
					
				// if either integer is negative, continue to keep looping  for valid input
				if (speed < 0 || strength < 0) { 
					System.out.println("The strength and speed should not be negative.");
					continue;
				}
				
				// either speed, strength must be both 0 or both positive. both 0 => use of default constructor 
				// if 1 is 0 and the other is not, continue to keep looping for valid input 
				if ((speed != DEFAULT_STAT_VAL && strength == DEFAULT_STAT_VAL) || (speed == DEFAULT_STAT_VAL && strength != DEFAULT_STAT_VAL)) { 
					System.out.println("The strength and speed must either both be " + DEFAULT_STAT_VAL + " or both nonzero.");
					continue;
				}
				
				// at this point, speed, strength are either both 0 or both integers (valid input). any case of invalid input would have hit a continue
				// call that would have jumped to the start of the loop
				
				// mark that we have valid input, and use the appropriate constructor (2 0's => default constructor, 2 positive's => regular constructor)
				carInvalid = false; 
				if (speed == DEFAULT_STAT_VAL && strength == DEFAULT_STAT_VAL) {
					cars[i] = new RaceCar();
				}
				else {
					cars[i] = new RaceCar(speed, strength);
				}
				System.out.println();
				
			} while (carInvalid);		
		}
//		consoleRdr.next();
		return cars;
	}
	public static FormulaOne[] getSomeFormulaOnes() {
		System.out.println("\nFor each FormulaOne: enter the speed and strength separated by a space.\nIf you want to construct a default RaceCar, enter " + DEFAULT_STAT_VAL + " for the speed and strength.");
		boolean carNumInvalid = true;
		// loop until the user provides a valid no. of cars (nonnegative integer) 
		do {
			System.out.print("How many FormulaOnes would you like to enter in the race? ");
			try { // try to get no. of cars as integer 
				if(consoleRdr.hasNext())
					numFormulaOnes = consoleRdr.nextInt();
			} 
			catch (InputMismatchException e) { // if the input is not an integer, clear the scanner buffer and continue to keep looping 
				System.out.println("Please enter a valid integer.");
				consoleRdr.nextLine();
				continue;
			}
			if (numFormulaOnes >= 0) { // if nonnegative integer, then we've got a valid input (mark loop end)  
				carNumInvalid = false;
			}
			else {
				System.out.println("Please enter a nonnegative integer.");
			}
		} while (carNumInvalid);
		System.out.println();
		FormulaOne[] cars = new FormulaOne[numFormulaOnes];
		// for each car user will enter, loop until the user provides valid car initialization data (2 nonnegtive integers that are either both 0 or nonzero) 
		for (int i = 0; i < numFormulaOnes; i++) {
			boolean carInvalid = true;
			do {
				System.out.print("FormulaOne #" + (i + 1) + ": ");
				int speed = -1;
				int strength = -1;
				try { // try to get 2 integers 
					speed = consoleRdr.nextInt();
					strength = consoleRdr.nextInt();
				}
				catch (InputMismatchException e) { // if either is not an integer, clear the scanner buffer and continue to keep looping to read input 
					System.out.println("The strength and speed should be valid integers.");
					consoleRdr.nextLine();
					continue;
				}
					
				// if either integer is negative, continue to keep looping  for valid input
				if (speed < 0 || strength < 0) { 
					System.out.println("The strength and speed should not be negative.");
					continue;
				}
				
				// either speed, strength must be both 0 or both positive. both 0 => use of default constructor 
				// if 1 is 0 and the other is not, continue to keep looping for valid input 
				if ((speed != DEFAULT_STAT_VAL && strength == DEFAULT_STAT_VAL) || (speed == DEFAULT_STAT_VAL && strength != DEFAULT_STAT_VAL)) { 
					System.out.println("The strength and speed must either both be " + DEFAULT_STAT_VAL + " or both nonzero.");
					continue;
				}
				
				// at this point, speed, strength are either both 0 or both integers (valid input). any case of invalid input would have hit a continue
				// call that would have jumped to the start of the loop
				
				// mark that we have valid input, and use the appropriate constructor (2 0's => default constructor, 2 positive's => regular constructor)
				carInvalid = false; 
				if (speed == DEFAULT_STAT_VAL && strength == DEFAULT_STAT_VAL) {
					cars[i] = new FormulaOne();
				}
				else {
					cars[i] = new FormulaOne(speed, strength);
				}
				System.out.println();
				
			} while (carInvalid);		
		}
		return cars;
	}
	public static SportsCar[] getSomeSportsCars() {
		System.out.println("\nFor each SportsCar: enter the speed and strength separated by a space.\nIf you want to construct a default RaceCar, enter " + DEFAULT_STAT_VAL + " for the speed and strength.");
		boolean carNumInvalid = true;
		// loop until the user provides a valid no. of cars (nonnegative integer) 
		do {
			System.out.print("How many SportsCars would you like to enter in the race? ");
			try { // try to get no. of cars as integer 
				numSportsCars = consoleRdr.nextInt();
			} 
			catch (InputMismatchException e) { // if the input is not an integer, clear the scanner buffer and continue to keep looping 
				System.out.println("Please enter a valid integer.");
				consoleRdr.nextLine();
				continue;
			}
			if (numSportsCars >= 0) { // if nonnegative integer, then we've got a valid input (mark loop end)  
				carNumInvalid = false;
			}
			else {
				System.out.println("Please enter a nonnegative integer.");
			}
		} while (carNumInvalid);
		System.out.println();
		SportsCar[] cars = new SportsCar[numSportsCars];
		// for each car user will enter, loop until the user provides valid car initialization data (2 nonnegtive integers that are either both 0 or nonzero) 
		for (int i = 0; i < numSportsCars; i++) {
			boolean carInvalid = true;
			do {
				System.out.print("SportsCar #" + (i + 1) + ": ");
				int speed = -1;
				int strength = -1;
				try { // try to get 2 integers 
					speed = consoleRdr.nextInt();
					strength = consoleRdr.nextInt();
				}
				catch (InputMismatchException e) { // if either is not an integer, clear the scanner buffer and continue to keep looping to read input 
					System.out.println("The strength and speed should be valid integers.");
					consoleRdr.nextLine();
					continue;
				}
					
				// if either integer is negative, continue to keep looping  for valid input
				if (speed < 0 || strength < 0) { 
					System.out.println("The strength and speed should not be negative.");
					continue;
				}
				
				// either speed, strength must be both 0 or both positive. both 0 => use of default constructor 
				// if 1 is 0 and the other is not, continue to keep looping for valid input 
				if ((speed != DEFAULT_STAT_VAL && strength == DEFAULT_STAT_VAL) || (speed == DEFAULT_STAT_VAL && strength != DEFAULT_STAT_VAL)) { 
					System.out.println("The strength and speed must either both be " + DEFAULT_STAT_VAL + " or both nonzero.");
					continue;
				}
				
				// at this point, speed, strength are either both 0 or both integers (valid input). any case of invalid input would have hit a continue
				// call that would have jumped to the start of the loop
				
				// mark that we have valid input, and use the appropriate constructor (2 0's => default constructor, 2 positive's => regular constructor)
				carInvalid = false; 
				if (speed == DEFAULT_STAT_VAL && strength == DEFAULT_STAT_VAL) {
					cars[i] = new SportsCar();
				}
				else {
					cars[i] = new SportsCar(speed, strength);
				}
				System.out.println();
				
			} while (carInvalid);		
		}
		consoleRdr.close();
		return cars;
		}
	}
