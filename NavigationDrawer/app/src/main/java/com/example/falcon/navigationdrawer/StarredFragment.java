package com.example.falcon.navigationdrawer;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class StarredFragment extends Fragment {


    TextView textViewInboxFragment;
    Button buttonChangeText;

    public StarredFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_inbox, container, false);
        ((MainActivity) getActivity()).getSupportActionBar().setTitle("Fragment Starred");

        buttonChangeText = (Button) view.findViewById(R.id.buttonFragmentInbox);

        textViewInboxFragment = (TextView) view.findViewById(R.id.textViewInboxFragment);

        buttonChangeText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                textViewInboxFragment.setText("This is the Starred Fragment");
                textViewInboxFragment.setTextColor(getResources().getColor(R.color.material_blue_grey_900));

            }
        });
        return view;
    }

}
