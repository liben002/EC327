package com.main;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;

import com.RoyalGameofUr.ec327.R;

public class MainActivity extends Activity {

    Button singleButton;
    Button multiButton;
    Button rulesButton;

    ImageView rulesBoard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // hide toolbar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);

        // hide navigation bar
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);

        setContentView(R.layout.activity_main);

        singleButton = findViewById(R.id.singleButton);
        multiButton = findViewById(R.id.multiButton);
        rulesButton = findViewById(R.id.rulesButton);
        rulesBoard = findViewById(R.id.rulesBoard);

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
    }

    // overriding window change for navigation bar
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            getWindow().getDecorView().setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        }
    }
}

