package com.example.clickgame;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {

    Button btnPlayAgain;
    TextView tvScore, tvBestScore, tvBestScoreNo;
    Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        btnPlayAgain = findViewById(R.id.buttonPlayAgain);
        tvScore = findViewById(R.id.textViewScore);
        tvBestScore = findViewById(R.id.textViewBestScore);
        tvBestScoreNo = findViewById(R.id.textViewBestScoreNo);

        btnPlayAgain.setEnabled(false);

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                btnPlayAgain.setEnabled(true);
            }
        };
        handler.postDelayed(runnable, 2000);

        Intent intent = getIntent();
        int scores = intent.getIntExtra("score", 0);

        tvScore.setText("" + scores);

        SharedPreferences preferences = getSharedPreferences("PREFERENCES", MODE_PRIVATE);
        int bestscores = preferences.getInt("bestscore", 0);

        if (scores > bestscores) {
            SharedPreferences.Editor editor = getSharedPreferences("PREFERENCES", MODE_PRIVATE).edit();
            editor.putInt("bestscore", scores);
            editor.apply();
            tvBestScoreNo.setText("" + scores);
        } else tvBestScoreNo.setText("" + bestscores);

        btnPlayAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ResultActivity.this, HomeActivity.class));
                finish();
            }
        });
    }
}