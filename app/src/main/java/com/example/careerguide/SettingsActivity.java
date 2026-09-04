package com.example.careerguide;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class SettingsActivity extends AppCompatActivity {

    private EditText nameInput;
    private Spinner educationSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        nameInput = findViewById(R.id.prefName);
        educationSpinner = findViewById(R.id.educationSpinner);
        Button save = findViewById(R.id.savePrefsButton);

        SharedPreferences prefs = getSharedPreferences("career_prefs", MODE_PRIVATE);
        nameInput.setText(prefs.getString("student_name", ""));

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = prefs.edit();
                editor.putString("student_name", nameInput.getText().toString().trim());
                editor.putString("education_level", educationSpinner.getSelectedItem().toString());
                editor.apply();
                Toast.makeText(SettingsActivity.this, "Preferences saved", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
