
//Title:        Your Product Name
//Version:
//Copyright:    Copyright (c) 1999
//Author:       Liam Christopher
//Company:      illogiKal integrity
//Description:  Your description
/** binaryCycleCrossOver
    breeding algorithm that takes two tours and makes a child by picking

    NOT COMPLETED!!!!!
*/


import java.awt.Color;
public class binaryPartialCrossover extends BinaryOperator
{
    int childTourData[];


  public TspTour modifyTour(TspTour oldTour, TspTour otherTour)
  {

             return new TspTour();
  }
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
  public binaryPartialCrossover()
  {
    super.operatorColor = Color.green;
    super.operatorName = "  Partial crossover";

  }

  public static void main(String args[])
  {
    TspTour myTour = new TspTour(20);
    TspTour myTour2 = new TspTour(20);
    myTour.makeRandomTour();
    myTour2.makeRandomTour();
    System.out.println("Random Tour 1: ");
    myTour.printTour();
    System.out.println("Random Tour 2: ");
    myTour2.printTour();
    System.out.println("ModifiedTour: ");
    new binaryPartialCrossover().modifyTour(myTour, myTour2).printTour();

  }
}