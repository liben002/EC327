package com.ur;

public class Square
{
    //MEMBERS:
    //The location on screen where the square resides
    private Location screenLoc;
    //Whether or not the square is occuiped by a piece
    private boolean isOccupied;
    //Whether or not the square is a special one
    private boolean isRosette;


    //CONSTRUCTORS:
    //Default Constructor with a default location
    public Square()
    {
        screenLoc = new Location();
        isOccupied = false;
        isRosette = false;
    }

    //Constructor taking the squares location on the screen
    public Square(Location loc)
    {
        screenLoc = loc;
        isOccupied = false;
        isRosette = false;
    }


    //SETTERS AND GETTERS:
    //Setter
    public void setOccupied(boolean b)
    {
        isOccupied = b;
    }
    public void setRosette(boolean b)
    {
        isRosette = b;
    }
    //Getters
    public Location getScreenLoc()
    {
        return screenLoc;
    }
    public boolean isOccupied()
    {
        return isOccupied;
    }
    public boolean isRosette()
    {
        return isRosette;
    }
}