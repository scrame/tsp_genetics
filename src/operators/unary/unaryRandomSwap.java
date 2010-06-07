/**
    RandomSplice -- 
*/


import java.awt.Color;
public class unaryRandomSwap extends UnaryOperator
{

  public TspTour modifyTour(TspTour oldTour)
  {

//get the size of the tours to be constructed
    int tourSize = oldTour.getTourSize();
//select random cutpoints
    int startPoint = (int)(Math.random()*tourSize), endPoint = (int)(Math.random()*tourSize);
//create a new tour with as described above
    oldTour.swapCities(startPoint,endPoint);
// return the
    return oldTour;

  }
//constructor to set the attributes of this particular algortithm
  public unaryRandomSwap()
  {
    super.operatorColor = Color.yellow;
    super.operatorName = "   Random Swap";

  }

}