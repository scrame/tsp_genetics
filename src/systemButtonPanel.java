
//Title:        TSP Genetics
//Version:
//Copyright:    Copyright (c) 1999
//Author:       Liam Christopher
//Company:      illogiKal integrity
//Description:  Project for my senior project: TSP Genetics
/**

     systemButtonPanel -- class that registers the load and save settings buttons
     as well as the mapControlFrame, just 3 buttons and their actions


*/


import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JPanel;

public class systemButtonPanel extends JPanel implements ActionListener
{
//strings for labels and actions
  String helpString = "Help...";
  String mapString = "Map...";
//fiulechooser to make loading and saving easier
  JFileChooser fileChooser = new JFileChooser();
//a mapControlFrame to modify the current map
  mapControlFrame mapSettings;
//sets up the buttons
  final JButton mapButton = new JButton(mapString);
  final JButton helpButton = new JButton(helpString);

  public systemButtonPanel()
  {

    try
    {
  //set up the layout
      jbInit();
    }
    catch(Exception ex)
    {
  //let the user know if something goes wrong
      ex.printStackTrace();
    }
  //register the map button
    mapButton.setActionCommand(mapString);
    mapButton.addActionListener(this);
    this.add(mapButton);
  //register the help button
    helpButton.setActionCommand(helpString);
    helpButton.addActionListener(this);
    this.add(helpButton);
  }
//sets the layout
  private void jbInit() throws Exception
  {
    this.setLayout(new FlowLayout());
  }

//what to do if one of the buttons is pushed
  public void actionPerformed(ActionEvent e)
  {
  if (e.getActionCommand().equals(helpString))
  {

       new helpFrame();

  }
//if it is the map button, open a new mapControlFrame
  else if (e.getActionCommand().equals(mapString))
  {
     if(mapControlFrame.getInstanceNumber() == 0)
     {
       mapSettings = new mapControlFrame();
     }
  }
  }


}
