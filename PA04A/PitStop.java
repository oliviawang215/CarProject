//PA04
//Ruyang Wang
//2020.3.5
public class PitStop{

  public void enterPitStop(RaceCar[] cars, int n){ // TODO you can add parameters here!
    //This method enters a car into the pitstop.
    if (cars[n].stop == 0){
      if (cars[n].unit < 75){
        cars[n].lap--;
      }
      cars[n].unit = 75;
      System.out.println();
      System.out.print(cars[n].toString()+" has entered the pitstop");
    }
    cars[n].stop++;
    cars[n].speed = 0;
    cars[n].exist = false;
  }

  public void exitPitStop(RaceCar[] cars, int n){
    //This method exits a car from the pitstop.
    cars[n].damaged = false;
    cars[n].speed = cars[n].inispeed;
    cars[n].stop = 0;
    cars[n].unit += cars[n].inispeed;
    //the car moves immediately according to its speed
    cars[n].exist = true;
    if (cars[n].unit >= 100){
      cars[n].unit -= 100;
      cars[n].lap += 1;
    }
    System.out.println();
    System.out.print(cars[n].toString()+" has exited the pitstop");
  }
  public void enterPitStopSimu(RaceCar[] cars, int n){ // TODO you can add parameters here!
	    //This method enters a car into the pitstop.
	    if (cars[n].stop == 0){
	      if (cars[n].unit < 75){
	        cars[n].lap--;
	      }
	      cars[n].unit = 75;
	      //System.out.println();
	      //System.out.print(cars[n].toString()+" has entered the pitstop");
	    }
	    cars[n].stop++;
	    cars[n].speed = 0;
	    cars[n].exist = false;
	  }

	  public void exitPitStopSimu(RaceCar[] cars, int n){
	    //This method exits a car from the pitstop.
	    cars[n].damaged = false;
	    cars[n].speed = cars[n].inispeed;
	    cars[n].stop = 0;
	    cars[n].unit += cars[n].inispeed;
	    //the car moves immediately according to its speed
	    cars[n].exist = true;
	    if (cars[n].unit >= 100){
	      cars[n].unit -= 100;
	      cars[n].lap += 1;
	    }
	    //System.out.println();
	    //System.out.print(cars[n].toString()+" has exited the pitstop");
	  }
}
