package com.example.lostandfound;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private Button btnCreateAdvert, btnShowItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnCreateAdvert = findViewById(R.id.buttonCreateAdvert);
        btnShowItems = findViewById(R.id.buttonShowItems);

        btnCreateAdvert.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, AddItemActivity.class));
        });

        btnShowItems.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, ListItemsActivity.class));
        });
    }
}
