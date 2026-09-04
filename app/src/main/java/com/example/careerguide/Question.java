package com.example.careerguide;

/**
 * Represents a single quiz question.
 * "options" are the choices shown to the user.
 * "categories" is a parallel array: categories[i] is the career category
 * that gets +1 point when the user picks options[i].
 */
public class Question {

    private String questionText;
    private String[] options;
    private String[] categories;

    public Question(String questionText, String[] options, String[] categories) {
        this.questionText = questionText;
        this.options = options;
        this.categories = categories;
    }

    public String getQuestionText() {
        return questionText;
    }

    public String[] getOptions() {
        return options;
    }

    public String getCategoryForOption(int optionIndex) {
        return categories[optionIndex];
    }
}
