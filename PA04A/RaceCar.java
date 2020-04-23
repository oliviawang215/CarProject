//PA04
//Ruyang Wang
//2020.3.5
public class RaceCar{
  int speed;
  int strength;
  int lap;
  int unit;
  boolean damaged;
  boolean exist;
  int stop;
  int inispeed;
  public RaceCar(int speed, int strength){
    //sets this RaceCar's speed to any value between 55 and 30,and strength to any value between 2 and 4.
    //If one or both of the values is above the maximum bound,  set the value to the maximum bound.
    //If one or both of the input values are below the minimum bound, set the value to the minimum bound.
    if (speed > 55){
      speed = 55;
    } else if (speed < 30){
      speed = 30;
    }
    if (strength > 4){
      strength = 4;
    } else if (strength < 2){
      strength = 2;
    }
    this.speed = speed;
    this.strength = strength;
    this.lap = 0;
    this.unit = 0;
    this.damaged = false;
    this.exist = true;
    this.stop = 0;
    this.inispeed = speed;
  }

  public RaceCar(){
    //The generic constructor that sets this RaceCar's speed to 40 and strength to 3
    this.speed = 40;
    this.strength = 3;
    this.lap = 0;
    this.unit = 0;
    this.damaged = false;
    this.exist = true;
    this.stop = 0;
    this.inispeed = 40;
  }

  public int getLocation(){
    //This method returns the car's current unit location---30th unit of lap 3 is at location 330.
    //throw new UnsupportedOperationException("not implemented yet");
    return 100*lap + unit;
  }

  public String toString(){
    //This method returns a string in the form "TypeOfCarSpeed/Strength"
    //throw new UnsupportedOperationException("not implemented yet");
    return "RaceCar"+inispeed+"/"+strength;
  }
}
