package com.main;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import com.example.ec327.R;

public class MainActivity extends Activity {
<<<<<<< HEAD
=======

    Button singleButton;
    Button multiButton;
>>>>>>> master

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        singleButton = findViewById(R.id.singleButton);
        multiButton = findViewById(R.id.multiButton);


<<<<<<< HEAD
        singleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, GameScreenActivity.class));
            }
        });


        multiButton.setOnClickListener(new View.OnClickListener() {
<<<<<<< HEAD
            @Override
=======
        startButton.setOnClickListener(new View.OnClickListener() {

>>>>>>> master
=======
>>>>>>> master
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, GameScreenActivity.class));
            }
        });
    }
}