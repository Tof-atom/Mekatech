package ca.appmobilecourse.mekatech;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        TODO: impliment logic for fragments activity
        Intent intent = new Intent(MainActivity.this, Activity_login.class);
        startActivity(intent);
    }
}