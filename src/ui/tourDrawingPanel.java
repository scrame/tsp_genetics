
//Title:        Your Product Name
//Version:
//Copyright:    Copyright (c) 1999
//Author:       Liam Christopher
//Company:      illogiKal integrity
//Description:  Your description
/**

        tourDrawingPanel -- a panel for use with tourDrawingFrame, handles the
        rendering of a single tour, which is then shuffled to create an animation

*/
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;

import javax.swing.JPanel;

public class tourDrawingPanel extends JPanel
{
//the size of the city points to draw
  final int CIRCLERADIUS = 5;

//the tour that will be drawn
  TspTour tourToRender;
//constructor that sets the size of the panel
  public tourDrawingPanel()
  {
    try
    {
//sets the layout
      jbInit();
//sets the rendering tour to a random tour
      tourToRender = new TspTour(SystemSettings.getMap().getNCities());
//sets the size of the panel
      setPreferredSize(new Dimension(SystemSettings.getMap().getMapWidth(),SystemSettings.getMap().getMapHeight()));
    }
    catch(Exception ex)
    {
//if there is an error with the layout, let the user know
      ex.printStackTrace();
    }

  }
//constructor that lets you specify the tour to draw
  public tourDrawingPanel(TspTour drawingTour)
  {
    try
    {
      jbInit();
      tourToRender = drawingTour;
      setPreferredSize(new Dimension(SystemSettings.getMap().getMapWidth(),SystemSettings.getMap().getMapHeight()));
      setEnabled(true);
      setVisible(true);
      repaint();
    }
    catch(Exception ex)
    {
      ex.printStackTrace();
    }
  }
//allows you to set the tour to render
  public void setRenderingTour(TspTour drawingTour)
  {
       tourToRender = drawingTour;
       repaint();
  }
//overloaded painting function that is responsible for the actual rendering of
//the tour
  public void paintComponent(Graphics g)
  {
//make sure the panel is working
        super.paintComponent(g);
//set the size of the panel
        setPreferredSize(new Dimension(SystemSettings.getMap().getMapWidth(),SystemSettings.getMap().getMapHeight()));
//set the color to draw
        g.setColor(Color.black);
        for(int i=0;i<SystemSettings.getMap().getNCities();i++)
        {
//mark the spots of all the cities
             g.fillOval(SystemSettings.getMap().getMapData(i).getX(),SystemSettings.getMap().getMapData(i).getY(),CIRCLERADIUS,CIRCLERADIUS);
        }
        drawTour(g,tourToRender);

  }
//method that draws every city in a tour
  public void drawTour(Graphics g,TspTour drawingTour)
  {
//loop through every city in the tour, and connect each one with a line
        for(int j=0;j<drawingTour.getTourSize()-1;j++)
        {
             g.drawLine(SystemSettings.getMap().getMapData(drawingTour.getCity(j)).getX(),SystemSettings.getMap().getMapData(drawingTour.getCity(j)).getY(),SystemSettings.getMap().getMapData(drawingTour.getCity(j+1)).getX(),SystemSettings.getMap().getMapData(drawingTour.getCity(j+1)).getY());
        }
        g.drawLine(SystemSettings.getMap().getMapData(drawingTour.getCity(drawingTour.getTourSize()-1)).getX(),SystemSettings.getMap().getMapData(drawingTour.getCity(drawingTour.getTourSize()-1)).getY(),SystemSettings.getMap().getMapData(drawingTour.getCity(0)).getX(),SystemSettings.getMap().getMapData(drawingTour.getCity(0)).getY());

  }
//sets the layout
  private void jbInit() throws Exception
  {
    this.setLayout(new FlowLayout());
  }
}