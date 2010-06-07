
//Title:        Your Product Name
//Version:
//Copyright:    Copyright (c) 1999
//Author:       Liam Christopher
//Company:      illogiKal integrity
//Description:  Your description
/** First implementation of a BinaryOperator. This is simply the
    identity function (it returns the first parent it is passed). But this
    serves as the template for the other binary functions.

*/


import java.awt.Color;
public class binaryIdentity extends BinaryOperator
{

//overloaded tour that simply returns the first tour in its argument list.
  public TspTour modifyTour(TspTour oldTour, TspTour otherTour)
  {
           return oldTour;
  }
  public binaryIdentity()
  {
//Sets the properties of this operator
    super.operatorColor = Color.white;
    super.operatorName = "    Binary Identity";

  }

}
