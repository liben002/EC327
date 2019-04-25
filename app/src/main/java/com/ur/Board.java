package com.ur;

public class Board
{
    //MEMBERS:
    //Describes turn: true = p1; false = p2;
    private boolean turn;
    //Player objects for players one and two
    Player p1, p2;
    //An array of squares describing the board
    Square[] squares;
    //An array of pieces on the board
    Piece[] pieces;
    //An array of the pieces' starting locations
    private Location[] pieceStartLocations;


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
        {
            pieces[i] = new Piece();
            pieces[i].setScreenLoc(pieceStartLocations[i]);
        }

        //Saves the starting locations of the pieces
        this.pieceStartLocations = pieceStartLocations;

		//Sets turn to player one
		turn = true;
    }


    //METHODS:
    //Updates the board state given the moving piece and the roll value
    //Returns 0 = ongoing; 1 = player 1 victory; 2 = player 2 victory
    public int updateBoardState(int pieceIndex, int steps)
    {
        System.out.println("Player " + getTurn() + " rolled " + steps + " for piece " + pieceIndex);
        //Return if piece moves zero spaces:
        if(pieceIndex == -1 || steps == 0)
        {
            //The other player's turn
            turn = !turn;
            return 0;
        }

        //Checks the rules:

        //Getting data to check the rules:
        //If player gets a second roll
        boolean secondRoll = false;
        //The old track location
        int oldTrackLoc = pieces[pieceIndex].getTrackLoc();
        //The old square index
        int oldSquareIndex;
        //The new location on its track that the piece resides
        int newTrackLoc = oldTrackLoc + steps;
        //The new square that the piece will move to
        int newSquareIndex;

        //Calculating the essential locations and indices:
        //The track the piece is currently on
        Track currentTrack;
        //Gets the relevant track information for the current player
        if(turn)
            currentTrack = p1.getTrack();
        else
            currentTrack = p2.getTrack();
        //The pieces held in the current track
        Piece[] currentTrackPieces = new Piece[7];
        for(int i = 0; i < 7; i++)
            currentTrackPieces[i] = pieces[currentTrack.getPieceIndex(i)];

        //Checking basic rules to avoid out of bounds errors
        //If the piece reaches the end goal
        if(newTrackLoc >= 14)
        {
            //The player scores
            if(turn)
                p1.incTokFinish();
            else
                p2.incTokFinish();
            //And the piece is removed from the game
            pieces[pieceIndex].setTrackLoc(20);
            pieces[pieceIndex].setScreenLoc(new Location(-100,-100));
            newSquareIndex = 20;
        }
        //If the piece is still on the board
        else
        {
            //Gets the new square index for the squares array
            newSquareIndex = currentTrack.getSquareIndex(newTrackLoc);
        }
        //Moves piece off its old square if there is an old square
        if(oldTrackLoc != -1)
        {
            oldSquareIndex = currentTrack.getSquareIndex(oldTrackLoc);
            squares[oldSquareIndex].setOccupied(false);
        }

        //Running the data through the rule set:
        //Checks if piece is still on the board
        System.out.println("New Square Index: " + newSquareIndex);
        if(newSquareIndex != 20)
        {
            //Lands on empty non-rosette
            if (!squares[newSquareIndex].isOccupied() && !squares[newSquareIndex].isRosette())
            {
                System.out.println("Lands on empty non-rosette.");
                squares[newSquareIndex].setOccupied(true);
            }

            //Lands on occupied non-rosette
            else if (squares[newSquareIndex].isOccupied() && !squares[newSquareIndex].isRosette())
            {
                System.out.println("Lands on occupied non-rosette.");
                //Iterates through the pieces to find the piece to send back home
                int iterator;
                for(int i = 0; i < 7; i++)
                {
                    //Self-Stomping
                    iterator = i + 7*(turn ? 0 : 1);
                    if(iterator != pieceIndex && currentTrackPieces[i].getTrackLoc() == newTrackLoc)
                    {
                        currentTrackPieces[i].setTrackLoc(-1);
                        currentTrackPieces[i].setScreenLoc(pieceStartLocations[iterator]);
                        System.out.println("Piece " + iterator + " sent back home.");
                    }
                    //Enemy-Stomping
                    iterator = i + 7*(turn ? 1 : 0);
                    if(pieces[iterator].getTrackLoc() == newTrackLoc)
                    {
                        if(newTrackLoc >= 4 && newTrackLoc < 12)
                        {
                            pieces[iterator].setTrackLoc(-1);
                            pieces[iterator].setScreenLoc(pieceStartLocations[iterator]);
                            System.out.println("Piece " + iterator + " sent back home.");
                        }
                    }
                }
            }

            //Lands on empty rosette
            else if (!squares[newSquareIndex].isOccupied() && squares[newSquareIndex].isRosette())
            {
                System.out.println("Lands on empty rosette.");
                squares[newSquareIndex].setOccupied(true);
                secondRoll = true;
            }

            //Lands on occupied rosette
            else if (squares[newSquareIndex].isOccupied() && squares[newSquareIndex].isRosette())
            {
                System.out.println("Lands on occupied rosette.");
                //Update piece's track and screen location
                //Moves the piece to its new track location
                pieces[pieceIndex].setTrackLoc(newTrackLoc);
                //Moves the piece to the new square's screen location
                pieces[pieceIndex].setScreenLoc(squares[newSquareIndex].getScreenLoc());

                //Moves another step
                int temp = updateBoardState(pieceIndex, 1);
                System.out.println("Rosette index: " + newSquareIndex);
                squares[newSquareIndex].setOccupied(true);
                System.out.println("---------------------------------------------------------");
                return temp;
            }


            //Update piece's track and screen location:
            //Moves the piece to its new track location
            pieces[pieceIndex].setTrackLoc(newTrackLoc);
            //Moves the piece to the new square's screen location
            pieces[pieceIndex].setScreenLoc(squares[newSquareIndex].getScreenLoc());
            System.out.println("Piece " + pieceIndex + " :: New Track Loc: " + newTrackLoc);
        }
        else
            System.out.println("SCORE!");


        //Checks if someone has won, gets a second roll, or continues the game as normal:
        if(p1.getTokensFinish() == 7)
            return 1;
        if(p2.getTokensFinish() == 7)
            return 2;
        if(!secondRoll)
            turn = !turn;
        return 0;
    }

    //Placeholder method for AI subclass
    public int getAIMove(int steps)
    {
        return -1;
    }


    //GETTERS:
    //Special Getters
    //Returns the screen location of a specified piece in the piece array
    public Location getPieceScreenLoc(int i)
    {
        return pieces[i].getScreenLoc();
    }
    //Returns which player's turn it is as an int
    public int getTurn()
    {
        return turn ? 1 : 2;
    }
    //Returns a two length int array with the player's current scores
    public int[] getScore()
    {
        return new int[]{p1.getTokensFinish(), p2.getTokensFinish()};
    }
}