package com.example.smartpantrymanager;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends Activity {

    private DatabaseHelper dbHelper;
    private ListView listPantry;
    private ArrayList<PantryItem> pantryItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHelper = new DatabaseHelper(this);

        Button btnAdd = findViewById(R.id.btnAddIngredient);
        Button btnSuggested = findViewById(R.id.btnSuggestedRecipes);
        Button btnSettings = findViewById(R.id.btnSettings);
        listPantry = findViewById(R.id.listPantry);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddEditIngredientActivity.class);
                startActivity(intent);
            }
        });

        btnSuggested.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SuggestedRecipesActivity.class);
                startActivity(intent);
            }
        });

        btnSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
                startActivity(intent);
            }
        });

        listPantry.setOnItemClickListener((parent, view, position, id) -> {
            PantryItem item = pantryItems.get(position);
            Intent intent = new Intent(MainActivity.this, AddEditIngredientActivity.class);
            intent.putExtra("ingredient_id", item.getId());
            intent.putExtra("ingredient_name", item.getName());
            intent.putExtra("ingredient_quantity", item.getQuantity());
            intent.putExtra("ingredient_unit", item.getUnit());
            intent.putExtra("ingredient_expiry", item.getExpiryDate());
            startActivity(intent);
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadPantryItems();
    }

    private void loadPantryItems() {
        pantryItems = dbHelper.getAllIngredients();
        PantryAdapter adapter = new PantryAdapter(this, pantryItems);
        listPantry.setAdapter(adapter);
    }
}
