package tspg.operators.binary;

//Title:        Your Product Name
//Version:
//Copyright:    Copyright (c) 1999
//Author:       Liam Christopher
//Company:      illogiKal integrity
//Description:  Your description
/** binaryCycleCrossOver
    breeding algorithm that takes two tours and makes a child by creating Precedence
    matrices and performing a logical intersection on the two, although this is technically
    more diffficult than some of the other binary operators, the majority of the work
    is done in the PrecedenceMatrix class, this simply calls and returns the intersection
    of the two...

*/


import java.awt.Color;

import tspg.core.TspTour;
public class binaryIntersection extends BinaryOperator
{
//data for constructing a new tour
  int childTourData[];

//overloaded method to do the actual computation
  public TspTour modifyTour(TspTour oldTour, TspTour otherTour)
  {
//create Precedence matrices of both parents
        PrecedenceMatrix tourOne = new PrecedenceMatrix(oldTour);
        PrecedenceMatrix tourTwo = new PrecedenceMatrix(otherTour);
//return the intersection of the two
        return tourOne.matrixIntersection(tourTwo).getTour();
  }
//simple function to determine if an element has been placed in the child tour yet
  public boolean inChildTour(int cityID, int tourSize)
  {
       for(int i=0;i<tourSize;i++)
       {
           if(childTourData[i] == cityID)
           {
               return true;
           }
       }
       return false;

  }
//constructor: sets properties of the algorithm
  public binaryIntersection()
  {
    super.operatorColor = Color.pink;
    super.operatorName = "Matrix Intersection";

  }
}


