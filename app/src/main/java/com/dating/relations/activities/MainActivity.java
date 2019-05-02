package com.dating.relations.activities;

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

import com.dating.relations.R;

public class MainActivity extends AppCompatActivity {

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
        setContentView(R.layout.activity_main);

        manAnswer = findViewById(R.id.btn_man_answer);
        womanAnswer = findViewById(R.id.btn_woman_answer);
        btnContinue = findViewById(R.id.btn_continue);
        text = findViewById(R.id.text_main);

        setTypefaces();

        final String checkFlag = getIntent().getStringExtra("flag");

        final Intent intent = new Intent(this, LookingActivity.class);
        btnContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (manAnswer.isChecked() || womanAnswer.isChecked()) {
                    intent.putExtra("flag", checkFlag);
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(),
                            R.string.choose_your_gender, Toast.LENGTH_SHORT).show();
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
