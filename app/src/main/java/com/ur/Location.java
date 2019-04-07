package com.ur;

public class Location
{
    //MEMBERS:
    //X and Y coordinates defining a position on the screen
    private int x, y;


    //CONSTRUCTORS:
    //Default Constructor setting both x and y to 0
    public Location()
    {
        x = 0;
        y = 0;
    }

    //Constructor taking the x and y coordinates defining a position on the screen
    public Location(int x, int y)
    {
        this.x = x;
        this.y = y;
    }


    //SETTERS AND GETTERS:
    //Setters
    public void setX(int x)
    {
        this.x = x;
    }
    public void setY(int y)
    {
        this.y = y;
    }
    //Getters
    public int getX()
    {
        return x;
    }
    public int getY()
    {
        return y;
    }
}