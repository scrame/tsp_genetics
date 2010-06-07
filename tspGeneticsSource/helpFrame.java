
//Title:        Your Product Name
//Version:
//Copyright:    Copyright (c) 1999
//Author:       Liam Christopher
//Company:      illogiKal integrity
//Description:  Your description
/**

       helpFrame -- a simple frame that shows the help file

*/

import java.awt.event.*;
import javax.swing.event.*;
import java.awt.*;
import javax.swing.*;
import java.net.*;
import java.io.*;

public class helpFrame extends JFrame
{     
  String helpLocation = "tspGeneticsHelp.htm";
  File helpFile = new File(helpLocation);
  JEditorPane contents;
  public helpFrame()
  {

       super("Online Help For TSP Genetics");
       contents = new JEditorPane();
       contents.setEditable(false);
       contents.addHyperlinkListener(new HyperlinkListener()
       {
            public void hyperlinkUpdate(HyperlinkEvent e)
            {
                 if((e.getEventType()) == HyperlinkEvent.EventType.ACTIVATED)
                 {
                      getThePage(e.getURL().toString() );
                 }
            }

       });
       try{
              getThePage(helpFile.toURL().toString());
              getContentPane().add(new JScrollPane(contents));
              setSize(400,400);
              setEnabled(true);
              setVisible(true);
      }
       catch(Exception e)
       {
             e.printStackTrace();
       }

  }
  private void getThePage(String location)
  {
       try{
           contents.setPage(location);
       }
       catch(IOException e)
       {
           e.printStackTrace();
           JOptionPane.showMessageDialog(this,"Can't find the help file", "Error",JOptionPane.ERROR_MESSAGE);
           setEnabled(false);
           setVisible(false);
       }
  }
}
