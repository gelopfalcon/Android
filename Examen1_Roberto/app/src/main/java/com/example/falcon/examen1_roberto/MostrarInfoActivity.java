package com.example.falcon.examen1_roberto;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MostrarInfoActivity extends AppCompatActivity {

    private TextView info;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar_info);
        info = (TextView)findViewById(R.id.info);
        Bundle b = this.getIntent().getExtras();
        String genero;
        int edad = 2016 - Integer.parseInt(b.getString("nacimiento"));
        if(b.getString("sexo").equalsIgnoreCase("M")){
            genero = "Masculino";
        }else {
            genero = "Femenino";
        }

        info.setText(" Mi nombre es: "+b.getString("nombre")+" "+b.getString("apellido")+
                "\n Soy nacionalidad: "+b.getString("nacionalidad")+
                "\n Mi sexo es: "+genero+
                "\n Mi edad es de: "+edad);
    }
}
