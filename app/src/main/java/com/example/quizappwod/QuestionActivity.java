package com.example.quizappwod;

import android.os.Bundle;
import android.util.Log;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        setQuestion3();
    }

    //Logic for question 1: Answer = "Lasombra"
    public void onRadioBtClick(View view) {

        boolean checked = ((RadioButton) view).isChecked();

        switch (view.getId()) {
            case R.id.rbv_lasombra:
                if (checked)
                    rightAnswers[0] = 1;
                Toast.makeText(this, rightAnswers[0] + "", Toast.LENGTH_SHORT).show();
                break;
            case R.id.rbv_gangrel:
            case R.id.rbv_ventrue:
                if (checked)
                    rightAnswers[0] = 0;
                Toast.makeText(this, rightAnswers[0] + "", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    //Logic for question 2: Answer = "Gangrel and Nosferatu"
    public void onCheckBox(View view) {

        boolean checked = ((CheckBox) view).isChecked();

        if (checked) clans.add(((CheckBox) view).getText().toString());
        else clans.remove(((CheckBox) view).getText().toString());

        if (clans.contains("Gangrel") && clans.contains("Nosferatu") && !clans.contains("Toreador"))
            rightAnswers[1] = 1;
        else rightAnswers[1] = 0;
        Toast.makeText(this, rightAnswers[1] + "", Toast.LENGTH_SHORT).show();
    }

    //Logic for question 3: Answer = "Brujah"
    private void setQuestion3() {
        EditText editText = findViewById(R.id.etv_clan_name);
        String clan = "Brujah";

        editText.setOnFocusChangeListener((v, hasFocus) -> {
            String typedClan = editText.getText().toString().toLowerCase();
            if (!hasFocus){
                Log.i("Teste", rightAnswers[2]+"");
                if(typedClan.equals(clan.toLowerCase())) {
                    rightAnswers[2] = 1;
                    Log.i("Teste", rightAnswers[2]+"");
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

    public void checkAnswers(View view) {
        Log.i("Resp", rightAnswers[0]+"");
        Log.i("Resp", rightAnswers[1]+"");
        Log.i("Resp", rightAnswers[2]+"");
    }
}