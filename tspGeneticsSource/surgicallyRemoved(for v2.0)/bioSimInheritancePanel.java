
//Title:        Your Product Name
//Version:
//Copyright:    Copyright (c) 1999
//Author:       Liam Christopher
//Company:      illogiKal integrity
//Description:  Your description
/**
          bioSimInheritancePanel -- a panel to be used in bioSimControlFrame
          contains information about the type of inheritance to be used in the
          simulation. Dominant inheritance sets the algorithms of all children
          identical to the superior parent, cooperative mixes the operators of
          both parents into the children.
*/

import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;

public class bioSimInheritancePanel extends JPanel
{
//Strings, labels and buttons/buttongroup
        String dominantString = "dominant";
        String cooperativeString = "cooperative";
	JLabel inheritanceTypeLabel = new JLabel("Inheritance Type");
	JRadioButton dominanceRadioButton = new JRadioButton(dominantString);
	JRadioButton cooperativeRadioButton = new JRadioButton(cooperativeString);
	ButtonGroup inheritanceButtonGroup = new ButtonGroup();

	public bioSimInheritancePanel()
	{

		try
		{
//try to set the layout and trap errors
			layoutInit();
//register actioncommands, to discriminate between events
                        dominanceRadioButton.setActionCommand(dominantString);
                        cooperativeRadioButton.setActionCommand(cooperativeString);
//separate buttons to button group to allow mutal exclusivity and easier management
			inheritanceButtonGroup.add(dominanceRadioButton);
			inheritanceButtonGroup.add(cooperativeRadioButton);
//determine which button to select based on system settings
                        if(SystemSettings.isDominantBreeding()==true)
                        {
                             dominanceRadioButton.setSelected(true);
                        }
                        else
                        {
                             cooperativeRadioButton.setSelected(true);
                        }
//add the individual components to the panel
			add(inheritanceTypeLabel);
			add(dominanceRadioButton);
			add(cooperativeRadioButton);
		}
//catch errors!
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
//simple function to set layout
	public void layoutInit()
	{
		setLayout(new FlowLayout());
	}
//access function to determine current breeding state. used by bioSimControlFrame
        public boolean isDominantBreeding()
        {
           return inheritanceButtonGroup.getSelection().getActionCommand()==dominantString;
        }

}