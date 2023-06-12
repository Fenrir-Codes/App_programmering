package com.example.personclient;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener,
        View.OnClickListener{

    //listview
    ListView list;
    List<Person> listOfAll;
    FloatingActionButton btnAdd;

    TextView appTitle;

    private static final int DELETE_PERSON_REQUEST_CODE = 2;

    ActivityResultLauncher<Intent> personViewActivityLauncher;
    ActivityResultLauncher<Intent> newPersonViewActivityLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        list = findViewById(R.id.list);
        list.setOnItemClickListener(this);
        btnAdd = findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(this);
        appTitle = findViewById(R.id.appTitle);

        //Person service instialize
        PersonService personService = ServiceBuilder.buildService(PersonService.class);

        // make call to get person
        Call<List<Person>> requestAll = personService.getAllPerson();

        // Register ActivityResultLaunchers
        personViewActivityLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        // update a person in the list
                        Person updatedPerson = (Person) result.getData()
                                .getSerializableExtra("person");
                        int requestCode = result.getData()
                                .getIntExtra("requestCode", 0);
                        if (requestCode == DELETE_PERSON_REQUEST_CODE) {
                            listOfAll.remove(updatedPerson);
                        } else {
                            for (int i = 0; i < listOfAll.size(); i++) {
                                if (listOfAll.get(i).getId() == updatedPerson.getId()) {
                                    listOfAll.set(i, updatedPerson);
                                    break;
                                }
                            }
                        }
                    }
                });


        newPersonViewActivityLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        // update list with the new person
                        Person newPerson = (Person) result.getData().
                                getSerializableExtra("newPerson");
                        listOfAll.add(newPerson);
                        PersonAdapter adapter =
                                new PersonAdapter(listOfAll, MainActivity.this);
                        list.setAdapter(adapter);
                    }
                }
        );


        requestAll.enqueue(new Callback<List<Person>>() {
            @Override
            public void onResponse(Call<List<Person>> call, Response<List<Person>> response) {
                listOfAll = response.body();
                PersonAdapter adapter =
                        new PersonAdapter(listOfAll, MainActivity.this);
                list.setAdapter(adapter);

            }
            @Override
            public void onFailure(Call<List<Person>> call, Throwable t) {
                appTitle.setText("Error" + t.getMessage());
                //Log.d("Person Error: ", t.getMessage());
            }

        });

    }

    //onclick an item
    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
        Person person = listOfAll.get(position);
        Intent intent = new Intent(MainActivity.this, PersonView.class);
        intent.putExtra("person", person);
        intent.putExtra("requestCode", 1);
        personViewActivityLauncher.launch(intent);

    }

    //onResume
    @Override
    protected void onResume() {
        super.onResume();
        PersonService personService = ServiceBuilder.buildService(PersonService.class);
        Call<List<Person>> requestAll = personService.getAllPerson();
        // asyn call to get all persons
        requestAll.enqueue(new Callback<List<Person>>() {
            // this will run if the request to the server successfull
            @Override
            public void onResponse(Call<List<Person>> call, Response<List<Person>> response) {
                listOfAll = response.body();
                //Make an adapter and set fill listview
                PersonAdapter adapter = new PersonAdapter(listOfAll, MainActivity.this);
                list.setAdapter(adapter);
            }
            //if fails the call then show error message
            @Override
            public void onFailure(Call<List<Person>> call, Throwable t) {
                Log.d("Error: ", t.getMessage());
            }
        });
    }
    
    //this is called if touching an element in the listview
    @Override
    public void onClick(View view) {
        Intent intent = new Intent(MainActivity.this, NewPerson.class);
        newPersonViewActivityLauncher.launch(intent);

    }
}