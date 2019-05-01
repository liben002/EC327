package com.main;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;

import com.RoyalGameofUr.ec327.R;

/**
 * This is the code for The Royal Game of Ur, created as the final project for EC327.
 * The game is a simple to learn, fun to play, 1-2 player game.
 *
 * @author  Kenza Bensouda, Amanda Maas, Benjamin Li, John Wilkins, and Briana Zhao
 * @version 1.4
 * @since   2019/4/30
 */

public class MainActivity extends Activity {

    Button singleButton;
    Button multiButton;
    Button rulesButton;
    Button soundButton;

    ImageView rulesBoard;

    MediaPlayer audio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        // hide android toolbar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);

        // make navigation bar transparent
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

        setContentView(R.layout.activity_main);

        // initialize all buttons
        singleButton = findViewById(R.id.singleButton);
        multiButton = findViewById(R.id.multiButton);
        rulesButton = findViewById(R.id.rulesButton);
        soundButton = findViewById(R.id.soundButton);
        rulesBoard = findViewById(R.id.rulesBoard);

        // play the audio track
        audio = MediaPlayer.create(MainActivity.this,R.raw.sao_meo_orchestral_mix);
        audio.start();
        audio.setLooping(true);

        // when the single player button is clicked, change to game screen passing singlePlayer to
        // the activity
        singleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String value="singlePlayer";
                Intent i = new Intent(MainActivity.this, GameScreenActivity.class);
                i.putExtra("key",value);
                startActivity(i);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            }
        });

        // when the multi player button is clicked, change to game screen passing multiPlayer to
        // the activity
        multiButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String value="multiPlayer";
                Intent i = new Intent(MainActivity.this, GameScreenActivity.class);
                i.putExtra("key",value);
                startActivity(i);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            }
        });

        // toggle rule display
        rulesButton.setOnClickListener(new View.OnClickListener() {

            boolean visible;
            @Override
            public void onClick(View v) {

                visible = !visible;

                rulesBoard.setVisibility(visible ? View.VISIBLE: View.INVISIBLE);
                singleButton.setVisibility(visible ? View.INVISIBLE: View.VISIBLE);
                multiButton.setVisibility(visible ? View.INVISIBLE: View.VISIBLE);
            }
        });

        // toggle sound
        soundButton.setOnClickListener(new View.OnClickListener() {

            boolean sound;
            @Override
            public void onClick(View v) {
                sound = !sound;

                if (sound) {
                    audio.stop();
                    audio.release();
                }
                else {
                    audio = MediaPlayer.create(MainActivity.this,R.raw.sao_meo_orchestral_mix);
                    audio.start();
                    audio.setLooping(true);
                }
            }
        });
    }
}

