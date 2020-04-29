public class FinishLine {
	public static Car[] rank=new Car[RaceTrack.numCars];
	static int temp=0;
	
	public static void enterFinishLine(int i) { // TODO you can add parameters here!
		rank[temp]=RaceTrack.cars[i];
		System.out.println(RaceTrack.cars[i]+" has finished the race in place "+(temp+1));
//		RaceTrack.cars[i]=RaceTrack.cars[RaceTrack.numCars-1]; //remove car
//		RaceTrack.cars[RaceTrack.numCars-1]=null;
//		RaceTrack.numCars--;
//		temp+=1;
		RaceTrack.cars[i]=null;
	}
	
	public static boolean finished() {
		for(int j=0;j<RaceTrack.numCars;j++){
			if (RaceTrack.cars[j]!=null) {
				return false;
			}
		}
		String out="GAME FINISHED\n"; //QAQQQQQ
		for (int i=0; i<out.length();i++) {
			System.out.print(out.charAt(i));
			try {
				Thread.sleep(15);
			} catch (InterruptedException e) {
				e.printStackTrace();
			} 
		}
		return true;
	}
	
	public static void enterFinishLineSimu(int i) { // TODO you can add parameters here!
		rank[temp]=RaceTrack.cars[i];
		//System.out.println(RaceTrack.cars[i]+" has finished the race in place "+(temp+1));
//		RaceTrack.cars[i]=RaceTrack.cars[RaceTrack.numCars-1]; //remove car
//		RaceTrack.cars[RaceTrack.numCars-1]=null;
//		RaceTrack.numCars--;
//		temp+=1;
		RaceTrack.cars[i]=null;
	}
	
	public static boolean finishedSimu() {
		for(int j=0;j<RaceTrack.numCars;j++){
			if (RaceTrack.cars[j]!=null) {
				return false;
			}
		}
		return true;
	}
}

