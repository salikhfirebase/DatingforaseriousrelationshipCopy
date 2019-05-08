package easy.dating.foryou.activities;

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


import easy.dating.foryou.R;
import easy.dating.foryou.utils.Utility;

public class SignInScreenActivity extends AppCompatActivity {

    private EditText textViewPassword;
    private EditText textViewEmail;
    private Button buttonEnter;
    private static final String FONT_PATH = "roboto_slab_regular.ttf";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_sign_in_screen);

        textViewPassword = findViewById(R.id.enterPasswordSign);
        textViewEmail = findViewById(R.id.enterEmailSign);
        buttonEnter = findViewById(R.id.button_registerSign);
        Button btnRegistry = findViewById(R.id.button_registerActivity);

        setTypefaces();

        Utility.checkPermission(SignInScreenActivity.this);

        final Intent intent = new Intent(this, ResultScreenActivity.class);
        final Intent intentRegister = new Intent(this, RegistrationScreenActivity.class);
        buttonEnter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!textViewPassword.getText().toString().equals("") || !buttonEnter.getText().toString().equals("")){
                    intent.putExtra("flag", "SignInScreenActivity");
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
        textViewPassword.setTypeface(typeface);
        textViewEmail.setTypeface(typeface);
        buttonEnter.setTypeface(typeface);
    }
}
