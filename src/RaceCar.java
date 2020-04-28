
public class RaceCar {
	
	public int speed;
	public final int ori_speed;
	public int last_unit;
	public int unit;
	public boolean damaged;
	public boolean last_damaged;
	public int strength;
	public int lap;
	public int number;
	public boolean ifpit;
	public boolean departfrompit;

	
	public RaceCar(int speed, int strength) { //car parameter
		if(speed>55){
			ori_speed=55;
		}
		else if(speed<1){
			ori_speed=1;
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
	
	public int getLocation() {
		return lap*100+unit;
	}
	
	public String toString() {
		return "RaceCar"+ori_speed+"/"+strength;
	}
	
	public boolean IfDamage(){
		return damaged;
	}
}
