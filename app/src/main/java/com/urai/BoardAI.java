package com.urai;

//IMPORT:
//Imports all of com.ur
import com.ur.*;

public class BoardAI extends Board
{
    //MEMBERS:
    //The weights for the AI to select a piece
    double[] weights;


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
    public int getAIMove()
    {
        updateWeights();
        return choosePiece() + 7;
    }

    //Updates weight array
    private void updateWeights()
    {
        //If piece has reached the goal, will no longer be selected
        for(int i = 0; i < 7; i++)
            if(pieces[i].getTrackLoc() == 20)
                weights[i] = 0.0;
        //TODO
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
}