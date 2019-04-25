package com.main;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.ur.Location;
import com.ur.Board;
import com.urai.BoardAI;
import java.lang.Math;
import com.RoyalGameofUr.ec327.R;

public class GameScreenActivity extends Activity {
//TODO Fix bugs, pop ups for actions

    // Shake detection variables
    private SensorManager mSensorManager;
    private Sensor mAccelerometer;
    private ShakeDetector mShakeDetector;


    Button rollButton;

    Location[] squareLocations;
    ImageView[] piecesImageViews;
    Location[] pieceStartLocations;
    Board board;

    TextView p1;
    TextView p2;

    int diceRoll;
    int gameStatus = 0;
    boolean activeAI = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // hide toolbar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);

        // hide navigation bar
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);

        setContentView(R.layout.activity_game_screen);


        // setup buttons
        rollButton = findViewById(R.id.rollButton);

        // initialize squares and pieces
        squareLocations = new Location[20];
        piecesImageViews = new ImageView[14];
        pieceStartLocations = new Location[14];

        // setup all the boards and pieces
        setup(squareLocations, piecesImageViews, pieceStartLocations);
        p1 = findViewById(R.id.player1Score);
        p2 = findViewById(R.id.player2Score);

        // game loop
        // click roll, choose piece, piece moves, next turn
        rollButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                diceRoll = 0;
                // weighted dice roll 0-4
                for (int i = 0; i < 4; i++) {
                    int individualRoll = (int) (Math.random() * 2);
                    diceRoll += individualRoll;
                }

                Context context = getApplicationContext();
                CharSequence text = "Roll: " + diceRoll;
                int duration = Toast.LENGTH_SHORT;

                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
                rollButton.setEnabled(false);
            }
        });

        // ShakeDetector initialization
        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mShakeDetector = new ShakeDetector();
        mShakeDetector.setOnShakeListener(new ShakeDetector.OnShakeListener() {

            @Override
            public void onShake(int count) {
                diceRoll = 0;
                // weighted dice roll 0-4
                for (int i = 0; i < 4; i++) {
                    int individualRoll = (int) (Math.random() * 2);
                    diceRoll += individualRoll;
                }

                Context context = getApplicationContext();
                CharSequence text = "Roll: " + diceRoll;
                int duration = Toast.LENGTH_SHORT;

                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
                rollButton.setEnabled(false);
            }
        });
    }

    public void setup(final Location[] squareLocations, ImageView[] piecesImageViews, final Location[] pieceStartLocations) {

        // iterate to find the button coordinates and set them in squareLocations
        for (int i = 0; i < squareLocations.length; i++) {
            String mapSquaresID = "square" + i + "Button";
            int resID = getResources().getIdentifier(mapSquaresID, "id", getPackageName());
            final Button currentButton = findViewById(resID);
            squareLocations[i] = new Location();
            final int index = i;

            // locations retrieved after the layout has been drawn
            currentButton.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                @Override
                public void onGlobalLayout() {

                    int[] location = new int[2];
                    currentButton.getLocationInWindow(location);
                    squareLocations[index].setX(location[0]);
                    squareLocations[index].setY(location[1]);
                    Log.d("target", "" + squareLocations[index].getX() + " " + squareLocations[index].getY());
                    currentButton.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                }
            });
            Log.d("targetOut", "" + squareLocations[index].getX() + " " + squareLocations[index].getY());
        }

        // Iterates to find all the ImageViews, gives them an index.
        int piecesImageViewsIndex = 0;
        for (int i = 1; i < 3; i++) {
            for (int j = 1; j < 8; j++) {
                String imageViewID = "piece" + j + "_player" + i;
                int resID = getResources().getIdentifier(imageViewID, "id", getPackageName());
                piecesImageViews[piecesImageViewsIndex] = findViewById(resID);
                piecesImageViews[piecesImageViewsIndex].setTag(piecesImageViewsIndex);
                piecesImageViewsIndex++;
            }
        }

        // Sets initial location of pieces.
        for (int i = 0; i < pieceStartLocations.length; i++) {
            final ImageView currentImageView = piecesImageViews[i];
            pieceStartLocations[i] = new Location();
            final int index = i;
            // Retrieves locations only after everything is drawn.
            currentImageView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                @Override
                public void onGlobalLayout() {

                    int[] location = new int[2];
                    currentImageView.getLocationInWindow(location);
                    pieceStartLocations[index].setX(location[0]);
                    pieceStartLocations[index].setY(location[1]);
                    Log.d("pieces", "" + currentImageView.getX() + " " + currentImageView.getY());
                    currentImageView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                }
            });
            Log.d("piecesOut", "" + currentImageView.getX() + " " + currentImageView.getY());
        }
        Log.d("squareLocation", "" + squareLocations[0].getX());
        if(!activeAI)
            board = new Board(squareLocations, pieceStartLocations);
        else
            board = new BoardAI(squareLocations, pieceStartLocations);
    }

    // When piece is clicked, if it's allowed to, it moves.
    public void buttonClicked(View view)
    {
        Log.d("click","button clicked");
        if (!rollButton.isEnabled())
        {
            int pieceIndex = (int) view.getTag();

            if((board.getTurn() == 1 && pieceIndex >= 7) || (board.getTurn() == 2 && pieceIndex < 7))
                return;
            gameStatus = board.updateBoardState(pieceIndex, diceRoll);

            //If playing AI, gets and performs the AI move
            if(activeAI)
            {
                diceRoll = 0;
                for(int i = 0; i < 4; i++)
                    diceRoll += (int)(Math.random()*2);
                gameStatus = board.updateBoardState(board.getAIMove(), diceRoll);
            }

            //Re-render all the pieces
            if (gameStatus == 0) {
                Location updateLoc;
                for(int j = 0; j < 14; j++) {
                    updateLoc = board.getPieceScreenLoc(j);
                    piecesImageViews[j].setX(updateLoc.getX());
                    piecesImageViews[j].setY(updateLoc.getY());
                }

                // Updates the score whenever a piece makes it to the end.
                int[] score = board.getScore();
                p1.setText(String.format("%d",score[0]));
                p2.setText(String.format("%d",score[1]));
                rollButton.setEnabled(true);
            }

            // If a player wins, go to the respective screen
            if (gameStatus == 1) {
                startActivity(new Intent(GameScreenActivity.this, WinScreenActivity.class));
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            } else if (gameStatus == 2) {
                startActivity(new Intent(GameScreenActivity.this, LoseScreenActivity.class));
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            }
        }
    }

    // Register Session Manager Listener onResume.
    @Override
    public void onResume() {
        super.onResume();
        mSensorManager.registerListener(mShakeDetector, mAccelerometer,	SensorManager.SENSOR_DELAY_UI);
    }

    // Unregistor Sensor Manager onPause.
    @Override
    public void onPause() {
        mSensorManager.unregisterListener(mShakeDetector);
        super.onPause();
    }

    // Overrides window change for navigation bar.
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

