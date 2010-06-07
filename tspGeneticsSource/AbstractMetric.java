
//Title:        Your Product Name
//Version:      
//Copyright:    Copyright (c) 1999
//Author:       Liam Christopher
//Company:      illogiKal integrity
//Description:  Your description
/**AbstractMetric is an abstract class that contains the requirements of a metric
the abstract method should be overloaded to return the value of the distance
between the two cities in a way that:
D = calculateDistance()
 a) D(x,y) == D(y,x)
 b) D(x,x) == 0
 c) D(x,z) <= D(x,z)+D(z,y)
 d) D(0,x) == x

These are the mathematical propeties of a metric, and if this is not a metric,
Then the results of the program are meaningless.
*/
import java.io.Serializable;
abstract public class AbstractMetric implements Serializable
{
//The name of the metric
  final static public String metricName = "default name";
//The abstract method to be overloaded by the above requirements
  abstract public double calculateDistance(TspCity c1, TspCity c2);
//Method to return the name of the metric
  final static public String getMetricName()
  {
    return metricName;
  }
//Constructor...
  public AbstractMetric()
  {
  }
} 