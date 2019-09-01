package com.example.ballbalancewithgyroscope;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    private Thread gameThread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startGameThread();
    }
    private void startGameThread() {
        gameThread = new Thread() {
            public void run() {
                while (true) {
                    long previousMillis = System.currentTimeMillis();
                    try {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                // Whatever you want to run over and over
                            }
                        });
                        Thread.sleep(Math.abs(previousMillis - System.currentTimeMillis() + 30));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        thread.start();
    }
}
