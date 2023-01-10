package ca.appmobilecourse.mekatech.Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import ca.appmobilecourse.mekatech.AddUpdateCarActivity;
import ca.appmobilecourse.mekatech.FindHelpActivity;
import ca.appmobilecourse.mekatech.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link GarageFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class GarageFragment extends Fragment {

    private Button addUpdateCar_btn;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public GarageFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment GarageFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static GarageFragment newInstance(String param1, String param2) {
        GarageFragment fragment = new GarageFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_garage, container, false);
        addUpdateCar_btn = (Button) view.findViewById(R.id.addUpdateCar_btn);
        addUpdateCar_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), AddUpdateCarActivity.class);
//                ((MainActivity) getActivity()).startActivity(intent);
                startActivity(intent);
            }
        });
        return view;
    }
}