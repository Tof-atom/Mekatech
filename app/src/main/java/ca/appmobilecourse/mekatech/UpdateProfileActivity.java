package ca.appmobilecourse.mekatech;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import ca.appmobilecourse.mekatech.Fragment.ProfileFragment;
import ca.appmobilecourse.mekatech.Models.User;

public class UpdateProfileActivity extends AppCompatActivity {

    private EditText firstName_editText;
    private EditText lastName_editText;
    private EditText email_editText;
    private EditText password_editText;
    private EditText passwordConf_editText;
    private RadioGroup genderGroup_rGr;
    private RadioButton female_rBtn;
    private RadioButton male_rBtn;
    private RadioButton gender_rBtn;

    private Button submitUpdateAccount_btn;
    private DataBaseHelper db;
    private User user;

    // creating constant keys for shared preferences.
    public static final String SHARED_PREFS = "shared_prefs";

    // key for storing email.
    public static final String EMAIL_KEY = "email_key";

    // variable for shared preferences.
    SharedPreferences sharedpreferences;
    String email, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_profile);

        firstName_editText = (EditText) findViewById(R.id.firstName_editText);
        lastName_editText = (EditText) findViewById(R.id.lastName_editText);
        email_editText = (EditText) findViewById(R.id.email_editText);
        password_editText = (EditText) findViewById(R.id.password_editText);
        passwordConf_editText = (EditText) findViewById(R.id.passwordConf_editText);
        genderGroup_rGr = (RadioGroup) findViewById(R.id.genderGroup);

        sharedpreferences = getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
        email = sharedpreferences.getString(EMAIL_KEY, null);
        db = new DataBaseHelper(UpdateProfileActivity.this);
        user = db.searchUser(email);

        firstName_editText.setText(user.getFirstName());
        lastName_editText.setText(user.getLastName());
        email_editText.setText(user.getEmail());
        password_editText.setText(user.getPassword());
        passwordConf_editText.setText("");


        submitUpdateAccount_btn = (Button) findViewById(R.id.submitUpdateAccount_btn);
        submitUpdateAccount_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int radioBtnIndex = genderGroup_rGr.getCheckedRadioButtonId();
                if (radioBtnIndex == -1){
                    Toast.makeText(UpdateProfileActivity.this, "Please select gender", Toast.LENGTH_LONG).show();
                    return;
                }
                gender_rBtn = (RadioButton) findViewById(radioBtnIndex);
                String gender = gender_rBtn.getText().toString();

                try {
                    user = new User(0, firstName_editText.getText().toString(), lastName_editText.getText().toString(), email_editText.getText().toString(), password_editText.getText().toString(), gender);
                    boolean success = db.updateUser(user);
                    if (success) {
                        Toast.makeText(UpdateProfileActivity.this, "Profile is updated", Toast.LENGTH_LONG).show();
                    }
                } catch (Exception e){
                    Toast.makeText(UpdateProfileActivity.this, "Error occurred", Toast.LENGTH_LONG).show();
                    return;
                }
            }
        });

    }
}