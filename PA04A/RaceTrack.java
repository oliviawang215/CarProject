//PA04
//Ruyang Wang
//2020.3.5
public class RaceTrack{
	/**
	 * DO NOT REMOVE THIS - you should be using this to log this track's events in part B. For more see the assignment PDF / documentation for TrackLoggerA.java
	 */
	// private TrackLoggerA logger;

  RaceCar[] cars;
  FinishLine finishline;
  PitStop pitstop;
  int tick;
  int finish;
  public int score;
  public String carsData = "";

  public RaceTrack(){
    //Constructs a new racetrack object to hold the track components (cars, finishline, pitstop).
    // logger = new TrackLoggerA(); // DO NOT REMOVE THIS LINE
    this.finishline = new FinishLine();
    this.pitstop = new PitStop();
    this.tick = 0;
    this.finish = 1;
  }

  public void setCars(RaceCar[] raceCar){
    //Sets the cars that will participate in your race. No more than 10 cars will participate in a single race.
    this.cars = new RaceCar[raceCar.length];
    for(int i=0; i < raceCar.length; i++){
      cars[i] = raceCar[i];
    }
  }

  public void tick(){
    //This method runs one tick. A tick moves every car a set distance equal to its speed.
    //If a car is damaged and passes the 75 unit mark during this tick, it enters the pitstop.
    //Upon exiting the pitstop, it starts at the 75 unit mark and immediately moves according to its speed.
    tick++;
    System.out.print("Tick "+tick);
    for (int i = 0; i < cars.length; i++){
        cars[i].unit += cars[i].speed;
        if (cars[i].unit >= 100){
          cars[i].lap++;
          cars[i].unit -= 100;
        }
        
    }
    for (int i = 0; i < cars.length; i++){
      if ((cars[i].unit >= 75 || (cars[i].unit < 75 && cars[i].unit - cars[i].speed <= -25)) && cars[i].damaged == true){
          pitstop.enterPitStop(cars,i);
          if (cars[i].stop == 3){
            pitstop.exitPitStop(cars,i);
          }

      }
    }
    checkCollision();
    for (int i = 0; i < cars.length; i++) {
    	if (cars[i].unit == 75 && cars[i].damaged == true && cars[i].exist == true) {
    	//if the car is crashed at 75 unit, it enters the pitstop immediately
    	//with this if loop commented, the car will enter the pitstop in the next tick instead
    		pitstop.enterPitStop(cars,i);
    	}
    }
    System.out.println();
    for (int i = 0; i < cars.length; i++){
      if (cars[i].exist == true){ // if the car has not finished and is not in the pitstop
        System.out.println(cars[i].toString()+": "+cars[i].lap+" lap "+cars[i].unit+" unit "+cars[i].speed);//print out current speed and location for check
      }
    }
  }

  public void checkCollision(){
    //This method checks to see if any two cars are located at the same unit.
    //If it finds two cars on the same unit, that is considered a collision, and both cars become damaged.
    for (int i = 0; i < cars.length; i++){
      for (int j = i+1; j < cars.length; j++){
        if (cars[i].unit == cars[j].unit && cars[i].exist == true && cars[j].exist == true && (cars[i].damaged == false || cars[j].damaged == false)){
          if (cars[i].damaged == false){
            cars[i].speed -= cars[i].strength*5;
            cars[i].damaged = true;
            System.out.println();
            System.out.print(cars[i].toString()+" has been damaged.");
          }
          if (cars[j].damaged == false){
            cars[j].speed -= cars[j].strength*5;
            cars[j].damaged = true;
            System.out.println();
            System.out.print(cars[j].toString()+" has been damaged.");
          }
        }
      }
    }
  }

  public void run(){
    //This method processes all the ticks until the race ends.
    while (finishline.finished() == false){
      tick();
      for (int i = 0; i < cars.length; i++){
        if (cars[i].lap*100 + cars[i].unit >= 1000 && cars[i].exist == true){
          finishline.enterFinishLine(cars,i);
          System.out.println(cars[i].toString()+ " has finished the race in place " + finish);
          finish++;
        }
      }
    }
    System.out.println("You scored "+calculateScore(tick)+" points.");
    score = calculateScore(tick);
    for (int i = 0; i < cars.length; i++) {
    	int iplus = i+1;
    	carsData += iplus+": speed = "+ cars[i].inispeed+"; strength = "+cars[i].strength+"\n";
    }
  }

