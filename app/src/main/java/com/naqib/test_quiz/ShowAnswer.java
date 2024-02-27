package com.naqib.test_quiz;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.text.HtmlCompat;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.Html;
import android.widget.TextView;

import java.util.List;

public class ShowAnswer extends AppCompatActivity {
    TextView soalan, result;
    int markah, bilsoalan;
    String[] Q = new String[11];
    String[] A = new String[11];
    String[] YA = new String[11];

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_answer);

        soalan = findViewById(R.id.soalan);
        result = findViewById(R.id.result);

        markah = getIntent().getIntExtra("markah", 0);
        bilsoalan = getIntent().getIntExtra("bilsoalan", 0);
        List<String> userAnswers = getIntent().getStringArrayListExtra("userAnswers");

        result.setText("Your Mark = " + markah + "/" + bilsoalan);

        Q[1] = "Select the suitable data type for price item";
        Q[2] = "Select the valid variable";
        Q[3] = "Express the CORRECT statement to write the condition statement in C<br>\"C = mark is greater or equal to 40\"";
        Q[4] = "Identify the acceptable way to define a constant.";
        Q[5] = "Identify the symbol that must end the C++ code statement.";
        Q[6] = "Identify which is NOT the type of looping control structure.";
        Q[7] = "Select the operator used to compare between two variables";
        Q[8] = "Choose the correct definition of comment in C+";
        Q[9] = "Identify the numbers of 'do.....while' loop which is guaranteed to loop";
        Q[10] = "Identify the number of repetitions that will occur for the following looping control structure:<br>\"for(count=1; count<=10;count++)\"";

        A[1] = "A. float";
        A[2] = "B. my_name";
        A[3] = "B. mark>=40";
        A[4] = "A. const int code = 200;";
        A[5] = "B. semicolon";
        A[6] = "D. if....else";
        A[7] = "B. ==";
        A[8] = "A. Comments are parts of the source code disregard by the compiler";
        A[9] = "C. 1";
        A[10] = "C. 10";

        if (userAnswers != null) {
            for (int x = 0; x < bilsoalan; x++) {
                if (x < userAnswers.size()) {
                    switch (userAnswers.get(x)) {
                        case "A. float":
                        case "B. char":
                        case "C. bool":
                        case "D. int":
                            YA[1] = userAnswers.get(x);
                            break;
                        case "A. =myname":
                        case "B. my_name":
                        case "C. 2myname":
                        case "D. my name":
                            YA[2] = userAnswers.get(x);
                            break;
                        case "A. mark==40":
                        case "B. mark>=40":
                        case "C. mark=>40":
                        case "D. mark<40":
                            YA[3] = userAnswers.get(x);
                            break;
                        case "A. const int code = 200;":
                        case "B. conts int code = ‘200’;":
                        case "C. int const code = \"200\";":
                        case "D. int const code = 200;":
                            YA[4] = userAnswers.get(x);
                            break;
                        case "A. comma":
                        case "B. semicolon":
                        case "C. colon":
                        case "D. hash":
                            YA[5] = userAnswers.get(x);
                            break;
                        case "A. for":
                        case "B. while":
                        case "C. do....while":
                        case "D. if....else":
                            YA[6] = userAnswers.get(x);
                            break;
                        case "A. =":
                        case "B. ==":
                        case "C. ++":
                        case "D. --":
                            YA[7] = userAnswers.get(x);
                            break;
                        case "A. Comments are parts of the source code disregard by the compiler":
                        case "B. Comments are executed by compiler to find the meaning of the comment":
                        case "C. Comments are executable":
                        case "D. Comments are main structure of C++ program":
                            YA[8] = userAnswers.get(x);
                            break;
                        case "A. 3":
                        case "B. 2":
                        case "C. 1":
                        case "D. 0":
                            YA[9] = userAnswers.get(x);
                            break;
                        case "A. 8":
                        case "B. 9":
                        case "C. 10":
                        case "D. 11":
                            YA[10] = userAnswers.get(x);
                            break;
                    }
                }
            }
        }

        soalan.setText(HtmlCompat.fromHtml("<br><br><br>Question 1 : " + Q[1] + "<br><br>Answer: " + A[1] + "<br>Your Answer: <font color='" + (A[1].equals(YA[1]) ? "green" : "red") + "'>" + YA[1] + "</font><br><br><br><br>", HtmlCompat.FROM_HTML_MODE_LEGACY));

        for (int x = 2; x <= bilsoalan; x++) {
            if (userAnswers != null && x <= userAnswers.size()) {
                soalan.append(HtmlCompat.fromHtml("Question " + x + " : " + Q[x] + "<br><br>Answer: " + A[x] + "<br>Your Answer: <font color='" + (A[x].equals(YA[x]) ? "green" : "red") + "'>" + YA[x] + "</font><br><br><br><br>", HtmlCompat.FROM_HTML_MODE_LEGACY));
            }
        }
    }
}
