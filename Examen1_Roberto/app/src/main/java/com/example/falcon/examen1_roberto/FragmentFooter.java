package com.example.falcon.examen1_roberto;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

public class FragmentFooter extends Fragment {

    private LinearLayout ly;

    public FragmentFooter() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View vista = inflater.inflate(R.layout.fragment_footer, container, false);
        ly = (LinearLayout)vista.findViewById(R.id.FragFooter);

        return vista;
    }
}
