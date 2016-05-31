package com.example.falcon.mistecnicos.modelo;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import java.sql.SQLException;

/**
 * Created by Falcon on 5/25/16.
 */
public class TecnicosProvider extends ContentProvider {
    /**
     * Nombre de la base de datos
     */
    private static final String DATABASE_NAME = "tecnicos.db";
    /**
     * Versión actual de la base de datos
     */
    private static final int DATABASE_VERSION = 1;
    /**
     * Instancia del administrado de BD
     */
    private DatabaseHelper databaseHelper;

    @Override
    public boolean onCreate() {
        // Inicializando gestor BD
        databaseHelper = new DatabaseHelper(
                getContext(),
                DATABASE_NAME,
                null,
                DATABASE_VERSION
        );

        return true;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        // Obtener base de datos
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        // Comparar Uri
        int match = TecnicosContract.uriMatcher.match(uri);

        Cursor c;

        switch (match) {
            case TecnicosContract.ALLROWS:
                // Consultando todos los registros
                c = db.query(TecnicosContract.ACTIVIDAD, projection,
                        selection, selectionArgs,
                        null, null, sortOrder);
                c.setNotificationUri(
                        getContext().getContentResolver(),
                        TecnicosContract.CONTENT_URI);
                break;
            case TecnicosContract.SINGLE_ROW:
                // Consultando un solo registro basado en el Id del Uri
                long idActividad = ContentUris.parseId(uri);
                c = db.query(TecnicosContract.ACTIVIDAD, projection,
                        TecnicosContract.Columnas._ID + " = " + idActividad,
                        selectionArgs, null, null, sortOrder);
                c.setNotificationUri(
                        getContext().getContentResolver(),
                        TecnicosContract.CONTENT_URI);
                break;
            default:
                throw new IllegalArgumentException("URI no soportada: " + uri);
        }
        return c;
    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        switch (TecnicosContract.uriMatcher.match(uri)) {

            case TecnicosContract.ALLROWS:

                return TecnicosContract.MULTIPLE_MIME;

            case TecnicosContract.SINGLE_ROW:

                return TecnicosContract.SINGLE_MIME;

            default:
                throw new IllegalArgumentException("Tipo de actividad desconocida: " + uri);
        }
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues values) {
        //validar la uri
        Uri uri_actividad=null;
        if(TecnicosContract.uriMatcher.match(uri)!=TecnicosContract.ALLROWS){
            throw new IllegalArgumentException("Uri desconocida: "+uri);
        }

        ContentValues contentValues;
        if(values!=null){
            contentValues = new ContentValues(values);
        }else{
            contentValues = new ContentValues();
        }

        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        long rowId = db.insert(TecnicosContract.ACTIVIDAD,null, contentValues);
        if(rowId > 0){
             uri_actividad = ContentUris.withAppendedId(TecnicosContract.CONTENT_URI,rowId);
            getContext().getContentResolver().notifyChange(uri_actividad,null);

        }
        return uri_actividad;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {

        int marcador = TecnicosContract.uriMatcher.match(uri);
        int afectado;
        SQLiteDatabase conexion = databaseHelper.getWritableDatabase();

        switch (marcador) {
            case TecnicosContract.ALLROWS:
                afectado = conexion.delete(TecnicosContract.ACTIVIDAD,
                        selection,
                        selectionArgs);
                break;

            case TecnicosContract.SINGLE_ROW:

                long idActividad = ContentUris.parseId(uri);
                afectado = conexion.delete(TecnicosContract.ACTIVIDAD,
                        TecnicosContract.Columnas._ID + "=" + idActividad
                                + (!TextUtils.isEmpty(selection) ?
                                " AND (" + selection + ')' : ""),
                        selectionArgs);

                getContext().getContentResolver().notifyChange(uri, null);
                break;
            default:
                throw new IllegalArgumentException("Elemento desconocido: " +
                        uri);
        }
        return afectado;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        SQLiteDatabase db= databaseHelper.getWritableDatabase();
        int afectado;
        switch (TecnicosContract.uriMatcher.match(uri)) {

            case TecnicosContract.ALLROWS:

                afectado = db.update(TecnicosContract.ACTIVIDAD, values,selection,selectionArgs);
                break;

            case TecnicosContract.SINGLE_ROW:

                String idActividad= uri.getPathSegments().get(1);
                afectado= db.update(TecnicosContract.ACTIVIDAD, values,
                        TecnicosContract.Columnas._ID + "=" + idActividad +
                                (!TextUtils.isEmpty(selection) ?
                                " AND (" + selection + ')' : "")
                        ,selectionArgs);
                    break;

            default:
                throw new IllegalArgumentException("Tipo de actividad desconocida: " + uri);

        }

        getContext().getContentResolver().notifyChange(uri,null);
        return afectado;
    }
}
