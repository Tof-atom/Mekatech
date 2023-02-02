package ca.appmobilecourse.mekatech;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import ca.appmobilecourse.mekatech.Models.User;

public class Activity_register extends AppCompatActivity {

    private EditText firstName_editText;
    private EditText lastName_editText;
    private EditText email_editText;
    private EditText password_editText;
    private EditText passwordConf_editText;
    private RadioGroup genderGroup_rGr;
    private RadioButton female_rBtn;
    private RadioButton male_rBtn;
    private RadioButton gender_rBtn;

    private Button login_btn;
    private Button register_btn;

    DataBaseHelper dataBaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        firstName_editText = (EditText) findViewById(R.id.firstName_editText);
        lastName_editText = (EditText) findViewById(R.id.lastName_editText);
        email_editText = (EditText) findViewById(R.id.email_editText);
        password_editText = (EditText) findViewById(R.id.password_editText);
        passwordConf_editText = (EditText) findViewById(R.id.passwordConf_editText);
        genderGroup_rGr = (RadioGroup) findViewById(R.id.genderGroup);

        dataBaseHelper = new DataBaseHelper(Activity_register.this);

        register_btn = (Button) findViewById(R.id.register_btn);
        register_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO: Email validation, password encrypt and double check, create a session
                // Receive checked radio button in gender group
                int radioBtnIndex = genderGroup_rGr.getCheckedRadioButtonId();
                if (radioBtnIndex == -1){
                    Toast.makeText(Activity_register.this, "Please select gender", Toast.LENGTH_LONG).show();
                    return;
                }
//                    View radioButton = genderGroup_rGr.findViewById(radioBtnIndex);
//                    int idx = genderGroup_rGr.indexOfChild(radioButton);

                gender_rBtn = (RadioButton) findViewById(radioBtnIndex);
                String gender = gender_rBtn.getText().toString();

                User user;

                try {
                    user = new User(0, firstName_editText.getText().toString(), lastName_editText.getText().toString(), email_editText.getText().toString(), password_editText.getText().toString(), gender);
                } catch (Exception e){
                    Toast.makeText(Activity_register.this, "Error occurred", Toast.LENGTH_LONG).show();
                    user = new User(-1, "error");
                }

                boolean success = dataBaseHelper.addUser(user);
                Toast.makeText(Activity_register.this, "You are registered", Toast.LENGTH_LONG).show();

                Intent intent = new Intent(Activity_register.this, Activity_login.class);
                startActivity(intent);
            }
        });

        login_btn = (Button) findViewById(R.id.login_btn);
        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Activity_register.this, Activity_login.class);
                startActivity(intent);
            }
        });
    }
}