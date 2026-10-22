package com.example.smartpantrymanager;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class AddEditIngredientActivity extends Activity {

    private EditText etName, etQuantity, etUnit, etExpiry;
    private Button btnSave, btnDelete;
    private DatabaseHelper dbHelper;
    private int ingredientId = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit);

        dbHelper = new DatabaseHelper(this);

        TextView tvTitle = findViewById(R.id.tvTitle);
        etName = findViewById(R.id.etName);
        etQuantity = findViewById(R.id.etQuantity);
        etUnit = findViewById(R.id.etUnit);
        etExpiry = findViewById(R.id.etExpiry);
        btnSave = findViewById(R.id.btnSave);
        btnDelete = findViewById(R.id.btnDelete);

        Intent intent = getIntent();
        if (intent.hasExtra("ingredient_id")) {
            ingredientId = intent.getIntExtra("ingredient_id", -1);
            tvTitle.setText("Edit Pantry Item");
            etName.setText(intent.getStringExtra("ingredient_name"));
            etQuantity.setText(String.valueOf(intent.getDoubleExtra("ingredient_quantity", 0.0)));
            etUnit.setText(intent.getStringExtra("ingredient_unit"));
            etExpiry.setText(intent.getStringExtra("ingredient_expiry"));
            btnDelete.setVisibility(View.VISIBLE);
        } else {
            tvTitle.setText("Add Pantry Item");
            btnDelete.setVisibility(View.GONE);
        }

        btnSave.setOnClickListener(v -> saveIngredient());

        btnDelete.setOnClickListener(v -> {
            if (ingredientId != -1) {
                dbHelper.deleteIngredient(ingredientId);
                Toast.makeText(AddEditIngredientActivity.this, "Ingredient deleted", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }

    private void saveIngredient() {
        String name = etName.getText().toString().trim();
        String qtyStr = etQuantity.getText().toString().trim();
        String unit = etUnit.getText().toString().trim();
        String expiry = etExpiry.getText().toString().trim();

        // Input Validation
        if (name.isEmpty()) {
            etName.setError("Ingredient name is required");
            etName.requestFocus();
            return;
        }

        if (qtyStr.isEmpty()) {
            etQuantity.setError("Quantity is required");
            etQuantity.requestFocus();
            return;
        }

        double quantity;
        try {
            quantity = Double.parseDouble(qtyStr);
            if (quantity <= 0) {
                etQuantity.setError("Quantity must be greater than 0");
                etQuantity.requestFocus();
                return;
            }
        } catch (NumberFormatException e) {
            etQuantity.setError("Invalid number format");
            etQuantity.requestFocus();
            return;
        }

        if (unit.isEmpty()) {
            etUnit.setError("Unit is required (e.g. g, cups, pieces)");
            etUnit.requestFocus();
            return;
        }

        if (ingredientId == -1) {
            dbHelper.addIngredient(name, quantity, unit, expiry);
            Toast.makeText(this, "Ingredient added successfully", Toast.LENGTH_SHORT).show();
        } else {
            dbHelper.updateIngredient(ingredientId, name, quantity, unit, expiry);
            Toast.makeText(this, "Ingredient updated successfully", Toast.LENGTH_SHORT).show();
        }

        finish();
    }
}
