package com.mazur.datingforaseriousrelationship.activities;

import android.content.Intent;
import android.graphics.Typeface;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.mazur.datingforaseriousrelationship.R;

public class LookingActivity extends AppCompatActivity {

    private RadioButton manAnswer;
    private RadioButton womanAnswer;
    private Button btnContinue;
    private TextView text;
    private static final String FONT_PATH = "roboto_slab_regular.ttf";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_looking);

        manAnswer = findViewById(R.id.btn_man_answer_looking);
        womanAnswer = findViewById(R.id.btn_woman_answer_looking);
        btnContinue = findViewById(R.id.btn_continue_looking);
        text = findViewById(R.id.text_looking);
        setTypefaces();

        final String checkFlag = getIntent().getStringExtra("flag");

        final Intent intent = new Intent(this, ResultActivity.class);

        btnContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (manAnswer.isChecked() || womanAnswer.isChecked()) {
                    intent.putExtra("flag", checkFlag);
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(),
                            R.string.who_are_you_looking_for, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void setTypefaces() {

        Typeface typeface = Typeface.createFromAsset(getAssets(), FONT_PATH);
        text.setTypeface(typeface);
        btnContinue.setTypeface(typeface);
        manAnswer.setTypeface(typeface);
        womanAnswer.setTypeface(typeface);
    }
}
