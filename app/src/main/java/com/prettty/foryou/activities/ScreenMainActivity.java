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


public class ScreenMainActivity extends AppCompatActivity {

    private RadioButton menRadioBtn;
    private RadioButton femRadioBtn;
    private Button nextBtn;
    private TextView tView;
    private static final String FONT_PATH = "roboto_slab_regular.ttf";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_screen_main);

        menRadioBtn = findViewById(R.id.i_want_male_yeah);
        femRadioBtn = findViewById(R.id.i_want_female_yeah);
        nextBtn = findViewById(R.id.gender_btn_cont);
        tView = findViewById(R.id.choose_ur_pol);

        setTypefaces();

        final String checkFlag = getIntent().getStringExtra("flag");

        final Intent intent = new Intent(this, WhoLookForActivity.class);
        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (menRadioBtn.isChecked() || femRadioBtn.isChecked()) {
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
        tView.setTypeface(typeface);
        nextBtn.setTypeface(typeface);
        menRadioBtn.setTypeface(typeface);
        femRadioBtn.setTypeface(typeface);
    }

}
