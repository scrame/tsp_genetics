package tspg.core;

//Title:        Your Product Name
//Version:
//Copyright:    Copyright (c) 1999
//Author:       Liam Christopher
//Company:      illogiKal integrity
//Description:  Your description
/**

       TspEnvironment -- the biggest and most important class. This is where the
       actual simulation takes place. The simulation runs in two phases: breeding
       and evaluating, during the evaluation phase, each organism is run through
       the map and assigned its fitness rating, during breeding, the organisms
       with above average fitness are separated into a second pool and a new
       pool of equal size is created by crossing the superior organisms
       repeatedly. The environment also keeps track of the statistics, like the
       best and average lengths, and is responsible for instantiating all of the
       drawing frames.

       A slight complication is that it contains an innerclass that is a frame
       containing a progress bar, once instantiated, this frame begins its own
       thread (another inner class) and actually runs the simulation, although
       this file is substantially longer because of this, it was the easiest way
       to integrate a useful progress bar (and stop button) into the program.

*/
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JProgressBar;

import tspg.ui.fitnessDrawingFrame;
import tspg.ui.tourDrawingFrame;

public class TspEnvironment
{
//constant that determines how many time the unary algorithm is run during
//each breeding phase
  private final int NUMBEROFMUTATIONS = 20;
//the pecentage of besttours to be added in the pool if it converges prematurely
  private   final int RESETRATIO = 10;
//every n mutations do a random swap, to break up convergence
  private   final int mutationFrequency = 50;
//to increment and compare with mutationFrequency
  private   int mutationCounter = 0;
//the number of best tours to keep track of
  private   int bestTourNumber;
//the number of tours recorded so far
  private   int bestIndex=0;
//the total number of organisms in the pool
  private   int nOrganisms;
//the organisms themselves
  private   TspOrganism environmentalOrganisms[];
//the maximum number of generations to simulate
  private   int numberOfGenerations;
//the current generation
  private   int generationIndex = 0;
//the best tours so far
  private   TspTour bestTours[];
//variables for comparing and calculating the best lengths for each
//generation
  private   double averageFitness,bestLength;
//the array that stores the average lengths for each generation
  private   double averageFitnessArray[];
//the array that stores the best lengths of each generation
  private   double bestFitnessArray[];
//the frame to animate the best tour progression
  private   tourDrawingFrame tdFrame;
//the frame that draws the comparison of lengths by generations
  static fitnessDrawingFrame fdFrame;
//the innerclass that does the actual computations and represents it with a
//progress bar
  private   progressFrame pFrame;
//constructor that initializes the tour from systemSettings, its only argument
//is the number of generations to be simulated
  public TspEnvironment(int nGenerations)
  {
//set the number of generations
    numberOfGenerations = nGenerations;
//record approximately one best tour for each generation
    bestTourNumber = nGenerations;
//create the array to save the best tours in
    bestTours = new TspTour[bestTourNumber];
//set the index of best tours to 0
    bestIndex = 0;
//create the gene pool
    initializeSimulation();
//allocate space for the two arrays based on the numbe rof generations
    averageFitnessArray = new double[nGenerations];
    bestFitnessArray = new double[nGenerations];
//start the simulation
    pFrame = new progressFrame();

  }
//this method creates the genepool based on the templates stored in the
//SystemSettings class
  private void initializeSimulation()
  {
//set to 0 just to be safe
      nOrganisms = 0;
//for the number of templates
     for(int i=0;i<SystemSettings.getOrganismTypeNumber();i++)
      {
//for the number of each kind of template to be added
          for(int j=0;j<SystemSettings.getOrganismTypeCount(i);j++)
          {
//increment the counter
                nOrganisms++;
          }
      }
//now nOrganisms holds a count of total number of organisms to be in the pool
//we use this to create the pool to the right size
      environmentalOrganisms = new TspOrganism[nOrganisms];
//repeat the counting process
      nOrganisms = 0;
      for(int i=0;i<SystemSettings.getOrganismTypeNumber();i++)
      {
          for(int j=0;j<SystemSettings.getOrganismTypeCount(i);j++)
          {
//instead of just counting, now we assign a template to each organism
            environmentalOrganisms[nOrganisms++] = new TspOrganism(new TspTour(SystemSettings.getMap().getNCities()),SystemSettings.getOrganismTemplate(i).getUnary(),SystemSettings.getOrganismTemplate(i).getBinary());
          }
      }
  }
/*

     one of the two main phases of the program, here each organism is evaluated
     with respect to the map and assigned its fitness, as well as keeping the
     data for the representation later

*/
  private void evaluateOrganisms()
  {

	  //to compare with the best length so far
    double tourLength;
//initialize the average fitness
    averageFitness = 0;
//loop through each organism and evaluate it
    for(int i=0;i<nOrganisms;i++)
    {
//find the length of the tour in the current organism
        tourLength = SystemSettings.getMap().calculateTour(environmentalOrganisms[i].getDNA());
//add it to the average fitness
        averageFitness += tourLength;
//set the organisms fitness equal to the length of its tour
        environmentalOrganisms[i].setFitnessRating(tourLength);
//if this length is shorter i.e. better, then save it
        if((tourLength < bestLength)|(bestLength == 0))
        {
//set the new best length
            bestLength = tourLength;
//add the tour to the best tour list
            addTour(environmentalOrganisms[i].getDNA());
        }
    }
//divide by the number of organisms to get an average
    averageFitness /= nOrganisms;
//record this average in its proper array
    averageFitnessArray[generationIndex] = averageFitness;
//record the current best fitness, even if it hasn't changed
    bestFitnessArray[generationIndex] = bestLength;
  }
//to fill in the best tours if they are never added, this pads the final animation
//and prevents nullpointerexceptions
  private void setBestTours()
  {
        if(bestTours[0] == null)
        {
             bestTours[0] = new TspTour(SystemSettings.getMap().getNCities());
        }
  //for every space reserved for a tour
        for(int i=1;i<bestTourNumber;i++)
        {
//if there is no tour set
             if(bestTours[i] == null)
             {
//then copy the previous tour
                 bestTours[i] = bestTours[i-1];
             }
        }
  }
//mutateOrganisms -- method that applies the unary algorithm to every organism
//a set numbre of times and also introduces random mutation
  private void mutateOrganisms()
  {
//tours to work with
    TspTour bestTour,workingTour;

   //loop through every individual
    for(int i=0;i<nOrganisms;i++)
    {
//initialize all of the data to the current tour
        bestTour = environmentalOrganisms[i].getDNA();
        bestLength = SystemSettings.getMap().calculateTour(bestTour);
        workingTour = environmentalOrganisms[i].getDNA();
//loop for the number of times this algorithm will run
        for(int j=0;j<NUMBEROFMUTATIONS;j++)
        {
//mutate this tour
             workingTour = environmentalOrganisms[i].getUnary().modifyTour(workingTour);
//if it is an improvement, then save it to be remutated
             if(SystemSettings.getMap().calculateTour(workingTour)<SystemSettings.getMap().calculateTour(bestTour))
             {
                  bestTour = workingTour;
             }
        }
//check the mutation counter to see if this organism gets a random swap
        if(++mutationCounter%mutationFrequency==0)
        {
             bestTour.swapCities((int)Math.random()*bestTour.getTourSize(),(int)Math.random()*bestTour.getTourSize());
        }
//record exit with the best tour in place
        environmentalOrganisms[i] = new TspOrganism(bestTour,environmentalOrganisms[i].getUnary(),environmentalOrganisms[i].getBinary());
    }
  }

//the other main phase of the simulation, this is where the evaluated organisms
//are separated and bred
  private void breedOrganisms()
  {
//the set for the organisms that are above average fitness
    TspOrganism superiorSet[] = new TspOrganism[nOrganisms];
//the new set of organisms to simulate the next round
    TspOrganism childSet[] = new TspOrganism[nOrganisms];
//the number of organisms counted so far
    int superiorIndex = 0;
//indexes of superior parents used to breed
    int parent1ID,parent2ID;
//run the unary modification on all of the organisms
    mutateOrganisms();
    {
//loop through every organism
       for(int i=0; i<nOrganisms;i++)
       {
//if its fitness is above average
          if(environmentalOrganisms[i].getFitnessRating() < averageFitness)
          {
//place it in the superior set
             superiorSet[superiorIndex] = environmentalOrganisms[i];
//and increment its position
             superiorIndex++;
          }
       }
    }
//if there are no outstanding organisms
    if(superiorIndex == 0)
    {
//create a new set
        superiorSet = new TspOrganism[nOrganisms];
//a tour used for resetting the organisms
        TspTour resetTour;
//loop through each organism
        for(int i=0;i<nOrganisms;i++)
        {
//create a new random tour to throw in the gene pool
                 resetTour = new TspTour(SystemSettings.getMap().getNCities());
//if it is time to throw in a successful tour
            if(i%RESETRATIO==0)
            {
//randomly select one of the best tours
                 resetTour = getBestTour((int)(Math.random()*getBestTourIndex()));
            }
//create a new organism based on one of the preset templates
            superiorSet[i] = new TspOrganism(resetTour,SystemSettings.getOrganismTemplate(i%SystemSettings.getOrganismTypeNumber()).getUnary(),SystemSettings.getOrganismTemplate(i%SystemSettings.getOrganismTypeNumber()).getBinary());
        }
    }
/*
  The following loop determines what organisms in the superior set
  mate with each other, currently, parents are picked randomly.
  this also determines what algorithms the child will inherit,
*/

//loops through every organism by 2's (because each pair of parents makes a pair
//of children)
    for(int j=0;j<nOrganisms;j+=2)
    {
//set the ID's of the superior parents
       parent1ID = (int)(Math.random()*superiorIndex);
       parent2ID = (int)(Math.random()*superiorIndex);
//create 2 children by mixing the algorithms of the two parents and modifying by
//the binary algorithm
       childSet[j] = new TspOrganism(superiorSet[parent1ID].binaryModify(superiorSet[parent2ID].getDNA()),superiorSet[parent1ID].getUnary(),superiorSet[parent2ID].getBinary());
       childSet[j+1] = new TspOrganism(superiorSet[parent2ID].binaryModify(superiorSet[parent1ID].getDNA()),superiorSet[parent2ID].getUnary(),superiorSet[parent1ID].getBinary());

    }
//assign the next generation as the newly constructed set
    environmentalOrganisms = childSet;
  }
//access funtion that returns a referenced best tour
  private TspTour getBestTour(int index)
  {
    return bestTours[index];
  }
//function that adds the best tour to a list of tours
  private void addTour(TspTour newTour)
  {
//increment the index used
      bestIndex++;
//shuffle the current best tours down one index position, leaving the last spot
//open
      for(int i=0;i<getBestTourIndex();i++)
      {
         bestTours[i] = bestTours[i+1];
      }
//set the last spot to the most current tour
      bestTours[getBestTourIndex()] = newTour;
  }
//get the index of the tours so far, since the increment in addTour is not
//constrained, this function compensates by returning the least of the two
//indexes, this ensures that there are no outofbounds errors
  private int getBestTourIndex()
  {
       return Math.min(bestIndex,bestTourNumber-1);
  }

//inner class that holds the thread and manages the actual running of the above
//functions, also displays the current best and average lengths as well as
//showing a progress bar, so people won't think the program has crashed on
//very long simulations
class progressFrame extends JFrame implements ActionListener
{
//string for actions and labels
    private String stopString = "STOP!";
//the thread that will do our work
    private Thread progressThread;
//simple object used to synchronize foreground and background threads
    private Object pLock;
//varible used to see if the stop button has been pressed and if it should just
//quit
    private boolean stopProgress = false;
//shows the progress of the simulation
    private JProgressBar progressBar;
//labels used to show the best stuff
    private JLabel generatingLabel = new JLabel("Generating Data...");
    private JLabel bestLabel = new JLabel("Best: ");
    private JLabel averageLabel = new JLabel("Average: ");
//button to allow you to stop the simulation
    private JButton stopButton = new JButton(stopString);
//constructor, sets up the basic frame
    public progressFrame()
    {
//set the title bar
        super("Working . . .");
//allocate the object to synchronize with
        pLock = new Object();
//make everything nice and orferly looking
        getContentPane().setLayout(new GridLayout(5,1));
//allocate the progress bar
        progressBar = new JProgressBar();
//add the components
        getContentPane().add(progressBar);
        getContentPane().add(generatingLabel);
        getContentPane().add(averageLabel);
        getContentPane().add(bestLabel);
//register and add the stop button
        stopButton.setActionCommand(stopString);
        stopButton.addActionListener(this);
        stopButton.setBackground(Color.red);
        getContentPane().add(stopButton);
        this.setResizable(false);
//registers the close box
        this.addWindowListener(new WindowAdapter() {
               public void windowClosing(WindowEvent e)
               {
                  stopThread();
               }
               });
//set the location on the screen
        this.setLocation(300,100);
//make it look purty
        pack();
//show and enable this frame
        setEnabled(true);
        setVisible(true);
//get it running
        startThread();
    }
//action for the stop button
    public void actionPerformed(ActionEvent e)
    {
        if(e.getActionCommand() == stopString)
        {
             stopThread();
        }
    }
//allocates a new thread and makes sure its running
    public void startThread()
    {
        if(progressThread == null)
        {
             progressThread = new progressUpdatingThread();
        }
        if(!progressThread.isAlive())
        {
              stopProgress = false;
              progressThread.start();
        }

    }
//stops the current thread
    public void stopThread()
    {
        synchronized(pLock)
        {
             stopProgress = true;
             pLock.notify();
        }
    }
//inner class to the inner class, this is the actual thread, this is what
//gets executed
    class progressUpdatingThread extends Thread
    {
        public void run()
        {
//to avoid errors
             setPriority(Thread.MIN_PRIORITY);
             int nGenerations = numberOfGenerations;
             if(nGenerations <= 0)
             {
             nGenerations = 1;
             }
//set the minimum and maximum values fo rhte progress bar
             int progressMinimum = 0;
             int progressMaximum = nGenerations;
             progressBar.setValue(progressMinimum);
             progressBar.setMinimum(progressMinimum);
             progressBar.setMaximum(progressMaximum);
//loop through each generation
             for(int i=0;i<nGenerations;i++)
             {
//evaluation phase
                 evaluateOrganisms();
//update frame information
                 progressBar.setValue(i);
                 averageLabel.setText("Average: "+averageFitnessArray[i]);
                 bestLabel.setText("Best: "+bestFitnessArray[i]);
//breeding phase
                 breedOrganisms();
//if the user pressed stop
                 synchronized(pLock)
                 {
                     if(stopProgress == true)
                        break;
                     try
                     {
                         pLock.wait(1);
                     }
                     catch(InterruptedException e)
                     {
                         //ignore the exception
                     }
                 }
//increment the counter for the current generation
                 generationIndex++;
             }
//kill the thread and get rid of the progress frame
            progressThread = null;
            pFrame.setEnabled(false);
            pFrame.setVisible(false);
            pFrame = null;
//fill in the remaining tours with the best one discovered
            setBestTours();
//create a new frame that draws all of the tours and animates the progression
            tdFrame = new tourDrawingFrame(getBestTourIndex(), bestTours);
            tdFrame.run();
//create a frame that shows a graph of improvement
            TspEnvironment.fdFrame = new fitnessDrawingFrame(generationIndex,averageFitnessArray,bestFitnessArray);
//thats it! ready for another go!
        }
    }
}
}