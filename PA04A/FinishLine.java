//PA04
//Ruyang Wang
//2020.3.5
public class FinishLine{

  int total;
  int counter;

  public void enterFinishLine(RaceCar[] cars, int n){
    //This method places a car into the FinishLine - removing it from the race.
    cars[n].speed = 0;
    cars[n].exist = false;
    total = cars.length;
    counter++;
  }

  public boolean finished(){
    //This method returns true when all cars entered in the race are now in the FinishLine
    //throw new UnsupportedOperationException("not implemented yet");
    if (counter == total && counter != 0){
      return true;
    } else {
      return false;
    }
  }
}
