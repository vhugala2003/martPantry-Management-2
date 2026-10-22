package com.example.smartpantrymanager;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Switch;
import android.widget.Toast;

public class SettingsActivity extends Activity {

    private Switch switchAlerts;
    private RadioButton rbMetric, rbImperial;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        sharedPreferences = getSharedPreferences("SmartPantryPrefs", MODE_PRIVATE);

        switchAlerts = findViewById(R.id.switchAlerts);
        rbMetric = findViewById(R.id.rbMetric);
        rbImperial = findViewById(R.id.rbImperial);
        Button btnBackMain = findViewById(R.id.btnBackMain);

        // Load saved preferences
        boolean alertsEnabled = sharedPreferences.getBoolean("alerts_enabled", true);
        boolean isMetric = sharedPreferences.getBoolean("is_metric", true);

        switchAlerts.setChecked(alertsEnabled);
        if (isMetric) {
            rbMetric.setChecked(true);
        } else {
            rbImperial.setChecked(true);
        }

        switchAlerts.setOnCheckedChangeListener((buttonView, isChecked) -> {
            sharedPreferences.edit().putBoolean("alerts_enabled", isChecked).apply();
            Toast.makeText(this, "Alerts setting updated", Toast.LENGTH_SHORT).show();
        });

        rbMetric.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                sharedPreferences.edit().putBoolean("is_metric", true).apply();
                Toast.makeText(this, "Unit set to Metric", Toast.LENGTH_SHORT).show();
            }
        });

        rbImperial.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                sharedPreferences.edit().putBoolean("is_metric", false).apply();
                Toast.makeText(this, "Unit set to Imperial", Toast.LENGTH_SHORT).show();
            }
        });

        btnBackMain.setOnClickListener(v -> finish());
    }
}
