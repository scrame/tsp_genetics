package tspg.operators.binary;


//Title:        Your Product Name
//Version:      
//Copyright:    Copyright (c) 1999
//Author:       Liam Christopher
//Company:      illogiKal integrity
//Description:  Your description
/** BinaryTypes
    a simple class that contains an instance of each of the binary operators to
    be used in the program. It holds the number internally, and uses the modulus
    of that number and returns the corresponding operator, so any number up to
    the maximum size of an integer can be passed and it will return a valid
    operator.

    Note: if you wish to expand the capabilities of this program by adding a new
    binary operator, all you have to do is add it to the bottom of this list,
    and increment NUMBEROFBINARYOPERATORS, the program will implement it
    automatically.

*/

public class BinaryTypes
{

  final int NUMBEROFBINARYOPERATORS = 6;
  BinaryOperator operatorList[];

  public BinaryOperator getBinaryOperator(int operatorID)
  {
        return operatorList[(operatorID%NUMBEROFBINARYOPERATORS)];
  }

  public BinaryTypes()
  {
        operatorList = new BinaryOperator[NUMBEROFBINARYOPERATORS];
        operatorList[0] = new binaryIdentity();
        operatorList[1] = new binaryOrderCrossover();
        operatorList[2] = new binaryEdgeRecombination();
        operatorList[3] = new binaryUnion();
        operatorList[4] = new binaryIntersection();
        operatorList[5] = new binaryMatrixXOR();
  }
}
