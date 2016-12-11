package com.developer.celulasdepaz.Interfaces;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v4.content.CursorLoader;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.developer.celulasdepaz.R;
import com.developer.celulasdepaz.modelo.Contrato;

/**
 * Created by Gerza on 20/04/2016.
 */
public class FragmentoEventos extends Fragment implements AdaptadorEventos.OnItemClickListener, LoaderManager.LoaderCallbacks<Cursor> {
    public Context context;
    private RecyclerView listaUI;
    private LinearLayoutManager linearLayoutManager;
    private AdaptadorEventos eventos;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // super.onCreateView(inflater, container, savedInstanceState);


        View view = inflater.inflate(R.layout.fragmneto_evento, container, false);


        // Preparar lista
        listaUI = (RecyclerView) view.findViewById(R.id.lista);
        listaUI.setHasFixedSize(true);

        linearLayoutManager = new LinearLayoutManager(getActivity());
        listaUI.setLayoutManager(linearLayoutManager);

        eventos = new AdaptadorEventos(this, this, getActivity());
        listaUI.setAdapter(eventos);

        FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v, "Me gusta este evento", Snackbar.LENGTH_LONG)
                        .setAction("Acción", null).show();
            }
        });


        //  compartir.setOnClickListener(this);


        // Iniciar loader
        getLoaderManager().restartLoader(1, null, this);
        return view;

    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new CursorLoader(getActivity(), Contrato.Eventos.URI_CONTENIDO, null, null, null, null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        if (eventos != null) {
            eventos.swapCursor(data);
        }
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }

    @Override
    public void onClick(AdaptadorEventos.ViewHolder holder, String idAlquiler) {

    }
}
