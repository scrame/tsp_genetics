
//Title:        TSP Genetics
//Version:
//Copyright:    Copyright (c) 1999
//Author:       Liam Christopher
//Company:      illogiKal integrity
//Description:  Project for my senior project: TSP Genetics
//
/**
        mapControlFrame -- a frame that contains the methods for changing
        various properties about the map the simulation is being run on.

        In particular, it lets you create a new map with identical settings,
        but a random layout, it lets you load and save maps, lets you modify
        the height and width of the map, and most importantly, lets you change
        the number of cities on the map which determines the speed and success
        of tspGenetics


*/
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;

public class mapControlFrame extends JFrame implements ActionListener
{
//the instance number makes sure that only one of these is open at a time
	static int instanceNumber = 0;
//constant string used for the button text and action-related things
        String okString = "Set!";
//initializes the set button
	JButton okButton = new JButton(okString);
//creates a panel to handle file related tasks (load, save)...
        mapFileButtonPanel fbPanel = new mapFileButtonPanel();
//creates a panel that lets the user modify the size and number of cities
        mapSettingsPanel sPanel = new mapSettingsPanel();
//constructor
	public mapControlFrame()
  	{
//sets the window title
		super("Map Control Panel");
//error checking for the layout
		try
		{
//set the layout
			layoutInit();
//register a close function for the window
                        this.addWindowListener(new WindowAdapter() {
                        public void windowClosing(WindowEvent e)
                        {
//resets instance number and hides window so it can be reopened
                        dispose();

                        }
                        });
//adds the component panels
			getContentPane().add(fbPanel);
			getContentPane().add(sPanel);
//adds the set button and registers its actionListener
                        okButton.setActionCommand(okString);
                        okButton.addActionListener(this);
                       	getContentPane().add(okButton);
//make it presentable
			pack();
//show the frame
			setVisible(true);
//register that it is a new instance
                        instanceNumber++;
		}
//from layout error
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
//sets the layout
	public void layoutInit() throws Exception
	{
		getContentPane().setLayout(new GridLayout(3,1));
	}
//returns the number of currently active mapControlFrames
        public static int getInstanceNumber()
        {
               return instanceNumber;
        }
//gets rid of a window and decrements its instancenumber
        public void dispose()
        {
           mapControlFrame.instanceNumber--;
           setEnabled(false);
           setVisible(false);

        }

 //if the set button is pushed
  public void actionPerformed(ActionEvent e)
  {
//verify that it was the set button
      if(e.getActionCommand() == okString)
      {
//if a map has been loaded recently
           if(fbPanel.isMapLoaded() == true)
           {
//use the loaded map
               SystemSettings.setMap(fbPanel.getMap());
           }
           else
           {
//otherwise make a new one based on the settings
               SystemSettings.setMap(new TspMap(sPanel.getNCities(),sPanel.getWidth(),sPanel.getHeight()));
           }
      }
  }
}
