
//Title:        Your Product Name
//Version:      
//Copyright:    Copyright (c) 1999
//Author:       Liam Christopher
//Company:      illogiKal integrity
//Description:  Your description



import java.awt.*;
import javax.swing.JFrame;

public class bioSimDrawingFrame extends JFrame
{
  bioSimDrawingPanel dPanel;

  public bioSimDrawingFrame(TspOrganism mapData[], int numberOfIndividuals,int mapX,int mapY)
  {
       super("Biological Simulation Map");
       dPanel = new bioSimDrawingPanel(mapData, numberOfIndividuals);
       dPanel.setPreferredSize(new Dimension(mapX,mapY));
       getContentPane().setLayout(new FlowLayout());
       getContentPane().add(dPanel);
       pack();
       setEnabled(true);
       setVisible(true);

  }
  public void updateBioSim(TspOrganism newOrganisms[], int index)
  {
       dPanel.setOrganisms(newOrganisms,index);
       dPanel.repaint();
  }

}