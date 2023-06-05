package com.example.appi_afsopgave_jozsef;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

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
                sendName();
            }
        });

    }

    private void  sendName(){
        String name = txtSendName.getText().toString();
        if (name.matches(""))
        {
            Toast.makeText(this,
                    "Please enter a name.",
                    Toast.LENGTH_SHORT).show();
        }
        else if (rbGroup.getCheckedRadioButtonId() == -1){
            Toast.makeText(this,
                    "Please choose one from above.",
                    Toast.LENGTH_SHORT).show();
        }
        else {
            // String type = send;
            intent.putExtra("txtNameToMain", name);
            intent.putExtra("txtNameToMain2", send);
            setResult(Activity.RESULT_OK, intent);
            finish();
        }

    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int checkedId){
            isCheckedId(checkedId);
    }

    private void isCheckedId(int checkedId) {
        if (checkedId == R.id.mother_id) {
            txtName.setText("Mother's name:");
            send = "Mother's name:";
        } else if (checkedId == R.id.father_id) {
            txtName.setText("Father's name:");
            send = "Father's name:";
        } else if (checkedId == R.id.cat_id) {
            txtName.setText("Cat's name:");
            send = "Cat's name:";
        } else if (checkedId == R.id.dog_id) {
            txtName.setText("Dog's name:");
            send = "Dog's name:";
        }
    }
}