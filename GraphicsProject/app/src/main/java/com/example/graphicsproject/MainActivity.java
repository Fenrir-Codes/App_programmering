package com.example.graphicsproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.LinearLayoutCompat;

import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity
{
    LinearLayoutCompat theLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        theLayout = findViewById(R.id.theLayout);
        Button btn = new Button(this);
        btn.setText("Ok");
        theLayout.addView(btn);

        MyView nv = new MyView(this);
        theLayout.addView(nv);
    }
}