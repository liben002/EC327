package com.main;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
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
import com.ur.Square;

public class GameScreenActivity extends Activity {

    private Button rollButton;

    private Location[] squareLocations;
    private ImageView[] piecesImageViews;
    private Location[] pieceStartLocations;
    private Board board;

    int diceRoll;
    int gameStatus = 0;

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
        //passButton = findViewById(R.id.passButton);

        // initialize squares and pieces
        squareLocations = new Location[20];
        piecesImageViews = new ImageView[14];
        pieceStartLocations = new Location[14];

        // setup all the boards and pieces
        setup(squareLocations, piecesImageViews, pieceStartLocations);

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
    }

    public void setup(final Location[] squareLocations, ImageView[] piecesImageViews, final Location[] pieceStartLocations) {

        // iterate to find the button coordinates and set them in squareLocations
        for (int i = 0; i < 20; i++) {
            String mapSquaresID = "square" + i + "Button";
            int resID = getResources().getIdentifier(mapSquaresID, "id", getPackageName());
            final Button currentButton = findViewById(resID);

            // only after everything is drawn will the locations be retrieved
            currentButton.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                @Override
                public void onGlobalLayout() {
                    int[] location = new int[2];
                    currentButton.getLocationOnScreen(location);
                    Log.d("myTag", "" + currentButton.getX() + " " + currentButton.getY());
                    currentButton.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                }
            });
            squareLocations[i] = new Location((int) currentButton.getX(), (int) currentButton.getY());
        }

        // iterate to find all the ImageViews, give them an index
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

        // set initial location of pieces
        for (int i = 0; i < 14; i++) {
            final ImageView currentImageView = piecesImageViews[i];

            // only after everything is drawn will the locations be retrieved
            currentImageView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                @Override
                public void onGlobalLayout() {
                    int[] location = new int[2];
                    currentImageView.getLocationOnScreen(location);
                    Log.d("pieces", "" + currentImageView.getX() + " " + currentImageView.getY());
                    currentImageView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                }
            });
            pieceStartLocations[i] = new Location();
            pieceStartLocations[i].setX((int) piecesImageViews[i].getX());
            pieceStartLocations[i].setY((int) piecesImageViews[i].getY());
        }
        board = new Board(squareLocations, pieceStartLocations);
    }

    // when piece is clicked, if it's allowed to, it moves
    public void buttonClicked(View view) {
        if (!rollButton.isEnabled()) {
            int pieceIndex = (int) view.getTag();
            Log.d("pieceIndex", "" + pieceIndex);
            if (board.getTurn() == 1 && pieceIndex < 7) {
                gameStatus = board.updateBoardState(pieceIndex, diceRoll);
                if (gameStatus == 0) {
                    Location p1Loc = board.getPieceScreenLoc(pieceIndex);
                    piecesImageViews[pieceIndex].setX(p1Loc.getX());
                    piecesImageViews[pieceIndex].setY(p1Loc.getY());
                    rollButton.setEnabled(true);
                }
            } else if (board.getTurn() == 2 && pieceIndex >= 7) {
                gameStatus = board.updateBoardState(pieceIndex, diceRoll);
                if (gameStatus == 0) {
                    Location p1Loc = board.getPieceScreenLoc(pieceIndex);
                    piecesImageViews[pieceIndex].setX(p1Loc.getX());
                    piecesImageViews[pieceIndex].setY(p1Loc.getY());
                    rollButton.setEnabled(true);
                }
            }

            // if a player wins, go to the respective screen
            if (gameStatus == 1) {
                startActivity(new Intent(GameScreenActivity.this, WinScreenActivity.class));
            } else if (gameStatus == 2) {
                startActivity(new Intent(GameScreenActivity.this, LoseScreenActivity.class));
            }
        }
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

