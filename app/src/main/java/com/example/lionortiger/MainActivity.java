package com.example.lionortiger;

import androidx.appcompat.app.AppCompatActivity;
import androidx.gridlayout.widget.GridLayout;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    enum Player{
        ONE,TWO,NO;
    }
    Player currentPlayer=Player.ONE;
    Player[] playerChoices=new Player[9];
    int [][] winningChoices={{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
    boolean gameOver=false;
    final int maxNoOfMoves=9;
    int moveCounter=0;
    Button resetButton;
    GridLayout gridLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gridLayout=findViewById(R.id.gridLayout);
        resetButton=findViewById(R.id.reset_button);
        for(int i=0;i<playerChoices.length;i++){
            playerChoices[i]=Player.NO;
        }
        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetTheGame();
            }
        });
    }
    public void onImageTapped(View imageView){
        ImageView tappedImageView=(ImageView) imageView;
        int imgTag=Integer.parseInt(imageView.getTag().toString());
        if(playerChoices[imgTag]==Player.NO&&gameOver==false) {
            playerChoices[imgTag] = currentPlayer;
            moveCounter=moveCounter+1;
            if (currentPlayer == Player.ONE) {
                tappedImageView.setImageResource(R.drawable.lion);
                tappedImageView.animate().alpha(1).rotationBy(3600).setDuration(500);
                //Toast.makeText(this, tappedImageView.getTag().toString(), Toast.LENGTH_SHORT).show();
                for (int[] winnerMoves : winningChoices) {
                    if (playerChoices[winnerMoves[0]] == playerChoices[winnerMoves[1]] && playerChoices[winnerMoves[1]] == playerChoices[winnerMoves[2]]
                            && playerChoices[winnerMoves[0]] != Player.NO) {
                        gameOver = true;
                        break;
                    }
                }
                if(moveCounter==maxNoOfMoves&&gameOver!=true){
                    Toast.makeText(this,"It's a draw!",Toast.LENGTH_SHORT).show();
                    resetButton.setVisibility(View.VISIBLE);
                }

                if (gameOver) {
                    Toast.makeText(this, "Player " + currentPlayer + " won the game.", Toast.LENGTH_SHORT).show();
                    resetButton.setVisibility(View.VISIBLE);
                }
                currentPlayer = Player.TWO;

            } else if (currentPlayer == Player.TWO) {
                tappedImageView.setImageResource(R.drawable.tiger);
                tappedImageView.animate().alpha(1).rotationBy(3600).setDuration(500);
                //Toast.makeText(this, tappedImageView.getTag().toString(), Toast.LENGTH_SHORT).show();
                for (int[] winnerMoves : winningChoices) {
                    if (playerChoices[winnerMoves[0]] == playerChoices[winnerMoves[1]] && playerChoices[winnerMoves[1]] == playerChoices[winnerMoves[2]]
                            && playerChoices[winnerMoves[0]] != Player.NO) {
                        gameOver = true;
                        break;
                    }
                }
                if(moveCounter==maxNoOfMoves){
                    gameOver=true;
                    resetButton.setVisibility(View.VISIBLE);
                }
                if (gameOver) {
                    Toast.makeText(this, "Player " + currentPlayer + " won the game.", Toast.LENGTH_SHORT).show();
                    resetButton.setVisibility(View.VISIBLE);
                }
                currentPlayer = Player.ONE;
            }
        }

        //
    }
    //reset the game
    public void resetTheGame(){
        for(int index=0; index<gridLayout.getChildCount(); index++){
            ImageView imageView=(ImageView) gridLayout.getChildAt(index);
            imageView.setImageDrawable(null);
            imageView.setAlpha(0.2f);

        }
        currentPlayer=Player.ONE;
        for(int i=0;i<playerChoices.length;i++){
            playerChoices[i]=Player.NO;
        }
        moveCounter=0;
        gameOver=false;
        resetButton.setVisibility(View.GONE);


    }
}