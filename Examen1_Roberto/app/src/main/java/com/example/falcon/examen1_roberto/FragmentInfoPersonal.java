package com.example.falcon.examen1_roberto;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

public class FragmentInfoPersonal extends Fragment {

    private LinearLayout ly;
    private Button btn_limpiar;
    private Button btn_enviar;
    private EditText nombre, apellido, genero, nacionalidad, anno_nacimientos;

    public FragmentInfoPersonal() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View vista = inflater.inflate(R.layout.fragment_info_personal, container, false);
        ly = (LinearLayout)vista.findViewById(R.id.FragInfo);

        btn_enviar = (Button)vista.findViewById(R.id.Enviar);
        btn_limpiar = (Button)vista.findViewById(R.id.Limpiar);

        nombre = (EditText)vista.findViewById(R.id.Nombre);
        apellido = (EditText)vista.findViewById(R.id.Apellido);
        genero = (EditText)vista.findViewById(R.id.Sexo);
        nacionalidad = (EditText)vista.findViewById(R.id.Nacionalidad);
        anno_nacimientos = (EditText)vista.findViewById(R.id.AnnoNacimiento);

        btn_enviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MostrarInfoActivity.class);
                Bundle b = new Bundle();
                b.putString("nombre",nombre.getText().toString());
                b.putString("apellido",apellido.getText().toString());
                b.putString("sexo",genero.getText().toString());
                b.putString("nacionalidad",nacionalidad.getText().toString());
                b.putString("nacimiento", anno_nacimientos.getText().toString());

                intent.putExtras(b);
                startActivity(intent);
            }
        });

        btn_limpiar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nombre.setText("");
                apellido.setText("");
                genero.setText("");
                nacionalidad.setText("");
                anno_nacimientos.setText("");
            }
        });
        return vista;
    }
}
