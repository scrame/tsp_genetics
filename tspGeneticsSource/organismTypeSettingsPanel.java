
//Title:        Your Product Name
//Version:      
//Copyright:    Copyright (c) 1999
//Author:       Liam Christopher
//Company:      illogiKal integrity
//Description:  Your description

package tspgenetics;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.event.*;

public class organismTypeSettingsPanel extends JPanel implements ActionListener
{
  GridLayout gridLayout1 = new GridLayout();
  String numberOfTypesString = "Number of Individual Types";
  String configureString = "Configure...";
  JTextField numberOfTypesField = new JTextField(3);
  JLabel numberOfTypesLabel = new JLabel(numberOfTypesString);
  JButton configureButton = new JButton(configureString);
  public organismTypeSettingsPanel()
  {
    try
    {
      jbInit();
    }
    catch(Exception ex)
    {
      ex.printStackTrace();
    }
    finally
    {
     add(numberOfTypesField);
     add(numberOfTypesLabel);
     configureButton.setActionCommand(configureString);
     configureButton.addActionListener(this);
     add(configureButton);

    }
  }

  private void jbInit() throws Exception
  {
    this.setLayout(gridLayout1);
  }

  public void actionPerformed(ActionEvent e)
  {
         if(e.getActionCommand() == configureString)
         {
           System.out.println("working on it....");
         }
  }
}