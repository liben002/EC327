package com.main;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import com.ur.Piece;

import java.lang.Math;

import com.example.ec327.R;

public class GameScreenActivity extends AppCompatActivity {

    //gameState: 1 for playing, 2 for player 1 won, 3 for player 2 won, 4 for lost (singleplayer)
    private int gameState = 1;
    private Piece[] player1Pieces = new Piece[7];
    private Piece[] player2Piece = new Piece[7];

    private boolean player1Turn = true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_screen);


        Button rollButton = (Button)findViewById(R.id.rollButton);
        Button passButton = (Button)findViewById(R.id.passButton);


        /*ImageView simpleImageView=(ImageView) findViewById(R.id.board);
        simpleImageView.setImageResource(R.drawable.boardimage);

        findViewById(R.id.piece1_player1);
        simpleImageView.setImageResource(R.drawable.piece_1);*/

        rollButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                int rollOutcome = (int) (Math.random() * 5);

                // roll Test
                Context context = getApplicationContext();
                CharSequence text = "Roll: " + rollOutcome;
                int duration = Toast.LENGTH_SHORT;

                Toast toast = Toast.makeText(context, text, duration);
                toast.show();

                if(player1Turn) {
                    
                } else {
                    //move the piece
                }
            }
        });


    }
}
