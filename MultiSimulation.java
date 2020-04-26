import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;
public class MultiSimulation {
	
	public static void main(String[] args) {
		System.out.println("Welcome to the Need for Speed Simulator (Multiple Runtimes.ver)!");
		Random r = new Random();
		Scanner in = new Scanner(System.in);
		System.out.println("Please enter numbers of simulation you want to test: ");
		int numSimu = in.nextInt();
		int[] scoreRecords = new int[numSimu];
		String[] carsData = new String[numSimu];
		int numCars = -1;
		boolean carNumInvalid = true;
		do {
			System.out.println("Do you want to set a fixed number of RaceCars for each run? ");
			System.out.println("If yes, please enter a positive integer for number of cars; if no, please enter 0.");
			try { // try to get no. of cars as integer
				numCars = in.nextInt();
			}
			catch (InputMismatchException e) { // if the input is not an integer, clear the scanner buffer and continue to keep looping
				System.out.println("Please enter a valid integer.");
				in.nextLine();
				continue;
			}
			if (numCars >= 0) { // if nonnegative integer, then we've got a valid input (mark loop end)
				carNumInvalid = false;
			}
			else {
				System.out.println("Please enter a nonnegative integer.");
			}
		} while (carNumInvalid);
		System.out.println();
		if (numCars == 0) {
			numCars = r.nextInt(10)+1;
		}
		for (int i = 0; i < numSimu; i++) {
			RaceTrack track = new RaceTrack();
			RaceCar[] cars = new RaceCar[numCars];
			for (int j = 0; j < numCars; j++) {
				int randomSpeed = r.nextInt(26)+30;
				int randomStrength = r.nextInt(3)+2;
				cars[j] = new RaceCar(randomSpeed,randomStrength);
			}
			track.setCars(cars);
			track.runSimu();
			scoreRecords[i] = track.score;
			carsData[i] = track.carsData;
		}
		int index = 0;
		int max = 0;
		for (int i = 0; i < numSimu; i++) {			
			//System.out.println(scoreRecords[i]);
			if (scoreRecords[i]>max) {
				max = scoreRecords[i];
				index = i;
			}
		}
		index++;
		System.out.println("Your max score is acheived in simulation No."+index+"\n"+"The data of cars are: \n"+carsData[index-1]);
	}
}
