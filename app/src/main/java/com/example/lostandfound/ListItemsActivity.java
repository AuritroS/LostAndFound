package com.example.lostandfound;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ListItemsActivity extends AppCompatActivity  {

    private RecyclerView recyclerView;
    private Spinner filterSpinner;
    private DatabaseHelper dbHelper;
    private ItemAdapter adapter;
    private String currentFilter = "All";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_items);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        filterSpinner = findViewById(R.id.spinnerFilter);
        dbHelper = new DatabaseHelper(this);

        ArrayAdapter<CharSequence> filterAdapter = ArrayAdapter.createFromResource(
                this,
                R.array.filter_array,
                android.R.layout.simple_spinner_item
        );
        filterAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        filterSpinner.setAdapter(filterAdapter);

        filterSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                currentFilter = parent.getItemAtPosition(position).toString();
                loadItems();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) { }
        });

        loadItems();
    }

    private void loadItems() {
        ArrayList<ItemModel> allItems = dbHelper.getAllItems();
        ArrayList<ItemModel> filteredItems = new ArrayList<>();

        for (ItemModel item : allItems) {
            if (currentFilter.equals("All") || item.getStatus().equalsIgnoreCase(currentFilter)) {
                filteredItems.add(item);
            }
        }

        adapter = new ItemAdapter(filteredItems, item -> {
            ItemDetailsFragment fragment = new ItemDetailsFragment(item, dbHelper, this::loadItems);
            fragment.show(getSupportFragmentManager(), "ItemDetails");
        });
        recyclerView.setAdapter(adapter);

        recyclerView.setAdapter(adapter);
    }
}
