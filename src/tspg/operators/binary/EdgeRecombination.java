package tspg.operators.binary;


//Title:        Your Product Name
//Version:
//Copyright:    Copyright (c) 1999
//Author:       Liam Christopher
//Company:      illogiKal integrity
//Description:  Your description
/** binaryCycleCrossOver
    breeding algorithm that takes two tours and makes a child by constructing an
    edge map from the two tours. An edge map holds all four edges of a given point
    found in both tours. Once this map is constructed, it picks a seed and then
    adds successive cities by referencing the edge map for the given city and randomly
    picking one of the four neighbors.

*/


import java.awt.Color;

import tspg.core.TspTour;
public class binaryEdgeRecombination extends BinaryOperator
{

//constant used to declare the edge matrix
    final int POSSIBLENEIGHBORS = 4;
//array used to hold the final data
    int childTourData[];
//matrix used to represent the edge map
    int edgeMap[][];

//overloaded function to provide the actual modification
  public TspTour modifyTour(TspTour oldTour, TspTour otherTour)
  {

//determines the size of the new tour as well as the index for any loops
        int tourSize = oldTour.getTourSize();
//allocates space for the new tour
        childTourData = new int[tourSize];
//allocates space for the edgemap
        edgeMap = new int[tourSize][POSSIBLENEIGHBORS];

  //build the edge map...
        for(int i=0;i<tourSize;i++)
        {
//special case: if the city is in position 0, then it needs to take the edge of
//the last element in the tour
             if(i==0)
             {
//wraps around by choosing tourSize-1
                 edgeMap[oldTour.getCity(i)][0] = oldTour.getCity(tourSize-1);
             }
//default case, edges are selected by neighbors in tour
             else
             {
                 edgeMap[oldTour.getCity(i)][0] = oldTour.getCity(i-1);
             }
//modulus function for the successor neighbor precludes need for two cases
             edgeMap[oldTour.getCity(i)][1] = oldTour.getCity((i+1)%tourSize);
//second loop, finds matching element in the second tour and repeats the process
             for(int j=0;j<tourSize;j++)
             {
                  if(oldTour.getCity(i) == otherTour.getCity(j))
                  {
                      if(j==0)
                      {
                          edgeMap[otherTour.getCity(i)][2] = otherTour.getCity(tourSize-1);
                      }
                      else
                      {
                          edgeMap[otherTour.getCity(i)][2] = otherTour.getCity(j-1);
                      }

                      edgeMap[otherTour.getCity(j)][3] = otherTour.getCity((j+1)%tourSize);
                  }
             }
        }
//build a new tour
        int childIndex;
//randomly select a seed to construct a new tour with
        childIndex = (int)(Math.random()*tourSize);
//set the first element to this seed
        childTourData[0] = childIndex;
//loop through the tour, picking random neighbors from either tour as a successor
        for(int i=1;i<tourSize;i++)
        {
           do{
                 childIndex = edgeMap[childTourData[i-1]][(int)(Math.random()%POSSIBLENEIGHBORS)];
           }while((inChildTour(childIndex,i)));

           childTourData[i] = childIndex;
        }
        return new TspTour(tourSize, childTourData);
  }
//simple comparison function which tells if the cityID has been placed in the child
//tour yet.
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
//constructor: sets the operators Color and name for use with tspGenetics
  public binaryEdgeRecombination()
  {
    super.operatorColor = Color.magenta;
    super.operatorName = " Edge Recombination";

  }
}
