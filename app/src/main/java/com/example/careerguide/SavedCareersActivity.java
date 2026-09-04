package com.example.careerguide;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import java.io.FileOutputStream;
import java.util.ArrayList;

/**
 * Shows saved careers from SQLite and allows deletion and export.
 */
public class SavedCareersActivity extends AppCompatActivity {

    private CareerDatabaseHelper dbHelper;
    private ArrayList<String> items = new ArrayList<>();
    private ArrayList<Long> ids = new ArrayList<>();
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saved_careers);

        dbHelper = new CareerDatabaseHelper(this);
        ListView lv = findViewById(R.id.savedListView);
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, items);
        lv.setAdapter(adapter);

        lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                long itemId = ids.get(position);
                dbHelper.deleteById(itemId);
                loadData();
                Toast.makeText(SavedCareersActivity.this, "Deleted", Toast.LENGTH_SHORT).show();
                return true;
            }
        });

        findViewById(R.id.exportButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                exportToFile();
            }
        });

        loadData();
    }

    private void loadData() {
        items.clear(); ids.clear();
        Cursor c = dbHelper.getAll();
        if (c != null) {
            while (c.moveToNext()) {
                long id = c.getLong(c.getColumnIndexOrThrow(CareerDatabaseHelper.COL_ID));
                String name = c.getString(c.getColumnIndexOrThrow(CareerDatabaseHelper.COL_NAME));
                String date = c.getString(c.getColumnIndexOrThrow(CareerDatabaseHelper.COL_DATE));
                items.add(name + (date != null && !date.isEmpty() ? " (" + date + ")" : ""));
                ids.add(id);
            }
            c.close();
        }
        adapter.notifyDataSetChanged();
    }

    private void exportToFile() {
        StringBuilder sb = new StringBuilder();
        Cursor c = dbHelper.getAll();
        if (c != null) {
            while (c.moveToNext()) {
                String name = c.getString(c.getColumnIndexOrThrow(CareerDatabaseHelper.COL_NAME));
                String desc = c.getString(c.getColumnIndexOrThrow(CareerDatabaseHelper.COL_DESC));
                String date = c.getString(c.getColumnIndexOrThrow(CareerDatabaseHelper.COL_DATE));
                sb.append("Name: ").append(name).append("\n");
                sb.append("Date: ").append(date).append("\n");
                sb.append("Desc: ").append(desc).append("\n\n");
            }
            c.close();
        }

        try (FileOutputStream fos = openFileOutput("career_plan.txt", MODE_PRIVATE)) {
            fos.write(sb.toString().getBytes());
            Toast.makeText(this, "Exported to internal storage: career_plan.txt", Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            Toast.makeText(this, "Export failed: " + e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }
}
