package com.example.falcon.mistecnicos.UI;

import android.support.v4.app.Fragment;
import android.content.ContentUris;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.falcon.mistecnicos.R;
import com.example.falcon.mistecnicos.modelo.TecnicosContract;

/**
 * Created by Falcon on 6/1/16.
 */
public class FragmentoDetalle extends Fragment{

    /**
     * Textos del layout
     */
    private TextView descripcion, categoria, entidad, prioridad, estado;

    /**
     * Identificador de la actividad
     */
    private long id;

    public FragmentoDetalle() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragmento_detalle, container, false);

        // Obtención de views
        descripcion = (TextView) view.findViewById(R.id.descripcion_text);
        categoria = (TextView) view.findViewById(R.id.categoria_text);
        entidad = (TextView) view.findViewById(R.id.tecnico_text);
        prioridad = (TextView) view.findViewById(R.id.prioridad_text);
        estado = (TextView) view.findViewById(R.id.estado_text);

        return view;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onResume() {
        super.onResume();
        id = getActivity().getIntent().getLongExtra(TecnicosContract.Columnas._ID, -1);
        updateView(id);  // Actualzar la vista con los datos de la actividad
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case R.id.action_edit:
                beginUpdate(); // Actualizar
                return true;
            case R.id.action_delete:
                deleteData(); // Eliminar
                getActivity().finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    /**
     * Elimina la actividad actual
     */
    private void deleteData() {
        Uri uri = ContentUris.withAppendedId(TecnicosContract.CONTENT_URI, id);
        getActivity().getContentResolver().delete(
                uri,
                null,
                null
        );
    }

    /**
     * Envía todos los datos de la actividad hacia el formulario
     * de actualización
     */
    private void beginUpdate() {
        getActivity()
                .startActivity(
                        new Intent(getActivity(), UpdateActivity.class)
                                .putExtra(TecnicosContract.Columnas._ID, id)
                                .putExtra(TecnicosContract.Columnas.DESCRIPCION, descripcion.getText())
                                .putExtra(TecnicosContract.Columnas.CATEGORIA, categoria.getText())
                                .putExtra(TecnicosContract.Columnas.TECNICO, entidad.getText())
                                .putExtra(TecnicosContract.Columnas.PRIORIDAD, prioridad.getText())
                                .putExtra(TecnicosContract.Columnas.ESTADO, estado.getText())
                );
    }


    /**
     * Actualiza los textos del layout
     *
     * @param id Identificador de la actividad
     */
    private void updateView(long id) {
        if (id == -1) {
            descripcion.setText("");
            categoria.setText("");
            entidad.setText("");
            prioridad.setText("");
            estado.setText("");

            return;
        }

        Uri uri = ContentUris.withAppendedId(TecnicosContract.CONTENT_URI, id);
        Cursor c = getActivity().getContentResolver().query(
                uri,
                null, null, null, null);

        if (!c.moveToFirst())
            return;

        String descripcion_text = c.getString(c.getColumnIndex(TecnicosContract.Columnas.DESCRIPCION));
        String categoria_text = c.getString(c.getColumnIndex(TecnicosContract.Columnas.CATEGORIA));
        String entidad_text = c.getString(c.getColumnIndex(TecnicosContract.Columnas.TECNICO));
        String prioridad_text = c.getString(c.getColumnIndex(TecnicosContract.Columnas.PRIORIDAD));
        String estado_text = c.getString(c.getColumnIndex(TecnicosContract.Columnas.ESTADO));

        descripcion.setText(descripcion_text);
        categoria.setText(categoria_text);
        entidad.setText(entidad_text);
        prioridad.setText(prioridad_text);
        estado.setText(estado_text);

        c.close(); // Liberar memoria del cursor
    }
}
