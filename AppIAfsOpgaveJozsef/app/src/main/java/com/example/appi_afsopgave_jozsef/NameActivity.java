package com.example.appi_afsopgave_jozsef;


import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

public class NameActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener {

    Intent intent;

    Button btnSendName;
    TextView txtName;
    EditText txtSendName;
    RadioGroup rbGroup;

    String send;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_name);

        btnSendName = findViewById(R.id.btnSendName);
        txtSendName = findViewById(R.id.txtSendName);
        txtName = findViewById(R.id.txtName);
        rbGroup = findViewById(R.id.rbGroup);
        rbGroup.setOnCheckedChangeListener(this);

        intent = getIntent();

        btnSendName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = txtSendName.getText().toString();
                // String type = send;
                intent.putExtra("txtNameToMain", name);
                intent.putExtra("txtNameToMain2", send);
                setResult(Activity.RESULT_OK, intent);

                finish();
            }
        });

    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
        isCheckedId(checkedId);
    }

    private void isCheckedId(int checkedId) {
        switch (checkedId) {
            case R.id.mother:
                txtName.setText("Mother´s name:");
                send = "Mother´s name:";
                break;
            case R.id.father:
                txtName.setText("Father´s name:");
                send = "Father´s name:";
                break;
            case R.id.cat:
                txtName.setText("Cat´s name:");
                send = "Cat´s name:";
                break;
            case R.id.dog:
                txtName.setText("Dog´s name:");
                send = "Dog´s name:";
                break;
        }
    }
}