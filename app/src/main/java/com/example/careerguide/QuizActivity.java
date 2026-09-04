package com.example.careerguide;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class QuizActivity extends AppCompatActivity {

    private TextView questionText;
    private RadioGroup optionsGroup;
    private Button nextButton;
    private TextView progressText;

    private List<Question> questions;
    private List<String> selectedCategories = new ArrayList<>();
    private int currentIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        questionText = findViewById(R.id.questionText);
        optionsGroup = findViewById(R.id.optionsGroup);
        nextButton = findViewById(R.id.nextButton);
        progressText = findViewById(R.id.progressText);

        questions = CareerScorer.getQuestions();

        showQuestion(currentIndex);

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleNext();
            }
        });
    }

    private void showQuestion(int index) {
        Question question = questions.get(index);

        questionText.setText(question.getQuestionText());
        progressText.setText("Question " + (index + 1) + " of " + questions.size());

        optionsGroup.removeAllViews();

        String[] options = question.getOptions();
        for (int i = 0; i < options.length; i++) {
            RadioButton radioButton = new RadioButton(this);
            radioButton.setText(options[i]);
            radioButton.setId(i); // use option index as the view id
            radioButton.setTextSize(16);
            radioButton.setPadding(8, 24, 8, 24);
            optionsGroup.addView(radioButton);
        }

        // Last question -> change button text
        if (index == questions.size() - 1) {
            nextButton.setText("See Result");
        } else {
            nextButton.setText("Next");
        }
    }

    private void handleNext() {
        int selectedId = optionsGroup.getCheckedRadioButtonId();

        if (selectedId == -1) {
            Toast.makeText(this, "Please select an option", Toast.LENGTH_SHORT).show();
            return;
        }

        // selectedId is the option index we assigned above
        Question currentQuestion = questions.get(currentIndex);
        String category = currentQuestion.getCategoryForOption(selectedId);
        selectedCategories.add(category);

        currentIndex++;

        if (currentIndex < questions.size()) {
            showQuestion(currentIndex);
        } else {
            finishQuiz();
        }
    }

    private void finishQuiz() {
        String recommendedCareer = CareerScorer.getRecommendation(selectedCategories);

        Intent intent = new Intent(QuizActivity.this, ResultActivity.class);
        intent.putExtra("career", recommendedCareer);
        startActivity(intent);
        finish();
    }
}
