package tspg.ui;

//Title:        Your Product Name
//Version:
//Copyright:    Copyright (c) 1999
//Author:       Liam Christopher
//Company:      illogiKal integrity
//Description:  Your description
/**
        fitnessDrawingFrame -- frame that manages the average and best tours and
        is responsible for the rendering in fitnessDrawingPanel.

*/


import java.awt.FlowLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

public class fitnessDrawingFrame extends JFrame
{
//constant that defines the size of the graph
  final int XYCONSTANT = 610;
//tourDrawingPanel used to render both sets of lengths
  fitnessDrawingPanel tdPanel;
//constructor takes the size of the two arrays, the bounds in relative coordinates of the panel
//and the arrays of tourlengths
  public fitnessDrawingFrame(int nGenerations, double averageLengths[], double bestLengths[])
  {
//set up window and set its name
      super("Average(Red) vs. Best(Yellow) Tours by Generation");
      try{
//register close box action, and hide window when it is clicked
         this.addWindowListener(new WindowAdapter() {
               public void windowClosing(WindowEvent e)
               {
                  setEnabled(false);
               }
               });
//create a new tourDrawinPanel with the information passed to it (this class holds no local copies
         tdPanel = new fitnessDrawingPanel((double)nGenerations, averageLengths, bestLengths);
//set the size of the frame
         setSize(XYCONSTANT,XYCONSTANT);
//set the layout of the frame
         getContentPane().setLayout(new FlowLayout());
//add the panel to the frame
         getContentPane().add(tdPanel);
//make it visible
         setVisible(true);
         setEnabled(true);
//repaint just for good measure
         tdPanel.repaint();

     }
//trap layout errors
      catch(Exception e)
      {
         e.printStackTrace();
      }
    }
 }
