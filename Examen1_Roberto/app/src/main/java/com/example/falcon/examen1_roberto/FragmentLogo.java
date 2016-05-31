package com.example.falcon.examen1_roberto;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;


public class FragmentLogo extends Fragment {

    private FrameLayout ly;

    public FragmentLogo() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View vista = inflater.inflate(R.layout.fragment_logo, container, false);
        ly = (FrameLayout)vista.findViewById(R.id.FragLogo);

        return vista;

    }

}
