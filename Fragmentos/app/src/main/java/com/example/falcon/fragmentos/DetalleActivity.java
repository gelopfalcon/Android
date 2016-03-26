package com.example.falcon.fragmentos;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class DetalleActivity extends AppCompatActivity {

    public static final String EXTRA_TEXTO =
            "package com.example.falcon.fragmentos.EXTRA_TEXTO";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle);

        FragmentDetalle detalle = (FragmentDetalle)getSupportFragmentManager()
                        .findFragmentById(R.id.FrgDetalle);

        detalle.mostrarDetalle(getIntent().getStringExtra(EXTRA_TEXTO));
    }
}
