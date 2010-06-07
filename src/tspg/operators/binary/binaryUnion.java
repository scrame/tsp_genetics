//Title:        Your Product Name
//Version:
//Copyright:    Copyright (c) 1999
//Author:       Liam Christopher
//Company:      illogiKal integrity
//Description:  Your description
/** binaryCycleCrossOver
    breeding algorithm that takes two tours and makes a child by creating precedence
    matrices and performing a logical union on the two, although this is technically
    more diffficult than some of the other binary operators, the majority of the work
    is done in the precedenceMatrix class, this simply calls and returns the union
    of the two...

*/


import java.awt.Color;
public class binaryUnion extends BinaryOperator
{
//data for constructing a new tour
  int childTourData[];

//overloaded method to do the actual computation
  public TspTour modifyTour(TspTour oldTour, TspTour otherTour)
  {
//create precedence matrices of both parents
        precedenceMatrix tourOne = new precedenceMatrix(oldTour);
        precedenceMatrix tourTwo = new precedenceMatrix(otherTour);
//return the union of the two
        return tourOne.matrixUnion(tourTwo).getTour();
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
  public binaryUnion()
  {
    super.operatorColor = Color.pink;
    super.operatorName = "Matrix Union";

  }
}


