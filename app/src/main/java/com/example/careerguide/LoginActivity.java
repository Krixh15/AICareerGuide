package com.example.careerguide;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

/**
 * Simple login screen to demonstrate validation and SharedPreferences.
 */
public class LoginActivity extends AppCompatActivity {

    private EditText usernameInput;
    private EditText passwordInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        usernameInput = findViewById(R.id.username);
        passwordInput = findViewById(R.id.password);
        Button loginBtn = findViewById(R.id.loginButton);

        // If user already logged in, skip to MainActivity
        SharedPreferences prefs = getSharedPreferences("career_prefs", MODE_PRIVATE);
        boolean logged = prefs.getBoolean("logged_in", false);
        if (logged) {
            startActivity(new Intent(this, MainActivity.class));
            finish();
            return;
        }

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = usernameInput.getText().toString().trim();
                String pass = passwordInput.getText().toString().trim();

                if (user.isEmpty()) {
                    usernameInput.setError("Enter username");
                    return;
                }

                if (pass.isEmpty()) {
                    passwordInput.setError("Enter password");
                    return;
                }

                // Simple local validation only
                SharedPreferences.Editor editor = prefs.edit();
                editor.putString("student_name", user);
                editor.putBoolean("logged_in", true);
                editor.apply();

                Toast.makeText(LoginActivity.this, "Login successful", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(LoginActivity.this, MainActivity.class));
                finish();
            }
        });
    }
}
