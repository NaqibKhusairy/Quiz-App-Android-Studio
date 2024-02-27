package com.naqib.test_quiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    TextView soalanTextView , currentquest;
    Button pilihanAButton, pilihanBButton, pilihanCButton, pilihanDButton;

    List<Question> questions;
    List<String> userAnswers = new ArrayList<>();

    int currentQuestionIndex = 0;
    int markah = 0;
    int soalanperlujawab = 10;

    String userAnswer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        soalanTextView = findViewById(R.id.soalan);
        pilihanAButton = findViewById(R.id.pilihanA);
        pilihanBButton = findViewById(R.id.pilihanB);
        pilihanCButton = findViewById(R.id.pilihanC);
        pilihanDButton = findViewById(R.id.pilihanD);
        currentquest = findViewById(R.id.currentquest);

        questions = new ArrayList<>();
        questions.add(new MultipleChoiceQuestion("Select the suitable data type for price item", "float", "char", "bool", "int", "A"));
        questions.add(new MultipleChoiceQuestion("Select the valid variable", "=myname","my_name","2myname","my name","B"));
        questions.add(new MultipleChoiceQuestion("Express the CORRECT statement to write the condition statement in C\n\"C = mark is greater or equal to 40\"", "mark==40", "mark>=40", "mark=>40", "mark<40", "B"));
        questions.add(new MultipleChoiceQuestion("Identify the acceptable way to define a constant.",  "const int code = 200;","conts int code = ‘200’;","int const code = \"200\";","int const code = 200;","A"));
        questions.add(new MultipleChoiceQuestion("Identify the symbol that must end the C++ code statement.", "comma", "semicolon", "colon", "hash", "B"));
        questions.add(new MultipleChoiceQuestion("Identify which is NOT the type of looping control structure.", "for", "while", "do....while", "if....else", "D"));
        questions.add(new MultipleChoiceQuestion("Select the operator used to compare between two variables","=", "==", "++","--","B"));
        questions.add(new MultipleChoiceQuestion("Choose the correct definition of comment in C++","Comments are parts of the source code disregard by the compiler","Comments are executed by compiler to find the meaning of the comment","Comments are executable","Comments are main structure of C++ program","A"));
        questions.add(new MultipleChoiceQuestion("Identify the numbers of 'do.....while' loop which is guaranteed to loop",  "3","2","1","0","C"));
        questions.add(new MultipleChoiceQuestion("Identify the number of repetitions that will occur for the following looping control structure:\n\"for(count=1; count<=10;count++)\"",  "8","9","10","11","C"));

        Collections.shuffle(questions);

        displayQuestion();

        pilihanAButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer("A");
            }
        });

        pilihanBButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer("B");
            }
        });

        pilihanCButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer("C");
            }
        });

        pilihanDButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer("D");
            }
        });
    }

    private void displayQuestion() {
        if (currentQuestionIndex < soalanperlujawab) {
            Question question = questions.get(currentQuestionIndex);
            soalanTextView.setText(question.getQuestion());
            currentquest.setText((currentQuestionIndex + 1)+" / " + soalanperlujawab);
            if (question instanceof MultipleChoiceQuestion) {
                MultipleChoiceQuestion mcQuestion = (MultipleChoiceQuestion) question;
                pilihanAButton.setText("A. " + mcQuestion.getOptionA());
                pilihanBButton.setText("B. " + mcQuestion.getOptionB());
                if (!mcQuestion.getOptionC().isEmpty() && !mcQuestion.getOptionD().isEmpty()) {
                    pilihanCButton.setText("C. " + mcQuestion.getOptionC());
                    pilihanDButton.setText("D. " + mcQuestion.getOptionD());
                    pilihanCButton.setVisibility(View.VISIBLE);
                    pilihanDButton.setVisibility(View.VISIBLE);
                } else {
                    pilihanCButton.setVisibility(View.GONE);
                    pilihanDButton.setVisibility(View.GONE);
                }
            }
        } else {
            Intent intent = new Intent(MainActivity.this, Markah.class);
            intent.putExtra("markah", markah);
            intent.putExtra("bilsoalan", soalanperlujawab);
            intent.putExtra("userAnswers", new ArrayList<>(userAnswers));
            startActivity(intent);
        }
    }

    private void checkAnswer(String answer) {
        switch (answer) {
            case "A":
                userAnswer = pilihanAButton.getText().toString();
                break;
            case "B":
                userAnswer = pilihanBButton.getText().toString();
                break;
            case "C":
                userAnswer = pilihanCButton.getText().toString();
                break;
            case "D":
                userAnswer = pilihanDButton.getText().toString();
                break;
        }
        // Store the user's answer
        userAnswers.add(userAnswer);

        if (answer.equals(questions.get(currentQuestionIndex).getCorrectAnswer())) {
            Toast.makeText(this, "True!", Toast.LENGTH_SHORT).show();
            markah++;
        }
        currentQuestionIndex++;
        displayQuestion();
    }

    private static abstract class Question {
        private String question;

        public Question(String question) {
            this.question = question;
        }

        public String getQuestion() {
            return question;
        }

        public abstract String getCorrectAnswer();
    }

    private static class MultipleChoiceQuestion extends Question {
        private String optionA, optionB, optionC, optionD;
        private String correctAnswer;

        public MultipleChoiceQuestion(String question, String optionA, String optionB, String optionC, String optionD, String correctAnswer) {
            super(question);
            this.optionA = optionA;
            this.optionB = optionB;
            this.optionC = optionC;
            this.optionD = optionD;
            this.correctAnswer = correctAnswer;
        }

        public String getOptionA() {
            return optionA;
        }

        public String getOptionB() {
            return optionB;
        }

        public String getOptionC() {
            return optionC;
        }

        public String getOptionD() {
            return optionD;
        }

        @Override
        public String getCorrectAnswer() {
            return correctAnswer;
        }
    }

    private static class TrueFalseQuestion extends Question {
        private boolean correctAnswer;

        public TrueFalseQuestion(String question, boolean correctAnswer) {
            super(question);
            this.correctAnswer = correctAnswer;
        }

        public boolean isCorrectAnswer() {
            return correctAnswer;
        }

        @Override
        public String getCorrectAnswer() {
            return String.valueOf(correctAnswer);
        }
    }

    private static class FillInTheBlankQuestion extends Question {
        private String correctAnswer;

        public FillInTheBlankQuestion(String question, String correctAnswer) {
            super(question);
            this.correctAnswer = correctAnswer;
        }

        @Override
        public String getCorrectAnswer() {
            return correctAnswer;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Toast.makeText(this, "Questions reset!", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
    }
}