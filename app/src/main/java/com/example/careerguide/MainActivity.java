package com.example.careerguide;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("CareerGuide", "MainActivity onCreate called");

        // Home dashboard buttons
        Button startButton = findViewById(R.id.startButton);
        Button exploreButton = findViewById(R.id.exploreButton);
        Button savedButton = findViewById(R.id.savedButton);
        Button aboutButton = findViewById(R.id.aboutButton);
        Button settingsButton = findViewById(R.id.settingsButton);

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, QuizActivity.class);
                startActivity(intent);
            }
        });

        exploreButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CareerListActivity.class);
                startActivity(intent);
            }
        });

        savedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SavedCareersActivity.class);
                startActivity(intent);
            }
        });

        aboutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AboutActivity.class);
                startActivity(intent);
            }
        });

        settingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("CareerGuide", "MainActivity onStart called");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("CareerGuide", "MainActivity onResume called");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("CareerGuide", "MainActivity onPause called");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("CareerGuide", "MainActivity onStop called");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("CareerGuide", "MainActivity onDestroy called");
    }
}
