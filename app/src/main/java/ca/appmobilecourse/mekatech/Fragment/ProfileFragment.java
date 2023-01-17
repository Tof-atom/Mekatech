package ca.appmobilecourse.mekatech.Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import ca.appmobilecourse.mekatech.Activity_login;
import ca.appmobilecourse.mekatech.FindHelpActivity;
import ca.appmobilecourse.mekatech.MainActivity;
import ca.appmobilecourse.mekatech.R;
import ca.appmobilecourse.mekatech.Session.SessionManagement;
import ca.appmobilecourse.mekatech.UpdateProfileActivity;


public class ProfileFragment extends Fragment {

    private Button updateAccount_btn, logout_btn;

    public ProfileFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        updateAccount_btn = (Button) view.findViewById(R.id.updateAccount_btn);
        updateAccount_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), UpdateProfileActivity.class);
//                ((MainActivity) getActivity()).startActivity(intent);
                startActivity(intent);
            }
        });

        logout_btn = (Button) view.findViewById(R.id.logout_btn);
        logout_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SessionManagement sessionManagement = new SessionManagement(getContext());
                sessionManagement.removeSession();

                Intent intent = new Intent(getContext(), Activity_login.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });

        return view;
    }
}