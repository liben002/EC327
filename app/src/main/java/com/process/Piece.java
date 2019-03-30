package com.process;

public class Piece
{
    int trackLoc;
    Location screenLoc;

    public Piece(Location screenLoc)
    {
        trackLoc = -1;
        this.screenLoc = screenLoc;
    }

    public Location getScreenLoc()
    {
        return screenLoc;
    }
}