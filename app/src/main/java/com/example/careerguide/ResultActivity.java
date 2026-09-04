package com.example.careerguide;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.Toast;

import java.util.Date;

public class ResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        TextView careerTitle = findViewById(R.id.careerTitle);
        TextView careerDescription = findViewById(R.id.careerDescription);
        Button restartButton = findViewById(R.id.restartButton);
        Button viewDetails = findViewById(R.id.viewDetailsButton);
        Button saveButton = findViewById(R.id.saveButton);
        Button shareButton = findViewById(R.id.shareButtonResult);

        final CareerDatabaseHelper dbHelper = new CareerDatabaseHelper(this);

        String career = getIntent().getStringExtra("career");

        careerTitle.setText(career != null ? career : "Explore More!");
        careerDescription.setText(CareerScorer.getDescription(career));
        viewDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ResultActivity.this, CareerDetailsActivity.class);
                intent.putExtra("career_name", career);
                startActivity(intent);
            }
        });

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                long id = dbHelper.insertCareer(career, CareerScorer.getDescription(career), new Date().toString());
                if (id > 0) Toast.makeText(ResultActivity.this, "Saved", Toast.LENGTH_SHORT).show();
            }
        });

        shareButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_SENDTO);
                intent.setData(Uri.parse("mailto:"));
                intent.putExtra(Intent.EXTRA_SUBJECT, "My career suggestion: " + career);
                intent.putExtra(Intent.EXTRA_TEXT, CareerScorer.getDescription(career));
                startActivity(Intent.createChooser(intent, "Share via email"));
            }
        });

        restartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ResultActivity.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                finish();
            }
        });
    }
}
