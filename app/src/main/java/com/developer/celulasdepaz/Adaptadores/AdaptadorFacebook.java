package com.developer.celulasdepaz.Adaptadores;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.developer.celulasdepaz.R;
import com.developer.celulasdepaz.modelo.Glosario;
import com.developer.celulasdepaz.modelo.Pages;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Irving on 25/11/2016.
 */
public class AdaptadorFacebook extends RecyclerView.Adapter<AdaptadorFacebook.ViewHolder> {
    private final List<Pages> items;

    public AdaptadorFacebook(List<Pages> items) {
        this.items = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {

        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_lista_evento, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        items.get(position);
        holder.nombre.setText(items.get(position).getNombre());
        holder.precio.setText(items.get(position).getCategoria());
        holder.imagen.setText(items.get(position).getId());
        Picasso.with(holder.itemView.getContext()).load(items.get(position).getUrl()).into(holder.url);

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder
    {
        // Campos respectivos de un item
        public TextView nombre;
        public TextView precio;
        public TextView imagen;
        public ImageView url;

        public ViewHolder(View v)
        {
            super(v);

            nombre = (TextView) v.findViewById(R.id.nombre);
            precio = (TextView) v.findViewById(R.id.descripcion);
            imagen = (TextView) v.findViewById(R.id.ubicacion);
            url = (ImageView) v.findViewById(R.id.foto);
        }
    }


}
