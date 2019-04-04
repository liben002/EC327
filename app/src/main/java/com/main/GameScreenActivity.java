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


        /*ImageView simpleImageView=(ImageView) findViewById(R.id.board);
        simpleImageView.setImageResource(R.drawable.boardimage);

        findViewById(R.id.piece1_player1);
        simpleImageView.setImageResource(R.drawable.piece_1);*/

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
