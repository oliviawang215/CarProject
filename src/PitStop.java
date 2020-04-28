public class PitStop {
	static int[] turn=new int[]{1,1,1,1,1,1,1,1,1,1};
	public static void enterPitStop(int i) { // TODO you can add parameters here!
		RaceTrack.cars[i].unit=75;
		RaceTrack.cars[i].speed=0;
		RaceTrack.cars[i].ifpit=true;
		turn[i]=(turn[i]+1)%3; //%n:stop n ticks
		if(turn[i]==1){ //recover after three turns
			RaceTrack.out_2+=("cars "+RaceTrack.cars[i].number+" has EXITED the pit stop. ");
			RaceTrack.cars[i].speed=RaceTrack.cars[i].ori_speed;
			RaceTrack.cars[i].damaged=false;
			RaceTrack.cars[i].ifpit=false;
			RaceTrack.cars[i].departfrompit=true;
		}
	}
}
