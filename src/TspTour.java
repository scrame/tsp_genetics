
//Title:        Your Product Name
//Version:
//Copyright:    Copyright (c) 1999
//Author:       Liam Christopher
//Company:      illogiKal integrity
//Description:  Your description
/** TspTour.java
   this is the structure for the tours that are a component of every organism
   and is in some senses, the heart of the program. Data is represented by a
   array whose size is passed to it in the constructor, if no argument is passed,
   the program exits (an empty tour should not be created).

   The TspTour class has the following methods:
   private:
   bool checkPosition(int CityPosition)
      returns true if CityPosition < TourLength
   public:
   void swapCities(int first, int second)
      swaps the position of two cities in the array
   int getCity(int CityPos)
      returns the ID of the city at CityPos
   int getTourSize()
      returns the length of the tour.
   TspTour annexTour(TspTour tourChunk)
      returns a new tour that is the concatenation of the called tour
      followed by tourChunk.
   TspTour getSubTour(int start,int end)
      returns a tour containing a subtour bounded by start and end
      note: the function returns a reverse tour if start > end.

   TspTour also has the following constructors:
   TspTour()
      prints an error message and exits the program.
   TspTour(int tourLength)
      creates an empty tour of size tourLength.
   TspTour(int tourLength, int[] tourData)
      creates a tour of size tourLength, and assigns it tourData.
*/


import java.lang.Math;
public class TspTour {

//Constant to determine the number of swaps when randomizing tour
  final int RANDOMTOURSEED = 100;
//Data and paired access functions
//the length of the tour
  private int tourSize;
//returns the length of the tour
  public int getTourSize()
  {
   return tourSize;
  }
//the array of ints that represent each cities position
  private int tourData[];
//access function that returns the ID of the city at a given index.
  public int getCity(int cityPos)
  {
//check to see if cityPos is valid
    if(checkPosition(cityPos)!=true)
    {
//scream and die...
      System.err.println("City num too high");
      System.exit(0);
    }
//return with data if valid
      return tourData[cityPos];
  }

//method used to exchange 2 cities in a list
  public void swapCities(int cityPos1,int cityPos2)
  {
   if((checkPosition(cityPos1) == true) && (checkPosition(cityPos1) == true))
   {
      int swapTemp = tourData[cityPos1];
      tourData[cityPos1] = tourData[cityPos2];
      tourData[cityPos2] = swapTemp;
    }
    else
    {
      System.err.println("City num too high");
    }

  }


//  function that returns the subtour bounded by the 2 cities,
// function returns the subtour in reverse if citypos1>citypos2
  public TspTour getSubTour(int cityPos1,int cityPos2)
  {
//Data to be returned...
    TspTour subTour;
//Check if both positions are valid and unequal
   if(!((checkPosition(cityPos1) == true) && (checkPosition(cityPos2) == true)))
   {
     System.err.println("Error! Subtour errors out of range or equal");
//     System.exit(0);
   }


//sets the size of the subtour based on the endpoints of subTour
      int subTourSize = Math.abs(cityPos1 - cityPos2);
//allocates data
      int subTourData[] = new int[subTourSize];
//initiallize index used to track the index of the new Tour.
      int subTourIndex = 0;
//case where the indices are equal, returns a one element subtour
   if(cityPos1 == cityPos2)
   {

       return new TspTour(0,null);
   }

//if true the subtour will be forward with respect to the original
   else   if(cityPos1 < cityPos2)
      {
//loop between the endpoints
        for(int i=cityPos1; i< cityPos2; i++)
        {
//set the corresponding element in the new tour
          subTourData[subTourIndex] = tourData[i];
//increment index to keep up with i.
          subTourIndex++;
        }
      }
//if true, subtour will be reversed with respect to original
      else if(cityPos1 > cityPos2)
      {
//loop between endpoints
        for(int i=cityPos1-1; i>= cityPos2; i--)
        {
//set the corresponding element in the new tour.
          subTourData[subTourIndex] = tourData[i];
//increment index.
          subTourIndex++;
        }
      }
//create a new tour.
      subTour = new TspTour(subTourSize, subTourData);


  return subTour;
  }
//private function that simply checks if a given city is within bounds.
  private boolean checkPosition(int cityPosition)
  {
    return (cityPosition <= tourSize);
  }

//constructor, creates random tour of size initialSize
  public TspTour(int initialSize)
  {
    tourSize = initialSize;
    tourData = new int[tourSize];
    makeRandomTour();
  }
//constructor, creates a tour of size initialSize and fills it with initialData
  public TspTour(int initialSize, int initialData[])
  {
    tourSize = initialSize;
    tourData = initialData;


  }
//function to randomize a tour
  public void makeRandomTour()
  {
     final int nSwaps = RANDOMTOURSEED;
     int pos1,pos2;

     makeOrderedTour();
     for(int i=0;i<nSwaps;i++)
     {
        pos1 =(int) (Math.random()*tourSize);
        pos2 =(int) (Math.random()*tourSize);
        swapCities(pos1,pos2);
     }
  }
//function to fill a tour with each elements value being its index number
  public void makeOrderedTour()
  {
   for(int i=0;i<tourSize;i++)
   {
    tourData[i] = i;
   }
  }
//dumps a tour to stdout
  public void printTour()
  {
         for(int i=0;i<tourSize;i++)
         {
          System.out.print(getCity(i)+" ");
         }
         System.out.println();

  }
//creates a new tour with tourchunk added to the end of this tour
  public TspTour annexTour(TspTour tourChunk)
  {
    int newTourSize = tourSize + tourChunk.getTourSize();
    int newData[] = new int[newTourSize];
    for(int i=0;i<tourSize;i++)
    {
       newData[i] = tourData[i];
    }
    for(int i=0; i<tourChunk.getTourSize();i++)
    {
      newData[i+tourSize] = tourChunk.getCity(i);
    }
    return new TspTour(newTourSize, newData);
  }

//function to compare to tours
  public boolean compareTours(TspTour otherTour)
  {
      if(tourSize!=otherTour.getTourSize())
      {
          System.err.println("ERROR! Tours of differing Size");
      }
      for(int i=0;i<tourSize;i++)
      {
          if(tourData[i] != otherTour.getCity(i))
          {
              return false;
          }
      }
      return true;
  }


}