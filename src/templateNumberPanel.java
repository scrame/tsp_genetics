
//Title:        Your Product Name
//Version:
//Copyright:    Copyright (c) 1999
//Author:       Liam Christopher
//Company:      illogiKal integrity
//Description:  Your description
/**

      templateNumberPanel-- a class that simply lets the user define
      how many templates of organisms to put in the pool, hitting configure
      creates a new individualTypeFrame with the number of instances equal to
      the number of panels in it, and hence the number of templates to be set


*/

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.event.*;

public class templateNumberPanel extends JPanel implements ActionListener
{
//the maximum number of templates to use
  final int MAXPANELS = 8;
//the minimum number of templates
  final int MINPANELS = 1;
//the default number
  final int DEFAULTTYPENUMBER = 1;
//strings for labels and actions
  String configureString = "Configure...";
  String templateString = "Number of individual types";
  String moreString = "More";
  String lessString = "Less";
//the buttons used in the panel
  JButton configureButton = new JButton(configureString);
  JButton moreButton = new JButton(moreString);
  JButton lessButton = new JButton(lessString);
  JLabel templateLabel = new JLabel(templateString);
  Integer templateNumber = new Integer(DEFAULTTYPENUMBER);
  JLabel numberLabel = new JLabel(templateNumber.toString());
//constructor: sets up buttons and registers actions
  public templateNumberPanel()
  {
    try
    {
//set layout
      jbInit();
//set up the more button
       moreButton.setActionCommand(moreString);
       moreButton.addActionListener(this);
       this.add(moreButton);
//set up the label showing the current number to set
       this.add(numberLabel);
//set up the less button
       lessButton.setActionCommand(lessString);
       lessButton.addActionListener(this);
       this.add(lessButton);
//set up the label to describe the purpose of the buttons
       this.add(templateLabel);
//set up hte configure button
       configureButton.setActionCommand(configureString);
       configureButton.addActionListener(this);
       this.add(configureButton);
    }
    catch(Exception ex)
    {
//let the user know if something goes wrong
      ex.printStackTrace();
    }
  }
//sets up the layout
  private void jbInit() throws Exception
  {
    this.setLayout(new FlowLayout());
  }

//find out what to do when the buttons are pushed
  public void actionPerformed(ActionEvent e)
  {
//if its configure, open a new individualTypeFrame
    if(e.getActionCommand() == configureString)
    {
       if(individualTypeFrame.getInstanceNumber() == 0)
       {
         new individualTypeFrame(templateNumber.intValue ());
       }
    }
//if more, increment the number of templates up to MAXPANELS
    else if(e.getActionCommand() == moreString)
    {
        if(templateNumber.intValue() < MAXPANELS)
        {
         int newNumber = templateNumber.intValue();
         newNumber++;
         templateNumber = new Integer(newNumber);
         numberLabel.setText((templateNumber.toString()));
         repaint();
        }
    }
//if less decrement down to MINPANELS
    else if(e.getActionCommand() == lessString)
    {
       if(templateNumber.intValue()>MINPANELS);
       {
         int newNumber = templateNumber.intValue();
         newNumber--;
         //for some reason the above condition isn't checking correctly, so I need this too.
         if(newNumber < MINPANELS)
         {
             newNumber = MINPANELS;
         }
         templateNumber = new Integer(newNumber);
         numberLabel.setText((templateNumber.toString()));
         repaint();
        }
    }

   }
}