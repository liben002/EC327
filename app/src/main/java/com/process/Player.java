package com.process;

public class Player
{
    Track track;
    Piece[] tokens = new Piece[7];
    int tokensStart, tokensFinish;

    //Default Constructor
    public Player(int i)
    {
        track = new Track(i);
    }
}