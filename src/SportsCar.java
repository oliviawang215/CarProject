
public class SportsCar extends RaceCar {

	public SportsCar(int speed, int strength) {
		if(speed>45){
			ori_speed=45;
		}
		else if(speed<20){
			ori_speed=20;
		}
		else{
			this.ori_speed=speed;
		}
		this.speed=ori_speed;
		if(strength>3){
			strength=3;
		}
		else if(strength<1){
			strength=1;
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
	
	public SportsCar() {
		this(30,2);
	}
	public String toString() {
		return "FormulaOne" + speed+"/"+strength;
	}
}
