package tspg.metrics;

import tspg.core.TspCity;


//Title:        Your Product Name
//Version:      
//Copyright:    Copyright (c) 1999
//Author:       Liam Christopher
//Company:      illogiKal integrity
//Description:  Your description
/**

       So far the only implementation of AbstractMetric, returns the standard
       euclidean distance between two points on a cartesian plane

*/

public class EuclideanMetric extends AbstractMetric
{
//sets the properties of this metric
  final static String metricName = "euclidean";
//overloaded method that does the actual computation
  public double calculateDistance(TspCity city1, TspCity city2)
  {
    double xVal = city1.getX() - city2.getX();
    double yVal = city1.getY() - city2.getY();
    double xVal2 = xVal*xVal;
    double yVal2 = yVal*yVal;
    return (Math.sqrt((xVal2+yVal2)));
  }
//constructor
  public EuclideanMetric()
  {
  }
}
