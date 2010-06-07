package tspg.ui;


//Title:        TSP Genetics
//Version:
//Copyright:    Copyright (c) 1999
//Author:       Liam Christopher
//Company:      illogiKal integrity
//Description:  Project for my senior project: TSP Genetics
//
/**  This class contains the data for defining different kinds of individuals.
     It is made so that it can be set up several times and make many kinds of
     individuals, and the current control frame implements up to eight of them.


 */


import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import tspg.core.SystemSettings;
import tspg.core.TspOrganism;
import tspg.core.TspTour;
import tspg.operators.binary.BinaryOperator;
import tspg.operators.binary.BinaryTypes;
import tspg.operators.unary.UnaryOperator;
import tspg.operators.unary.UnaryTypes;

public class individualTypePanel extends JPanel implements ActionListener {
//constant to set the text field for the number of each template to
  final Integer DEFAULTORGANISMNUMBER = new Integer(24);
//constant for the size of the text field
  final int NCOLUMNS = 3;
//strings for labels, buttons, and action registry's
  String numberString = "Number";
  String binaryString = "Binary Cycle";
  String unaryString = "Unary Cycle";
//the textfield that holds the number of each kind of template
  JTextField numberTextField = new JTextField(DEFAULTORGANISMNUMBER.toString(),NCOLUMNS);
//label that explains this
  JLabel numberLabel = new JLabel(numberString);
//button to let you cycle through the binary algorithms
  JButton binaryButton = new JButton(binaryString);
//button that lets you choose a unary algorithm
  JButton unaryButton = new JButton(unaryString);
//ID number used to make sure that it is making an appropriate number of organisms
  int idNumber;
//the actual value of the contents of the text field
  int numberOfOrganisms = DEFAULTORGANISMNUMBER.intValue();
//counter used to reference the types of algorithms used
  int unaryCounter = 0;
  int binaryCounter = 0;
//initializes containers of the binary and unary algorithms
  UnaryTypes unaryTypes = new UnaryTypes();
  BinaryTypes binaryTypes = new BinaryTypes();
//holds the type of operator currently used
  UnaryOperator currentUnary = unaryTypes.getUnaryOperator(unaryCounter);
  BinaryOperator currentBinary = binaryTypes.getBinaryOperator(binaryCounter);
//labels to hold the names of the current operators
  JLabel unaryLabel;
  JLabel binaryLabel;
//constructor
  public individualTypePanel(int ID) {
//catch layout errors
    try  {
//set the layout
      jbInit();
      setPreferredSize(new Dimension(650,50));
//set the id number
      idNumber = ID;
//make sure it is allowed to open
      if(idNumber < SystemSettings.getOrganismTypeNumber())
      {
          setType(SystemSettings.getOrganismTemplate(idNumber));
          numberTextField = new JTextField(new Integer(SystemSettings.getOrganismTypeCount(idNumber)).toString(),NCOLUMNS);
      }
//set the labels to the current operators
      unaryLabel = new JLabel(currentUnary.getOperatorName());
      binaryLabel = new JLabel(currentBinary.getOperatorName());
//adds the labels for the text field
      this.add(numberTextField,FlowLayout.LEFT);
      this.add(numberLabel,FlowLayout.LEFT);
//sets up the binary cycle button and labels the current binary operator
      binaryButton.setActionCommand(binaryString);
      binaryButton.addActionListener(this);
      this.add(binaryButton,FlowLayout.LEFT);
      this.add(binaryLabel,FlowLayout.LEFT);
//sets up the unary cycle button and labels the current unary operator
      unaryButton.setActionCommand(unaryString);
      unaryButton.addActionListener(this);
      this.add(unaryButton,FlowLayout.LEFT);
      this.add(unaryLabel,FlowLayout.LEFT);


    }
//if there is a probelem with the layout...
    catch(Exception ex) {
      ex.printStackTrace();
    }
  }
//sets the current panel to a different type of organisms
  public void setType(TspOrganism newTemplate)
  {
//an organism is nothing but a couple operators
       currentBinary = newTemplate.getBinary();
       currentUnary = newTemplate.getUnary();
  }
//sets the layout
  private void jbInit() throws Exception {
    this.setLayout(new FlowLayout());
    return;
      }
//returns the organism template defined in the panel
   public TspOrganism getOrganism()
  {
        return new TspOrganism(new TspTour(SystemSettings.getMap().getNCities()), currentUnary, currentBinary);
  }
//returns the content of the textfield
  public int getNumberOfOrganisms()
  {
        if(new Integer(numberTextField.getText()).intValue() <0)
             return DEFAULTORGANISMNUMBER.intValue();
        else
             return new Integer(numberTextField.getText()).intValue();
  }
//to cycle through the two sets of operators
  public void actionPerformed(ActionEvent e)
  {
// if its the unary cycle button
      if(e.getActionCommand() == unaryString)
      {
//increment the counter of unaryOperators
            unaryCounter++;
//get the corresponding operator from the set
            currentUnary = unaryTypes.getUnaryOperator(unaryCounter);
//set the label to reflect this new operator
            unaryLabel.setText(currentUnary.getOperatorName());
//make the change show up
            repaint();
      }
      else if(e.getActionCommand() == binaryString)
//if its the binary button...
      {
//increment the counter of binaryOperators
            binaryCounter++;
//get the corresponding operator from the set
            currentBinary = binaryTypes.getBinaryOperator(binaryCounter);
//set the label to reflect this new operator
            binaryLabel.setText(currentBinary.getOperatorName());
//make the change apparent
            repaint();
      }
  }
}