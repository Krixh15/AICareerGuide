package com.example.careerguide;

import android.app.DatePickerDialog;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import java.util.Calendar;

/**
 * Simple career details screen. Allows saving career to local SQLite and sharing.
 */
public class CareerDetailsActivity extends AppCompatActivity {

    private TextView titleView, descView, dateView;
    private CareerDatabaseHelper dbHelper;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_career_details);

        titleView = findViewById(R.id.careerName);
        descView = findViewById(R.id.careerLongDesc);
        dateView = findViewById(R.id.planDate);

        dbHelper = new CareerDatabaseHelper(this);

        String career = getIntent().getStringExtra("career_name");
        titleView.setText(career != null ? career : "Career");

        String desc = CareerScorer.getDescription(career);
        if (desc == null || desc.isEmpty()) desc = "Explore skills and courses for " + career + ".";
        descView.setText(desc);

        Button saveBtn = findViewById(R.id.saveCareerButton);
        Button shareBtn = findViewById(R.id.shareButton);
        Button mapBtn = findViewById(R.id.mapButton);
        Button pickDateBtn = findViewById(R.id.pickDateButton);
        Button reminderBtn = findViewById(R.id.reminderButton);

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                long id = dbHelper.insertCareer(titleView.getText().toString(), descView.getText().toString(), dateView.getText().toString());
                if (id > 0) Toast.makeText(CareerDetailsActivity.this, "Saved to history", Toast.LENGTH_SHORT).show();
            }
        });

        shareBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_SENDTO);
                intent.setData(Uri.parse("mailto:"));
                intent.putExtra(Intent.EXTRA_SUBJECT, "Career suggestion: " + titleView.getText().toString());
                intent.putExtra(Intent.EXTRA_TEXT, descView.getText().toString());
                startActivity(Intent.createChooser(intent, "Share via email"));
            }
        });

        mapBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Open maps for nearby learning centers (simple geo URI)
                Uri gmmIntentUri = Uri.parse("geo:0,0?q=learning+centers");
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                startActivity(mapIntent);
            }
        });

        pickDateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();
                int y = c.get(Calendar.YEAR);
                int m = c.get(Calendar.MONTH);
                int d = c.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog dp = new DatePickerDialog(CareerDetailsActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        String s = dayOfMonth + "/" + (month+1) + "/" + year;
                        dateView.setText(s);
                    }
                }, y, m, d);
                dp.show();
            }
        });

        createNotificationChannel();

        reminderBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NotificationCompat.Builder builder = new NotificationCompat.Builder(CareerDetailsActivity.this, "career_channel")
                        .setSmallIcon(android.R.drawable.ic_dialog_info)
                        .setContentTitle("Career Guide Reminder")
                        .setContentText("Time to work on your career plan: " + titleView.getText().toString())
                        .setPriority(NotificationCompat.PRIORITY_DEFAULT);

                NotificationManagerCompat.from(CareerDetailsActivity.this).notify(1001, builder.build());
            }
        });
    }

    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "Career Reminders";
            String description = "Reminders for career planning";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel("career_channel", name, importance);
            channel.setDescription(description);
            NotificationManager nm = getSystemService(NotificationManager.class);
            if (nm != null) nm.createNotificationChannel(channel);
        }
    }
}
