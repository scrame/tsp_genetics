package tspg.operators.binary;


//Title:        Your Product Name
//Version:
//Copyright:    Copyright (c) 1999
//Author:       Liam Christopher
//Company:      illogiKal integrity
//Description:  Your description
/** binaryOrderCrossOver is a binary algorithm that creates a new tour from two
    other tours by picking two random cutpoints, copying the symbols from the
    first parent until the first cutpoint, copying nonrepeating symbols from the
    second parent until the second cutpoint, then filling in the remaining
    spaces with the first parents nonrepeating pieces.

*/


import java.awt.Color;

import tspg.core.TspTour;
public class binaryOrderCrossover extends BinaryOperator
{
//temporary data used to construct a new tour
     int childTourData[];


  public TspTour modifyTour(TspTour oldTour, TspTour otherTour)
  {
//Assumes tour sizes are the same
//finds the size of tour to create the child...
           int newTourSize = oldTour.getTourSize();
//selects random cutpoints for the crossover
           int crossOverPoint1 = (int)(Math.random() * newTourSize);
           int crossOverPoint2 = (int)(Math.random() * newTourSize);
//swaps the cutpoints if they are mismatched (the first cutpoint should be lower
           if(crossOverPoint2 < crossOverPoint1)
           {
               int swapTemp = crossOverPoint1;
               crossOverPoint1 = crossOverPoint2;
               crossOverPoint2 = swapTemp;
           }

//initializes data for the new tour
           childTourData = new int[newTourSize];
           int childIndex = 0;
//loop to do the actual crossing over, this works as a two part conditional,
//the if case is if it is copying from the second tour, and the default case
//(else) is for copying from the first tour, which encompasses two parts of the
//algorithm.
//for each element in the new array,
           for(int i=0;i<newTourSize;i++)
           {
//if copying from the second parent
               if((i>crossOverPoint1)&&(i<crossOverPoint2))
               {
//if the current element of the second parent is not currently in the tour,
                   if(inChildTour(otherTour.getCity(i),childIndex) == false)
                   {
//add it to the new data and increment the new data index
                        childTourData[childIndex++] = otherTour.getCity(i);
                   }
               }

//if it is currently copying from the first tour
               else
               {
//if the current element of the first parent is not currently in the tour,
                   if(inChildTour(oldTour.getCity(i),childIndex) == false)
                   {
                        childTourData[childIndex++] = oldTour.getCity(i);
                   }

               }

           }
//if any spaces are left over, fill them up from the second tour...
           for(int i=childIndex;i<newTourSize;i++)
           {
                for(int j=0;j<newTourSize;j++)
                {
                   if(inChildTour(otherTour.getCity(j),childIndex) == false)
                   {
                        childTourData[childIndex++] = otherTour.getCity(j);
                   }
                   if(inChildTour(otherTour.getCity(j),childIndex) == false)
                         break;
                }
           }

//create a new tour with the data, and return it
           return new TspTour(childIndex, childTourData);
  }
//returns whether or not the element passed is already in the data
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
//Constructor: sets the properties of this algorithm
  public binaryOrderCrossover()
  {
    super.operatorName = "    Order Crossover";
    super.operatorColor = Color.darkGray;

  }

}