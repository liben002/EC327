package com.ur;

public class Board
{
    //MEMBERS:
    private Player p1, p2;
    private Square[] squares;
    private Piece[] pieces = new Piece[14];


    //CONSTRUCTORS:
    //Constructor taking the boolean if the game is single or multiplayer
    public Board(Location[] squareLocations)
    {
        //Sets up players one and two
        p1 = new Player(1);
        p2 = new Player(2);

		//Sets up square locations
        squares = new Square[20];
		for(int i = 0; i < 20; i++)
		{
		    squares[i] = new Square(squareLocations[i]);
        }
    }


    //METHODS:
    //Updates the board state given the moving piece and the roll value
    public void updateBoardState(int pieceIndex, int steps)
    {
        //Return if passing the turn
        if(pieceIndex == -1)
        {
            return;
        }
        //Go through rules and update board state
    }


    //SETTERS AND GETTERS
    //Getter
    public Piece[] getPieces()
    {
        return pieces;
    }
}