package easy.dating.foryou.activities;

import android.content.Intent;
import android.graphics.Typeface;
import androidx.appcompat.app.AppCompatActivity;
import easy.dating.foryou.R;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;


public class LookingForActivity extends AppCompatActivity {

    private RadioButton maleAnswer;
    private RadioButton femaleAnswer;
    private Button buttonContinue;
    private TextView text_view;
    private static final String FONT_PATH = "roboto_slab_regular.ttf";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_looking_for);

        maleAnswer = findViewById(R.id.button_man_answer_looking);
        femaleAnswer = findViewById(R.id.button_woman_answer_looking);
        buttonContinue = findViewById(R.id.button_continue_looking);
        text_view = findViewById(R.id.text_looking_for);
        setTypefaces();

        final String checkFlag = getIntent().getStringExtra("flag");

        final Intent intent = new Intent(this, ResultScreenActivity.class);

        buttonContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (maleAnswer.isChecked() || femaleAnswer.isChecked()) {
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
        text_view.setTypeface(typeface);
        buttonContinue.setTypeface(typeface);
        maleAnswer.setTypeface(typeface);
        femaleAnswer.setTypeface(typeface);
    }
}
