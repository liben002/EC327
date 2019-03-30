package com.process;

public class Board
{
    Player p1, p2;
    Square[] squares;

    //Constructor taking the boolean if the game is single or multi-player
    public Board(boolean isMulti, Location[] squareLocs)
    {
        //Set up players and/or AI if game is single- or multi-player
        if(isMulti)
        {
            p1 = new Player(1);
            p2 = new Player(2);
        }
        else
		{
			p1 = new Player(1);
			//Set up AI Player
		}

		//Sets up square locations
		for(int i = 0; i < squareLocs.length; i++)
		{
		    squares[i] = new Square(squareLocs[i]);
        }
    }

    //Updates the board state given the moving piece and the roll value
    public void updateBoardState(Piece p, int steps)
    {
        if(p == null)
        {
            return;
        }
    }

    //Returns all of the pieces currently on the board
    public Piece[] getPieces()
    {
        return null;
    }
}