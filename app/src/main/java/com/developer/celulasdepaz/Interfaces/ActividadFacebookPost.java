package com.developer.celulasdepaz.Interfaces;


import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.developer.celulasdepaz.R;
import com.developer.celulasdepaz.modelo.Contrato;
import com.facebook.CallbackManager;
import com.facebook.FacebookSdk;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.widget.ShareDialog;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Created by Irving on 13/11/2016.
 */

public class ActividadFacebookPost extends AppCompatActivity {

    private Button btnCamara;
    private EditText titulo;
    private EditText descripcion;
    private Button publicar;
    private CallbackManager callbackManager;
    private ImageView subir_foto;
    private ShareDialog shareDialog;
    private String path;


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        callbackManager = CallbackManager.Factory.create();
        shareDialog = new ShareDialog(this);


        setContentView(R.layout.actividad_postear_facebook);
        btnCamara = (Button) findViewById(R.id.iv_img_postear);
        titulo = (EditText) findViewById(R.id.TituloAcrividad);
        descripcion = (EditText) findViewById(R.id.editDescripcion);
        publicar = (Button) findViewById(R.id.btnPublicar);
        subir_foto = (ImageView) findViewById(R.id.BtnCamara);

        btnCamara.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent galeria = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.INTERNAL_CONTENT_URI);
                startActivityForResult(galeria, 0);

            }
        });
        publicar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String titulo_proyeco = titulo.getText().toString();
                String des = descripcion.getText().toString();
                if (titulo_proyeco.length() == 0 || des.length() == 0) {
                    Snackbar.make(view, "Debes llenar titulo y descripcion", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                } else {

                    if (ShareDialog.canShow(ShareLinkContent.class)) {
                        ShareLinkContent linkContent = new ShareLinkContent.Builder()
                                .setContentTitle(titulo_proyeco)
                                .setContentDescription(
                                        des)
                                .setContentUrl(Uri.parse("https://www.youtube.com/watch?v=Fw-js-G_t6k"))
                                .build();

                        shareDialog.show(linkContent);
                    }

                    // Termina el metodo de Share
                }
            }


        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        try {
            if (requestCode == 0 && resultCode == RESULT_OK && null != data) {
                Uri selected_image = data.getData();
                path = selected_image.toString();
                Bitmap bitmap = null;
                bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), selected_image);
                subir_foto.setImageBitmap(bitmap);


            }
        } catch (FileNotFoundException e)

        {
            e.printStackTrace();
            Toast.makeText(this, "No puedes usar la imagen selecionada ", Toast.LENGTH_LONG).show();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void agregarToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final ActionBar ab = getSupportActionBar();

        assert ab != null;
        if (ab.getTitle() != null) {
            ab.setTitle("Postear");
        }
        if (ab != null) {
            // Poner ícono del drawer toggle
            ab.setHomeAsUpIndicator(R.drawable.ic_ayuda);
            ab.setDisplayHomeAsUpEnabled(true);
            setTitle("Espacios");
        }

    }


}




