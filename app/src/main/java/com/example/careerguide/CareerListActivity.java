package com.example.careerguide;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;

/**
 * Shows a simple list of careers (ListView) and opens details on click.
 */
public class CareerListActivity extends AppCompatActivity {

    private String[] careers = new String[]{
            "Software Developer",
            "Web Developer",
            "Android Developer",
            "Data Analyst",
            "AI/ML Engineer",
            "Cyber Security Analyst",
            "UI/UX Designer",
            "Graphic Designer",
            "Business Analyst",
            "Digital Marketing Specialist",
            "Cloud Engineer",
            "Database Administrator",
            "Project Manager",
            "Network Administrator",
            "Content/Technical Writer"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_career_list);

        ListView listView = findViewById(R.id.careerListView);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, careers);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(CareerListActivity.this, CareerDetailsActivity.class);
                intent.putExtra("career_name", careers[position]);
                startActivity(intent);
            }
        });
    }
}
