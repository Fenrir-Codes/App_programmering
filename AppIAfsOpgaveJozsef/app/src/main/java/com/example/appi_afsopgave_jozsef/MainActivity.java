package com.example.appi_afsopgave_jozsef;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    ActivityResultLauncher<Intent> nameActivityLauncher;
    ActivityResultLauncher<Intent> colorActivityLauncher;

    Button btnGetName;
    Button btnGetColor;
    TextView txtName;
    TextView txtType;
    LinearLayout layOut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnGetName = findViewById(R.id.btnGetName);
        btnGetColor = findViewById(R.id.btnGetColor);
        txtName = findViewById(R.id.txtName);
        txtType = findViewById(R.id.txtType);
        layOut = findViewById(R.id.layOut);

        btnGetName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, NameActivity.class);
                nameActivityLauncher.launch(intent);
            }
        });

        btnGetColor.setOnClickListener(this);

        nameActivityLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        Intent intent = result.getData();
                        String text = intent.getStringExtra("txtNameToMain");
                        String type = intent.getStringExtra("txtNameToMain2");
                        txtName.setText(text);
                        txtType.setText(type);
                    }
                }
        );

        colorActivityLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        Intent intent = result.getData();
                        String color = intent.getStringExtra("txtColorToMain");
                        layOut.setBackgroundColor(Color.parseColor(color));

                    }
                }
        );
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(MainActivity.this, ColorActivity.class);
        colorActivityLauncher.launch(intent);
    }
}