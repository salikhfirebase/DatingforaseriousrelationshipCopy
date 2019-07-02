package com.prettty.foryou.activities;

import android.content.Intent;
import android.graphics.Typeface;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.prettty.foryou.R;
import com.prettty.foryou.utils.Utility;

public class LogInScreenActivity extends AppCompatActivity {

    private EditText passTView;
    private EditText mailTView;
    private Button btnNext;
    private static final String FONT_PATH = "roboto_slab_regular.ttf";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_log_in_screen);

        passTView = findViewById(R.id.passLogIn);
        mailTView = findViewById(R.id.mailLogIn);
        btnNext = findViewById(R.id.btn_log_in);
        Button btnRegistry = findViewById(R.id.btn_reg_open_activity);

        setTypefaces();

        Utility.checkPermission(LogInScreenActivity.this);

        final Intent intent = new Intent(this, ScreenFinishActivity.class);
        final Intent intentRegister = new Intent(this, RegistrationScreenActivity.class);
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!passTView.getText().toString().equals("") || !btnNext.getText().toString().equals("")){
                    intent.putExtra("flag", "LogInScreenActivity");
                    startActivity(intent);
                }else {
                    Toast.makeText(getApplicationContext(),
                            R.string.fill_right_fields, Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnRegistry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intentRegister);
            }
        });
    }
    private void setTypefaces() {
        Typeface typeface = Typeface.createFromAsset(getAssets(), FONT_PATH);
        passTView.setTypeface(typeface);
        mailTView.setTypeface(typeface);
        btnNext.setTypeface(typeface);
    }
}
