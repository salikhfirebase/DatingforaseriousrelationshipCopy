package com.prettty.foryou.activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Typeface;
import androidx.appcompat.app.AppCompatActivity;
import com.prettty.foryou.R;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;


import java.text.SimpleDateFormat;
import java.util.Date;

public class RegistrationScreenActivity extends AppCompatActivity {

    private EditText signInTView;
    private EditText passTView;
    private EditText mailTView;
    private DatePicker pickaDate;
    private Button btnCreateAcc;
    private static final String FONT_PATH = "roboto_slab_regular.ttf";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_registration_screen);

        signInTView = findViewById(R.id.login_field);
        passTView = findViewById(R.id.pass_field);
        mailTView = findViewById(R.id.mail_field);
        btnCreateAcc = findViewById(R.id.sign_up_reg);
        pickaDate = findViewById(R.id.choose_date_picker);

        setTypefaces();

        final Intent intent = new Intent(this, ScreenMainActivity.class);
        btnCreateAcc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!signInTView.getText().toString().equals("") || !passTView.getText().toString().equals("")
                        || !mailTView.getText().toString().equals("")){
                    intent.putExtra("flag", "RegistrationScreenActivity");
                    startActivity(intent);
                }else {
                    Toast.makeText(getApplicationContext(),
                            getString(R.string.fill_right_fields), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void getDate() {
        int day = pickaDate.getDayOfMonth();
        int month = pickaDate.getMonth() + 1;
        int year = pickaDate.getYear();
        @SuppressLint("SimpleDateFormat") SimpleDateFormat dateFormatter = new SimpleDateFormat("MM-dd-yyyy");
        Date d = new Date(year, month, day);
        String strDate = dateFormatter.format(d);
        Toast.makeText(getApplicationContext(),
                strDate, Toast.LENGTH_SHORT).show();
    }

    private void setTypefaces() {
        Typeface typeface = Typeface.createFromAsset(getAssets(), FONT_PATH);
        signInTView.setTypeface(typeface);
        passTView.setTypeface(typeface);
        mailTView.setTypeface(typeface);
        btnCreateAcc.setTypeface(typeface);
    }

}
