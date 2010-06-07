
//Title:        Your Product Name
//Version:
//Copyright:    Copyright (c) 1999
//Author:       Liam Christopher
//Company:      illogiKal integrity
//Description:  Your description
/**
     bioSimDrawingPanel is a class that contains the method for drawing the state
     of the biological simulation into a bioSimMap. The drawing is accomplished by
     reading each organisms coordinates and drawing a box based on the colors registered
     in the algorithms.


*/

import java.awt.*;
import javax.swing.*;

public class bioSimDrawingPanel extends JPanel
{
//constants used to determine the size of the organisms when drawn
// generally, YOFFSET should be half of XOFFSET because the organism is split in
// half.
  final int XOFFSET = 10;
  final int YOFFSET = 5;
//data used to draw each organisms
  TspOrganism organismsToRender[];
//the number of organisms to draw
  int renderingIndex;
  public bioSimDrawingPanel(TspOrganism drawingOrganisms[], int numberOfOrganisms)
  {
    try
    {
//set the layout of this panel
      jbInit();
//assign the organisms to be rendered
      organismsToRender = drawingOrganisms;
//assign the size of organismsToRender
      renderingIndex = numberOfOrganisms;
//make the background look purty
      setBackground(Color.black);
//let us use the panel
      setEnabled(true);
//let us see the panel
      setVisible(true);
    }
//just in case something went wrong with the layout
    catch(Exception ex)
    {
      ex.printStackTrace();
    }
  }
//method that draws an individual organism, note that it must be passed a graphics
//object which makes it unsuitable to be called from anywhere other than paintComponent
  private void drawOrganism(Graphics g, TspOrganism drawingOrganism)
  {
//sets the color for the top half of the organisms
      g.setColor(drawingOrganism.getUnary().getOperatorColor());
//draws the top half
      g.fillRect(drawingOrganism.getX(),drawingOrganism.getY(),XOFFSET,YOFFSET);
//sets the color for the bottom half of the organisms
      g.setColor(drawingOrganism.getBinary().getOperatorColor());
//draws the bottom half
      g.fillRect(drawingOrganism.getX(),drawingOrganism.getY()+YOFFSET,XOFFSET,YOFFSET);

  }
//access function that allows the environment to change
  public void setOrganisms(TspOrganism drawingOrganisms[],int numberOfOrganisms)
  {
          organismsToRender = drawingOrganisms;
          renderingIndex = numberOfOrganisms;
  }
//overloaded method to draw the actual environment
  public void paintComponent(Graphics g)
  {
//make sure everything is normal
      super.paintComponent(g);
//loop through the organisms and draw each one
      for(int i=0; i<renderingIndex;i++)
      {
           drawOrganism(g,organismsToRender[i]);
      }

  }
//simple function to separate layout from the constructor
  private void jbInit() throws Exception
  {
    this.setLayout(new FlowLayout());
  }
}