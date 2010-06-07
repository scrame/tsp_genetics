
//Title:        Your Product Name
//Version:
//Copyright:    Copyright (c) 1999
//Author:       Liam Christopher
//Company:      illogiKal integrity
//Description:  Your description
/**
         bioSimMap -- class that holds the representation of the biological
         simulation as well as being responsible for having it drawn. Contains
         a bioSimDrawingFrame
*/

import java.awt.*;
import javax.swing.*;

public class bioSimMap
{
//constant for the default
  final int XYCONSTANT = 300;
//for drawing the current state of the biological simulation
  bioSimDrawingPanel dPanel;
//data passed by the environment
  TspOrganism mapData[];
//size of mapData
  int numberOfIndividuals;
//initialize the size of the windows to constant
  int mapX = XYCONSTANT;
  int mapY = XYCONSTANT;
//metric used with the breeding radius to determine survivors
  AbstractMetric mapMetric;
//distance to another organism must be less than breeding radius to mate
  double breedingRadius;
//constructor intializes above dataTypes
  public bioSimMap(TspOrganism drawingOrganisms[],int newNumberOfIndividuals, int newX, int newY, double newBreedingRadius)
  {
       numberOfIndividuals = newNumberOfIndividuals;
       mapData = drawingOrganisms;
       mapX = newX;
       mapY = newY;
       breedingRadius = newBreedingRadius;
       mapMetric = new euclideanMetric();

  }
//method that calculates the distance between two organisms using the set metric
  public boolean checkDistance(TspOrganism o1, TspOrganism o2)
  {
      return (mapMetric.calculateDistance(new TspCity(o1.getX(),o1.getY()),new TspCity(o2.getX(),o2.getY()))<breedingRadius);
  }
//returns organism located at index
  public TspOrganism getMapData(int index)
  {
      return mapData[index];
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
