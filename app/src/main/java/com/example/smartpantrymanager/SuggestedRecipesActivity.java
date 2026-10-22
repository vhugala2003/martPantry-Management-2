package com.example.smartpantrymanager;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

public class SuggestedRecipesActivity extends Activity {

    private DatabaseHelper dbHelper;
    private ListView listSuggested;
    private TextView tvEmpty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suggested_recipes);

        dbHelper = new DatabaseHelper(this);
        listSuggested = findViewById(R.id.listSuggested);
        tvEmpty = findViewById(R.id.tvEmpty);

        listSuggested.setOnItemClickListener((parent, view, position, id) -> {
            Recipe recipe = (Recipe) parent.getItemAtPosition(position);
            Intent intent = new Intent(SuggestedRecipesActivity.this, RecipeDetailActivity.class);
            intent.putExtra("recipe_id", recipe.getId());
            startActivity(intent);
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadSuggestedRecipes();
    }

    private void loadSuggestedRecipes() {
        List<Recipe> suggestedRecipes = dbHelper.getSuggestedRecipes();

        if (suggestedRecipes.isEmpty()) {
            listSuggested.setVisibility(View.GONE);
            tvEmpty.setVisibility(View.VISIBLE);
        } else {
            listSuggested.setVisibility(View.VISIBLE);
            tvEmpty.setVisibility(View.GONE);
            RecipeAdapter adapter = new RecipeAdapter(this, suggestedRecipes);
            listSuggested.setAdapter(adapter);
        }
    }
}
