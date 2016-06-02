package com.example.falcon.mistecnicos.UI;

import android.content.ContentValues;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.falcon.mistecnicos.R;
import com.example.falcon.mistecnicos.modelo.TecnicosContract;

/**
 * Created by Falcon on 6/1/16.
 */
public class InsertarFragmento extends Fragment {

    /**
     * Views del formulario
     */
    private EditText descripcion;
    private Spinner prioridad;
    private Spinner entidad;
    private Spinner estado;
    private Spinner categoria;


    public InsertarFragmento() {
        // Required empty public constructor

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.insertar_fragmento, container, false);

        // Obtener views
        descripcion = (EditText) view.findViewById(R.id.descripcion_input);
        prioridad = (Spinner) view.findViewById(R.id.prioridad_spinner);
        entidad = (Spinner) view.findViewById(R.id.tecnico_spinner);
        estado = (Spinner) view.findViewById(R.id.estado_spinner);
        categoria = (Spinner) view.findViewById(R.id.categoria_spinner);

        return view;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case android.R.id.home:
                saveData(); // Guardar datos
                getActivity().finish();
                return true;
            case R.id.action_discard:
                getActivity().finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);

        }


    }

    private void saveData() {
        // Obtención de valores actuales
        ContentValues values = new ContentValues();
        values.put(TecnicosContract.Columnas.ESTADO, estado.getSelectedItem().toString());
        values.put(TecnicosContract.Columnas.PRIORIDAD, prioridad.getSelectedItem().toString());
        values.put(TecnicosContract.Columnas.CATEGORIA, categoria.getSelectedItem().toString());
        values.put(TecnicosContract.Columnas.TECNICO, entidad.getSelectedItem().toString());
        values.put(TecnicosContract.Columnas.DESCRIPCION, descripcion.getText().toString());

        getActivity().getContentResolver().insert(
                TecnicosContract.CONTENT_URI,
                values
        );
    }
}
