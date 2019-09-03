package com.example.ballbalancewithgyroscope;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class GameView extends View {
    private Paint paint = new Paint();
    private float x, y, vx, vy, ax, ay;

    public GameView(Context context) {
        super(context);
    }
    public GameView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }
    public GameView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    @Override
    protected void onDraw(Canvas canvas) {
        paint.setColor(Color.GREEN);
        canvas.drawCircle(Screen.width / 2f + x, Screen.height / 2f + y, 30, paint);
        x += vx;
        y += vy;
        vx -= ax / 10;
        vy -= ay / 10;

        if (Math.abs(x) > Screen.width / 2f + 30) {
            paint.setColor(Color.RED);
            paint.setTextAlign(Paint.Align.CENTER);
            paint.setTextSize(Screen.height / 30);
            canvas.drawText("You Lose", Screen.width / 2, Screen.height / 2, paint);
        }

        super.onDraw(canvas);
    }
    public void draw(float sx, float sy) {
        ax = sx;
        ay = sy;
        invalidate();
        requestLayout();
    }
}
