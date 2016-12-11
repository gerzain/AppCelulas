package com.developer.celulasdepaz.Interfaces;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.developer.celulasdepaz.R;

/**
 * Created by Gerza on 24/04/2016.
 */
public class AdaptadorEventos extends RecyclerView.Adapter<AdaptadorEventos.ViewHolder> {

    public Button compartir;
    public Activity activity;
    private Context contexto;
    private FragmentoEventos con;
    private Cursor items;
    private OnItemClickListener escucha;

    public AdaptadorEventos(FragmentoEventos contexto, OnItemClickListener escucha, Context con) {
        this.con = contexto;
        this.escucha = escucha;
        this.contexto = con;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_lista_evento, parent, false);

        compartir = (Button) v.findViewById(R.id.compartir);
        compartir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                   /* Toast.makeText(contexto.getApplicationContext(),
                            "Button is clicked!", Toast.LENGTH_LONG).show();*/
                shareIt();
            }
        });
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        items.moveToPosition(position);

        String s;

        // Asignación UI
        s = items.getString(ConsultaAlquileres.NOMBRE);
        holder.viewNombre.setText(s);

        s = items.getString(ConsultaAlquileres.UBICACION);
        holder.viewUbicacion.setText(s);

        s = items.getString(ConsultaAlquileres.DESCRIPCION);
        holder.viewDescripcion.setText(s);

        s = items.getString(ConsultaAlquileres.PRECIO);
        holder.viewPrecio.setText(s);

        s = items.getString(ConsultaAlquileres.URL);
        Glide.with(contexto).load(s).centerCrop().into(holder.viewFoto);


    }

    private String obtenerIdAlquiler(int posicion) {
        if (items != null) {
            if (items.moveToPosition(posicion)) {
                return items.getString(ConsultaAlquileres.ID_ALQUILER);
            }
        }

        return null;
    }

    @Override
    public int getItemCount() {
        if (items != null)
            return items.getCount();
        return 0;
    }

    public void swapCursor(Cursor nuevoCursor) {
        if (nuevoCursor != null) {
            items = nuevoCursor;
            notifyDataSetChanged();
        }
    }

    public Cursor getCursor() {
        return items;
    }

    private void shareIt() {

        Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
        sharingIntent.setType("text/plain");
        sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Compatir ");
        sharingIntent.putExtra(Intent.EXTRA_TEXT, "Has compartido un evento");
        contexto.startActivity(Intent.createChooser(sharingIntent, "Compartir via "));
    }

    interface OnItemClickListener {
        void onClick(ViewHolder holder, String idAlquiler);
    }

    interface ConsultaAlquileres {
        int ID_ALQUILER = 1;
        int NOMBRE = 2;
        int UBICACION = 3;
        int DESCRIPCION = 4;
        int PRECIO = 5;
        int URL = 6;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView viewNombre;
        public TextView viewUbicacion;
        public TextView viewDescripcion;
        public TextView viewPrecio;
        public ImageView viewFoto;
        public Button compartir;

        public ViewHolder(View itemView) {
            super(itemView);

            viewNombre = (TextView) itemView.findViewById(R.id.nombre);
            viewUbicacion = (TextView) itemView.findViewById(R.id.ubicacion);
            viewDescripcion = (TextView) itemView.findViewById(R.id.descripcion);
            viewPrecio = (TextView) itemView.findViewById(R.id.precio);
            viewFoto = (ImageView) itemView.findViewById(R.id.foto);
            compartir = (Button) itemView.findViewById(R.id.compartir);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            escucha.onClick(this, obtenerIdAlquiler(getAdapterPosition()));
        }
    }


}
