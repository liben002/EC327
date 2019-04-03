package com.ur;

public class Square
{
    //MEMBERS:
    //The location on screen where the square resides
    private Location screenLoc;

    private boolean isRosette;


    //CONSTRUCTORS:
    //Default Constructor
    public Square()
    {
        screenLoc = new Location();
    }
    //Constructor taking the squares location on the screen
    public Square(Location loc)
    {
        screenLoc = loc;
    }


    //GETTERS:
    //Getter
    public Location getScreenLoc()
    {
        return screenLoc;
    }
}