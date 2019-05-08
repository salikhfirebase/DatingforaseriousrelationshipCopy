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


public class MainPageActivity extends AppCompatActivity {

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
        setContentView(R.layout.activity_main_page);

        maleAnswer = findViewById(R.id.button_man_answer);
        femaleAnswer = findViewById(R.id.button_woman_answer);
        buttonContinue = findViewById(R.id.button_continue);
        text_view = findViewById(R.id.text_main_gender);

        setTypefaces();

        final String checkFlag = getIntent().getStringExtra("flag");

        final Intent intent = new Intent(this, LookingForActivity.class);
        buttonContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (maleAnswer.isChecked() || femaleAnswer.isChecked()) {
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
        text_view.setTypeface(typeface);
        buttonContinue.setTypeface(typeface);
        maleAnswer.setTypeface(typeface);
        femaleAnswer.setTypeface(typeface);
    }

}
