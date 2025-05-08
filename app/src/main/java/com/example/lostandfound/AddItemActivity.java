package com.example.lostandfound;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AddItemActivity extends AppCompatActivity {

    private EditText nameInput, phoneInput, descriptionInput, dateInput, locationInput;
    private Spinner statusSpinner;
    private Button saveButton;
    private String selectedStatus = "Lost";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);

        // Initialize views
        nameInput = findViewById(R.id.editTextName);
        phoneInput = findViewById(R.id.editTextPhone);
        descriptionInput = findViewById(R.id.editTextDescription);
        dateInput = findViewById(R.id.editTextDate);
        locationInput = findViewById(R.id.editTextLocation);
        statusSpinner = findViewById(R.id.spinnerStatus);
        saveButton = findViewById(R.id.buttonSave);

        // Set up spinner
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this,
                R.array.status_array,
                android.R.layout.simple_spinner_item
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        statusSpinner.setAdapter(adapter);

        statusSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedStatus = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) { }
        });

        // Save button click
        saveButton.setOnClickListener(v -> {
            String name = nameInput.getText().toString().trim();
            String phone = phoneInput.getText().toString().trim();
            String description = descriptionInput.getText().toString().trim();
            String date = dateInput.getText().toString().trim();
            String location = locationInput.getText().toString().trim();

            if (name.isEmpty()) {
                Toast.makeText(this, "Name is required", Toast.LENGTH_SHORT).show();
                return;
            }

            if (location.isEmpty()) {
                Toast.makeText(this, "Location is required", Toast.LENGTH_SHORT).show();
                return;
            }



            DatabaseHelper dbHelper = new DatabaseHelper(this);
            dbHelper.addItem(name, phone, description, date, location, selectedStatus);

            Toast.makeText(this, "Item added successfully", Toast.LENGTH_SHORT).show();
            finish(); // Return to previous screen
        });
    }
}
