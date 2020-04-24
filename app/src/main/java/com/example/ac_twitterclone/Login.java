package com.example.ac_twitterclone;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class Login extends AppCompatActivity {

    Button btnSignUpStart, btnLogin, btnForgotPassword;
    EditText edtLoginUsername, edtLoginPassword;
    ImageView imgLogo;
    TextView txtAppName;

    // Splash screen part 2
    Handler handler = new Handler();
    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            edtLoginUsername.animate().alpha(1).setDuration(2000);
            edtLoginPassword.animate().alpha(1).setDuration(2000);
            btnLogin.animate().alpha(1).setDuration(2000);
            btnSignUpStart.animate().alpha(1).setDuration(2000);
            btnForgotPassword.animate().alpha(1).setDuration(2000);
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        btnSignUpStart = findViewById(R.id.btnSignUpStart);
        btnLogin = findViewById(R.id.btnLogin);
        edtLoginUsername = findViewById(R.id.edtUsername);
        edtLoginPassword = findViewById(R.id.edtPassword);
        btnForgotPassword = findViewById(R.id.btnForgotPassword);
        imgLogo = findViewById(R.id.imgLogo);
        txtAppName = findViewById(R.id.txtAppName);

        //Splash screen part 1
        imgLogo.animate().translationY(0).setDuration(2000).setStartDelay(2000);
        txtAppName.animate().translationY(0).setDuration(2000).setStartDelay(2000);

        handler.postDelayed(runnable, 3000);

        // Check if user is logged in, if true load the social media activity
        if (ParseUser.getCurrentUser() != null) {
            transitionToSocialMediaActivity();
        }
    }

    // Handles the Login process
    public void onClickLogin(View v) {
        //checks if all fields are completed
        if (!edtLoginUsername.getText().toString().isEmpty() && !edtLoginPassword.getText().toString().isEmpty()) {
            //Check login details against Parse Users on server
            ParseUser.logInInBackground(edtLoginUsername.getText().toString(), edtLoginPassword.getText().toString(), new LogInCallback() {
                @Override
                public void done(ParseUser user, ParseException e) {
                    //login if no error
                    if (user != null && e == null) {
                        transitionToSocialMediaActivity();
                    } else {
                        Toast.makeText(Login.this, e.getMessage(), Toast.LENGTH_LONG).show();
                        edtLoginPassword.setText(null);
                    }
                }
            });

        } else {
            Toast.makeText(Login.this, "Username and password required", Toast.LENGTH_LONG).show();
        }
    }

    // Populate the Sign Up Alert Dialogue
    public void OnClickSignUp(View v) {

        // Sets up resource for Alert Dialog
        AlertDialog.Builder mDialog = new AlertDialog.Builder(Login.this);
        View mView = getLayoutInflater().inflate(R.layout.dialog_sign_up, null);
        final EditText edtSignUpUsername = mView.findViewById(R.id.edtSignUpUsername);
        final EditText edtSignUpEmailAddress = mView.findViewById(R.id.edtSignUpEmailAddress);
        final EditText edtSignUpPassword = mView.findViewById(R.id.edtSignUpPassword);
        final EditText edtSignUpPasswordConfirm = mView.findViewById(R.id.edtSignUpPasswordConfirm);
        Button btnSignUp = mView.findViewById(R.id.btnSignUp);

        // Handles the Sign Up process when Sign Up is clicked inside the Alert Dialog
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Checks if all fields have been filled in
                if (!edtSignUpUsername.getText().toString().isEmpty() &&
                        !edtSignUpEmailAddress.getText().toString().isEmpty() &&
                        !edtSignUpPassword.getText().toString().isEmpty() &&
                        !edtSignUpPasswordConfirm.getText().toString().isEmpty()) {

                    // Checks if the passwords match
                    if (edtSignUpPassword.getText().toString().equals(edtSignUpPasswordConfirm.getText().toString())) {
                        //   Create a new Parse User & assign related email and password from user input
                        final ParseUser appUser = new ParseUser();
                        appUser.setUsername(edtSignUpUsername.getText().toString());
                        appUser.setEmail(edtSignUpEmailAddress.getText().toString());
                        appUser.setPassword(edtSignUpPasswordConfirm.getText().toString());
                        appUser.signUpInBackground(new SignUpCallback() {
                            @Override
                            public void done(ParseException e) {
                                // sign up user if no error
                                if (e == null) {
                                    Toast.makeText(Login.this, appUser.getUsername() + " Signed Up Successfully", Toast.LENGTH_LONG).show();
                                    transitionToSocialMediaActivity();
                                    // Else show error message and remove text in password fields
                                } else {
                                    Toast.makeText(Login.this, e.getMessage(), Toast.LENGTH_LONG).show();
                                    edtSignUpPassword.setText(null);
                                    edtSignUpPasswordConfirm.setText(null);
                                }
                            }
                        });
                    } else {
                        Toast.makeText(Login.this, "Passwords Do Not Match!", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(Login.this, "Please fill in all fields", Toast.LENGTH_LONG).show();
                }

            }
        });

        //Builds the Alert Dialog
        mDialog.setView(mView);
        AlertDialog dialog = mDialog.create();
        dialog.show();
    }

    //Transfers user to social media activity
    private void transitionToSocialMediaActivity() {
        Intent intent = new Intent(Login.this, SocialMediaActivity.class);
        startActivity(intent);
        finish();
    }


}
