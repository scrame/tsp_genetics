package tspg.operators.binary;

/**
  TSP Genetics:
  this is the abstract class for a unary operator, ie an operator that works on
  one tour and returns a modified tour, it also has a few elements that will
  make it identifiable in the GUI

  the constructor should set the name and a color unique to that algorithm.

*/



import java.awt.Color;

import tspg.core.TspTour;

abstract public class BinaryOperator {

  //to be overloaded with the actual unary algorithm
  abstract public TspTour modifyTour(TspTour parent1, TspTour parent2);

  //Color to be drawn in bioSim mode to indicate individual type
  Color operatorColor;


  //Access method for the operator color,
  //note that it is only to be set by the constructor
  final public Color getOperatorColor()
  {
    return operatorColor;
  }

  //String that holds the name of the algorithm
  String operatorName;

  //Access function for the operator name
  final public String getOperatorName()
  {
    return operatorName;
  }

  //abstract method used to set the pattern and name of the algorithm
 public BinaryOperator() {

  }

}