package com.example.falcon.api;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


public class MainActivity extends AppCompatActivity {

    private Button btnListar;
    public static final MediaType JSON
            = MediaType.parse("application/json; charset=utf-8");


    OkHttpClient client = new OkHttpClient();
    private EditText txtId, txtNombre, txtApellido, txtTelefono, txtEmail, txtUserName,txtPassword;

    private ListView lstClientes;
    private Button btnObtener, btnInsertar, btnEliminar;

    private TextView lblResultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // definición de labels
        lblResultado = (TextView)findViewById(R.id.lblResultado);

        // definicion de textviews
        txtId = (EditText)findViewById(R.id.txtId);
        txtNombre = (EditText)findViewById(R.id.txtNombre);
        txtApellido = (EditText)findViewById(R.id.txtApellido);
        txtEmail = (EditText)findViewById(R.id.email);
        txtTelefono = (EditText)findViewById(R.id.txtTelefono);
        txtPassword = (EditText)findViewById(R.id.txtPassword);
        txtUserName = (EditText)findViewById(R.id.txtUsername);

        // definición de botones
        btnListar = (Button)findViewById(R.id.btnListar);
        btnInsertar = (Button)findViewById(R.id.btnInsertar);
        lstClientes = (ListView)findViewById(R.id.lstClientes);
        btnObtener = (Button)findViewById(R.id.btnObtener);
        btnEliminar = (Button)findViewById(R.id.btnEliminar);

