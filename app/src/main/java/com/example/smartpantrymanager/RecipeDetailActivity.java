package com.example.smartpantrymanager;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;
import java.util.Locale;

public class RecipeDetailActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_detail);

        int recipeId = getIntent().getIntExtra("recipe_id", -1);
        if (recipeId == -1) {
            finish();
            return;
        }

        DatabaseHelper dbHelper = new DatabaseHelper(this);
        Recipe recipe = null;
        for (Recipe r : dbHelper.getAllRecipes()) {
            if (r.getId() == recipeId) {
                recipe = r;
                break;
            }
        }

        if (recipe == null) {
            finish();
            return;
        }

        TextView tvRecipeName = findViewById(R.id.tvRecipeName);
        TextView tvIngredientsList = findViewById(R.id.tvIngredientsList);
        TextView tvInstructions = findViewById(R.id.tvInstructions);
        Button btnBack = findViewById(R.id.btnBack);

        tvRecipeName.setText(recipe.getName());

        StringBuilder ingBuilder = new StringBuilder();
        List<RecipeIngredient> ingredients = recipe.getIngredients();
        for (int i = 0; i < ingredients.size(); i++) {
            RecipeIngredient ing = ingredients.get(i);
            ingBuilder.append(String.format(Locale.getDefault(), "• %.1f %s %s",
                    ing.getQuantity(), ing.getUnit(), ing.getName()));
            if (i < ingredients.size() - 1) {
                ingBuilder.append("\n");
            }
        }
        tvIngredientsList.setText(ingBuilder.toString());
        tvInstructions.setText(recipe.getInstructions());

        btnBack.setOnClickListener(v -> finish());
    }
}
