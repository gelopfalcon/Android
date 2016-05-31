package com.example.falcon.mistecnicos.modelo;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Falcon on 5/25/16.
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    public DatabaseHelper(Context context,
                          String name,
                          SQLiteDatabase.CursorFactory factory,
                          int version) {
        super(context, name, factory, version);
    }

    public void onCreate(SQLiteDatabase database) {
        createTable(database); // Crear la tabla "actividad"
        loadDummyData(database); // Cargar 5 datos de prueba
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Actualizaciones
    }

    /**
     * Crear tabla en la base de datos
     *
     * @param database Instancia de la base de datos
     */
    private void createTable(SQLiteDatabase database) {
        String cmd = "CREATE TABLE " + TecnicosContract.ACTIVIDAD + " (" +
                TecnicosContract.Columnas._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                TecnicosContract.Columnas.CATEGORIA + " TEXT, " +
                TecnicosContract.Columnas.PRIORIDAD + " TEXT, " +
                TecnicosContract.Columnas.ESTADO + " TEXT, " +
                TecnicosContract.Columnas.TECNICO + " TEXT, " +
                TecnicosContract.Columnas.DESCRIPCION + " TEXT);";
        database.execSQL(cmd);
    }

    /**
     * Carga datos de ejemplo en la tabla
     * @param database Instancia de la base de datos
     */
    private void loadDummyData(SQLiteDatabase database) {
        ContentValues values = new ContentValues();
        values.put(TecnicosContract.Columnas.CATEGORIA, "Factibilidad");
        values.put(TecnicosContract.Columnas.PRIORIDAD, "Media");
        values.put(TecnicosContract.Columnas.ESTADO, "Abierta");
        values.put(TecnicosContract.Columnas.TECNICO, "Juan Pedrozo");
        values.put(TecnicosContract.Columnas.DESCRIPCION, "LLevar router MX230");
        database.insert(TecnicosContract.ACTIVIDAD, null, values);

        values = new ContentValues();
        values.put(TecnicosContract.Columnas.CATEGORIA, "Reparación");
        values.put(TecnicosContract.Columnas.PRIORIDAD, "Alta");
        values.put(TecnicosContract.Columnas.ESTADO, "En Curso");
        values.put(TecnicosContract.Columnas.TECNICO, "Mirta Gomez");
        values.put(TecnicosContract.Columnas.DESCRIPCION, "Internet intermitente");
        database.insert(TecnicosContract.ACTIVIDAD, null, values);

        values = new ContentValues();
        values.put(TecnicosContract.Columnas.CATEGORIA, "Traslado");
        values.put(TecnicosContract.Columnas.PRIORIDAD, "Baja");
        values.put(TecnicosContract.Columnas.ESTADO, "Cerrada");
        values.put(TecnicosContract.Columnas.TECNICO, "Carlos Gutierrez");
        values.put(TecnicosContract.Columnas.DESCRIPCION, "Nueva dirección: Cra 4 #2C-90");
        database.insert(TecnicosContract.ACTIVIDAD, null, values);

        values = new ContentValues();
        values.put(TecnicosContract.Columnas.CATEGORIA, "Migración");
        values.put(TecnicosContract.Columnas.PRIORIDAD, "Baja");
        values.put(TecnicosContract.Columnas.ESTADO, "Abierta");
        values.put(TecnicosContract.Columnas.TECNICO, "Gloria Quiñonez");
        values.put(TecnicosContract.Columnas.DESCRIPCION, "Sustitución cable soporte ipV6");
        database.insert(TecnicosContract.ACTIVIDAD, null, values);

        values = new ContentValues();
        values.put(TecnicosContract.Columnas.CATEGORIA, "Mantenimiento");
        values.put(TecnicosContract.Columnas.PRIORIDAD, "Media");
        values.put(TecnicosContract.Columnas.ESTADO, "En Curso");
        values.put(TecnicosContract.Columnas.TECNICO, "Julian Arreondo");
        values.put(TecnicosContract.Columnas.DESCRIPCION, "LLevar Lista de checkeo rendimiento");
        database.insert(TecnicosContract.ACTIVIDAD, null, values);
    }
}
