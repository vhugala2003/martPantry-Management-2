package com.example.smartpantrymanager;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;
import java.util.Locale;

public class PantryAdapter extends ArrayAdapter<PantryItem> {

    public PantryAdapter(Context context, List<PantryItem> items) {
        super(context, android.R.layout.simple_list_item_1, items);
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

        PantryItem item = getItem(position);

        if (item != null) {
            String display = item.getName() + "\n" +
                    String.format(Locale.getDefault(), "Quantity: %.1f %s",
                            item.getQuantity(), item.getUnit());

            if (item.getExpiryDate() != null && !item.getExpiryDate().isEmpty()) {
                display += " | Expires: " + item.getExpiryDate();
            }

            text.setText(display);
        }

        return layout;
    }
}
