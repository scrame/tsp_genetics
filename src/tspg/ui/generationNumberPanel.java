
//Title:        Your Product Name
//Version:      
//Copyright:    Copyright (c) 1999
//Author:       Liam Christopher
//Company:      illogiKal integrity
//Description:  Your description
/**
      simple panel that allows you to enter the number of generations to simulate
      just a label, a textfield and an access function

*/


import java.awt.FlowLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class generationNumberPanel extends JPanel
{
//default constant
  final int DEFAULTNGENERATIONS = 10;

//the label
  JLabel generationLabel = new JLabel("Number of Generations");
//the textfield
  JTextField generationTextField = new JTextField(new Integer(DEFAULTNGENERATIONS).toString(),3);
//the constructor
  public generationNumberPanel()
  {
    try
    {
  //sets the layout
      jbInit();
  //adds the label and textfield
      add(generationTextField);
      add(generationLabel);
    }
    catch(Exception ex)
    {
  //catches layout exceptions
      ex.printStackTrace();
    }
  }
//...and the accessfunction
  public int getNGenerations()
  {
      Integer nGen = new Integer(generationTextField.getText());

      return nGen.intValue();
  }
//sets the layout
  private void jbInit() throws Exception
  {
    this.setLayout(new FlowLayout());
  }
} 