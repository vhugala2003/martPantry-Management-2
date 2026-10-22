package com.example.smartpantrymanager;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;
import java.util.Locale;

public class RecipeAdapter extends ArrayAdapter<Recipe> {

    public RecipeAdapter(Context context, List<Recipe> recipes) {
        super(context, android.R.layout.simple_list_item_1, recipes);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LinearLayout layout;
        TextView text;

        if (convertView instanceof LinearLayout) {
            layout = (LinearLayout) convertView;
            text = (TextView) layout.getChildAt(0);
        } else {
            layout = new LinearLayout(getContext());
            layout.setOrientation(LinearLayout.VERTICAL);
            layout.setPadding(24, 18, 24, 18);

            text = new TextView(getContext());
            text.setTextSize(16);
            layout.addView(text);
        }

        Recipe recipe = getItem(position);

        if (recipe != null) {
            StringBuilder sb = new StringBuilder();
            sb.append(recipe.getName()).append("\nIngredients required: ");
            for (int i = 0; i < recipe.getIngredients().size(); i++) {
                RecipeIngredient ing = recipe.getIngredients().get(i);
                sb.append(String.format(Locale.getDefault(), "%.1f %s %s",
                        ing.getQuantity(), ing.getUnit(), ing.getName()));
                if (i < recipe.getIngredients().size() - 1) {
                    sb.append(", ");
                }
            }
            text.setText(sb.toString());
        }

        return layout;
    }
}
