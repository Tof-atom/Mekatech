package ca.appmobilecourse.mekatech;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import ca.appmobilecourse.mekatech.Models.User;
import ca.appmobilecourse.mekatech.Session.SessionManagement;

public class Activity_login extends AppCompatActivity {

    private EditText email_editText;
    private EditText password_editText;
    private Button login_btn;
    private Button register_btn;

    // creating constant keys for shared preferences.
    public static final String SHARED_PREFS = "shared_prefs";

    // key for storing email.
    public static final String EMAIL_KEY = "email_key";

    // key for storing password.
    public static final String PASSWORD_KEY = "password_key";

    // variable for shared preferences.
    SharedPreferences sharedpreferences;
    String email, password;

    DataBaseHelper db = new DataBaseHelper(Activity_login.this);
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        sharedpreferences = getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
        email = sharedpreferences.getString("EMAIL_KEY", null);
        password = sharedpreferences.getString("PASSWORD_KEY", null);

        // TODO: Email validation, password encrypt and double check

        login_btn = (Button) findViewById(R.id.login_btn);
        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                email_editText = (EditText) findViewById(R.id.email_editText);
                String email = email_editText.getText().toString();
                user = db.searchUser(email);

//                Toast.makeText(Activity_login.this, user.toString(), Toast.LENGTH_LONG).show();
                //TODO: display message for non-existing user
                if (user != null) {
                    login(user);
                } else {
                    Toast.makeText(Activity_login.this, "User account not found", Toast.LENGTH_LONG).show();
                }
            }
        });

        register_btn = (Button) findViewById(R.id.register_btn);
        register_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Activity_login.this, Activity_register.class);
                startActivity(intent);
            }
        });
    }

    public void login(User user) {
        // 1. log in to app and save session of user
        // 2. move to mainActivity

//        SessionManagement sessionManagement = new SessionManagement(Activity_login.this);
//        sessionManagement.saveSession(user);

        SharedPreferences.Editor editor = sharedpreferences.edit();

        // below two lines will put values for
        // email and password in shared preferences.
        editor.putString(EMAIL_KEY, user.getEmail());
        editor.putString(PASSWORD_KEY, user.getPassword());

        // to save our data with key and value.
        editor.apply();

        movedToMainActivity();
    }

    private void movedToMainActivity() {
        Intent intent = new Intent(Activity_login.this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    @Override
    protected void onStart() {
        super.onStart();

        //check if user is logged in
        //if user is logged in --> moveToMainActivity

//        SessionManagement sessionManagement = new SessionManagement(Activity_login.this);
//        int userId = sessionManagement.getSession();
//        if (userId != -1){
//            movedToMainActivity();
//        }

        if (email != null && password != null) {
            Intent intent = new Intent(Activity_login.this, MainActivity.class);
            startActivity(intent);
        }
    }

}