  public int calculateScore(int ticks){
    //This method returns the games score: 1000 base minus 20 per tick plus 150 per RaceCar entered in the race.
    //throw new UnsupportedOperationException("not implemented yet");
    return 1000-20*ticks+150*cars.length;
  }

  /**
	 * Needed for part B
	 * This method returns the logger instance used by this RaceTrack. You <b>SHOULD NOT</b> be using this method.
	 * @return logger with this track's events
	 */
	/*
	public TrackLoggerA getLogger() {
		return logger;
	}
	*/
  public void runSimu() {
	//This method processes all the ticks until the race ends.
	    while (finishline.finished() == false){
	      tickSimu();
	      for (int i = 0; i < cars.length; i++){
	        if (cars[i].lap*100 + cars[i].unit >= 1000 && cars[i].exist == true){
	          finishline.enterFinishLine(cars,i);
	          //System.out.println(cars[i].toString()+ " has finished the race in place " + finish);
	          finish++;
	        }
	      }
	    }
	    //System.out.println("You scored "+calculateScore(tick)+" points.");
	    score = calculateScore(tick);
	    for (int i = 0; i < cars.length; i++) {
	    	int iplus = i+1;
	    	carsData += iplus+": speed = "+ cars[i].inispeed+"; strength = "+cars[i].strength+"\n";
	    }
  }
  public void tickSimu(){
	    //This method runs one tick. A tick moves every car a set distance equal to its speed.
	    //If a car is damaged and passes the 75 unit mark during this tick, it enters the pitstop.
	    //Upon exiting the pitstop, it starts at the 75 unit mark and immediately moves according to its speed.
	    tick++;
	    //System.out.print("Tick "+tick);
	    for (int i = 0; i < cars.length; i++){
	        cars[i].unit += cars[i].speed;
	        if (cars[i].unit >= 100){
	          cars[i].lap++;
	          cars[i].unit -= 100;
	        }
	        
	    }
	    for (int i = 0; i < cars.length; i++){
	      if ((cars[i].unit >= 75 || (cars[i].unit < 75 && cars[i].unit - cars[i].speed <= -25)) && cars[i].damaged == true){
	          pitstop.enterPitStopSimu(cars,i);
	          if (cars[i].stop == 3){
	            pitstop.exitPitStopSimu(cars,i);
	          }

	      }
	    }
	    checkCollisionSimu();
	    for (int i = 0; i < cars.length; i++) {
	    	if (cars[i].unit == 75 && cars[i].damaged == true && cars[i].exist == true) {
	    	//if the car is crashed at 75 unit, it enters the pitstop immediately
	    	//with this if loop commented, the car will enter the pitstop in the next tick instead
	    		pitstop.enterPitStopSimu(cars,i);
	    	}
	    }
	    //System.out.println();
	    for (int i = 0; i < cars.length; i++){
	      if (cars[i].exist == true){ // if the car has not finished and is not in the pitstop
	        //System.out.println(cars[i].toString()+": "+cars[i].lap+" lap "+cars[i].unit+" unit "+cars[i].speed);//print out current speed and location for check
	      }
	    }
	  }
  public void checkCollisionSimu(){
	    //This method checks to see if any two cars are located at the same unit.
	    //If it finds two cars on the same unit, that is considered a collision, and both cars become damaged.
	    for (int i = 0; i < cars.length; i++){
	      for (int j = i+1; j < cars.length; j++){
	        if (cars[i].unit == cars[j].unit && cars[i].exist == true && cars[j].exist == true && (cars[i].damaged == false || cars[j].damaged == false)){
	          if (cars[i].damaged == false){
	            cars[i].speed -= cars[i].strength*5;
	            cars[i].damaged = true;
	            //System.out.println();
	            //System.out.print(cars[i].toString()+" has been damaged.");
	          }
	          if (cars[j].damaged == false){
	            cars[j].speed -= cars[j].strength*5;
	            cars[j].damaged = true;
	            //System.out.println();
	            //System.out.print(cars[j].toString()+" has been damaged.");
	          }
	        }
	      }
	    }
	  }
}
