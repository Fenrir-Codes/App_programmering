package com.example.appi_afsopgave_jozsef;


import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

public class ColorActivity extends AppCompatActivity implements View.OnClickListener {

    Intent intent;

    Button btnSendColor;
    Spinner spnFirst;
    Spinner spnSecond;
    Spinner spnThird;
    TextView txtColor;
    String firstChoice;
    String secondChoice;
    String thirdChoice;
    String hexColor;

    String[] firstspn = {"00", "10", "20", "30", "40", "50", "60", "70", "80", "90", "A0", "B0", "C0", "D0", "E0", "F0", "FF"};
    String[] secondspn = {"00", "10", "20", "30", "40", "50", "60", "70", "80", "90", "A0", "B0", "C0", "D0", "E0", "F0", "FF"};
    String[] thirdspn = {"00", "10", "20", "30", "40", "50", "60", "70", "80", "90", "A0", "B0", "C0", "D0", "E0", "F0", "FF"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_color);

        //Initialize
        btnSendColor = findViewById(R.id.btnSendColor);
        btnSendColor.setOnClickListener(this);
        spnFirst = findViewById(R.id.spnFirst);
        spnSecond = findViewById(R.id.spnSecond);
        spnThird = findViewById(R.id.spnThird);
        txtColor = findViewById(R.id.txtColor);

        //Initialize Intent component
        intent = getIntent();

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, firstspn);
        spnFirst.setAdapter(adapter);

        ArrayAdapter<String> adapter1 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, secondspn);
        spnSecond.setAdapter(adapter1);

        ArrayAdapter<String> adapter2 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, thirdspn);
        spnThird.setAdapter(adapter2);

        spnFirst.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                firstChoice = adapterView.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        spnSecond.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                secondChoice = adapterView.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        spnThird.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                thirdChoice = adapterView.getItemAtPosition(position).toString();

                hexColor = "#" + firstChoice + secondChoice + thirdChoice;
                Log.d("Color", hexColor);

                txtColor.setBackgroundColor(Color.parseColor(hexColor));            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }

        });



    }

    @Override
    public void onClick(View view) {
        intent.putExtra("txtColorToMain", hexColor);
        setResult(Activity.RESULT_OK, intent);

        finish();
    }
}