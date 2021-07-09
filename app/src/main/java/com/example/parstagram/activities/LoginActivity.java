package com.example.parstagram.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.parstagram.R;
import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class LoginActivity extends AppCompatActivity {

    public static final String TAG = "LoginActivity";

    private EditText etUsername;
    private EditText etPassword;
    private Button btnLogin;
    private Button btnSignup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        if (ParseUser.getCurrentUser() != null) {
            goMainActivity();
        }
        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);
        btnSignup = findViewById(R.id.btnSignup);


        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = etUsername.getText().toString();
                String password = etPassword.getText().toString();
                loginUser(username, password);
            }
        });

        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = etUsername.getText().toString();
                String password = etPassword.getText().toString();
                signupUser(username, password);
            }
        });
    }

    private void loginUser(String username, String password) {
        Log.i(TAG,"Attempting to login " + username);

        if (username.isEmpty()) {
            Toast.makeText(LoginActivity.this, "Your username cannot be empty!", Toast.LENGTH_LONG);
            return;
        }

        if (password.isEmpty()) {
            Toast.makeText(LoginActivity.this, "Your username cannot be empty!", Toast.LENGTH_LONG);
            return;
        }

        // Todo: navigate to the main activity if the user has signed in successfully
        ParseUser.logInInBackground(username, password, new LogInCallback() {
            @Override
            public void done(ParseUser user, ParseException e) {
                if (e != null) {
                    Log.i(TAG, "Issue with login", e);
                    return;
                }
                Log.i(TAG, "Navigating into the feed");
                // TODO: navigate to the Main Activity if the user has signed in successfully
                goMainActivity();
            }
        });
    }

    private void signupUser(String username, String password) {
        Log.i(TAG,"Registering the user " + username);

        if (username.isEmpty()) {
            Toast.makeText(LoginActivity.this, "Your username cannot be empty!", Toast.LENGTH_LONG);
            return;
        }

        if (password.isEmpty()) {
            Toast.makeText(LoginActivity.this, "Your username cannot be empty!", Toast.LENGTH_LONG);
            return;
        }

        ParseUser user = new ParseUser();

        user.setUsername(username);
        user.setPassword(username);

        user.signUpInBackground(new SignUpCallback() {
            @Override
            public void done(ParseException e) {
                if (e != null) {
                    Log.i(TAG, "Issue with login", e);
                    return;
                }
                Log.i(TAG, "Navigating into the feed");
                // TODO: navigate to the Main Activity if the user has signed in successfully
                goMainActivity();
            }
        });
    }

    void goMainActivity() {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }

    private void logoutUser() {
        ParseUser.logOut();
    }
}