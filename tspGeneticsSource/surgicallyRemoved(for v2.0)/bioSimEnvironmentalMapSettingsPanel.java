
//Title:        Your Product Name
//Version:
//Copyright:    Copyright (c) 1999
//Author:       Liam Christopher
//Company:      illogiKal integrity
//Description:  Your description
/**
          bioSimEnvironmentalMapSettingsPanel -- a panel used in bioSimControlFrame
          allows you to change some fundamental properties of the biological simulation
          namely: the width and height of the map, and the breeding radius used in
          determining the survival of the superior species in the simulation
*/

import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;

class bioSimEnvironmentalMapSettingsPanel extends JPanel
{
//constant used to make the textFields a uniform size
      final int TEXTFIELDSIZE = 5;
//textfields and the associated labels
        JTextField lengthTextField = new JTextField(new Integer(SystemSettings.getBioSimHeight()).toString(),TEXTFIELDSIZE);
	JTextField widthTextField = new JTextField(new Integer(SystemSettings.getBioSimWidth()).toString(),TEXTFIELDSIZE);
	JTextField radiusTextField = new JTextField(new Double(SystemSettings.getBreedingRadius()).toString(),TEXTFIELDSIZE);
	JLabel lengthLabel = new JLabel("Simulation Map Height");
	JLabel widthLabel = new JLabel("Simulation Map Width");
	JLabel radiusLabel = new JLabel("Proximity Radius to Determine Breeding");
//constructor
	public bioSimEnvironmentalMapSettingsPanel()
	{
//sets the layout and traps errors
		try
		{
			layoutInit();
//adds the fields and labels to the panel
			add(lengthTextField);
			add(lengthLabel);
			add(widthTextField);
			add(widthLabel);
			add(radiusTextField);
			add(radiusLabel);

		}
		catch(Exception e)
		{
			System.err.println("ERROR! bioSimEnvironmentalMapSettingsPanel: layout problem");
			System.exit(0);
		}

	}
//access functions that return the contents of each textfield
        public int getWidth()
        {
            return new Integer(widthTextField.getText()).intValue();
        }
        public int getHeight()
        {
            return new Integer(lengthTextField.getText()).intValue();
        }
        public double getRadius()
        {
            return new Double(radiusTextField.getText()).doubleValue();
        }

//simple function to set the layout
        public void layoutInit() throws Exception
        {
		setLayout(new GridLayout(3,2));
	}
}