package com.ur;

public class BoardAI extends Board
{
    //MEMBERS:
    //The weights for the AI to select a piece
    private double[] weights;
    //Weight multipliers for different boolean objectives
    private final double ROSETTE = 100.0;
    private final double SCORE = 20.0;
    //Weight multipliers for different distance objectives
    private final double STOMP = 0.75;
    private final double STEP = 0.25;
    //Weight multipliers for different safety conditions
    private final double SAFETY = 2.0;


    //CONSTRUCTOR:
    //Constructor setting up a normal board from arguments and an AI
    public BoardAI(Location[] squareLocations, Location[] pieceStartLocations)
    {
        //Calls the normal Board constructor to set up
        super(squareLocations, pieceStartLocations);
        //AI is equally likely to pick any piece to start off with
        weights = new double[]{1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0};
    }


    //METHODS:
    //Chooses a piece to move; returns that piece id
    public int getAIMove(int steps)
    {
        updateWeights(steps);
        return choosePiece() + 7;
    }

    //Updates weight array
    private void updateWeights(int steps)
    {
        //If piece has reached the goal, it will no longer be selected
        for(int i = 0; i < 7; i++)
        {
            if(pieces[i+7].getTrackLoc() == 20)
                weights[i] = 0.0;
            else
                weights[i] = 1.0;
        }

        //Straight up nopes out before this zero can break anything
        if(steps == 0)
            return;

        //AI Implementation:
        //Iterates through pieces to update weights
        int squareIndex, newSquareIndex, trackLoc, newTrackLoc;
        double deltaSafety;
        System.out.println("PCE  ROS    SCR    STM    STP    SFT");
        for(int i = 0; i < 7; i++)
        {
            //Calculating current and new locations and indices:
            trackLoc = pieces[i+7].getTrackLoc();
            newTrackLoc = trackLoc + steps;
            try
            {
                while (trackLoc == 20) {
                    i++;
                    if (i == 7)
                        throw new RuntimeException();
                    trackLoc = pieces[i + 7].getTrackLoc();
                    newTrackLoc = trackLoc + steps;
                }
            } catch(Exception ex) {break;}
            if(trackLoc != -1)
                squareIndex = p2.getTrack().getSquareIndex(trackLoc);
            else
                squareIndex = -1;
            if(newTrackLoc < 14)
                newSquareIndex = p2.getTrack().getSquareIndex(newTrackLoc);
            else
                newSquareIndex = 20;


            //Adjusting Weights:
            //Boolean desirability to go for unoccupied rosettes
            if((newTrackLoc == 3 && !squares[17].isOccupied())
                    || (newTrackLoc == 7 && !squares[7].isOccupied())
                    || (newTrackLoc == 13 && !squares[19].isOccupied())) {
                weights[i] *= ROSETTE;
            }
            System.out.printf("%2d:  %.2f   ", i+7, weights[i]);

            //Boolean desirability to score
            if(newTrackLoc > 13)
                weights[i] *= SCORE;
            System.out.printf("%.2f   ", weights[i]);

            //Exponential desirability to stomp enemy as they approach the end
            //Also checks and stops self-stomping
            if(willStomp(newTrackLoc, i))
                weights[i] *= Math.pow(Math.E, STOMP*(newTrackLoc-4));
            System.out.printf("%.2f   ", weights[i]);

            //Exponential desirability to step forward as approach the end
            weights[i] *= Math.pow(Math.E, STEP*(newTrackLoc));
            System.out.printf("%.2f   ", weights[i]);

            //Calculating change in safety as ratio of probabilities of not getting stomped
            deltaSafety = (1-calcDanger(newSquareIndex)) / (1-calcDanger(squareIndex));
            //Scaled desirability to stay safer as approach the end
            if(deltaSafety > 1.0)
                weights[i] *= SAFETY * deltaSafety;
            else if(deltaSafety < 1.0)
                weights[i] *= 1/SAFETY * deltaSafety;
            System.out.printf("%.2f\n", weights[i]);
        }

        //TODO: Additional Strategies

        //Prints out new weights for reference
        double totalWeight = 0;
        for(int i = 0; i < 7; i++)
            totalWeight += weights[i];
        System.out.print("AI: ");
        for(int i = 0; i < 7; i++)
            System.out.print((i+7) + "[" + (int)(weights[i]/totalWeight*100) + "%] ");
        System.out.println();
    }

    //Chooses and returns a weighted random piece to move (0-6)
    private int choosePiece()
    {
        //Finds the total number of weights to choose from
        double totalWeights = 0.0;
        for(int i = 0; i < 7; i++)
            totalWeights += weights[i];
        //Chooses a random weight
        double selection = Math.random()*totalWeights;

        //Searches for the corresponding piece index
        double searcher = 0.0;
        for(int i = 0; i < 7; i++)
        {
            if(selection >= searcher && selection < searcher + weights[i])
                return i;
            else
                searcher += weights[i];
        }

        //Default return (should only happen if all weights are 0)
        return -1;
    }

    //Checks if moving a piece will stomp an enemy
    //Also checks and stops self stomping
    private boolean willStomp(int newTrackLoc, int pieceIndex)
    {
        //Checks not stomping own piece:
        //Checks if will slide off a rosette
        if((newTrackLoc == 3 && squares[17].isOccupied())
                || (newTrackLoc == 7 && squares[7].isOccupied())
                || (newTrackLoc == 13 && squares[19].isOccupied())) {
            newTrackLoc++;
        }
        //Checks if piece will go off the board
        if(newTrackLoc > 13)
            return false;
        //Checks if will stomp on own piece and stops it
        for(int i = 0; i < 7; i++)
        {
            if(pieces[i+7].getTrackLoc() == newTrackLoc)
                weights[pieceIndex] *= 0.0;
        }


        //Checks stomping on enemy piece on the shared track:
        if(newTrackLoc >= 4 && newTrackLoc < 12)
        {
            for (int i = 0; i < 7; i++)
            {
                if (pieces[i].getTrackLoc() == newTrackLoc)
                    return true;
            }
        }
        return false;
    }

    //Calculates probability of getting stomped on (0-1)
    private double calcDanger(int squareIndex)
    {
        //Checks if will slide off a rosette
        if((squareIndex == 3 && squares[17].isOccupied())
                || (squareIndex == 7 && squares[7].isOccupied())
                || (squareIndex == 13 && squares[19].isOccupied())) {
            squareIndex++;
        }

        //If anywhere except the non-rosette shared track, no chance of getting stomped
        if(squareIndex == -1 || squareIndex >= 14 || squareIndex == 7)
            return 0.0;

        //Iterates through the enemy pieces to find distances and add to the stomped probability
        int distance;
        double probability = 0.0;
        for(int i = 0; i < 7; i++)
        {
            distance = squareIndex - pieces[i].getTrackLoc();
            if(distance == 2)
                probability += .375;
            if(distance == 1 || distance == 3)
                probability += .25;
            if(distance == 4)
                probability += .0625;
        }

        return probability;
    }
}