        btnListar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                lblResultado.setText("");
                TareaWSListar tarea = new TareaWSListar();
                tarea.execute();
            }
        });

        btnObtener.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                TareaWSObtener tarea = new TareaWSObtener();
                tarea.execute(txtId.getText().toString());
            }
        });

        btnInsertar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                TareaWSInsertar tarea = new TareaWSInsertar();
                tarea.execute(txtNombre.getText().toString(), txtPassword.getText().toString(),
                        txtApellido.getText().toString(),txtTelefono.getText().toString(),
                        txtEmail.getText().toString(),txtUserName.getText().toString());

            }
        });

        btnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                TareaWSEliminar tarea = new TareaWSEliminar();
                tarea.execute(txtId.getText().toString());
            }
        });
    }



    //Tarea Asíncrona para llamar al WS de listado en segundo plano
    private class TareaWSListar extends AsyncTask<String,Integer,Boolean> {

        private String[] clientes;

        protected Boolean doInBackground(String... params) {
            Boolean resultado;
            try {
                 run("http://ag925cr.com/api/get.php");
                //run("http://www.w3schools.com/angular/customers.php");

                resultado=true;
            } catch (IOException e) {
                resultado=false;
                e.printStackTrace();
            } catch (JSONException e) {
                resultado=false;
                e.printStackTrace();
            }

            return resultado;
        }

        void run(String url) throws IOException, JSONException {
            Request request = new Request.Builder()
                    .url(url)
                    .build();

            Response response = client.newCall(request).execute();
            String jsonData = response.body().string();

            JSONArray respJSON =   new JSONArray(jsonData);

            clientes = new String[respJSON.length()];

            for (int i = 0; i < respJSON.length(); i++) {
                JSONObject object     = respJSON.getJSONObject(i);

                String fname = object.getString("user_name");
                String lname = object.getString("lname");
                String phone = object.getString("user_phone");

                clientes[i] = "" + fname + "-" + lname + " Teléfono: " + phone;
            }
        }

        protected void onPostExecute(Boolean result) {

            if (result)
            {

                ArrayAdapter<String> adaptador =
                        new ArrayAdapter<String>(MainActivity.this,
                                android.R.layout.simple_list_item_1, clientes);

                lstClientes.setAdapter(adaptador);
            }
        }
    }

    //Tarea Asíncrona para llamar al WS de obtener en segundo plano
    private class TareaWSObtener extends AsyncTask<String,Integer,Boolean> {

        private String[] clientes;



        protected Boolean doInBackground(String... params) {
            Boolean resultado;
            try {
               // acá capturamos el ID que deseamos buscar
                String id = params[0];

                  post("http://ag925cr.com/api/obtener.php", id);

                resultado=true;
            } catch (IOException e) {
                resultado=false;
                e.printStackTrace();
            } catch (JSONException e) {
                resultado=false;
                e.printStackTrace();
            }

            return resultado;
        }

        void post(String url,String id) throws IOException, JSONException {

            RequestBody formBody = new FormBody.Builder()
                    .add("id", id)
                    .build();
            Request request = new Request.Builder()
                    .url(url)
                    .post(formBody)
                    .build();
            Response response = client.newCall(request).execute();
            String jsonData = response.body().string();

            JSONArray respJSON =   new JSONArray(jsonData);

            clientes = new String[respJSON.length()];

            for (int i = 0; i < respJSON.length(); i++) {
                JSONObject object     = respJSON.getJSONObject(i);

                String fname = object.getString("user_name");
                String lname = object.getString("lname");
                String phone = object.getString("user_phone");

                clientes[i] = "" + fname + "-" + lname + " Teléfono: " + phone;
            }
        }

        protected void onPostExecute(Boolean result) {

            if (result)
            {

                ArrayAdapter<String> adaptador =
                        new ArrayAdapter<String>(MainActivity.this,
                                android.R.layout.simple_list_item_1, clientes);

                lstClientes.setAdapter(adaptador);
            }
        }
    }

    //Tarea Asíncrona para llamar al WS de insertar en segundo plano
    private class TareaWSInsertar extends AsyncTask<String,Integer,Boolean> {

        protected Boolean doInBackground(String... params) {
            Boolean resultado;
            try {
                // acá capturamos el ID que deseamos buscar
                String user_name = params[0];
                String usr_pwd = params[1];
                String user_lastname = params[2];
                String user_phone = params[3];
                String user_mail = params[4];
                String user_user_name = params[5];

                insertar("http://ag925cr.com/api/insertar.php", user_name, usr_pwd, user_lastname,
                        user_phone, user_mail, user_user_name);

                resultado=true;
            } catch (IOException e) {
                resultado=false;
                e.printStackTrace();
            } catch (JSONException e) {
                resultado=false;
                e.printStackTrace();
            }

            return resultado;
        }

        void insertar(String url,String name,String pass,String lastname, String phone
        ,String mail,String user_name) throws IOException, JSONException {

            RequestBody formBody = new FormBody.Builder()
                    .add("user_name", name)
                    .add("usr_pwd", pass)
                    .add("user_lastname", lastname)
                    .add("user_phone", phone)
                    .add("user_mail", mail)
                    .add("user_user_name", user_name)
                    .build();
            Request request = new Request.Builder()
                    .url(url)
                    .post(formBody)
                    .build();
            Response response = client.newCall(request).execute();
            String jsonData = response.body().string();

        }

        protected void onPostExecute(Boolean result) {

            if (result)
            {
                txtApellido.setText("");
                txtEmail.setText("");
                txtPassword.setText("");
                txtTelefono.setText("");
                txtNombre.setText("");
                txtUserName.setText("");
                lstClientes.setAdapter(null);
                lblResultado.setText("Insertado Exitosamente");

            }
        }
    }


    //Tarea Asíncrona para llamar al WS de eliminar en segundo plano
    private class TareaWSEliminar extends AsyncTask<String,Integer,Boolean> {


        protected Boolean doInBackground(String... params) {
            Boolean resultado;
            try {
                // acá capturamos el ID que deseamos buscar
                String user_id = params[0];

                eliminar("http://ag925cr.com/api/eliminar.php", user_id);
                resultado=true;
            } catch (IOException e) {
                resultado=false;
                e.printStackTrace();
            } catch (JSONException e) {
                resultado=false;
                e.printStackTrace();
            }



            return resultado;
        }

        void eliminar(String url,String id) throws IOException, JSONException {

            RequestBody formBody = new FormBody.Builder()
                    .add("id", id)
                    .build();
            Request request = new Request.Builder()
                    .url(url)
                    .post(formBody)
                    .build();
            Response response = client.newCall(request).execute();
            String jsonData = response.body().string();

        }

        protected void onPostExecute(Boolean result) {

            if (result)
            {
                txtId.setText("");
                lstClientes.setAdapter(null);
                lblResultado.setText("Eliminado Exitosamente");

            }
        }
    }



}
