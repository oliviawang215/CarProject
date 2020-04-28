
public class RaceCar extends Car {
	
	public RaceCar(int speed, int strength) { 
		if(speed>55){
			ori_speed=55;
		}
		else if(speed<30){
			ori_speed=30;
		}
		else{
			this.ori_speed=speed;
		}
		this.speed=ori_speed;
		if(strength>4){
			strength=4;
		}
		else if(strength<2){
			strength=2;
		}
		this.strength=strength;
		this.lap=1;
		this.unit=0;
		this.last_unit=0;
		this.last_damaged=false;
		this.damaged=false;
		this.ifpit=false;
		this.departfrompit=false;
	}
	
	public RaceCar() {
		this(40,3);
	}
	
	public String toString() {
		return "RaceCar"+ori_speed+"/"+strength;
	}
	
}
