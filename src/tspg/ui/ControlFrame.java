
//Title:        TSP Genetics
//Version:      
//Copyright:    Copyright (c) 1999
//Author:       Liam Christopher
//Company:      illogiKal integrity
//Description:  Project for my senior project: TSP Genetics
/**
    This class is the main window of the program. It contains all the settings
    that let the simulation run, and it is largely the extent of the user input
    settings from the controlFrame should be reflected in a systemSettings class
    and that should create instances of the various subclasses
*/

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;

public class ControlFrame extends JFrame implements ActionListener
{
//strings for labeling and registering...
  final String goString = "GO!";
//the various panels that make up a control frame
  final templateNumberPanel otsPanel = new templateNumberPanel();
  final systemButtonPanel sbPanel = new systemButtonPanel();
  final generationNumberPanel gnPanel = new generationNumberPanel();
//button that starts the simulation
  final JButton goButton = new JButton(goString);

//intialize static data so different components can communicate
  SystemSettings mySet = new SystemSettings();
//constructor
  public ControlFrame()
  {
//sets up window and sets name
    super("TSP Genetics Main Control");
       try  {
//sets layout and traps errors
      jbInit();
     }

    catch(Exception ex) {
      ex.printStackTrace();
    }
//registers the close box
    this.addWindowListener(new WindowAdapter() {
               public void windowClosing(WindowEvent e)
               {
                  System.exit(0);
               }
               });
//adds the component panels
    this.getContentPane().add(otsPanel);
    this.getContentPane().add(gnPanel);
    this.getContentPane().add(sbPanel);
//registers the go button and adds it to the frame
    goButton.setActionCommand(goString);
    goButton.addActionListener(this);
    this.getContentPane().add(goButton);
    this.setResizable(false);
//set the position of the control frame on the screen
    this.setLocation(200,100);
//show the frame
    this.pack();
    this.setVisible(true);
  }
//sets the layout
  private void jbInit() throws Exception
  {
    this.getContentPane().setLayout(new GridLayout(4,1));
    return;
  }
//function called if the go button is pressed, starts the actual simulation
  public void actionPerformed(ActionEvent e)
  {
//creates a new environment and simulates it for the given number of generations
      if(e.getActionCommand() == goString)
      {
          new TspEnvironment(gnPanel.getNGenerations());
      }

  }

}