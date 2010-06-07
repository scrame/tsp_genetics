package tspg.ui;


//Title:        TSP Genetics
//Version:
//Copyright:    Copyright (c) 1999
//Author:       Liam Christopher
//Company:      illogiKal integrity
//Description:  Project for my senior project: TSP Genetics
//
/**
         mapFileButtonPanel -- this panel holds the controls for the map file
         handling functions, like save and load, this also manages a button that
         generates a random map from the current settings, as well as the access
         functions used by the mapControlFrame it is contained in

*/

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import tspg.core.SystemSettings;
import tspg.core.TspMap;

public class mapFileButtonPanel extends JPanel implements ActionListener
{
//local copy of current map, also stores a loaded map if that function is used
        TspMap myMap = SystemSettings.getMap();
//set to true when a map is loaded from disk
        boolean loadedMap = false;
//strings for the buttons and actions
	String randomMapString = "Randomize Map";
	String loadString = "Load Map...";
	String saveString = "Save Map...";
//initializations of the three buttons
	JButton randomMapButton = new JButton(randomMapString);
	JButton loadButton = new JButton(loadString);
	JButton saveButton = new JButton(saveString);
//fileChooser for the disk methods
	JFileChooser fileChooser = new JFileChooser();
//constructor
	public mapFileButtonPanel()
	{
//catch errors from layoutInit()
		try
		{
//set the layout
			layoutInit();
//register action classes for the random button
			randomMapButton.addActionListener(this);
			randomMapButton.setActionCommand(randomMapString);
//register action classes for the load button
			loadButton.addActionListener(this);
			loadButton.setActionCommand(loadString);
//register action classes for the save button
			saveButton.addActionListener(this);
			saveButton.setActionCommand(saveString);
//adds the buttons to the frame
			add(randomMapButton);
			add(loadButton);
			add(saveButton);
		}
//just in case there are layout errors...
		catch(Exception e)
		{
			e.printStackTrace();
			System.exit(1);
		}
	}
//called when a button is pushed
	public void actionPerformed(ActionEvent e)
	{
//File handle for loading and saving:
      File mapFile;
//variable to determine the actions with the fileChooser
      int chooserChoice;
//if the load button was pushed
      if (e.getActionCommand().equals(loadString))
     	{
//show a file chooser for opening files
			chooserChoice = fileChooser.showOpenDialog(this);
//if a file was selected to open
			if(chooserChoice == JFileChooser.APPROVE_OPTION)
			{
                            try{
                              mapFile = fileChooser.getSelectedFile();
                              ObjectInputStream ifStream = new ObjectInputStream(new FileInputStream(mapFile));
                              myMap =(TspMap) ifStream.readObject();
                              ifStream.close();
                              loadedMap = true;
                            }
                            catch(Exception ex)
                            {
                              JOptionPane.showMessageDialog(this,"Can't Load Map", "Error",JOptionPane.ERROR_MESSAGE);
                            }
			}
   	}
//if save was pushed
		else if (e.getActionCommand().equals(saveString))
      {
//show a chooser for saving filese
			chooserChoice = fileChooser.showSaveDialog(this);
//if a file was selected to save
			if(chooserChoice == JFileChooser.APPROVE_OPTION)
			{
                            try{
                              mapFile = fileChooser.getSelectedFile();
                              mapFile.createNewFile();
                              ObjectOutputStream ofStream = new ObjectOutputStream(new FileOutputStream(mapFile));
                              ofStream.writeObject(SystemSettings.getMap());
                              ofStream.flush();
                              ofStream.close();
                            }
                            catch(IOException ex)
                            {
                              JOptionPane.showMessageDialog(this,"Can't Save Map", "Error",JOptionPane.ERROR_MESSAGE);
                            }
			}

   	}
//if randomize button was pushed
		else if (e.getActionCommand().equals(randomMapString))
    	{
			myMap.makeRandomMap();
		}

	}
//returns true if a map has been loaded from disk
        public boolean isMapLoaded()
        {
            return loadedMap;
        }
//returns the map being used currently
        public TspMap getMap()
        {
             return myMap;
        }

//sets the layout
	public void layoutInit()
	{
		setLayout(new FlowLayout());
	}


}