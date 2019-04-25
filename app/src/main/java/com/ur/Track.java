package com.ur;

class Track
{
    //MEMBERS:
    //The indices of the squares, in order, the pieces must travel
    private int[] squares = new int[14];
    //The indices of the pieces that traverse this track
    private int[] pieces = new int[7];


    //CONSTRUCTOR:
    //Constructor taking the player number and assigning the appropriate track
    Track(int player)
    {
        if(player == 1)
        {
            //Player One Track
            for(int i = 0; i < 14; i++)
                squares[i] = i;

            //Player One Pieces
            for(int i = 0; i < 7; i++)
                pieces[i] = i;
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

            //Player Two Pieces
            for(int i = 0; i < 7; i++)
                pieces[i] = i + 7;
        }
    }


    //GETTERS:
    //Getters
    public int getSquareIndex(int i)
    {
        return squares[i];
    }
    int getPieceIndex(int i)
    {
        return pieces[i];
    }
}