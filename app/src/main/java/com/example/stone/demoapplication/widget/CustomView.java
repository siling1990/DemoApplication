package com.example.stone.demoapplication.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.view.WindowManager;

public class CustomView extends android.support.v7.widget.AppCompatTextView {

    private Paint paint;
    private int lastX;
    private int lastY;


    public CustomView(Context context) {
        super(context);
        initDraw();
    }

    public CustomView(Context context, @Nullable  AttributeSet attrs) {
        super(context, attrs);
        initDraw();

    }

    public CustomView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initDraw();
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {

        int x = (int) event.getX();
        int y = (int) event.getY();

        int rawX = (int) event.getRawX();
        int rawY = (int) event.getRawY();

        int offsetX = x - lastX;
        int offsetY = y - lastY;

        ViewGroup mViewGroup = (ViewGroup) getParent();

        int pWidth = mViewGroup.getWidth();
        int pHeight = mViewGroup.getHeight();

        int left = getLeft() + offsetX;
        left = left <= 0 ? 0 : left;

        int top = getTop() + offsetY;
        top = top <= 0 ? 0 : top;

        int right = getRight() + offsetX;
        right = right >= pWidth ? pWidth : right;

        int bottom = getBottom() + offsetY;
        bottom = bottom >= pHeight ? pHeight : bottom;
        if(top==0){
            bottom =getHeight();
        }
        if(left==0){
            right=getWidth();
        }
        if(right==pWidth){
            left=pWidth-getWidth();
        }
        if(bottom==pHeight){
            top=pHeight-getHeight();
        }

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                lastX = x;
                lastY = y;
                break;
            case MotionEvent.ACTION_MOVE:
                layout(left, top, right, bottom);
                break;
            case MotionEvent.ACTION_UP:
                if (rawX <= pWidth / 2) {
                    layout(0, top, getWidth(), bottom);
                } else {
                    layout(pWidth - getWidth(), top, pWidth, bottom);
                }

                break;
        }

        return true;
    }

    private void initDraw() {
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.YELLOW);
        paint.setStrokeWidth(1.5f);
    }

    @Override
    protected void onDraw(Canvas canvas) {

        super.onDraw(canvas);
        int width = getWidth();
        int height = getHeight();
        canvas.drawLine(0, height / 2, width, height / 2, paint);
    }
}
