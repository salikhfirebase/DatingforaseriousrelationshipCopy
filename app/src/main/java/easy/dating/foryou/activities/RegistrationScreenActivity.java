package easy.dating.foryou.activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Typeface;
import androidx.appcompat.app.AppCompatActivity;
import easy.dating.foryou.R;

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

    private EditText textViewLogin;
    private EditText textViewPassword;
    private EditText textViewEmail;
    private DatePicker datePicker;
    private Button buttonRegister;
    private static final String FONT_PATH = "roboto_slab_regular.ttf";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_registration_screen);

        textViewLogin = findViewById(R.id.enterLogin);
        textViewPassword = findViewById(R.id.enterPassword);
        textViewEmail = findViewById(R.id.enterEmail);
        buttonRegister = findViewById(R.id.button_register);
        datePicker = findViewById(R.id.enter_datePicker);

        setTypefaces();

        final Intent intent = new Intent(this, MainPageActivity.class);
        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!textViewLogin.getText().toString().equals("") || !textViewPassword.getText().toString().equals("")
                        || !textViewEmail.getText().toString().equals("")){
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
        int day = datePicker.getDayOfMonth();
        int month = datePicker.getMonth() + 1;
        int year = datePicker.getYear();
        @SuppressLint("SimpleDateFormat") SimpleDateFormat dateFormatter = new SimpleDateFormat("MM-dd-yyyy");
        Date d = new Date(year, month, day);
        String strDate = dateFormatter.format(d);
        Toast.makeText(getApplicationContext(),
                strDate, Toast.LENGTH_SHORT).show();
    }

    private void setTypefaces() {
        Typeface typeface = Typeface.createFromAsset(getAssets(), FONT_PATH);
        textViewLogin.setTypeface(typeface);
        textViewPassword.setTypeface(typeface);
        textViewEmail.setTypeface(typeface);
        buttonRegister.setTypeface(typeface);
    }

}
