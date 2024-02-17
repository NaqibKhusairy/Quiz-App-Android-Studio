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
    int currentQuestionIndex = 0;
    int markah = 0;
    int soalanperlujawab = 6;

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
        questions.add(new MultipleChoiceQuestion("Apakah Maksud Bahasa Istana 'Kaus' ?", "Berjalan", "Kasut", "Berlari", "Topi", "B"));
        questions.add(new MultipleChoiceQuestion("Kepala Dalam Bahasa Istana ialah 'Iram-Iram'?", "Betul","Salah","","","B"));
        questions.add(new MultipleChoiceQuestion("-3+9-x+12=10\nBerapakan Nilai x?", "-8", "18", "10", "8", "D"));
        questions.add(new MultipleChoiceQuestion("Fungsi Koroid Ialah Mencegah Pantulan Cahaya Dalam Mata",  "Betul","Salah","","","A"));
        questions.add(new MultipleChoiceQuestion("(2p −3)(5p + 4) =", "4p^2-120", "10p^2-12", "10p^2-7p-12", "10p^2+7p-12", "C"));
        questions.add(new MultipleChoiceQuestion("What The Meanig Of 'Skills in Economic Planning", "Kemahiran dalam Pemusnah Planning Ekonomi", "kemahiran dalam Kemelesetan Ekonomi", "Kemahiran dalam Perancangan Ekonomi", "Kemahiran dalam Inflasi", "C"));
        questions.add(new MultipleChoiceQuestion("Negara X - kenaikan harga barang dan perkhidmatan secara berterusan\nApakah Yang Mungkin Berlaku Di Negara X ?","Deflasi", "Kemelesetan Ekonomi", "Ekonomi Negara X menigkat tinggi","Inflasi","D"));
        questions.add(new MultipleChoiceQuestion("Sultan Ibrahim ibni Sultan Iskandar adalah Sultan Johor","Betul","Salah","","","A"));
        questions.add(new MultipleChoiceQuestion("Hari Raya Aidil-Fitri berlangsung pada Bulan Zulhijah",  "Betul","Salah","","","B"));

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
            startActivity(intent);
        }
    }

    private void checkAnswer(String answer) {
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