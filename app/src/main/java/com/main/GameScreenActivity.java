package com.main;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import com.ur.Location;
import com.ur.Piece;
import com.ur.Board;
import java.lang.Math;
import com.example.ec327.R;
import com.ur.Location;

public class GameScreenActivity extends AppCompatActivity {
    public static GameScreenActivity gameScreenActivity;

    //gameState: 1 for playing, 2 for player 1 won, 3 for player 2 won, 4 for lost (singleplayer)
    private int gameState = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_screen);

        //setup
        boolean isMulti = true;
        Location[] squareLocs = new Location[20];
        for(int i = 0; i < 20; i++) {
            //TODO get actual locations
            squareLocs[i].setX(i);
            squareLocs[i].setY(i);
        }
        final Board board = new Board(squareLocs);

        Button rollButton = (Button)findViewById(R.id.rollButton);
        Button passButton = (Button)findViewById(R.id.passButton);
        ImageView test = (ImageView)findViewById(R.id.piece1_player1);

        ImageView piece1_player1 = (ImageView) findViewById(R.id.piece1_player1);
        ImageView piece2_player1 = (ImageView) findViewById(R.id.piece2_player1);
        ImageView piece3_player1 = (ImageView) findViewById(R.id.piece3_player1);
        ImageView piece4_player1 = (ImageView) findViewById(R.id.piece4_player1);
        ImageView piece5_player1 = (ImageView) findViewById(R.id.piece5_player1);
        ImageView piece6_player1 = (ImageView) findViewById(R.id.piece6_player1);
        ImageView piece7_player1 = (ImageView) findViewById(R.id.piece7_player1);

        ImageView piece1_player2 = (ImageView) findViewById(R.id.piece1_player2);
        ImageView piece2_player2 = (ImageView) findViewById(R.id.piece2_player2);
        ImageView piece3_player2 = (ImageView) findViewById(R.id.piece3_player2);
        ImageView piece4_player2 = (ImageView) findViewById(R.id.piece4_player2);
        ImageView piece5_player2 = (ImageView) findViewById(R.id.piece5_player2);
        ImageView piece6_player2 = (ImageView) findViewById(R.id.piece6_player2);
        ImageView piece7_player2 = (ImageView) findViewById(R.id.piece7_player2);

        //Array of starting locations
        Location[] pieces = new Location[14];

        //Player 1 pieces
        pieces[0].setX(piece1_player1.getLeft());
        pieces[0].setY(piece1_player1.getTop());
        pieces[1].setX(piece2_player1.getLeft());
        pieces[1].setY(piece2_player1.getTop());
        pieces[2].setX(piece3_player1.getLeft());
        pieces[2].setY(piece3_player1.getTop());
        pieces[3].setX(piece4_player1.getLeft());
        pieces[3].setY(piece4_player1.getTop());
        pieces[4].setX(piece5_player1.getLeft());
        pieces[4].setY(piece5_player1.getTop());
        pieces[5].setX(piece6_player1.getLeft());
        pieces[5].setY(piece6_player1.getTop());
        pieces[6].setX(piece7_player1.getLeft());
        pieces[6].setY(piece7_player1.getTop());
        //Player 2 pieces
        pieces[7].setX(piece1_player2.getLeft());
        pieces[7].setY(piece1_player2.getTop());
        pieces[8].setX(piece2_player2.getLeft());
        pieces[8].setY(piece2_player2.getTop());
        pieces[9].setX(piece3_player2.getLeft());
        pieces[9].setY(piece3_player2.getTop());
        pieces[10].setX(piece4_player2.getLeft());
        pieces[10].setY(piece4_player2.getTop());
        pieces[11].setX(piece5_player2.getLeft());
        pieces[11].setY(piece5_player2.getTop());
        pieces[12].setX(piece6_player2.getLeft());
        pieces[12].setY(piece6_player2.getTop());
        pieces[13].setX(piece7_player2.getLeft());
        pieces[13].setY(piece7_player2.getTop());



        rollButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                int diceRoll = 0;
                for (int i = 0; i < 5; i++) {
                    int individualRoll = (int) (Math.random() * 2);
                    diceRoll += individualRoll;
                }



                // roll Test
                Context context = getApplicationContext();
                CharSequence text = "Roll: " + diceRoll;
                int duration = Toast.LENGTH_SHORT;

                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
                while(gameState == 0) {
                    for(int i = 0; i < 14; i ++) {
                        System.out.println(board.getPieceScreenLoc(i));
                    }
                }
            }
        });


    }
}
