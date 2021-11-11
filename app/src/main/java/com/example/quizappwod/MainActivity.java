package com.example.quizappwod;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityOptionsCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void startQuiz(View view) {
        Intent intent = new Intent(this, QuestionActivity.class);
        ImageView wodImage = (ImageView) findViewById(R.id.iv_wod);
        ActivityOptionsCompat activityOptionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(this, wodImage, "iv_wod");

        startActivity(intent, activityOptionsCompat.toBundle());
    }
}