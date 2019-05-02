package com.dating.relations.activities;

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

import com.dating.relations.R;
import com.dating.relations.utils.Utility;
import com.yandex.metrica.YandexMetrica;
import com.yandex.metrica.YandexMetricaConfig;

public class SignInPageActivity extends AppCompatActivity {

    private EditText textPassword;
    private EditText textEmail;
    private Button btnEnter;
    private Button btnRegistry;
    private static final String FONT_PATH = "roboto_slab_regular.ttf";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_sign_in_page);

        textPassword = findViewById(R.id.etPasswordSign);
        textEmail = findViewById(R.id.etEmailSign);
        btnEnter = findViewById(R.id.btn_registerSign);
        btnRegistry = findViewById(R.id.btn_registerActivity);

        setTypefaces();

        Utility.checkPermission(SignInPageActivity.this);

        final Intent intent = new Intent(this, ResultActivity.class);
        final Intent intentRegister = new Intent(this, RegistrationPageActivity.class);
        btnEnter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!textPassword.getText().toString().equals("") || !textEmail.getText().toString().equals("")){
                    intent.putExtra("flag", "SignInPageActivity");
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
        textPassword.setTypeface(typeface);
        textEmail.setTypeface(typeface);
        btnEnter.setTypeface(typeface);
    }
}
