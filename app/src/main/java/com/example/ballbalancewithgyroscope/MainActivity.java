package com.example.ballbalancewithgyroscope;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements SensorEventListener {
    private Thread gameThread;
    private GameView gameView;
    private SensorManager sm;
    private Sensor ax, ay, az;
    private List<Sensor> al;
    private float x, y, z, sensorX, sensorY, sensorZ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gameView = findViewById(R.id.gameView);

        sm = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        al = sm.getSensorList(Sensor.TYPE_ACCELEROMETER);
        System.out.println(al.size());

        if (al.size() != 0) {
            ax = al.get(0);
            ay = al.get(1);
            az = al.get(2);
            sm.registerListener(this, ax, SensorManager.SENSOR_DELAY_GAME);
            sm.registerListener(this, ay, SensorManager.SENSOR_DELAY_GAME);
            sm.registerListener(this, az, SensorManager.SENSOR_DELAY_GAME);
        }

        x = y = sensorX = sensorY = 0;

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
                                gameView.draw(sensorX, sensorY);
                            }
                        });
                        Thread.sleep(Math.abs(previousMillis - System.currentTimeMillis() + 30));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        gameThread.start();
    }

    @Override
    protected void onPause() {
        sm.unregisterListener(this, ax);
        sm.unregisterListener(this, ay);
        sm.unregisterListener(this, az);

        super.onPause();
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        try {
            Thread.sleep(16);
            sensorX = sensorEvent.values[0];
            sensorY = sensorEvent.values[1];
            sensorZ = sensorEvent.values[2];
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}
