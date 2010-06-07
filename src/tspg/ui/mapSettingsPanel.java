package tspg.ui;

//Title:        TSP Genetics
//Version:
//Copyright:    Copyright (c) 1999
//Author:       Liam Christopher
//Company:      illogiKal integrity
//Description:  Project for my senior project: TSP Genetics
//
/**
                mapSettingsPanel - a panel containing 3 textfields that let you
                modify the size and number of cities in the current map
*/


import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import tspg.core.SystemSettings;

class mapSettingsPanel extends JPanel
{
 //constant to format the textfields with
      final int TEXTFIELDSIZE = 5;
//constant that sets the maximum size the map can be
      final int XYMAX = 600;
//value to return if an illegal value is entered in the city number
      final int DEFAULTCITYNUMBER = 30;
//textfield and label initializations
	JTextField lengthTextField = new JTextField(new Integer(SystemSettings.getMap().getMapHeight()).toString(),TEXTFIELDSIZE);
	JTextField widthTextField = new JTextField(new Integer(SystemSettings.getMap().getMapWidth()).toString(),TEXTFIELDSIZE);
	JTextField nCitiesTextField = new JTextField(new Integer(SystemSettings.getMap().getNCities()).toString(),TEXTFIELDSIZE);
	JLabel lengthLabel = new JLabel("Simulation Map Height");
	JLabel widthLabel = new JLabel("Simulation Map Width");
	JLabel nCitiesLabel = new JLabel("Number of Cities");

	public mapSettingsPanel()
	{
//sets the layout and traps errors
		try
		{
			layoutInit();
			add(lengthTextField);
			add(lengthLabel);
			add(widthTextField);
			add(widthLabel);
			add(nCitiesTextField);
			add(nCitiesLabel);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
//access functions for the control frame that simply returns the contents of the three fields
        public int getHeight()
        {
             if((new Integer(lengthTextField.getText()).intValue() > XYMAX)||(new Integer(lengthTextField.getText()).intValue() <0))
                 return XYMAX;
             else
                 return new Integer(lengthTextField.getText()).intValue();
        }
        public int getWidth()
        {
             if((new Integer(widthTextField.getText()).intValue() > XYMAX)||(new Integer(widthTextField.getText()).intValue() <0))
                 return XYMAX;
             else
                 return new Integer(widthTextField.getText()).intValue();
        }
        public int getNCities()
        {
             if(new Integer(nCitiesTextField.getText()).intValue() < 0)
                  return DEFAULTCITYNUMBER;
             else
                  return new Integer(nCitiesTextField.getText()).intValue();
        }
//sets the layout
	public void layoutInit() throws Exception
	{
		setLayout(new GridLayout(3,2));
	}
}