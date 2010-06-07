/** First implementation of a UnaryOperator. This operator
    picks two point and removes that subtour and appends it to the
    end. For example, if A,B, and C are subtours of ABC,
    this modification will return ACB.

    This version picks two random cutpoints and splices on those.
*/


import java.awt.Color;
public class unaryRandomSplice extends UnaryOperator
{

  public TspTour modifyTour(TspTour oldTour)
  {

//get the size of the tours to be constructed
    int tourSize = oldTour.getTourSize();
//select random cutpoints
    int startPoint = (int)(Math.random()*tourSize), endPoint = (int)(Math.random()*tourSize);
//make sure that they are in proper order, otherwise this function is the identity
    if(startPoint>endPoint)
    {
        int swapTemp = endPoint;
        endPoint = startPoint;
        startPoint = endPoint;
    }
//create a new tour with as described above
    TspTour newTour = oldTour.getSubTour(startPoint, endPoint).annexTour(oldTour.getSubTour(0,startPoint).annexTour(oldTour.getSubTour(endPoint,oldTour.getTourSize())));
// return the
    return newTour;

  }
//constructor to set the attributes of this particular algortithm
  public unaryRandomSplice()
  {
    super.operatorColor = Color.green;
    super.operatorName = "   Random Splice";

  }

}
