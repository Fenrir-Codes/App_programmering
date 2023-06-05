package com.example.graphicsproject;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;



public class MyView extends View implements View.OnTouchListener
{
    MainActivity main;
    int xCenter = 100, yCenter = 100;
    int Radius = 50;
    int xPrev, yPrev;
    boolean moving;

    public MyView(Context context) {
        super(context);
        main = (MainActivity) context;
        this.setOnTouchListener(this);
    }

    @Override
    protected void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);

        Paint paint = new Paint();
        paint.setColor(Color.BLUE);
        canvas.drawCircle(xCenter,yCenter,Radius,paint);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event)
    {
        int fingers = event.getPointerCount();
        int xNew = (int) event.getX();
        int yNew = (int) event.getY();
        switch(event.getAction()){
            case MotionEvent.ACTION_DOWN:
                if (Math.sqrt((xNew - xCenter) * (xNew -xCenter)
                        + (yNew - yCenter) * (yNew -yCenter)) <= Radius)
                {
                    moving = true;
                    xPrev = xNew;
                    yPrev = yNew;
                }
                break;
                case MotionEvent.ACTION_MOVE:
                    if (moving)
                    {
                        int xDelta = xNew - xPrev;
                        int yDelta = yNew - yPrev;
                        xCenter += xDelta;
                        yCenter += yDelta;

                        xPrev = xNew;
                        yPrev = yNew;
                        invalidate();

                    }
                    break;
                    case MotionEvent.ACTION_UP:
                        moving = false;
                        break;
        }
        return true;
    }
}
