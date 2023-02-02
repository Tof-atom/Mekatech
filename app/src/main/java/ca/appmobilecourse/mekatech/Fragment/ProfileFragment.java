package ca.appmobilecourse.mekatech.Fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import ca.appmobilecourse.mekatech.Activity_login;
import ca.appmobilecourse.mekatech.DataBaseHelper;
import ca.appmobilecourse.mekatech.FindHelpActivity;
import ca.appmobilecourse.mekatech.MainActivity;
import ca.appmobilecourse.mekatech.Models.User;
import ca.appmobilecourse.mekatech.R;
import ca.appmobilecourse.mekatech.Session.SessionManagement;
import ca.appmobilecourse.mekatech.UpdateProfileActivity;


public class ProfileFragment extends Fragment {

    private TextView firstName_textView;
    private TextView lastName_textView;
    private TextView email_textView;
    private Button updateAccount_btn, logout_btn;

    private User user;
    private DataBaseHelper db;

    // creating constant keys for shared preferences.
    public static final String SHARED_PREFS = "shared_prefs";

    // key for storing email.
    public static final String EMAIL_KEY = "email_key";

    // key for storing password.
    public static final String PASSWORD_KEY = "password_key";

    // variable for shared preferences.
    SharedPreferences sharedpreferences;
    String email, password;

    public ProfileFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // initializing our shared preferences.
        sharedpreferences = getActivity().getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);

        // getting data from shared prefs and
        // storing it in our string variable.
        email = sharedpreferences.getString(EMAIL_KEY, null);
        db = new DataBaseHelper(getActivity());
        user = db.searchUser(email);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_profile, container, false);

//        TODO: fix bug after update profile
        firstName_textView = (TextView) view.findViewById(R.id.firstName_textView);
        lastName_textView = (TextView) view.findViewById(R.id.lastName_textView);
        email_textView = (TextView) view.findViewById(R.id.email_textView);

        if (user != null) {
            firstName_textView.setText(user.getFirstName());
            lastName_textView.setText(user.getLastName());
            email_textView.setText(user.getEmail());
        }

        updateAccount_btn = (Button) view.findViewById(R.id.updateAccount_btn);
        updateAccount_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), UpdateProfileActivity.class);
                startActivity(intent);
            }
        });

        logout_btn = (Button) view.findViewById(R.id.logout_btn);
        logout_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                SessionManagement sessionManagement = new SessionManagement(getContext());
//                sessionManagement.removeSession();

                // calling method to edit values in shared prefs.
                SharedPreferences.Editor editor = sharedpreferences.edit();

                // below line will clear
                // the data in shared prefs.
                editor.clear();

                // below line will apply empty
                // data to shared prefs.
                editor.apply();

                Intent intent = new Intent(getContext(), Activity_login.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });

        return view;
    }
}