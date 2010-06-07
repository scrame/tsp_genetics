
//Title:        Your Product Name
//Version:
//Copyright:    Copyright (c) 1999
//Author:       Liam Christopher
//Company:      illogiKal integrity
//Description:  Your description
/**
        individualTypeFrame -- class that is responsible for setting the organism
        templates in the system settings. depending on the number passed ot it it will
        make up to MAXPANELS individualTypePanels, which each represent an organisms
        template, it also manages the number of each template that is put into the
        environment.


*/


import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.event.*;

public class individualTypeFrame extends JFrame implements ActionListener
{
//constant is the most individualTypePanels to create
  final int MAXPANELS = 8;
//to ensure that only one set of organisms get set up
  static int instanceNumber = 0;
//array of individualType panels, initialized in constructor
  individualTypePanel itPanel[];
//the size of itPanel[]
  int itpNumber;
//String used for the setbutton's text and actionlistener
  String setString = "Set Types...";
//initializes the set button
  JButton setButton = new JButton(setString);
//constructor, takes and integer and creates a frame with that many individualTypePanel's
  public individualTypeFrame(int nPanels)
  {
//make everything right and good
    super("Configure Organism Templates");
//catch errors caused by the layout
    try
    {
//just in case an inappropriate number is passed
      if(nPanels < MAXPANELS)
      {
        itpNumber = nPanels;
      }
      else
      {
        itpNumber = MAXPANELS;
      }
//set the layout for this frame
      layoutInit();
//register a window listener for the close box
      this.addWindowListener(new WindowAdapter() {
               public void windowClosing(WindowEvent e)
               {
//decrement instance number
                  dispose();
//hide window
                  setEnabled(false);
                  setVisible(false);
               }
               });
      itPanel = new individualTypePanel[itpNumber];
      for(int i=0;i<itpNumber;i++)
      {
         itPanel[i] = new individualTypePanel(i);

         this.getContentPane().add(itPanel[i]);
      }
 //register the setbutton
      setButton.addActionListener(this);
      setButton.setActionCommand(setString);
//add the set button
      this.getContentPane().add(setButton);
//show the window
      pack();
      setVisible(true);
//register this window as a new instance
      instanceNumber++;

    }
//just in case...
    catch(Exception e)
    {
      e.printStackTrace();
    }
  }
//returns the numbe rof individualTypeFrames currently open
  public static int getInstanceNumber()
  {
      return instanceNumber;
  }
//sets the layout for this frame
  void layoutInit() throws Exception
  {
     getContentPane().setLayout(new GridLayout(itpNumber+1,1));
  }
//method that handles what happens when the set button is pushed
    public void actionPerformed(ActionEvent e)
  {
//check to see that it was the setbutton
     if(e.getActionCommand() == setString)
     {
//create a set of templates
        TspOrganism activeOrganisms[] = new TspOrganism[itpNumber];
//create a corresponding array that keeps track of how many of each template go into the
//environment
        int numberOfOrganisms[] = new int[itpNumber];
//loop through each individualTypePanel and save its current template
        for(int i=0;i<itpNumber;i++)
        {
//set the new template to each corresponding element of the individualTypeFrame
            activeOrganisms[i] = itPanel[i].getOrganism();
//since environmental breeding is done in pairs, then the total number of organisms
//must be even. This selection only passes even numbers into the system settings.
            if((itPanel[i].getNumberOfOrganisms()%2)==0)
            {
                numberOfOrganisms[i] = itPanel[i].getNumberOfOrganisms();
            }
            else
            {
                numberOfOrganisms[i] = itPanel[i].getNumberOfOrganisms()+1;
            }
        }
//register the organisms types and their respective number's in the systemsettings
        SystemSettings.setOrganisms(itpNumber,activeOrganisms,numberOfOrganisms);
     }
  }
//method used to decrement the number of instances when a window is closed
  public void dispose()
  {
     instanceNumber--;
  }
}