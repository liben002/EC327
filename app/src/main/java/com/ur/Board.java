package com.ur;

public class Board
{
    //MEMBERS:
    private boolean turn;
    private Player p1, p2;
    private Square[] squares;
    private Piece[] pieces;

    //CONSTRUCTORS:
    //Constructor taking an array of locations for the squares and starting pieces
    public Board(Location[] squareLocations, Location[] pieceStartLocations)
    {
        //Sets up players one and two
        p1 = new Player(1);
        p2 = new Player(2);

		//Sets up square locations
        squares = new Square[20];
		for(int i = 0; i < 20; i++)
		    squares[i] = new Square(squareLocations[i]);

        //Sets up the pieces
        pieces = new Piece[14];
		for(int i = 0; i < 14; i++)
		{
		    pieces[i] = new Piece();
            pieces[i].setScreenLoc(pieceStartLocations[i]);
        }

		//True is Player 1's turn; false is Player 2's turn
		turn = true;
    }


    //METHODS:
    //Updates the board state given the moving piece and the roll value
    //Returns 0 = ongoing; 1 = player 1 victory; 2 = player 2 victory
    public int updateBoardState(int pieceIndex, int steps)
    {
        //Return if passing the turn or if no piece moves
        if(pieceIndex == -1 || steps == 0)
        {
            //The other player's turn
            turn = !turn;
            return 0;
        }


        //Update piece location
        int newTrackLoc = pieces[pieceIndex].getTrackLoc() + steps;
        //If the piece reaches the goal
        if(newTrackLoc >= 14)
        {
            //The player scores
            if(turn)
                p1.incTokFinish();
            else
                p2.incTokFinish();
            //And the piece is removed from the game
            pieces[pieceIndex].setScreenLoc(null);
        }
        //If the piece moves to some new location on the board
        else
        {
            //The new square that the piece will move to
            int newSquareIndex;
            //The track the piece is currently on
            Track currentTrack;

            //Gets the relevant track information for the current player
            if(turn)
                currentTrack = p1.getTrack();
            else
                currentTrack = p2.getTrack();

            //Gets the new square index in squares
            newSquareIndex = currentTrack.getSquareIndex(newTrackLoc);
            //Moves the piece to the new square's screen location
            pieces[pieceIndex].setScreenLoc(squares[newSquareIndex].getScreenLoc());
            //Moves the piece to its new track location
            pieces[pieceIndex].setTrackLoc(newTrackLoc);
        }


        //Checks rules for any additional effects
        //TODO RULES


        //Checks if someone has won; if not, continuing the game as normal
        if(p1.getTokensFinish() == 7)
            return 1;
        if(p2.getTokensFinish() == 7)
            return 2;
        turn = !turn;
        return 0;
    }


    //SETTERS AND GETTERS:
    //Getters
    public Location getPieceScreenLoc(int i)
    {
        return pieces[i].getScreenLoc();
    }
    public int getTurn()
    {
        if(turn)
            return 1;
        return 2;
    }
    public int[] getScore()
    {
        return new int[]{p1.getTokensFinish(), p2.getTokensFinish()};
    }
}