
//Title:        Your Product Name
//Version:
//Copyright:    Copyright (c) 1999
//Author:       Liam Christopher
//Company:      illogiKal integrity
//Description:  Your description
/**

       TspOrganism -- this is the class that defines the way that the tours will
       be modified.
       Technically, an organism is really only a tour, a unary (learning) function
       and a binary (reproductive) function. These are set in the individual
       type panels, and then stored in a class of organism that is populated
       when the simulation runs.

*/


public class TspOrganism
{
// the tour carried by the organism
 TspTour dnaData;
//the length of the tour in the map
 double fitnessRating;
//the unary operator to be used on the tour
 UnaryOperator unaryOperator;
//the binary operator to be used on the tour
 BinaryOperator binaryOperator;

//constructor that sets the properties of this organism, note coordinates are
//automatically picked.
  public TspOrganism(TspTour newData, UnaryOperator unary, BinaryOperator binary)
  {
   dnaData = newData;
   unaryOperator = unary;
   binaryOperator = binary;
  }
//function used to see if it is the same (sans tours) as another organism
  public boolean compareAlgorithms(TspOrganism testOrg)
  {
     return ((getUnary().getOperatorName()==testOrg.getUnary().getOperatorName())&&(getBinary().getOperatorName()==testOrg.getBinary().getOperatorName()));
  }
//access function for the type of organism, constructs a name based on the combination
//of operators
  public String getName()
  {
      return new String(unaryOperator.getOperatorName()+"/"+binaryOperator.getOperatorName());
  }
//access function for the tour being held by this organism
  public TspTour getDNA()
  {
         return dnaData;
  }
//access functions for the operators
  public UnaryOperator getUnary()
  {
    return unaryOperator;
  }
  public BinaryOperator getBinary()
  {
    return binaryOperator;
  }
//function to mutate the tour being held in this organism
  public TspTour unaryModify()
  {
         return unaryOperator.modifyTour(dnaData);
  }
//function to breed its tour with the tour being passed to it.
  public TspTour binaryModify(TspTour mate)
  {
         return binaryOperator.modifyTour(dnaData,mate);
  }
//access function to set the length of the tour after it has been run through the map
  public void setFitnessRating(double newRating)
  {
      fitnessRating = newRating;
  }
//access funciton to return the length of its tour
  public double getFitnessRating()
  {
      return fitnessRating;
  }
}
