package com.developer.celulasdepaz.Interfaces;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.transition.Fade;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;

import com.developer.celulasdepaz.Fragments.DesercionEscolar;
import com.developer.celulasdepaz.R;
import com.developer.celulasdepaz.VideoActivity.EspaciosPublicos;
import com.developer.celulasdepaz.Videos.Fragment.AmbientesFamiliaresDeteriorados;
import com.developer.celulasdepaz.Videos.Fragment.CapitalSocialDebilitado;
import com.developer.celulasdepaz.Videos.Fragment.ConsumoAbusoDrogas;
import com.developer.celulasdepaz.Videos.Fragment.DesercionEscolarVideo;
import com.developer.celulasdepaz.Videos.Fragment.Espacios;
import com.developer.celulasdepaz.Videos.Fragment.MarginacionExlusionSocial;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by lenovo on 26/08/2016.
 */
public class FragmentoQueHacer extends Fragment {
    @InjectView(R.id.textView)
    TextView des1;
    private GridView gridView;
    private AdaptadorQueHacer adaptador;
    private Button ambientesfamiliares;
    private Button consumo_Drogas;
    private Button ExclusionSocial;
    private Button CapitalSocial;
    private Button AmbientesFamiliares;
    private Button DesersionEscolar;
    private Fragment generico;


    public FragmentoQueHacer() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragmneto_hacer, container, false);
        ButterKnife.inject(this, view);

        ambientesfamiliares = (Button) view.findViewById(R.id.BtnAmbientesFamiliares);
        ambientesfamiliares.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                IniciarVideo();
            }
        });


        consumo_Drogas = (Button) view.findViewById(R.id.BtnCuarto);
        consumo_Drogas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                VideoConsumoDrogas();
            }
        });
        ExclusionSocial = (Button) view.findViewById(R.id.BtnSegundo);
        ExclusionSocial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Marginacion();
            }
        });
        CapitalSocial = (Button) view.findViewById(R.id.BtnQuinto);
        CapitalSocial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                VideoCapitalSocial();
            }
        });
        AmbientesFamiliares = (Button) view.findViewById(R.id.BtnTercero);
        AmbientesFamiliares.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                VideoAmbientesFamiliaresDeteriorados();
            }
        });
        DesersionEscolar = (Button) view.findViewById(R.id.button);
        DesersionEscolar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                VideoDesercionEscolar();
            }
        });

        return view;

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {

        // Do something that differs the Activity's menu here
        inflater.inflate(R.menu.menu_actividad_principal, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @OnClick(R.id.BtnAmbientesFamiliares)
    public void Click() {
        IniciarVideo();
    }


    public void IniciarVideo()

    {
        generico = new Espacios();
        String titulo = des1.getText().toString();
        Bundle data = new Bundle();
        data.putString("titulo", titulo);
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.setCustomAnimations(R.anim.slide_in_left, R.anim.exit_left);
        transaction.replace(R.id.contenedor_principal, generico);
        generico.setArguments(data);
        transaction.commit();
    }

    public void VideoConsumoDrogas() {
        generico = new ConsumoAbusoDrogas();
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.setCustomAnimations(R.anim.slide_in_left, R.anim.exit_left);
        transaction.replace(R.id.contenedor_principal, generico);
        transaction.commit();

    }

    private void Marginacion() {
        generico = new MarginacionExlusionSocial();
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.setCustomAnimations(R.anim.slide_in_left, R.anim.exit_left);
        transaction.replace(R.id.contenedor_principal, generico);
        transaction.commit();
    }

    private void VideoCapitalSocial() {
        generico = new CapitalSocialDebilitado();
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.setCustomAnimations(R.anim.slide_in_left, R.anim.exit_left);
        transaction.replace(R.id.contenedor_principal, generico);
        transaction.commit();
    }

    private void VideoAmbientesFamiliaresDeteriorados() {
        generico = new AmbientesFamiliaresDeteriorados();
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.setCustomAnimations(R.anim.slide_in_left, R.anim.exit_left);
        transaction.replace(R.id.contenedor_principal, generico);
        transaction.commit();
    }

    private void VideoDesercionEscolar() {
        generico = new DesercionEscolarVideo();
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.setCustomAnimations(R.anim.slide_in_left, R.anim.exit_left);
        transaction.replace(R.id.contenedor_principal, generico);
        transaction.commit();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.action_carrito:

                // Not implemented here
                return false;
            case R.id.action_salir:

                // Do Fragment menu item stuff here
                return true;

            default:
                break;
        }
        return true;
    }
}
