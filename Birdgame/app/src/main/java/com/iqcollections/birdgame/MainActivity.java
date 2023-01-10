package com.iqcollections.birdgame;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        BirdGame birdGame = new BirdGame(this);

        Handler handler = new Handler();
        final long TIME_INTERVAL =30;

        Timer timer = new Timer();
        setContentView(birdGame);
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
               handler.post(new Runnable() {
                   @Override
                   public void run() {
                       birdGame.invalidate();
                   }
               });
            }
        };
        timer.schedule(task,0,TIME_INTERVAL);



    }
}