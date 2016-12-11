package com.developer.celulasdepaz.Interfaces;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.developer.celulasdepaz.modelo.Glosario;
import com.developer.celulasdepaz.R;

import java.util.List;

/**
 * Adaptador para el Glosario
 */
public class AdaptadorGlosario
        extends RecyclerView.Adapter<AdaptadorGlosario.ViewHolder> {


    private final List<Glosario> items;

    public AdaptadorGlosario(List<Glosario> items) {
        this.items = items;
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_lista_categorias, viewGroup, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, int i) {
        Glosario item = items.get(i);


        Glide.with(viewHolder.itemView.getContext())
                .load(item.getIdDrawable())
                .centerCrop()
                .into(viewHolder.imagen);
        viewHolder.nombre.setText(item.getNombre());
        viewHolder.precio.setText(item.getPrecio());


    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        // Campos respectivos de un item
        public TextView nombre;
        public TextView precio;
        public ImageView imagen;

        public ViewHolder(View v) {
            super(v);

            nombre = (TextView) v.findViewById(R.id.nombre_comida);
            precio = (TextView) v.findViewById(R.id.precio_comida);
            imagen = (ImageView) v.findViewById(R.id.miniatura_comida);
        }
    }


}