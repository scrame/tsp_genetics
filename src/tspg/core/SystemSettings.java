package tspg.core;

import tspg.operators.binary.binaryIdentity;
import tspg.operators.unary.unaryIdentity;


//Title:        Your Product Name
//Version:
//Copyright:    Copyright (c) 1999
//Author:       Liam Christopher
//Company:      illogiKal integrity
//Description:  Your description
//Class containing purely static variables, in a sense a complete record of the
//simulation to be performed, although each part of it is configured with a
//different frame. The "go" button on the main control frame then uses all of
//the information in here to work with the environment, and configure everything
//properly. This also holds all of the defaults for the program at large.
//Because this is a static class, one merely needs to be instantiated for
//smaller pieces of the program, aiding integretive development
//(hey, it works for me).

public class SystemSettings
{

  static int nCities;
/*
  nCities: the number of cities on the map, as well as the length of the
     organisms DNA. this is a part of the problem domain (in mathematics), and
     determines the size of the program.
*/
  static TspOrganism organismTemplate[];
//the templates of each type of organism to be simulated, set by the individual
//type frame.
  static int organismTemplateCount[];
//the number of each individual corresponding to a template type, these arrays
//must be of equal size.
  static int organismTypeNumber;
//the index maximum of the two above arrays.
  static TspMap workingMap;
//the map currently being used
//constructor
  public SystemSettings()
  {
//sets the above data to its defaults
      nCities = 30;
      workingMap = new TspMap(nCities);
      organismTypeNumber = 1;
      organismTemplate = new TspOrganism[1];
      organismTemplate[0] = new TspOrganism(new TspTour(nCities),new unaryIdentity(), new binaryIdentity());
      organismTemplateCount = new int[1];
      organismTemplateCount[0] = 24;

  }
//functions relating to the actual simulation
//function to preset organism templates
  public static void setOrganisms(int count, TspOrganism organismTypes[], int numberOfOrganisms[])
  {
     organismTypeNumber = count;
     organismTemplate = organismTypes;
     organismTemplateCount = numberOfOrganisms;
  }
//Access function to return the count of a given template
  public static int getOrganismTypeCount(int index)
  {
     return organismTemplateCount[index];
  }
  public static int getOrganismTypeNumber()
  {
     return organismTypeNumber;
  }
  public static TspOrganism getOrganismTemplate(int templateID)
  {
     return organismTemplate[templateID];
  }


//Map functions
//Access functions for the current map
  public static void setMap(TspMap newMap)
  {
      workingMap = newMap;
  }
  public static TspMap getMap()
  {
      return workingMap;
  }

}
