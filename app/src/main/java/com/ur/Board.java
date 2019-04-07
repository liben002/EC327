package com.ur;

public class Board
{
    //MEMBERS:
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
    private boolean turn;
=======
    private int turn;
>>>>>>> master
=======
    private boolean turn;
>>>>>>> master
=======
    //Describes turn: true = p1; false = p2;
    private boolean turn;
    //Player objects for players one and two
>>>>>>> master
    private Player p1, p2;
    //An array of squares describing the board
    private Square[] squares;
    //An array of pieces on the board
    private Piece[] pieces;


    //CONSTRUCTOR:
    //Constructor taking an array of locations for the squares and starting pieces
    public Board(Location[] squareLocations, Location[] pieceStartLocations)
    {
        //Sets up players one and two
        p1 = new Player(1);
        p2 = new Player(2);

		//Sets up square locations and whether is rosette
        squares = new Square[20];
		for(int i = 0; i < 20; i++)
		{
            squares[i] = new Square(squareLocations[i]);
            if(i == 3 || i == 17 || i == 7 || i == 13 || i == 19)
                squares[i].setRosette(true);
            else
                squares[i].setRosette(false);
        }

        //Sets up the pieces
        pieces = new Piece[14];
		for(int i = 0; i < 14; i++)
<<<<<<< HEAD
		    pieces[i].setScreenLoc(pieceStartLocations[i]);

		//Sets the turn to Player One (false is Player Two)
		turn = true;
=======
		{
		    pieces[i] = new Piece();
            pieces[i].setScreenLoc(pieceStartLocations[i]);
        }

<<<<<<< HEAD
<<<<<<< HEAD
		//1 is Player 1's turn, 2 is Player 2's turn
		turn = 1;
>>>>>>> master
=======
		//True is Player 1's turn; false is Player 2's turn
=======
		//Sets turn to player one
>>>>>>> master
		turn = true;
>>>>>>> master
    }


    //METHODS:
    //Updates the board state given the moving piece and the roll value
    //Returns 0 = ongoing; 1 = player 1 victory; 2 = player 2 victory
    public int updateBoardState(int pieceIndex, int steps)
    {
        //Return if piece moves zero spaces:
        if(steps == 0)
        {
            //The other player's turn
<<<<<<< HEAD
<<<<<<< HEAD
            turn = !turn;
=======
            if (turn == 1) {
                turn  = 2;
            } else {
                turn = 1;
            }
>>>>>>> master
=======
            turn = !turn;
>>>>>>> master
            return 0;
        }


        //Checks rules for any additional effects:
        int newTrackLoc = pieces[pieceIndex].getTrackLoc() + steps;
        int squareIndex = updateScreenLoc(pieceIndex, newTrackLoc);
        //Lands on empty non-rosette
        //TODO

        //Lands on occupied non-rosette
        //TODO

        //Lands on empty rosette
        //TODO

        //Lands on occupied rosette
        //TODO


        //Update piece screen location:
        updateScreenLoc(pieceIndex, newTrackLoc);


        //Checks if someone has won; if not, continuing the game as normal:
        if(p1.getTokensFinish() == 7)
            return 1;
        if(p2.getTokensFinish() == 7)
            return 2;
        turn = !turn;
        return 0;
    }

    //Updates a piece's screen location given its id and track location
    //Returns the square index of the square that defines the new screen location
    private int updateScreenLoc(int pieceIndex, int newTrackLoc)
    {
        //If the piece reaches the end goal
        if(newTrackLoc >= 14)
        {
            //The player scores
<<<<<<< HEAD
<<<<<<< HEAD
            if(turn)
=======
            if(turn == 1)
>>>>>>> master
=======
            if(turn)
>>>>>>> master
                p1.incTokFinish();
            else
                p2.incTokFinish();
            //And the piece is removed from the game
            pieces[pieceIndex].setTrackLoc(20);
            pieces[pieceIndex].setScreenLoc(null);

            //Returns 20 for exiting the bounds of the square array
            return 20;
        }
        //If the piece moves to some new location on the board
        else
        {
            //The new square that the piece will move to
            int newSquareIndex;
            //The track the piece is currently on
            Track currentTrack;

            //Gets the relevant track information for the current player
<<<<<<< HEAD
<<<<<<< HEAD
            if(turn)
=======
            if(turn == 1)
>>>>>>> master
=======
            if(turn)
>>>>>>> master
                currentTrack = p1.getTrack();
            else
                currentTrack = p2.getTrack();

            //Gets the new square index for the squares array
            newSquareIndex = currentTrack.getSquareIndex(newTrackLoc);

            //Moves the piece to its new track location
            pieces[pieceIndex].setTrackLoc(newTrackLoc);
            //Moves the piece to the new square's screen location
            pieces[pieceIndex].setScreenLoc(squares[newSquareIndex].getScreenLoc());

<<<<<<< HEAD
        //Checks if someone has won; if not, continuing the game as normal
        if(p1.getTokensFinish() == 7)
            return 1;
        if(p2.getTokensFinish() == 7)
            return 2;
<<<<<<< HEAD
<<<<<<< HEAD
        turn = !turn;
=======
        if (turn == 1) {
            turn  = 2;
        } else {
            turn = 1;
        }
>>>>>>> master
=======
        turn = !turn;
>>>>>>> master
        return 0;
=======
            //Returns square index of new location
            return newSquareIndex;
        }
>>>>>>> master
    }


    //SETTERS AND GETTERS:
    //Special Getters
    //Returns the screen location of a specified piece in the piece array
    public Location getPieceScreenLoc(int i)
    {
        return pieces[i].getScreenLoc();
    }
<<<<<<< HEAD
<<<<<<< HEAD
    public boolean getTurn()
    {
        return turn;
=======
=======
    //Returns which player's turn it is as an int
>>>>>>> master
    public int getTurn()
    {
        return turn ? 1 : 2;
    }
    //Returns a two length int array with the player's current scores
    public int[] getScore()
    {
        return new int[]{p1.getTokensFinish(), p2.getTokensFinish()};
>>>>>>> master
    }
}