
public class FormulaOne extends RaceCar{
	public int speed;
	public int ori_speed;
	public int last_unit;
	public int unit;
	public boolean damaged;
	public boolean last_damaged;
	public int strength;
	public int lap;
	public int number;
	public boolean ifpit;
	public boolean departfrompit;
	
	public FormulaOne(int strength, int speed) {
		if (speed > 70) {
			this.speed = 70;
		} else if (speed < 30) {
			this.speed = 30;
		} else {
			this.speed = speed;
		}
		if (strength < 3) {
			this.strength = 3;
		} else if (strength > 5) {
			this.strength = 5;
		} else {
			this.strength = strength;
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
	
	public FormulaOne() {
		super(50,4);
	}
	
	public int getLocation() {
		return lap*100+unit;
	}
	
	public String toString() {
		return "FormulaOne" + ori_speed+"/"+strength;
	}
}