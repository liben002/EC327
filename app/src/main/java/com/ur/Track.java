package com.ur;

class Track
{
    //MEMBERS:
    //The indices of the squares, in order, the pieces must travel
    private int[] squares = new int[14];


    //CONSTRUCTOR:
    //Constructor taking the player number and assigning the appropriate track
    Track(int player)
    {
        if(player == 1)
        {
            //Player One Track
            for(int i = 0; i < 14; i++)
                squares[i] = i;
        }
        else
        {
            //Player Two Track
            for(int i  = 0; i < 4; i++)
                squares[i] = i + 14;
            for(int i = 4; i < 12; i++)
                squares[i] = i;
            for(int i = 12; i < 14; i++)
                squares[i] = i + 6;
        }
    }


    //SETTERS AND GETTERS:
    //Getter
    int getSquareIndex(int i)
    {
        return squares[i];
    }
}