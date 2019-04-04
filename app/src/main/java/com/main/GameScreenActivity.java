package com.main;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import com.ur.Location;
import com.ur.Piece;
import com.ur.Board;
import java.lang.Math;
import com.example.ec327.R;
import com.ur.Location;

public class GameScreenActivity extends Activity {

    private Button rollButton;
    private Button passButton;

    Location[] squareLocs;
    ImageView[] piecesImageViews;
    Location[] pieces;
    Board board;

    int diceRoll = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_game_screen);


        // setup buttons
        rollButton = findViewById(R.id.rollButton);
        passButton = findViewById(R.id.passButton);

        // setup locations of the squares
        squareLocs = new Location[20];
        for (int i = 0; i < 20; i++) {
            //TODO get actual locations
            squareLocs[i] = new Location();
            squareLocs[i].setX(i);
            squareLocs[i].setY(i);
        }

        //TODO make setup into a function

        // setup the array of ImageViews
        piecesImageViews = new ImageView[14];
        int piecesImageViewsIndex = 0;

        // iterate to find all the ImageViews
        for (int i = 1; i < 3; i++) {
            for (int j = 1; j < 8; j++) {
                String imageViewID = "piece" + j + "_player" + i;
                int resID = getResources().getIdentifier(imageViewID, "id", getPackageName());
                piecesImageViews[piecesImageViewsIndex] = findViewById(resID);
                piecesImageViewsIndex++;
                //buttons[i][j].setOnClickListener(this);
            }
        }

        // setup array of starting locations
        pieces = new Location[14];
        for (int i = 0; i < 14; i++) {
            pieces[i] = new Location();
            pieces[i].setX(piecesImageViews[i].getLeft());
            pieces[i].setY(piecesImageViews[i].getTop());
        }

        // setup board
        board = new Board(squareLocs, pieces);

        // game loop
        // click roll, choose piece, piece moves, next turn
        rollButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                // weighted dice roll 0-4
                for (int i = 0; i < 5; i++) {
                    int individualRoll = (int) (Math.random() * 2);
                    diceRoll += individualRoll;
                }

                Context context = getApplicationContext();
                CharSequence text = "Roll: " + diceRoll;
                int duration = Toast.LENGTH_SHORT;

                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
                rollButton.setEnabled(false);

                // after player rolls, choose piece
                if (!rollButton.isEnabled()) {

                    // Player 1's turn
                    if (board.getTurn() == 1) {
                        piecesImageViews[0].setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                board.updateBoardState(0, diceRoll);
                                Location p1Loc = board.getPieceScreenLoc(0);
                                piecesImageViews[0].setX(p1Loc.getX());
                                piecesImageViews[0].setY(p1Loc.getY());
                                rollButton.setEnabled(true);
                            }
                        });
                    }

                    // Player 2's turn
                    if(board.getTurn() == 2) {
                        piecesImageViews[7].setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                board.updateBoardState(0, diceRoll);
                                Location p1Loc = board.getPieceScreenLoc(0);
                                piecesImageViews[7].setX(p1Loc.getX());
                                piecesImageViews[7].setY(p1Loc.getY());
                                rollButton.setEnabled(true);
                            }
                        });
                    }
                    diceRoll = 0;
                }
            }
        });
    }
}

