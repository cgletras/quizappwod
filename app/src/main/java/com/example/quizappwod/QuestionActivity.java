package com.example.quizappwod;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class QuestionActivity extends AppCompatActivity {

    int[] rightAnswers = new int[]{0, 0, 0, 0, 0};
    List<String> clans = new ArrayList<>();
    List<String> disciplines = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        setQuestion3();
    }

    //Logic for question 1: Answer = "Lasombra"
    public void onRadioBtClickQuestion1(View view) {

        boolean checked = ((RadioButton) view).isChecked();

        switch (view.getId()) {
            case R.id.rbv_lasombra:
                if (checked)
                    rightAnswers[0] = 1;
                break;
            case R.id.rbv_gangrel:
            case R.id.rbv_ventrue:
                if (checked)
                    rightAnswers[0] = 0;
                break;
        }
    }

    //Logic for question 2: Answer = "Gangrel and Nosferatu"
    public void onCheckBoxQuestion2(View view) {

        boolean checked = ((CheckBox) view).isChecked();

        if (checked) clans.add(((CheckBox) view).getText().toString());
        else clans.remove(((CheckBox) view).getText().toString());

        if (clans.contains("Gangrel") && clans.contains("Nosferatu") && !clans.contains("Toreador"))
            rightAnswers[1] = 1;
        else rightAnswers[1] = 0;
    }

    //Logic for question 3: Answer = "Brujah"
    private void setQuestion3() {
        EditText editText = findViewById(R.id.etv_clan_name);
        String clan = "Brujah";

        editText.setOnFocusChangeListener((v, hasFocus) -> {
            String typedClan = editText.getText().toString().toLowerCase();
            if (!hasFocus) {
                if (typedClan.equals(clan.toLowerCase())) {
                    rightAnswers[2] = 1;
                }
            }
        });

        //Clear focus
        editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    editText.clearFocus();
                }
                return false;
            }
        });


    }

    public void onRadioBtClickQuestion4(View view) {
        boolean checked = ((RadioButton) view).isChecked();

        switch (view.getId()) {
            case R.id.rbv_son:
                if (checked)
                    rightAnswers[3] = 1;
                break;
            case R.id.rbv_akashic:
            case R.id.rbv_verbena:
                if (checked)
                    rightAnswers[3] = 0;
                break;
        }
    }

    public void onCheckBoxQuestion5(View view) {
        boolean checked = ((CheckBox) view).isChecked();

        if (checked) disciplines.add(((CheckBox) view).getText().toString());
        else disciplines.remove(((CheckBox) view).getText().toString());

        if (disciplines.contains("Protean") && disciplines.contains("Fortitude") && disciplines.contains("Animalism") && !disciplines.contains("Celerity") && !disciplines.contains("Dominate")) {
            rightAnswers[4] = 1;
        } else {
            rightAnswers[4] = 0;
        }
    }

    public void checkAnswers(View view) {
        int score = 0;
        for (int i : rightAnswers) {
            score += i;
        }
        Toast.makeText(this, "You got " + score + " out of 5 question right!", Toast.LENGTH_SHORT).show();
        score = 0;
    }
}