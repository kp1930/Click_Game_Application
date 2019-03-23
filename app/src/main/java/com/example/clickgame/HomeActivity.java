package com.example.clickgame;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class HomeActivity extends AppCompatActivity {

    RelativeLayout relativeLayout;
    TextView tvScore, tvTime;
    Button btnPlay;
    int i = 0;
    CountDownTimer countDownTimer;
    long time_milli_seconds = 10000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        relativeLayout = findViewById(R.id.rLayout);
        tvTime = findViewById(R.id.textViewTime);
        tvScore = findViewById(R.id.textViewScore);
        btnPlay = findViewById(R.id.buttonPlay);

        tvScore.setText("0");
        tvTime.setText("10 Sec");
        relativeLayout.setEnabled(false);

        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                play();
            }
        });

        relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i++;
                tvScore.setText("" + i);
            }
        });
    }

    public void play() {
        btnPlay.setVisibility(View.GONE);
        relativeLayout.setEnabled(true);
        i = 0;
        startTime();
    }

    public void startTime() {
        countDownTimer = new CountDownTimer(time_milli_seconds, 1000) {
            @Override
            public void onTick(long l) {
                time_milli_seconds = l;
                update();
            }
            @Override
            public void onFinish() {
                Intent intent = new Intent(HomeActivity.this, ResultActivity.class);
                intent.putExtra("score", i);
                startActivity(intent);
                finish();
            }
        }.start();
    }

    public void update() {
        int seconds = (int) time_milli_seconds / 1000;
        tvTime.setText("" + seconds);
    }
}