package com.example.falcon.mistecnicos;

import android.content.Context;
import android.database.Cursor;
import android.support.v4.widget.CursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.falcon.mistecnicos.modelo.TecnicosContract;


public class AdaptadorActividades extends CursorAdapter {

    public AdaptadorActividades(Context context) {
        super(context, null, 0);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        return inflater.inflate(R.layout.item_layout, parent, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView categoria = (TextView) view.findViewById(R.id.categoria_text);
        categoria.setText(cursor.getString(
                cursor.getColumnIndex(TecnicosContract.Columnas.CATEGORIA)));

        TextView prioridad = (TextView) view.findViewById(R.id.prioridad_text);
        prioridad.setText(cursor.getString(
                cursor.getColumnIndex(TecnicosContract.Columnas.PRIORIDAD)));

        TextView tecnico = (TextView) view.findViewById(R.id.tecnico_text);
        tecnico.setText(cursor.getString(
                cursor.getColumnIndex(TecnicosContract.Columnas.TECNICO)));

        String estado = cursor.getString(
                cursor.getColumnIndex(TecnicosContract.Columnas.ESTADO));

        View indicator = view.findViewById(R.id.indicator);

        switch (estado) {
            case "Cerrada":
                indicator.setBackgroundResource(R.drawable.indicador_verde);
                break;
            case "En Curso":
                indicator.setBackgroundResource(R.drawable.indicador_rojo);
                break;
            case "Abierta":
                indicator.setBackgroundResource(R.drawable.indicador_anaranjado);
                break;
        }
    }
}
