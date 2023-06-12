package com.example.personclient;

import androidx.appcompat.app.AppCompatActivity;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.ToggleButton;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class PersonView extends AppCompatActivity {

    Intent intent;
    Spinner spnHairColor;
    Person person;
    RadioButton rbtCat;
    RadioButton rbtDog;
    RadioButton rbtBird;
    TextView txtUpdated;
    ToggleButton toggleFavorit;

    String[] hairColor = {"Blond", "Black", "Brown", "Gray"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person_view);

        EditText editName = findViewById(R.id.editName);
        EditText editTlf = findViewById(R.id.editTlf);
        EditText editJob = findViewById(R.id.editJob);
        spnHairColor = findViewById(R.id.spnHairColor);
        toggleFavorit = findViewById(R.id.btnIsFavorit);
        txtUpdated = findViewById(R.id.txtUpdated);

        rbtCat = findViewById(R.id.rbtCat);
        rbtDog = findViewById(R.id.rbtDog);
        rbtBird = findViewById(R.id.rbtBird);

        RadioGroup rbGroup = findViewById(R.id.rbGroup);

        Button btnSave = findViewById(R.id.btnSave);
        Button btnCancel = findViewById(R.id.btnCancel);
        Button btnDelete = findViewById(R.id.btnDelete);

        // Delete button
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PersonService personService = ServiceBuilder.buildService(PersonService.class);
                Call<Void> deleteRequest = personService.deletePersonById(person.getId());
                deleteRequest.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        if (response.isSuccessful()) {
                            Intent intentDelete = new Intent();
                            intentDelete.putExtra("person", person);
                            setResult(Activity.RESULT_OK, intentDelete);
                            finish();
                        }
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {

                    }
                });
            }
        });

        //Button for save or return
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        //Save Button
        //this updating the person object and send it to database
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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

                boolean isFavorit = toggleFavorit.isChecked();
                person.setName(name);
                person.setTlf(tlf);
                person.setJob(job);
                person.setHairColor(hairColorIndex);
                person.setPet(String.valueOf(pet));
                person.setFavorit(isFavorit);

                PersonService personService = ServiceBuilder.buildService(PersonService.class);
                Call<Void> updateRequest = personService.updatePerson(person.getId(), person);
                updateRequest.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        if (response.isSuccessful()) {
                            txtUpdated.setText("Person updated");
                            Intent resultIntent = new Intent();
                            resultIntent.putExtra("person", person);
                            setResult(Activity.RESULT_OK, resultIntent);
                            txtUpdated.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    txtUpdated.setVisibility(View.GONE);
                                }
                            }, 5000);
                            //finish();
                        }
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        txtUpdated.setText("Not updated!");
                    }
                });
            }
        });

        // Attaches an adapter to the spinner for hair dye
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                com.google.android.material.R.layout.support_simple_spinner_dropdown_item, hairColor);
        spnHairColor.setAdapter(adapter);

        intent = getIntent();
        person = (Person) intent.getSerializableExtra("person");

        if (person != null) {
            editName.setText(person.getName());
            // If it is an int, it must be converted to
            // a string, this can be done by putting "" in front
            editTlf.setText("" + person.getTlf());
            editJob.setText(person.getJob());

            int hairColorIndex = person.getHairColor();
            String hcStr = hairColor[hairColorIndex];
            int spnPos = adapter.getPosition(hcStr);
            spnHairColor.setSelection(spnPos);
            toggleFavorit.setChecked(person.getFavorit());
        }

        int pet = Integer.parseInt(person.getPet());
        switch (pet) {
            case 1:
                rbtCat.setChecked(true);
                break;
            case 2:
                rbtDog.setChecked(true);
                break;
            case 3:
                rbtBird.setChecked(true);
                break;
            default:
                break;
        }

    }

}