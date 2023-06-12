package com.example.personclient;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.ToggleButton;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewPerson extends AppCompatActivity {

    Intent intent;
    Spinner spnHairColor;
    Person newPerson;
    RadioButton rbtCat;
    RadioButton rbtDog;
    RadioButton rbtBird;
    TextView txtSaved;
    ToggleButton btnIsFavorit;

    String[] hairColor = {"Blond", "Black", "Brown", "Gray"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_person);

        EditText editName = findViewById(R.id.editName);
        EditText editTlf = findViewById(R.id.editTlf);
        EditText editJob = findViewById(R.id.editJob);
        spnHairColor = findViewById(R.id.spnHairColor);
        btnIsFavorit = findViewById(R.id.btnIsFavorit);
        txtSaved = findViewById(R.id.txtSaved);

        rbtCat = findViewById(R.id.rbtCat);
        rbtDog = findViewById(R.id.rbtDog);
        rbtBird = findViewById(R.id.rbtBird);

        RadioGroup rbGroup = findViewById(R.id.rbGroup);

        Button btnSave = findViewById(R.id.btnSave);
        Button btnCancel = findViewById(R.id.btnCancel);

        //make an adapter for the spinner
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                com.google.android.material.R.layout.support_simple_spinner_dropdown_item, hairColor);
        spnHairColor.setAdapter(adapter);

        //save button
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // getting the data for different elements
                String name = editName.getText().toString();
                int tlf = Integer.parseInt(editTlf.getText().toString());
                String job = editJob.getText().toString();
                int hairColorIndex = spnHairColor.getSelectedItemPosition();
                int pet = 0;
                if (rbtCat.isChecked()) {
                    pet = 1;
                } else if (rbtDog.isChecked()) {
                    pet = 2;
                } else if (rbtBird.isChecked()) {
                    pet = 3;
                }

                boolean isFavorit = btnIsFavorit.isChecked();

                //person object
                newPerson = new Person();

                newPerson.setName(name);
                newPerson.setTlf(tlf);
                newPerson.setJob(job);
                newPerson.setHairColor(hairColorIndex);
                newPerson.setPet(String.valueOf(pet));
                newPerson.setFavorit(isFavorit);

                // Send the new person
                PersonService personService = ServiceBuilder.buildService(PersonService.class);
                Call<Person> request = personService.addPerson(newPerson);
                request.enqueue(new Callback<Person>() {
                    @Override
                    public void onResponse(Call<Person> call, Response<Person> response) {
                        if (response.isSuccessful()) {
                            Person savedPerson = response.body();
                            Intent returnIntent = new Intent();
                            returnIntent.putExtra("newPerson", savedPerson);
                            setResult(Activity.RESULT_OK, returnIntent);
                            finish();

                        }
                    }

                    @Override
                    public void onFailure(Call<Person> call, Throwable t) {
                        Log.d("Person Error: ", t.getMessage());
                        txtSaved.setText("Person is not added");
                    }
                });

                finish();
                personService.getAllPerson();
            }
     ;
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}