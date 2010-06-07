
//Title:        TSP Genetics
//Version:
//Copyright:    Copyright (c) 1999
//Author:       Liam Christopher
//Company:      illogiKal integrity
//Description:  Project for my senior project: TSP Genetics



import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.event.*;

public class bioSimPanel extends JPanel implements ActionListener
{
 String settingButtonString = "Settings ...";
 String bioSimCheckBoxString = "Biological Simulation";
  final JCheckBox bioSimCheckBox = new JCheckBox(bioSimCheckBoxString);
  final JButton settingsButton = new JButton(settingButtonString);
  bioSimControlFrame settingsFrame;
  public bioSimPanel() {
    try
    {
      jbInit();
    }
    catch(Exception ex)
    {
      ex.printStackTrace();
    }
    bioSimCheckBox.addActionListener(this);
    bioSimCheckBox.setActionCommand(bioSimCheckBoxString);
    this.add(bioSimCheckBox);
    settingsButton.setActionCommand(settingButtonString);
    settingsButton.addActionListener(this);
    this.add(settingsButton);
  }
  public void actionPerformed(ActionEvent e)
  {
     if (e.getActionCommand() == settingButtonString)
     {
        if(bioSimControlFrame.getInstanceNumber() == 0)
        {
          settingsFrame = new bioSimControlFrame();
        }
     }
     else if(e.getActionCommand() == bioSimCheckBoxString)
     {
        SystemSettings.setBioSim(bioSimCheckBox.isSelected());
     }
  }
 private void jbInit() throws Exception {
    this.setLayout(new FlowLayout());
  }
}