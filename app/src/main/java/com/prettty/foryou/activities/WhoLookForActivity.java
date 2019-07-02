package com.prettty.foryou.activities;

import android.content.Intent;
import android.graphics.Typeface;
import androidx.appcompat.app.AppCompatActivity;
import com.prettty.foryou.R;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;


public class WhoLookForActivity extends AppCompatActivity {

    private RadioButton menRadioButton;
    private RadioButton femRadioButton;
    private Button nextBtn;
    private TextView tView;
    private static final String FONT_PATH = "roboto_slab_regular.ttf";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_who_look_for);

        menRadioButton = findViewById(R.id.look_for_male_yeah);
        femRadioButton = findViewById(R.id.look_for_female_yeah);
        nextBtn = findViewById(R.id.who_r_u_look_btn);
        tView = findViewById(R.id.who_r_u_look_for);
        setTypefaces();

        final String checkFlag = getIntent().getStringExtra("flag");

        final Intent intent = new Intent(this, ScreenFinishActivity.class);

        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (menRadioButton.isChecked() || femRadioButton.isChecked()) {
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
        tView.setTypeface(typeface);
        nextBtn.setTypeface(typeface);
        menRadioButton.setTypeface(typeface);
        femRadioButton.setTypeface(typeface);
    }
}
