package com.developer.celulasdepaz.Interfaces;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.developer.celulasdepaz.R;
import com.facebook.Profile;
import com.squareup.picasso.Picasso;

import java.util.zip.Inflater;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Irving on 22/09/2016.
 */
public class FragmentoPerfil extends Fragment {
    public static final String mypreference = "mypref";
    public static final String Name = "nameKey";
    public static final String key = "nameKe";
    public String nombre_usuario;
    public Profile profile;
    private TextView nombre;
    private ImageView foto_perfil;
    private String id_usuario;
    private String imageurl;
    private SharedPreferences sharedpreferences;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Intent intent = getActivity().getIntent();
        Bundle extras = intent.getExtras();


        View view = inflater.inflate(R.layout.fragmento_perfil, container, false);
        nombre = (TextView) view.findViewById(R.id.perfil_nombre);
        foto_perfil = (ImageView) view.findViewById(R.id.img_perfil);

        if (extras != null) {
            //Verificar la existencia de datos
            Bitmap c = extras.getParcelable("imagebitmap");
            String datoNombre = (String) extras.get("nombre");
            nombre.setText(datoNombre);
            foto_perfil.setImageBitmap(c);

        }
        sharedpreferences = getActivity().getSharedPreferences(mypreference,
                Context.MODE_PRIVATE);

        if (sharedpreferences.contains(Name)) {
            nombre.setText(sharedpreferences.getString(Name, ""));
        }


        if (sharedpreferences.contains(key)) {
            foto_perfil = (ImageView) view.findViewById(R.id.img_perfil);
            String set = sharedpreferences.getString(key, null);
            Log.e("UrlOnResumePerfil>>", set);
            Picasso.with(getContext()).load(set).into(foto_perfil);

        }

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        Log.d("En el metodo onResumme", "onRessume");
        Get();

    }

    @Override
    public void onPause() {
        super.onPause();
        Save();

    }

    public void Save() {
        nombre_usuario = nombre.getText().toString();
        profile = Profile.getCurrentProfile();
        if (profile != null) {
            id_usuario = profile.getId();

            imageurl = "https://graph.facebook.com/" + id_usuario + "/picture?type=large";

            SharedPreferences.Editor editor = sharedpreferences.edit();
            editor.putString(Name, nombre_usuario);
            editor.putString(key, imageurl);
            Log.e("Image Perfil>>:", imageurl);
            editor.apply();
        }
    }

    public void Get() {

        sharedpreferences = getActivity().getSharedPreferences(mypreference,
                Context.MODE_PRIVATE);

        if (sharedpreferences.contains(Name)) {
            nombre.setText(sharedpreferences.getString(Name, ""));
        }


        if (sharedpreferences.contains(key)) {

            String set = sharedpreferences.getString(key, null);
            Log.e("UrlOnResume>>", set);
            Picasso.with(getContext()).load(set).into(foto_perfil);

        }

    }
}
