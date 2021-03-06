package com.example.falcon.mistecnicos.UI;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;

import com.example.falcon.mistecnicos.R;

/**
 * Created by Falcon on 6/1/16.
 */
public class InsertarActividad extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (getSupportActionBar() != null)
            getSupportActionBar().setHomeAsUpIndicator(R.mipmap.ic_done);

        if (savedInstanceState == null) {
            InsertarFragmento fragment = new InsertarFragmento();
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(android.R.id.content, fragment, fragment.getClass().getSimpleName())
                    .commit();

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.insertar, menu);
        return true;
    }
}
