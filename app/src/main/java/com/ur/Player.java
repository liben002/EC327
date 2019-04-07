package com.ur;

public class Player
{
    //MEMBERS:
    //The track that this player must follow
    private Track track;
    //An array of indices for pieces that this player controls
    private int[] tokens = new int[7];
    //The number of tokens at the start and finish respectively
    private int tokensStart, tokensFinish;


    //CONSTRUCTORS:
    //Default Constructor assumes it is building a player one
    public Player()
    {
        //Creates the player one track
        track = new Track(1);
        //Sets the tokens for player one
        for (int i = 0; i < 7; i++)
            tokens[i] = i;
        //Sets initial values for effective token locations
        tokensStart = 7;
        tokensFinish = 0;

    }

    //Constructor given player number sets tracks and tokens
    public Player(int player)
    {
        //Creates an appropriate track for player number
        track = new Track(player);
        //Sets the tokens appropriate for player number
        if(player == 1)
        {
            for (int i = 0; i < 7; i++)
                tokens[i] = i;
        }
        else if(player == 2)
        {
            for (int i = 0; i < 7; i++)
                tokens[i] = i + 7;
        }
        //Sets initial values for effective token locations
        tokensStart = 7;
        tokensFinish = 0;
    }


    //METHODS:
    //Decrements and Increments number of start and finish tokens
    public void decTokStart()
    {
        tokensStart--;
    }
    public void incTokStart()
    {
        tokensStart++;
    }
    public void incTokFinish()
    {
        tokensFinish++;
    }


    //SETTERS AND GETTERS:
    //Setters
    public void setTokensStart(int n)
    {
        tokensStart = n;
    }
    public void setTokensFinish(int n)
    {
        tokensFinish = n;
    }
    //Getters
    public Track getTrack()
    {
        return track;
    }
    public int[] getTokens()
    {
        return tokens;
    }
    public int getTokensStart()
    {
        return tokensStart;
    }
    public int getTokensFinish()
    {
        return tokensFinish;
    }
}