package com.prettty.foryou.activities;

import android.content.Intent;
import android.graphics.Typeface;
import androidx.appcompat.app.AppCompatActivity;
import com.prettty.foryou.R;

import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;


public class ScreenFinishActivity extends AppCompatActivity {

    private TextView justTView;
    private static final String FONT_PATH = "roboto_slab_regular.ttf";
    private String checker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_screen_finish);

        justTView = findViewById(R.id.t_view_res);
        setTypefaces();

        checker = getIntent().getStringExtra("flag");
        if (checker.equals("LogInScreenActivity")) {
            justTView.setText(R.string.text_sign_in);
        } else
            justTView.setText(R.string.text_success_registration);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(this, ScreenFinishActivity.class);
        intent.putExtra("flag", checker);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    private void setTypefaces() {
        Typeface typeface = Typeface.createFromAsset(getAssets(), FONT_PATH);
        justTView.setTypeface(typeface);
    }
}
