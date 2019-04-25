package com.ur;

class Player {
    //MEMBERS:
    //The track that this player must follow
    private Track track;
    //The number of tokens at the start and finish respectively
    private int tokensFinish;


    //CONSTRUCTOR:
    //Constructor given player number sets tracks and tokens
    Player(int player)
    {
        //Creates an appropriate track for player number
        track = new Track(player);
        //Sets initial values for effective token locations
        tokensFinish = 0;
    }


    //METHODS:
    //Decrements and Increments number of start and finish tokens
    void incTokFinish()
    {
        tokensFinish++;
    }


    //GETTERS:
    //Getters
    Track getTrack()
    {
        return track;
    }
    int getTokensFinish()
    {
        return tokensFinish;
    }
}