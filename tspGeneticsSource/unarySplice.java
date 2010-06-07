/** First implementation of a UnaryOperator. This operator
    picks two point and removes that subtour and appends it to the
    end. For example, if A,B, and C are subtours of ABC,
    this modification will return ACB.

    This particular version of splice divedes the tour into thirds and splices
    as above. For a less predictable version of splice see RandomSplice
*/


import java.awt.Color;
public class unarySplice extends UnaryOperator
{

  public TspTour modifyTour(TspTour oldTour)
  {
//set the size of the new tour
    int tourSize = oldTour.getTourSize();
//select the cutpoints to be 1/3 of a tour
    int startPoint = tourSize/3, endPoint = 2*(tourSize/3);
//construct new tou by appending the chunk removed to the end of the existing tour
    TspTour newTour = oldTour.getSubTour(startPoint, endPoint).annexTour(oldTour.getSubTour(0,startPoint).annexTour(oldTour.getSubTour(endPoint,oldTour.getTourSize())));

    return newTour;

  }
//constructor to set the properties of htis algortihm
  public unarySplice()
  {
    super.operatorColor = Color.blue;
    super.operatorName = "          Splice";

  }

}
