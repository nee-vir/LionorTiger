package com.example.lionortiger;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    enum Player{
        ONE,TWO;
    }
    Player currentPlayer=Player.ONE;
    Player[] playerChoices=new Player[9];
    int [][] winningChoices={{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void onImageTapped(View imageView){
        ImageView tappedImageView=(ImageView) imageView;
        int imgTag=Integer.parseInt(imageView.getTag().toString());
        playerChoices[imgTag]=currentPlayer;
        if(currentPlayer==Player.ONE){
            tappedImageView.setImageResource(R.drawable.lion);
            tappedImageView.animate().alpha(1).rotation(3600).setDuration(500);
            currentPlayer=Player.TWO;
            Toast.makeText(this, tappedImageView.getTag().toString(), Toast.LENGTH_SHORT).show();
        }
        else if(currentPlayer==Player.TWO){
            tappedImageView.setImageResource(R.drawable.tiger);
            tappedImageView.animate().alpha(1).rotation(3600).setDuration(500);
            currentPlayer=Player.ONE;
            Toast.makeText(this, tappedImageView.getTag().toString(), Toast.LENGTH_SHORT).show();
        }

        //



    }
}