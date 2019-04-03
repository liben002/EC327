package com.main;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.ec327.R;

public class GameScreenActivity extends AppCompatActivity {

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



    }
}
