package ca.appmobilecourse.mekatech.Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import ca.appmobilecourse.mekatech.FindHelpActivity;
import ca.appmobilecourse.mekatech.R;
import ca.appmobilecourse.mekatech.UpdateProfileActivity;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProfileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProfileFragment extends Fragment {

    private Button updateAccount_btn;

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
        return view;
    }
}