/**
     bioSimControlFrame
     the GUI Frame that manages the options relating to the biological sim.
     also of note is that this contains the switch for the breeding type,
     which effects the program at large, but is preset, so it is not necessary
     to meddle with these controls to run the program.


*/

import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;

public class bioSimControlFrame extends JFrame implements ActionListener
{
//strings used for actionlisteners and button initialization
       String okString = "Set!";
//components for this frame
	JButton okButton = new JButton(okString);
//panels to be added to this frame
        bioSimInheritancePanel iP = new bioSimInheritancePanel();
        bioSimEnvironmentalMapSettingsPanel emsP = new bioSimEnvironmentalMapSettingsPanel();
 //to make sure only one is open at a time.
       static int instanceNumber = 0;
	public bioSimControlFrame()
	{
		super("Biological Simulation Control Panel");

      //Declares the panels to be added to the
		try
		{
                       	layoutInit();
		}
		catch(Exception e)
		{
			System.err.println("ERROR IN LAYOUT ON bioSimControlFrame");
		}
		finally
		{
//to clean up after the window is closed, so another one can be opened.
                    this.addWindowListener(new WindowAdapter() {
                     public void windowClosing(WindowEvent e)
                     {
                        dispose();
                      }
                      });
//add components to frame and register actionlisteners
			getContentPane().add(iP);
			getContentPane().add(emsP);
			getContentPane().add(okButton);
                        okButton.setActionCommand(okString);
                        okButton.addActionListener(this);
			pack();
			setVisible(true);
                        instanceNumber++;
		}
	}
//simple function to separate the layout from the constructor
	public void layoutInit() throws Exception
	{
		getContentPane().setLayout(new GridLayout(3,1));
	}
//rturns the number of active bioSimControlFrames
        public static int getInstanceNumber()
        {
          return instanceNumber;
        }
//decrements the number of windows currently active
        public void dispose()
        {
         bioSimControlFrame.instanceNumber--;
           setEnabled(false);
           setVisible(false);

        }



//method that decides what to do when a button is pressed
  public void actionPerformed(ActionEvent e)
  {
//if set button is pushed, register changes in system settings
        if(e.getActionCommand()==okString)
        {
           SystemSettings.setDominantBreeding(iP.isDominantBreeding());
           SystemSettings.setBioSimHeight(emsP.getHeight());
           SystemSettings.setBioSimWidth(emsP.getWidth());
           SystemSettings.setBreedingRadius(emsP.getRadius());
        }

  }
}