package tspg.ui;


//Title:        Your Product Name
//Version:
//Copyright:    Copyright (c) 1999
//Author:       Liam Christopher
//Company:      illogiKal integrity
//Description:  Your description
/**

        tourDrawingFrame -- the container for a tourDrawingPanel, manages the
        animation of the best tours and the thread information

*/


import java.awt.FlowLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

import tspg.core.SystemSettings;
import tspg.core.TspTour;

public class tourDrawingFrame extends JFrame implements Runnable
{
//the denominator is the actual fps, but time is in milliseconds, hence the 1000
  final long framesPerSecond = 1000/10;
//the array of the best tours to draw
  TspTour renderingTours[];
//the size of renderingTours
  int renderingIndex;
//thread to make animation possible
  Thread frameThread;
//the panel to draw
  tourDrawingPanel tdPanel = new tourDrawingPanel();
//constructor that sets the tours that need to be drawn
  public tourDrawingFrame(int tourIndex,TspTour bestTours[])
  {
//set the title of the window
      super("Best Tour Progression");
//set the tours to draw
      renderingTours = bestTours;
      renderingIndex = tourIndex;
      try{
//set the size of the frame
         setSize(SystemSettings.getMap().getMapWidth(),SystemSettings.getMap().getMapHeight());
//set the layout
         getContentPane().setLayout(new FlowLayout());
//get the thread started
         start();
//register an action for the closebox
         this.addWindowListener(new WindowAdapter() {
               public void windowClosing(WindowEvent e)
               {
                  stop();
                  setEnabled(false);
               }
               });

         }
      catch(Exception e)
      {
//let the user know if there is an error
         e.printStackTrace();
      }
  }
//create a new thread
  public void start()
  {
      if(frameThread == null)
      {
           frameThread = new Thread(this);
      }
      frameThread.start();
  }
//stop the thread from drawing
  public void stop()
  {
      frameThread = null;
  }
//what to do while the thread is running
  public void run()
  {
//for use in sleeping, find out when the thread started
      long startTime = System.currentTimeMillis();
//if this thread is running, do this stuff
      while(Thread.currentThread() == frameThread)
      {
         try{

              for(int i=0;i<renderingIndex;i++)
              {
 //sleep to keep in sync with the frames per second
                 Thread.sleep(Math.max(0,startTime-System.currentTimeMillis()));
//draw the cuurentTour
                 drawTour(renderingTours[i]);
//increment the time to sleep until
                 startTime+=framesPerSecond;

              }
//stop drawing after the whole thread is done
              stop();

         }
         catch(InterruptedException e)
         {
//let the user know if anything went wrong
           e.printStackTrace();
         }
      }
  }
//function that displays a new tourDrawingPanel containing drawingTour
  public void drawTour(TspTour drawingTour)
  {
      tdPanel.setVisible(false);
      tdPanel.setEnabled(false);

      tdPanel = new tourDrawingPanel(drawingTour);
      getContentPane().add(tdPanel);
      tdPanel.setVisible(true);
      tdPanel.setEnabled(true);
      setVisible(true);
      setEnabled(true);
      tdPanel.repaint();
  }
}
