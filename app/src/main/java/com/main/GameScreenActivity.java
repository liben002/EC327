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

        // setup the array of ImageViews
        piecesImageViews = new ImageView[14];
        piecesImageViews[0] = findViewById(R.id.piece1_player1);
        piecesImageViews[1] = findViewById(R.id.piece2_player1);
        piecesImageViews[2] = findViewById(R.id.piece3_player1);
        piecesImageViews[3] = findViewById(R.id.piece4_player1);
        piecesImageViews[4] = findViewById(R.id.piece5_player1);
        piecesImageViews[5] = findViewById(R.id.piece6_player1);
        piecesImageViews[6] = findViewById(R.id.piece7_player1);

        piecesImageViews[7] = findViewById(R.id.piece1_player2);
        piecesImageViews[8] = findViewById(R.id.piece2_player2);
        piecesImageViews[9] = findViewById(R.id.piece3_player2);
        piecesImageViews[10] = findViewById(R.id.piece4_player2);
        piecesImageViews[11] = findViewById(R.id.piece5_player2);
        piecesImageViews[12] = findViewById(R.id.piece6_player2);
        piecesImageViews[13] = findViewById(R.id.piece7_player2);

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
                            }
                        });

                        rollButton.setEnabled(true);
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
                            }
                        });

                        rollButton.setEnabled(true);
                    }
                }
            }
        });
    }
}

