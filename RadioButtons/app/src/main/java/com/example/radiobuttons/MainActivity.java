package com.example.radiobuttons;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    RadioGroup rdgChoose;
    TextView txtChoosen;
    Button btnWho;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rdgChoose = findViewById(R.id.radioGroup);
        txtChoosen = findViewById(R.id.txtChooseOne);
        btnWho = findViewById(R.id.btnWho);

        btnWho.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selectedRadioButtonId = rdgChoose.getCheckedRadioButtonId();
                if (selectedRadioButtonId != -1) {
                    RadioButton selectedRadioButton = findViewById(selectedRadioButtonId);
                    String selectedOption = selectedRadioButton.getText().toString();
                    txtChoosen.setText(selectedOption);
                } else {
                    txtChoosen.setText("No option selected");
                }
            }
        });


    }
}