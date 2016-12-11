package com.developer.celulasdepaz.Interfaces;


import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.developer.celulasdepaz.R;
import com.developer.celulasdepaz.modelo.Noticia;


/**
 * Created by lenovo on 25/08/2016.
 */
public class DetalleNoticia extends AppCompatActivity {
    public static final String EXTRA_PARAM_ID = "com.developer.cultufinanzas.extra.ID";
    public static final String VIEW_NAME_HEADER_IMAGE = "imagen_compartida";

    private Noticia itemDetallado;
    private ImageView imagenExtendida;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detalle_noticia);


        // Obtener la noticia  con el identificador establecido en la actividad principal
        itemDetallado = Noticia.getItem(getIntent().getIntExtra(EXTRA_PARAM_ID, 0));

        imagenExtendida = (ImageView) findViewById(R.id.imagen_extendida);

        cargarImagenExtendida();
        agregarToolbar();
    }

    private void agregarToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setDisplayHomeAsUpEnabled(true);
        }
    }

    private void cargarImagenExtendida() {
        Glide.with(imagenExtendida.getContext())
                .load(itemDetallado.getIdDrawable())
                .into(imagenExtendida);


    }

    public void Transicion() {

        Fragment newFragment = new FragmentoGrid();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.NoticiasPrincipal, newFragment)
                .addToBackStack(null)
                .commit();


    }
}// Finaliza la clase


