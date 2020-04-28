
public abstract class Car {
	
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

		
	public int getLocation() {
		return lap*100+unit;
	}
	
	public boolean IfDamage(){
		return damaged;
	}

}
