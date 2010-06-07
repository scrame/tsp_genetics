package tspg.core;


//Title:        Your Product Name
//Version:      
//Copyright:    Copyright (c) 1999
//Author:       Liam Christopher
//Company:      illogiKal integrity
//Description:  Your description
/**

      TspCity -- class that simply holds the coordinates of a city on the map,
      coordinates are integers and describe points on a standard cartesian plane


*/
import java.io.Serializable;
public class TspCity implements Serializable
{

//coordinates
  int cityX;
  int cityY;
//constructor to randomly make coordinates
  public TspCity()
  {
    cityX = (int) (Math.random()*SystemSettings.getMap().getMapWidth());
    cityY = (int) (Math.random()*SystemSettings.getMap().getMapHeight());

  }
//constructor that lets you set the coordinates
  public TspCity(int x, int y)
  {
    cityX = x;
    cityY = y;
  }
//access functions to get and set the coordinates
  public int getX()
  {
    return cityX;
  }
  public int getY()
  {
    return cityY;
  }
}