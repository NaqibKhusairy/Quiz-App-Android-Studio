package com.naqib.test_quiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class Markah extends AppCompatActivity {
    TextView markahTextView;
    int markah , bilsoalan;
    double peratus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_markah);

        markahTextView = findViewById(R.id.markah);

        markah = getIntent().getIntExtra("markah", 0);
        bilsoalan = getIntent().getIntExtra("bilsoalan", 0);
        peratus = ((double) markah / bilsoalan) * 100; // Assuming there are 5 questions in total
        String formattedPeratus = String.format("%.2f", peratus);

        markahTextView.setText("Markah : " + markah + "/"+bilsoalan+"\nPeratus : " + formattedPeratus+ "%");
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Toast.makeText(this, "Soalan Direset Semula!", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); // Clear the back stack
        startActivity(intent);
        finish(); // Finish current activity
    }
}
