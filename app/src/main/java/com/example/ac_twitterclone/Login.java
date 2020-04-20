package com.example.ac_twitterclone;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Objects;

public class Login extends AppCompatActivity{

    Button btnSignUpStart;
    EditText edtLoginUsername, edtLoginPassword;
    Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Objects.requireNonNull(getSupportActionBar()).setTitle("Login");
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.colorBase)));
        btnSignUpStart = findViewById(R.id.btnSignUpStart);
        btnSignUpStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder mDialog = new AlertDialog.Builder(Login.this);
                View mView = getLayoutInflater().inflate(R.layout.dialog_sign_up, null);
                final EditText edtSignUpUsername = mView.findViewById(R.id.edtSignUpUsername);
                final EditText edtSignUpEmailAddress = mView.findViewById(R.id.edtSignUpEmailAddress);
                final EditText edtSignUpPassword = mView.findViewById(R.id.edtSignUpPassword);
                final EditText edtSignUpPasswordConfirm = mView.findViewById(R.id.edtSignUpPasswordConfirm);
                Button btnSignUp = mView.findViewById(R.id.btnSignUp);

                btnSignUp.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        if (!edtSignUpUsername.getText().toString().isEmpty() &&
                                !edtSignUpEmailAddress.getText().toString().isEmpty() &&
                                !edtSignUpPassword.getText().toString().isEmpty() &&
                                !edtSignUpPasswordConfirm.getText().toString().isEmpty() ) {
                            Toast.makeText(Login.this, edtSignUpUsername.getText().toString() + " has been registered", Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(Login.this, "Please fill in all fields", Toast.LENGTH_LONG).show();
                        }

                    }
                });
                mDialog.setView(mView);
                AlertDialog dialog = mDialog.create();
                dialog.show();
            }
        });



    }
}
