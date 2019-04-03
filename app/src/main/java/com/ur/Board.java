package com.ur;

public class Board
{
    //MEMBERS:
    private int boardState;
    private boolean turn;
    private Player p1, p2;
    private Square[] squares;
    private Piece[] pieces = new Piece[14];

    //CONSTRUCTORS:
    //Constructor taking an array of locations for the squares
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

        boardState = 0;
		turn = true;
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

        //Update piece location
        int currentTrackLoc = pieces[pieceIndex].getTrackLoc();
        int newSquareIndex = 0;
        Track currentTrack;

        if(turn)
            currentTrack = p1.getTrack();
        else
            currentTrack = p2.getTrack();

        newSquareIndex = currentTrack.getSquareIndex(currentTrackLoc + steps);
        pieces[pieceIndex].setScreenLoc(squares[newSquareIndex].getScreenLoc());
    }


    //SETTERS AND GETTERS
    //Getters
    public Location getPieceScreenLoc(int i)
    {
        return pieces[i].getScreenLoc();
    }
    public int getBoardState()
    {
        return boardState;
    }
    public boolean getTurn()
    {
        return turn;
    }
}