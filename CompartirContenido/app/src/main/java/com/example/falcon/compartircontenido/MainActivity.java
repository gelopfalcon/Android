package com.example.falcon.compartircontenido;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // listeners of our two buttons
        View.OnClickListener handler = new View.OnClickListener() {
            public void onClick(View v) {
                switch (v.getId()) {

                    case R.id.buttonShareTextUrl:
                        shareTextUrl();
                        break;

                    case R.id.buttonShareImage:
                        shareImage();
                        break;
                }
            }
        };

        // our buttons
        findViewById(R.id.buttonShareTextUrl).setOnClickListener(handler);
        findViewById(R.id.buttonShareImage).setOnClickListener(handler);

    }

    private void shareTextUrl() {
        Intent share = new Intent(android.content.Intent.ACTION_SEND);
        share.setType("text/plain");
        share.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);

        // Add data to the intent, the receiving app will decide
        // what to do with it.
        share.putExtra(Intent.EXTRA_SUBJECT, "Title Of The Post");
        share.putExtra(Intent.EXTRA_TEXT, "codefalcon.blogspot.com");

        startActivity(Intent.createChooser(share, "Share link!"));
    }

    private void shareImage() {
        Intent share = new Intent(Intent.ACTION_SEND);

        // If you want to share a png image only, you can do:
        // setType("image/png"); OR for jpeg: setType("image/jpeg");
        share.setType("image/*");

        // Make sure you put example png image named myimage.png in your
        // directory
       // String imagePath = Environment.getExternalStorageDirectory()
          //      + "/myimage.JPEG";

        Uri imageUri = null;

        imageUri = Uri.parse(MediaStore.Images.Media.insertImage(this.getContentResolver(),
                BitmapFactory.decodeResource(getResources(), R.drawable.my_image), null, null));


        share.putExtra(Intent.EXTRA_STREAM, imageUri);

        startActivity(Intent.createChooser(share, "Share Image!"));
    }
}
