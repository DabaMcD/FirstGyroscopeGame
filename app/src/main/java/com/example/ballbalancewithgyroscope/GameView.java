package com.example.ballbalancewithgyroscope;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class GameView extends View {
    private Context context;
    public GameView(Context context) {
        super(context);
        this.context = context;
    }
    public GameView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
    }
    public GameView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
    }
    @Override
    protected void onDraw(Canvas canvas) {


        super.onDraw(canvas);
    }
    public void draw() {
        invalidate();
        requestLayout();
    }
}
