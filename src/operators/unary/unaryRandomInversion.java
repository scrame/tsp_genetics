
//Title:        Your Product Name
//Version:
//Copyright:    Copyright (c) 1999
//Author:       Liam Christopher
//Company:      illogiKal integrity
//Description:  Your description

/**
    Cuts out a section defined by two cutpoints, and reverses the middle section
    For example, if A,B, and C are subtours of ABC,
    and B' is the string in reverse, then
    this modification will return AB'C.

    Note that this implementation selects cutpoints randomly

*/


import java.awt.Color;
public class unaryRandomInversion extends UnaryOperator
{

  public TspTour modifyTour(TspTour oldTour)
  {
//get the size of the tour to operate on
    int tourSize = oldTour.getTourSize();
//select the cutpoints
    int startPoint = (int)(Math.random()*tourSize), endPoint = (int)(Math.random()*tourSize);
//make sure that they are in proper order, otherwise this function is the identity
    if(startPoint>endPoint)
    {
        int swapTemp = endPoint;
        endPoint = startPoint;
        startPoint = endPoint;
    }
//construct new tour from the using the process described above
    TspTour newTour = oldTour.getSubTour(0, startPoint).annexTour(oldTour.getSubTour(endPoint,startPoint).annexTour(oldTour.getSubTour(endPoint,oldTour.getTourSize())));

    return newTour;

  }
//constructor to set the properties of this algorithm
  public unaryRandomInversion()
  {
    super.operatorColor = Color.darkGray;
    super.operatorName = "Random inversion";

  }
}
