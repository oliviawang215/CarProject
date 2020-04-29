
public class FormulaOne extends RaceCar{
	
	public FormulaOne(int strength, int speed) {
		if(speed>70){
			ori_speed=70;
		}
		else if(speed<30){
			ori_speed=30;
		}
		else{
			this.ori_speed=speed;
		}
		this.speed=ori_speed;
		if(strength>5){
			strength=5;
		}
		else if(strength<3){
			strength=3;
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
	
	public String toString() {
		return "FormulaOne" + ori_speed+"/"+strength;
	}
}