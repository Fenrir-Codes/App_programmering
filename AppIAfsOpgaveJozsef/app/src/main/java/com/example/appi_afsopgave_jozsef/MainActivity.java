package com.example.appi_afsopgave_jozsef;

//imports
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    //A launcher for a previously-prepared call to start the process of executing
    ActivityResultLauncher<Intent> nameActivityLauncher;
    ActivityResultLauncher<Intent> colorActivityLauncher;

    //Initialize objects
    Button btnGetName;
    Button btnGetColor;
    TextView txtName;
    TextView txtType;
    LinearLayout layOut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Initialize buttons, etc...
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

        //Registering for activities : Name activity
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

        //color activity
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

    //Broadcast with intent
    @Override
    public void onClick(View view) {
        Intent intent = new Intent(MainActivity.this, ColorActivity.class);
        colorActivityLauncher.launch(intent);
    }
}