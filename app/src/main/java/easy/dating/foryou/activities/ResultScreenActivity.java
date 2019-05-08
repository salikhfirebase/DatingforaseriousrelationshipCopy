package easy.dating.foryou.activities;

import android.content.Intent;
import android.graphics.Typeface;
import androidx.appcompat.app.AppCompatActivity;
import easy.dating.foryou.R;

import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;


public class ResultScreenActivity extends AppCompatActivity {

    private TextView textView;
    private static final String FONT_PATH = "roboto_slab_regular.ttf";
    private String checkFlag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_result_screen);

        textView = findViewById(R.id.text_view_result);
        setTypefaces();

        checkFlag = getIntent().getStringExtra("flag");
        if (checkFlag.equals("SignInScreenActivity")) {
            textView.setText(R.string.text_sign_in);
        } else
            textView.setText(R.string.text_success_registration);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(this, ResultScreenActivity.class);
        intent.putExtra("flag", checkFlag);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    private void setTypefaces() {
        Typeface typeface = Typeface.createFromAsset(getAssets(), FONT_PATH);
        textView.setTypeface(typeface);
    }
}
