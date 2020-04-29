public class RaceTrack {
	
	/**
	 * DO NOT REMOVE THIS - you should be using this to log this track's events in part B. For more see the assignment PDF / documentation for TrackLoggerA.java
	 */
	// private TrackLoggerA logger;
	public static Car[] cars;
	public int tick=0;
	public static int numCars;
	public static int ori_numCars;
	public static String out;
	public static String out_2="";
	public static boolean crashed;
	public int score;
	public String carsData = "";
	
	public RaceTrack() {
		// logger = new TrackLoggerA(); // DO NOT REMOVE THIS LINE
	}
	
	public void setCars(Car[] raceCars) { //cars
		cars=raceCars;
		ori_numCars=cars.length;
		numCars=ori_numCars;
		for(int i=0;i<numCars;i++){
			cars[i].number=i+1;
		}
	}
	
	public void tick(){ //for every tick, cars move, print cars logging and check collision, chekc if enterpitstop
		tick+=1;
		out="tick "+tick+":"; 
		out_2="";
		for(int i=0;i<numCars;i++) { ///move
			if(cars[i]!=null){
				cars[i].last_unit=cars[i].unit;
				cars[i].last_damaged=cars[i].damaged;
				cars[i].departfrompit=false;
				cars[i].unit+=cars[i].speed;	
			}
		}
		for(int i=0;i<numCars;i++) { //collision
			if(cars[i]!=null){
				checkCollision(i);
			}
		}
		for(int i=0;i<numCars;i++) { //pitstop
			if(cars[i]!=null){
				if(((cars[i].last_damaged&&cars[i].last_unit<=75&&cars[i].unit>=75)||(cars[i].last_damaged==false&&cars[i].damaged&&cars[i].unit==75))){
					PitStop.enterPitStop(i);
				}
			}
		}
		for(int i=0;i<numCars;i++){ //print
			if(cars[i]!=null){
				if(PitStop.turn[i]==0){
					out_2+="cars "+cars[i].number+" is at the pit stop. ";
				}
				else if(PitStop.turn[i]==2){
					out_2+="cars "+cars[i].number+" has entered the pit stop. ";
				}
				if(cars[i].unit>=100){
					cars[i].unit%=100;
					cars[i].lap+=1;
				}
				out+=" cars "+cars[i].number+" is at "+cars[i].lap+" lap "+cars[i].unit+" unit; ";	
				cars[i].last_unit=cars[i].unit;
				cars[i].last_damaged=cars[i].damaged;	
			}
		}		
		System.out.println(out);
		if(!out_2.equals("")){
			System.out.println(out_2);
		}
		checkfinish();
	}

	public static void checkCollision(int i){
		for(int j=0;j<numCars;j++){
			if(cars[j]!=null){
				if (i!=j&&cars[i].unit==cars[j].unit){
					crashed=(cars[i].damaged==false&&cars[i].departfrompit==false&&wentpit(j)==false&&cars[j].ifpit==false);
					if(crashed){ 
						//if car is not previously damaged & not depart from pit & car[j] does not enter pit during a tick & car[j] is not in pit
						out_2+="cars "+cars[i].number+" has been DAMAGED. ";
						cars[i].damaged=true;
						cars[i].speed-=cars[i].strength*5;
					}
				}
			}
		}
	}
	
	public void checkfinish(){
		for(int i=0;i<numCars;i++) {
			if(cars[i]!=null&&cars[i].lap>10){
				FinishLine.enterFinishLine(i);
				i--;
			}
		}
	}
	
	public static boolean wentpit(int i){
		if(cars[i].last_damaged==true&&cars[i].unit>=75&&cars[i].last_unit<=75){
			return true;
		}
		return false;
	}
	
	public void run(){
		for (int i = 0; i < cars.length; i++) {
	    	int iplus = i+1;
	    	carsData += iplus+": speed = "+ cars[i].ori_speed+"; strength = "+cars[i].strength+"\n";
	    }
		while(FinishLine.finished()==false){
			tick();
		}
	    
	}
	
	public void tickSimu(){ //for every tick, cars move, print cars logging and check collision, chekc if enterpitstop
		tick+=1;
		out="tick "+tick+":"; 
		out_2="";
		for(int i=0;i<numCars;i++) { ///move
			if(cars[i]!=null){
				cars[i].last_unit=cars[i].unit;
				cars[i].last_damaged=cars[i].damaged;
				cars[i].departfrompit=false;
				cars[i].unit+=cars[i].speed;	
			}
		}
		for(int i=0;i<numCars;i++) { //collision
			if(cars[i]!=null){
				checkCollision(i);
			}
		}
		for(int i=0;i<numCars;i++) { //pitstop
			if(cars[i]!=null){
				if(((cars[i].last_damaged&&cars[i].last_unit<=75&&cars[i].unit>=75)||(cars[i].last_damaged==false&&cars[i].damaged&&cars[i].unit==75))){
					PitStop.enterPitStop(i);
				}
			}
		}
		for(int i=0;i<numCars;i++){ //print
			if(cars[i]!=null){
				if(PitStop.turn[i]==0){
					out_2+="cars "+cars[i].number+" is at the pit stop. ";
				}
				else if(PitStop.turn[i]==2){
					out_2+="cars "+cars[i].number+" has entered the pit stop. ";
				}
				if(cars[i].unit>=100){
					cars[i].unit%=100;
					cars[i].lap+=1;
				}
				out+=" cars "+cars[i].number+" is at "+cars[i].lap+" lap "+cars[i].unit+" unit; ";	
				cars[i].last_unit=cars[i].unit;
				cars[i].last_damaged=cars[i].damaged;	
			}
		}		
		//System.out.println(out);
		if(!out_2.equals("")){
			//System.out.println(out_2);
		}
		checkfinishSimu();
	}
	
	public void checkfinishSimu(){
		for(int i=0;i<numCars;i++) {
			if(cars[i]!=null&&cars[i].lap>10){
				FinishLine.enterFinishLineSimu(i);
				//System.out.println("You scored "+calculateScore()+" points.");
				i--;
			}
		}
	}

	
	public void runSimu(){
		for (int i = 0; i < cars.length; i++) {
	    	int iplus = i+1;
	    	carsData += iplus+": speed = "+ cars[i].ori_speed+"; strength = "+cars[i].strength+"\n";
	    }
		while(FinishLine.finishedSimu()==false){
			tickSimu();
		}
	    
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
}
