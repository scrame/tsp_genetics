package tspg.ui;

//Title:        Your Product Name
//Version:
//Copyright:    Copyright (c) 1999
//Author:       Liam Christopher
//Company:      illogiKal integrity
//Description:  Your description
/**

            fitnessDrawingPanel -- contained in fitnessDrawingFrame. handles the
            rendering routines for the average and best fitness from each simulation

*/


import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;

import javax.swing.JPanel;

public class fitnessDrawingPanel extends JPanel
{
//constant for the size of the panel, note that for a small number of generations, this will
//look a bit grainy
  final int XYCONSTANT = 600;
//storage for data passed to it from fitnessDrawingFrame
  double numberOfGenerations;
  double minFitness=0;
  double averageFitness[];
  double bestFitness[];
//constructor, parameters match fitnessDrawingFrame's
  public fitnessDrawingPanel(double yMax,double averageData[],double bestData[])
  {
    try
    {
//set the layout
      jbInit();
//set the record for each parameter passed to it
      numberOfGenerations = yMax;
      averageFitness = averageData;
      bestFitness = bestData;
//set the size of the panel (so we can see it)
      setPreferredSize(new Dimension(XYCONSTANT,XYCONSTANT));
//show the panel
      setEnabled(true);
      setVisible(true);
    }
//trap layout errors
    catch(Exception ex)
    {
      ex.printStackTrace();
    }
  }
//method to return absolute translation of relative coordinates
  public int findXCoords(double xVal)
  {
//pixelOffset is the scalar translation from a variable coordinate system to pixel coords
       double pixelOffset = XYCONSTANT/numberOfGenerations;
       if(pixelOffset == 0)
       {
            pixelOffset = 1;
       }
//find real coordinates by multiplying desired value by the offset
       return (int) (xVal*pixelOffset);
  }
//method to return absolute translation of relative coordinates
  public int findYCoords(double yVal)
  {
//pixelOffset is the scalar translation from a variable coordinate system to pixel coords
       double pixelOffset = XYCONSTANT/averageFitness[0];
//find real coordinates by multiplying desired value by the offset
       return (int) (XYCONSTANT - (yVal*pixelOffset));
  }
//meat of the rendering
  public void paintComponent(Graphics g)
  {
//make sure the default stuff is there
        super.paintComponent(g);
//make the background more appealing
        setBackground(Color.black);
//set the color to represent the average fitness
        g.setColor(Color.red);
        for(int i=0;i<(int)numberOfGenerations-1;i++)
        {
//draw a point at the pixel coordinates for the number of generations done and the fitness
//at that generation
            g.drawLine(findXCoords(i),findYCoords(averageFitness[i]),findXCoords((i+1)%(int)numberOfGenerations),findYCoords(averageFitness[(i+1)%(int)numberOfGenerations]));
        }
//set the color for the best fitness
        g.setColor(Color.yellow);
        for(int i=0;i<(int)numberOfGenerations-1;i++)
        {
//draw a point at the pixel coordinates for the number of generations done and the fitness
//at that generation
            g.drawLine(findXCoords(i),findYCoords(bestFitness[i]),findXCoords((i+1)),findYCoords(bestFitness[(i+1)]));
        }

  }
//sets the layout
  private void jbInit() throws Exception
  {
    this.setLayout(new FlowLayout());
  }
}