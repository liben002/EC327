package com.ur;

public class Piece
{
    //MEMBERS:
    //The index of the pieces location along its track (-1 is home; 20 is end)
    private int trackLoc;
    //The location on screen where the piece resides
    private Location screenLoc;


    //CONSTRUCTOR:
    //Default Constructor setting track location to home and screen location to null
    Piece()
    {
        trackLoc = -1;
        screenLoc = null;
    }


    //SETTERS AND GETTERS:
    //Setters
    void setTrackLoc(int i)
    {
        trackLoc = i;
    }
    void setScreenLoc(Location screenLoc)
    {
        this.screenLoc = screenLoc;
    }
    //Getters
    public int getTrackLoc()
    {
        return trackLoc;
    }
    Location getScreenLoc()
    {
        return screenLoc;
    }
}