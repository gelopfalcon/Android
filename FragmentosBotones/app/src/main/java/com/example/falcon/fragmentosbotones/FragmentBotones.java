package com.example.falcon.fragmentosbotones;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentBotones extends Fragment {

    LinearLayout ly;
    Button bt1;
    Button bt2;
    Button bt3;
    Button bt4;

    public FragmentBotones() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View vista = inflater.inflate(R.layout.fragment_botones, container, false);
        ly = (LinearLayout)vista.findViewById(R.id.contenedor);

        bt1 = (Button)vista.findViewById(R.id.btn1);
        bt2 = (Button)vista.findViewById(R.id.btn2);
        bt3 = (Button)vista.findViewById(R.id.btn3);
        bt4 = (Button)vista.findViewById(R.id.btn4);

        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ly.setBackgroundColor(Color.parseColor("#0000FF"));
            }
        });

        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ly.setBackgroundColor(Color.parseColor("#FF022C"));
            }
        });

        bt3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ly.setBackgroundColor(Color.parseColor("#53FF02"));
            }
        });

        bt4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getActivity(), Segundo.class);
                startActivity(intent);
            }
        });


        return vista;
        //return inflater.inflate(R.layout.fragment_botones, container, false);

    }

    @Override
    public void onActivityCreated(Bundle state) {
        super.onActivityCreated(state);



    }

}
