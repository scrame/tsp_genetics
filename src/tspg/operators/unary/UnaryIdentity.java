package tspg.operators.unary;


//Title:        Your Product Name
//Version:
//Copyright:    Copyright (c) 1999
//Author:       Liam Christopher
//Company:      illogiKal integrity
//Description:  Your description
/** First implementation of a UnaryOperator. This is simply the
    identity function (it returns what it is passed). But this
    serves as the template for the other unary functions.

*/


import java.awt.Color;

import tspg.core.TspTour;
public class unaryIdentity extends UnaryOperator
{

  public TspTour modifyTour(TspTour oldTour)
  {
//return the tour with no modifications
           return oldTour;
  }
  public unaryIdentity()
  {
    super.operatorColor = Color.white;
    super.operatorName = "  Unary Identity";

  }
}
