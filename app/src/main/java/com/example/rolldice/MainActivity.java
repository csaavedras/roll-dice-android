package com.example.rolldice;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;


import java.util.Random;

public class MainActivity extends AppCompatActivity implements ShakeDetector.OnShakeListener {
    private ImageView imageDice1;
    private ImageView imageDice2;

    private SensorManager mSensorManager;
    private Sensor mAccelerometer;
    private ShakeDetector mShakeDetector;

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

        // ShakeDetector initialization
        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mAccelerometer = mSensorManager
                .getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mShakeDetector = new ShakeDetector();
        mShakeDetector.setOnShakeListener(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        // Register the Sensor Manager Listener onResume
        mSensorManager.registerListener(mShakeDetector, mAccelerometer, SensorManager.SENSOR_DELAY_UI);
    }

    @Override
    public void onPause() {
        // Unregister the Sensor Manager Listener onPause
        mSensorManager.unregisterListener(mShakeDetector);
        super.onPause();
    }

    @Override
    public void onShake(int count) {
        rollDices();
    }

    @Override
    public void rollDices() {
        Random random = new Random();
        int randomNumberDice1 = random.nextInt(6) + 1;
        int randomNumberDice2 = random.nextInt(6) + 1;

        Animation rotateAnimation = AnimationUtils.loadAnimation(this, R.anim.rotate_animation);

        imageDice1.startAnimation(rotateAnimation);
        imageDice2.startAnimation(rotateAnimation);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                setImageResource(imageDice1, randomNumberDice1);
                setImageResource(imageDice2, randomNumberDice2);
            }
        }, 1000);
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
