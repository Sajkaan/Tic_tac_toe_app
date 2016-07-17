package com.sajkaan.tictac;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    //0 = superman, 1 = batman

    int activePlayer = 0;

    boolean gameIsActive =  true;

    //2 means unplayed

    int[] state={2, 2, 2, 2, 2, 2, 2, 2, 2};

    int[][] winningPositions = {{0, 1 , 2}, {3, 4 , 5}, {6, 7 ,8 }, {0 , 3 ,6 }, {1, 4 ,7}, {2, 5 , 8}, {0, 4 ,8} , {2, 4 , 6}};

    public void dropIn(View view){

        ImageView counter = (ImageView) view;



        int tappedCounter = Integer.parseInt(counter.getTag().toString());

        if(state[tappedCounter] == 2 && gameIsActive) {

            state[tappedCounter] = activePlayer;

            counter.setTranslationY(-1000f);

            if (activePlayer == 0) {

                counter.setImageResource(R.drawable.superman);

                activePlayer = 1;

            } else {

                counter.setImageResource(R.drawable.batman);

                activePlayer = 0;
            }

            counter.animate().translationYBy(1000f).rotation(360).setDuration(600);

            for(int[] winningPositions : winningPositions){


                if (state[winningPositions[0]] == state[winningPositions[1]] &&
                        state[winningPositions[1]] == state[winningPositions[2]] &&
                         state[winningPositions[0]] != 2){

                    //default string winner
                    String winner = "Batman";

                    if(state[winningPositions[0]] == 0 ){

                        winner = "Superman";
                    }

                    //Winner is decided

                    gameIsActive = false;

                    TextView winnerMessage = (TextView) findViewById(R.id.winnerMessage);

                    winnerMessage.setText(winner + " has won!");

                    LinearLayout layout  = (LinearLayout) findViewById(R.id.playAgainLayout);
                    layout.setVisibility(View.VISIBLE);

                } else{
                    boolean gameOver = true;

                    for(int counterState : state){

                        if(counterState == 2) gameOver = false;
                    }

                    if(gameOver){

                        TextView winnerMessage = (TextView) findViewById(R.id.winnerMessage);

                        winnerMessage.setText("It's draw.");

                        LinearLayout layout  = (LinearLayout) findViewById(R.id.playAgainLayout);
                        layout.setVisibility(View.VISIBLE);

                    }
                }
            }

        }
    }

    public void playAgain(View view){

        gameIsActive = true;

        LinearLayout layout  = (LinearLayout) findViewById(R.id.playAgainLayout);
        layout.setVisibility(View.INVISIBLE);

        //Reset the numbers

        int activePlayer = 0;

        for(int i = 0; i <state.length; i++){
            state[i] = 2;
        }

        GridLayout gridLayout = (GridLayout) findViewById(R.id.gridLayout);

        // set ImageView to be empty

        for(int i = 0; i<gridLayout.getChildCount(); i++ ){
            ((ImageView) gridLayout.getChildAt(i)).setImageResource(0);
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
