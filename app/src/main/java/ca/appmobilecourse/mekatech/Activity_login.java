package ca.appmobilecourse.mekatech;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.IntentSender;
import android.database.sqlite.SQLiteDatabase;
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

    DataBaseHelper db = new DataBaseHelper(Activity_login.this);
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // TODO: Email validation, password encrypt and double check

        login_btn = (Button) findViewById(R.id.login_btn);
        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                email_editText = (EditText) findViewById(R.id.email_editText);
                String email = email_editText.getText().toString();
                user = db.searchOne(email);

                Toast.makeText(Activity_login.this, user.toString(), Toast.LENGTH_LONG).show();
                //TODO: display message for non-existing user
                if (user.get_id() != -1) login(user);
            }
        });

        register_btn = (Button) findViewById(R.id.register_btn);
        register_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Activity_login.this, activity_register.class);
                startActivity(intent);
            }
        });
    }

    public void login(User user){
        // 1. log in to app and save session of user
        // 2. move to mainActivity

        SessionManagement sessionManagement = new SessionManagement(Activity_login.this);
        sessionManagement.saveSession(user);

        movedToMainActivity();
    }

    private void movedToMainActivity() {
        Intent intent = new Intent(Activity_login.this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    @Override
    protected void onStart(){
        super.onStart();

        //check if user is logged in
        //if user is logged in --> moveToMainActivity

        SessionManagement sessionManagement = new SessionManagement(Activity_login.this);
        int userId = sessionManagement.getSession();
        if (userId != -1){
            movedToMainActivity();
        }
    }

}