
//Title:        Your Product Name
//Version:
//Copyright:    Copyright (c) 1999
//Author:       Liam Christopher
//Company:      illogiKal integrity
//Description:  Your description

/**
    Cuts out a section defined by two cutpoints, and reverses the middle section
    For example, if A,B, and C are subtours of ABC,
    and B' is the reversal of B
    this modification will return AB'C.

    Note that the cutpoints here are always in the same place, 1/3 of the way
    through, for less predictable behavior, see RandomInversion

*/


import java.awt.Color;
public class unaryInversion extends UnaryOperator
{

  public TspTour modifyTour(TspTour oldTour)
  {
//find the size of the new tour to be constructed
    int tourSize = oldTour.getTourSize();
//divide the tour into thirds
    int startPoint = tourSize/3, endPoint = 2*(tourSize/3);
//construct a new tour using the above rules
    TspTour newTour = oldTour.getSubTour(0, startPoint).annexTour(oldTour.getSubTour(endPoint,startPoint).annexTour(oldTour.getSubTour(endPoint,oldTour.getTourSize())));
//return the new tour
    return newTour;

  }
//constructor that sets the properties of this algorithm
  public unaryInversion()
  {
    super.operatorColor = Color.cyan;
    super.operatorName = "       Inversion";

  }       
}
