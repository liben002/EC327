package com.ur;

class Square
{
    //MEMBERS:
    //The location on screen where the square resides
    private Location screenLoc;
    //Whether or not the square is occupied by a piece
    private boolean isOccupied;
    //Whether or not the square is a special one
    private boolean isRosette;


    //CONSTRUCTOR:
    //Constructor taking the squares location on the screen
    Square(Location loc)
    {
        screenLoc = loc;
        isOccupied = false;
        isRosette = false;
    }


    //SETTERS AND GETTERS:
    //Setter
    void setOccupied(boolean b)
    {
        isOccupied = b;
    }
    void setRosette(boolean b)
    {
        isRosette = b;
    }
    //Getters
    Location getScreenLoc()
    {
        return screenLoc;
    }
    boolean isOccupied()
    {
        return isOccupied;
    }
    boolean isRosette()
    {
        return isRosette;
    }
}