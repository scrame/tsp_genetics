package tspg.operators.unary;

//Title:        Your Product Name
//Version:
//Copyright:    Copyright (c) 1999
//Author:       Liam Christopher
//Company:      illogiKal integrity
//Description:  Your description
/** UnaryTypes
    a simple class that contains an instance of each of the unary operators to
    be used in the program. It holds the number internally, and uses the modulus
    of that number and returns the corresponding operator, so any number up to
    the maximum size of an integer can be passed and it will return a valid
    operator.

    Note: if you wish to expand the capabilities of this program by adding a new
    unary operator, all you have to do is add it to the bottom of this list,
    and increment NUMBEROFUNARYOPERATORS, the program will implement it
    automatically.

*/

public class UnaryTypes
{


  final int NUMBEROFBINARYOPERATORS = 6;
  UnaryOperator operatorList[];

  public UnaryOperator getUnaryOperator(int operatorID)
  {
        return operatorList[operatorID%NUMBEROFBINARYOPERATORS];
  }

  public UnaryTypes()
  {
        operatorList = new UnaryOperator[NUMBEROFBINARYOPERATORS];
        operatorList[0] = new unaryIdentity();
        operatorList[1] = new unaryInversion();
        operatorList[2] = new unarySplice();
        operatorList[3] = new unaryRandomInversion();
        operatorList[4] = new unaryRandomSplice();
        operatorList[5] = new unaryRandomSwap();
  }

}
