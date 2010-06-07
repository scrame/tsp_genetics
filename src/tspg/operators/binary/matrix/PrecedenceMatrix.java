package tspg.operators.binary;

import tspg.core.TspTour;

//Title:        TSP Genetics
//Version:
//Copyright:    Copyright (c) 1999
//Author:       Liam Christopher
//Company:      illogiKal integrity
//Description:  Project for my senior project: TSP Genetics
//
/**
                PrecedenceMatrix -- a class used with two of the binary algorithms:
                binaryUnion and binaryIdentity creates a predecessor matrix of a
                TspTour. A predecessor matrix is constructed by setting up a
                boolean matrix, a true is placed in spot x,y iff y comes after x
                in the tour. This requirement gives way to three criteria for being
                a predecessor matrix
                1) element x,x = 0 for all x
                2) (i,j == true) and (j,k==true) => (i,k == true)
                3) the number of ones is exactly n(n-1)/2

                using these constraints it is possible to make a set closure algorithm
                that makes a valid tour out of an underconstrained matrix
*/
public class PrecedenceMatrix
{
//data for the matrix
   boolean matrixData[][];
//the length and width of the matrix (it is always square)
   int matrixSize;
//constructor: takes a tour and generates a matrix from it
 public PrecedenceMatrix(TspTour workingTour)
 {
//initialize the matrix to the proper size
        matrixSize = workingTour.getTourSize();
        matrixData = new boolean[matrixSize][matrixSize];
//go through every element in a tour
        for(int i=0;i<matrixSize-1;i++)
        {
//loop through every number after i
            for(int j=i+1;j<matrixSize;j++)
            {
//any element after city[i] is set to true for the row city[i]
                 matrixData[workingTour.getCity(i)][workingTour.getCity(j)] = true;
            }
        }
//after this is set, guarantee condition 1 to avoid cycles
        for(int i=0;i<matrixSize;i++)
        {
            matrixData[i][i] = false;
        }
 }
//constructor that simply sets the data
 public PrecedenceMatrix(int tourSize, boolean tourData[][])
 {
       matrixData = tourData;
       matrixSize = tourSize;
 }
//method that simultaneously prints a tour, but also makes sure it is a valid
//tour by enforcing the constraints and making sure every city is accounted for
 public TspTour getTour()
 {
 //used to construct the new tour
       int tourData[] = new int[matrixSize];
 //keeps track of the numbers that have been used
       boolean isUsed[] = new boolean[matrixSize];
 //to determine position number in the tour
       int rowSum;
 //for constrant #3
       int trueCount = 0;
       for(int i=0;i<matrixSize;i++)
       {
 //since 0 is a valid cityID, then make -1 mean unused
           tourData[i] = -1;
//since nothing has been used yet
           isUsed[i] = false;
       }
//for eachelement in the array
       for(int i=0;i<matrixSize;i++)
       {
           rowSum = 0;
           for(int j=0;j<matrixSize;j++)
           {
               if(matrixData[j][i] == true)
               {
//increment both variables
                   rowSum++;
                   trueCount++;
               }
           }
//rowSum is the position of i in the tour
           tourData[rowSum] = i;
       }
//verify constraint 2
       int id1,id2,id3;
       for(id1=0;id1<matrixSize;id1++)
       {
           for(id2=0;id2<matrixSize;id2++)
           {
                if(matrixData[id1][id2] == true)
                {
                     for(id3=0;id3<matrixSize;id3++)
                     {
                         if(matrixData[id2][id3] == true)
                         {
                              matrixData[id1][id3] = true;
                         }
                     }
                }
           }
//verify constraint 1
           matrixData[id1][id1] = false;
       }
//see which numbers are not yet accounted for
       for(int i=0;i<matrixSize;i++)
       {
           if(tourData[i] != -1)
           {
               isUsed[tourData[i]] = true;
           }
       }
//flag assure each number only gets paired up once
       boolean isFound = false;
       for(int i=0;i<matrixSize;i++)
       {
//there is a corresponding spot in tourData
           if(isUsed[i] == false)
           {
               isFound = false;
               for(int j=0;j<matrixSize;j++)
               {
                   if((tourData[j] == -1)&&(isFound == false))
                   {
//find the corresponding element and set it
                       tourData[j] = i;
                       isUsed[i] = true;
                       isFound = true;
                   }
               }
           }
       }
//once everything is accounted for, then return a new tour
       return new TspTour(matrixSize,tourData);
 }
//access function to get the individual elements of the matrix
 public boolean getData(int row, int col)
 {
       return matrixData[row][col];
 }
 //returns an underconstrained union of two Precedence matrices, getTour shapes it up
 public PrecedenceMatrix matrixUnion(PrecedenceMatrix orMatrix)
 {
       boolean targetMatrix[][] = new boolean[matrixSize][matrixSize];
       for(int i=0;i<matrixSize;i++)
       {
           for(int j=0;j<matrixSize;j++)
           {
 //creates a new matrix and sets it to the or of every corrseponding element in the other matrices
                targetMatrix[i][j] = (matrixData[i][j] || orMatrix.getData(i,j));
           }
       }
       return new PrecedenceMatrix(matrixSize, targetMatrix);

 }
 //returnrs an underconstrained intersection of two Precedence matrices
 public PrecedenceMatrix matrixIntersection(PrecedenceMatrix andMatrix)
 {
       boolean targetMatrix[][] = new boolean[matrixSize][matrixSize];
       for(int i=0;i<matrixSize;i++)
       {
           for(int j=0;j<matrixSize;j++)
           {
 //creates new data and sets every element to the and of the corresponding elements
                targetMatrix[i][j] = (matrixData[i][j] && andMatrix.getData(i,j));
           }
       }
       return new PrecedenceMatrix(matrixSize, targetMatrix);
 }
 //returnrs an underconstrained intersection of two Precedence matrices
 public PrecedenceMatrix matrixXOR(PrecedenceMatrix xorMatrix)
 {
       boolean targetMatrix[][] = new boolean[matrixSize][matrixSize];
       for(int i=0;i<matrixSize;i++)
       {
           for(int j=0;j<matrixSize;j++)
           {
 //creates new data and sets every element to the and of the corresponding elements
                targetMatrix[i][j] = (matrixData[i][j] & xorMatrix.getData(i,j));
           }
       }
       return new PrecedenceMatrix(matrixSize, targetMatrix);
 }
}
