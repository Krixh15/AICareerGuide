package com.example.careerguide;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This class is the "brain" of the app.
 *
 * The original project used trained ML models (KNN, Decision Tree, SVM,
 * Logistic Regression) to map quiz answers to a career. For a beginner
 * Android app, we replace that with simple, transparent point-scoring:
 * every answer nudges one or more career categories up by a point, and
 * whichever category ends with the most points is the recommendation.
 *
 * This is easy to understand, easy to debug, and needs no server,
 * no model file, and no network calls.
 */
public class CareerScorer {

    // Career category keys
    public static final String WEB_DEV = "Web Development";
    public static final String DATA_SCIENCE = "Data Science";
    public static final String AI_ML = "AI / Machine Learning";
    public static final String CYBERSECURITY = "Cybersecurity";
    public static final String MOBILE_DEV = "Mobile App Development";
    public static final String DEVOPS = "DevOps / Cloud Engineering";
    public static final String GAME_DEV = "Game Development";
    public static final String UI_UX = "UI/UX Design";

    public static List<Question> getQuestions() {
        List<Question> questions = new ArrayList<>();

        questions.add(new Question(
                "Which activity sounds most enjoyable to you?",
                new String[]{
                        "Designing a beautiful, easy-to-use app screen",
                        "Finding patterns hidden in a large spreadsheet of data",
                        "Building a website from scratch",
                        "Figuring out how a hacker broke into a system"
                },
                new String[]{UI_UX, DATA_SCIENCE, WEB_DEV, CYBERSECURITY}
        ));

        questions.add(new Question(
                "Pick the tool/topic that excites you the most:",
                new String[]{
                        "Neural networks and predictive models",
                        "Servers, containers, and deployment pipelines",
                        "Game physics and 3D graphics engines",
                        "Building apps that run on phones"
                },
                new String[]{AI_ML, DEVOPS, GAME_DEV, MOBILE_DEV}
        ));

        questions.add(new Question(
                "In a group project, you naturally gravitate toward:",
                new String[]{
                        "Making sure the product looks and feels great to use",
                        "Writing the backend logic and APIs",
                        "Automating the testing/deployment process",
                        "Analyzing the collected user data for insights"
                },
                new String[]{UI_UX, WEB_DEV, DEVOPS, DATA_SCIENCE}
        ));

        questions.add(new Question(
                "Which puzzle would you rather solve?",
                new String[]{
                        "Spotting a security vulnerability in code",
                        "Training a model to recognize images accurately",
                        "Designing a game level that's fun and balanced",
                        "Making a mobile app run smoothly on low-end phones"
                },
                new String[]{CYBERSECURITY, AI_ML, GAME_DEV, MOBILE_DEV}
        ));

        questions.add(new Question(
                "Which subject did/do you enjoy studying most?",
                new String[]{
                        "Statistics and probability",
                        "Human-computer interaction / design principles",
                        "Networking and systems security",
                        "Cloud infrastructure and automation"
                },
                new String[]{DATA_SCIENCE, UI_UX, CYBERSECURITY, DEVOPS}
        ));

        questions.add(new Question(
                "You'd rather spend a weekend:",
                new String[]{
                        "Building a small game prototype",
                        "Sketching app wireframes in Figma",
                        "Building a personal website/portfolio",
                        "Playing with a Kaggle dataset"
                },
                new String[]{GAME_DEV, UI_UX, WEB_DEV, DATA_SCIENCE}
        ));

        questions.add(new Question(
                "Which career outcome matters most to you?",
                new String[]{
                        "Building products people use every day on their phones",
                        "Keeping systems and data safe from attackers",
                        "Making machines that can learn and predict",
                        "Keeping large-scale systems running reliably"
                },
                new String[]{MOBILE_DEV, CYBERSECURITY, AI_ML, DEVOPS}
        ));

        questions.add(new Question(
                "Pick the phrase that describes you best:",
                new String[]{
                        "I care a lot about how things look and feel",
                        "I like breaking things to understand how they work",
                        "I enjoy working with numbers and finding trends",
                        "I like creating interactive/fun experiences"
                },
                new String[]{UI_UX, CYBERSECURITY, DATA_SCIENCE, GAME_DEV}
        ));

        return questions;
    }

    /**
     * Given the list of categories the user "earned" (one per answered
     * question), tally points and return the category with the highest score.
     */
    public static String getRecommendation(List<String> selectedCategories) {
        Map<String, Integer> scores = new HashMap<>();

        for (String category : selectedCategories) {
            int current = scores.containsKey(category) ? scores.get(category) : 0;
            scores.put(category, current + 1);
        }

        String bestCategory = null;
        int bestScore = -1;

        for (Map.Entry<String, Integer> entry : scores.entrySet()) {
            if (entry.getValue() > bestScore) {
                bestScore = entry.getValue();
                bestCategory = entry.getKey();
            }
        }

        return bestCategory;
    }

    public static String getDescription(String category) {
        if (category == null) return "";

        switch (category) {
            case WEB_DEV:
                return "You enjoy building things people interact with directly on the web. Look into HTML/CSS/JavaScript, React, and backend frameworks like Node.js or Django.";
            case DATA_SCIENCE:
                return "You're drawn to finding patterns and insights in data. Look into Python, pandas, SQL, and statistics.";
            case AI_ML:
                return "You're fascinated by machines that learn. Look into Python, scikit-learn, TensorFlow/PyTorch, and linear algebra.";
            case CYBERSECURITY:
                return "You like understanding and defending systems. Look into networking, ethical hacking (CEH), and tools like Wireshark and Burp Suite.";
            case MOBILE_DEV:
                return "You like building apps people carry in their pockets. Look into Kotlin/Java for Android or Swift for iOS.";
            case DEVOPS:
                return "You like making systems reliable and automated. Look into Docker, Kubernetes, CI/CD, and cloud platforms like AWS/GCP.";
            case GAME_DEV:
                return "You enjoy building interactive, playable experiences. Look into Unity, Unreal Engine, and C#/C++.";
            case UI_UX:
                return "You care about how products look, feel, and work for real people. Look into Figma, design systems, and user research.";
            default:
                return "Explore a few different tech fields to see what excites you most!";
        }
    }
}
