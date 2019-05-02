package com.dating.relations.activities;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.dating.relations.R;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;

public class MainScreenActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mCreateAccountBtn;
    private Button mSignInBtn;
    private View mView1;
    private LinearLayout mSignInLinearLayout;
    private LoginButton mButtonFacebookLogin;
    private CallbackManager callbackManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main_screen);
        initView();
        callbackManager = CallbackManager.Factory.create();


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);

    }

    private void initView() {

        mCreateAccountBtn = (Button) findViewById(R.id.btn_create_account);
        mCreateAccountBtn.setOnClickListener(this);
        mSignInBtn = (Button) findViewById(R.id.btn_sign_in);
        mSignInBtn.setOnClickListener(this);
        mView1 = (View) findViewById(R.id.view1);
        mSignInLinearLayout = (LinearLayout) findViewById(R.id.linearLayout_sign_in);
        mButtonFacebookLogin = (LoginButton) findViewById(R.id.login_button_facebook);
        mButtonFacebookLogin.setReadPermissions(Arrays.asList("email", "public_profile", "user_friends"));

        mButtonFacebookLogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {


        Intent intent;
        switch (v.getId()) {

            case R.id.btn_create_account:
                intent = new Intent(this, SignInPageActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_sign_in:
                intent = new Intent(this, SignInPageActivity.class);
                startActivity(intent);
                break;
            case R.id.login_button_facebook:

                mButtonFacebookLogin.registerCallback(callbackManager,
                        new FacebookCallback<LoginResult>() {
                            @Override
                            public void onSuccess(LoginResult loginResult) {
                                setFacebookData(loginResult);
                            }

                            @Override
                            public void onCancel() {
                            }

                            @Override
                            public void onError(FacebookException exception) {
                            }
                        });
                break;
            default:
                break;
        }


    }

    private void setFacebookData(final LoginResult loginResult) {
        GraphRequest request = GraphRequest.newMeRequest(
                loginResult.getAccessToken(),
                new GraphRequest.GraphJSONObjectCallback() {
                    @Override
                    public void onCompleted(JSONObject object, GraphResponse response) {
                        // Application code
                        try {
                            Log.i("Response", response.toString());

                            String email = response.getJSONObject().getString("email");
                            String firstName = response.getJSONObject().getString("first_name");
                            Intent intent1;
                            intent1 = new Intent(getApplicationContext(), RegistrationPageActivity.class);
                            startActivity(intent1);
                            finish();


//                            Profile profile = Profile.getCurrentProfile();
//                            String id = profile.getId();
//                            String link = profile.getLinkUri().toString();
//                            Log.i("Link", link);
//                            if (Profile.getCurrentProfile() != null) {
//                                Log.i("Login", "ProfilePic" + Profile.getCurrentProfile().getProfilePictureUri(200, 200));
//                            }




                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
        Bundle parameters = new Bundle();
        parameters.putString("fields", "id,email,first_name,last_name");
        request.setParameters(parameters);
        request.executeAsync();
    }
}