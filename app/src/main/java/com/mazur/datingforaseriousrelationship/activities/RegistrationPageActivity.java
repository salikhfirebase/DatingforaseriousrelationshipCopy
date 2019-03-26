package com.mazur.datingforaseriousrelationship.activities;

import android.content.Intent;
import android.graphics.Typeface;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.mazur.datingforaseriousrelationship.R;

import java.text.SimpleDateFormat;
import java.util.Date;

public class RegistrationPageActivity extends AppCompatActivity {

    private EditText textLogin;
    private EditText textPassword;
    private EditText textEmail;
    private DatePicker datePicker;
    private Button btnRegister;
    private static final String FONT_PATH = "roboto_slab_regular.ttf";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_registration_page);

        textLogin = findViewById(R.id.etLogin);
        textPassword = findViewById(R.id.etPassword);
        textEmail = findViewById(R.id.etEmail);
        btnRegister = findViewById(R.id.btn_register);
        datePicker = findViewById(R.id.datePicker);

        setTypefaces();

        final Intent intent = new Intent(this, MainActivity.class);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!textLogin.getText().toString().equals("") || !textPassword.getText().toString().equals("")
                        || !textEmail.getText().toString().equals("")){
                    intent.putExtra("flag", "RegistrationPageActivity");
                    startActivity(intent);
                }else {
                    Toast.makeText(getApplicationContext(),
                            getString(R.string.fill_right_fields), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void getDate() {
        int day = datePicker.getDayOfMonth();
        int month = datePicker.getMonth() + 1;
        int year = datePicker.getYear();
        SimpleDateFormat dateFormatter = new SimpleDateFormat("MM-dd-yyyy");
        Date d = new Date(year, month, day);
        String strDate = dateFormatter.format(d);
        Toast.makeText(getApplicationContext(),
                strDate, Toast.LENGTH_SHORT).show();
    }

    private void setTypefaces() {
        Typeface typeface = Typeface.createFromAsset(getAssets(), FONT_PATH);
        textLogin.setTypeface(typeface);
        textPassword.setTypeface(typeface);
        textEmail.setTypeface(typeface);
        btnRegister.setTypeface(typeface);
    }

}
