package dk.tec.accelerationsensors;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.view.View;
import androidx.core.content.res.ResourcesCompat;

public class BallView extends View implements SensorEventListener
{
    MainActivity main;
    float xMove = 0.0f;
    float yMove = 0.0f;
    int xPos = 300;
    int yPos = 300;
    Drawable drwBall;
    int drwWidth;
    int drwHeight;
    int viewWidth;
    int viewHeigth;

    public BallView(MainActivity mainActivity)
    {   super(mainActivity);
        main = mainActivity;

        drwBall = ResourcesCompat.getDrawable(getResources(), R.drawable.ball_basket, null);
        drwWidth = drwBall.getIntrinsicWidth() / 5;
        drwHeight = drwBall.getIntrinsicHeight() / 5;

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        viewWidth = w;
        viewHeigth = h;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        drwBall.setBounds(xPos, yPos, xPos + drwWidth, yPos + drwHeight);
        drwBall.draw(canvas);
    }

    @Override
    public void onSensorChanged(SensorEvent event)
    {
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            main.upDateValues(event.values);

            xMove = event.values[0];
            yMove = event.values[1];

            xPos -= xMove * 2;
            yPos += yMove * 2;


            if (xPos <= 0) {
                xPos = 0;
                xMove = 0;  // Stop horizontal movement
            }
            if (xPos >= viewWidth - drwWidth) {
                xPos = viewWidth - drwWidth;
                xMove = 0;  // Stop horizontal movement
            }
            if (yPos <= 0) {
                yPos = 0;
                yMove = 0;  // Stop vertical movement
            }
            if (yPos >= viewHeigth - drwHeight) {
                yPos = viewHeigth - drwHeight;
                yMove = 0;  // Stop vertical movement
            }
        }
        invalidate();
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
