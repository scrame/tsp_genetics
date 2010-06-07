package tspg.core;


//Title:        Your Product Name
//Version:
//Copyright:    Copyright (c) 1999
//Author:       Liam Christopher
//Company:      illogiKal integrity
//Description:  Your description
/**

         TspMap -- a class that contains the information about the actual problem
         this contains the coordinates of every city, as well as a metric used to
         measure the distance between cities.

*/
import java.io.Serializable;

import tspg.metrics.AbstractMetric;
import tspg.metrics.EuclideanMetric;
public class TspMap implements Serializable
{
//default size of the map
  final int XYCONSTANT = 300;
//list of all the cities
  TspCity mapData[];
//the size of mapData
  int numberOfCities;
//the size of the map
  int mapX;
  int mapY;
//the metric to determine the distance between two cities
  AbstractMetric mapMetric;

//constructor that creates a random map of size n
  public TspMap(int nCities)
  {
    numberOfCities = nCities;
    mapMetric = new EuclideanMetric();
    initMap();
  }
//same as above, but lets the metric be defined as well
  public TspMap(int nCities, AbstractMetric newMetric)
  {
    numberOfCities = nCities;
    mapMetric = newMetric;
    initMap();
  }
//functions common to both constructors: sets the size of the map and
//randomizes the city locations
  public void initMap()
  {
    mapX = XYCONSTANT;
    mapY = XYCONSTANT;
    makeRandomMap();

  }
//constructor that lets you set the number of cites and size of the map
  public TspMap(int nCities, int newX, int newY)
  {
       numberOfCities = nCities;
       mapMetric = new EuclideanMetric();
       mapX = newX;
       mapY = newY;
       makeRandomMap();
  }
//constructor to create a map if all the data is specified
  public TspMap(int nCities, int newX, int newY, TspCity cityData[])
  {
       numberOfCities = nCities;
       mapX = newX;
       mapY = newY;
       mapData = cityData;
  }
//returns city at (index)
  public TspCity getMapData(int index)
  {
      return mapData[index];
  }
//returns the size of the problem (this is where it is stored for the program
//any function wanting to find the number of cities should access the current
//map through SystemSettings.getMap
  public int getNCities()
  {
      return numberOfCities;
  }
//function to calculate the length of a tour
  public double calculateTour(TspTour readTour)
  {
    double distanceAccumulator = 0;
    for(int i=0;i<numberOfCities;i++)
    {
      distanceAccumulator += mapMetric.calculateDistance(mapData[readTour.getCity(i)],mapData[readTour.getCity((i+1)%numberOfCities)]);
    }
    return distanceAccumulator;
  }
//function to randomize a map based on its current settings
  public void makeRandomMap()
  {
    mapData = new TspCity[numberOfCities];
    for(int i=0; i<numberOfCities;i++)
    {
//set each city to a random coordinate
       mapData[i] = new TspCity((int)(Math.random()*mapX),(int)(Math.random()*mapY));
    }
  }
//access functions for the size of the map
  public int getMapWidth()
  {
      return mapX;
  }
  public int getMapHeight()
  {
      return mapY;
  }
}
