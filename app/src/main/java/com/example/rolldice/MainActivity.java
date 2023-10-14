package com.example.rolldice;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private ImageView imageDice1;
    private ImageView imageDice2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageDice1 = findViewById(R.id.imageDice1);
        imageDice2 = findViewById(R.id.imageDice2);

        Button rollButton = findViewById(R.id.rollButton);

        rollButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rollDices();
            }
        });
    }

    private void rollDices() {
        Random random = new Random();
        int randomNumberDice1 = random.nextInt(6) + 1;
        int randomNumberDice2 = random.nextInt(6) + 1;
        setImageResource(imageDice1, randomNumberDice1);
        setImageResource(imageDice2, randomNumberDice2);
    }

    private void setImageResource(ImageView imageView, int diceValue) {
        int[] diceImages = {
                R.drawable.dice1,
                R.drawable.dice2,
                R.drawable.dice3,
                R.drawable.dice4,
                R.drawable.dice5,
                R.drawable.dice6
        };

        int index = diceValue - 1;
        if (index >= 0 && index < diceImages.length) {
            imageView.setImageResource(diceImages[index]);
        }
    }
}
