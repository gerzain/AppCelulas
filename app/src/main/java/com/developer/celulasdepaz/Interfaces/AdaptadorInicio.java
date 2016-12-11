package com.developer.celulasdepaz.Interfaces;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.developer.celulasdepaz.modelo.Glosario;
import com.developer.celulasdepaz.R;

/**
 * Adaptador  para  la sección "Inicio"
 */
public class AdaptadorInicio
        extends RecyclerView.Adapter<AdaptadorInicio.ViewHolder> {


    public AdaptadorInicio() {


    }

    @Override
    public int getItemCount() {
        return Glosario.Eventos.size();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_lista_inicio, viewGroup, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        Glosario item = Glosario.Eventos.get(i);

        Glide.with(viewHolder.itemView.getContext())
                .load(item.getIdDrawable())
                .centerCrop()
                .into(viewHolder.imagen);
        viewHolder.nombre.setText(item.getNombre());
        viewHolder.precio.setText("" + item.getPrecio());

    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        // Campos respectivos de un item
        public TextView nombre;
        public TextView precio;
        public ImageView imagen;

        public ViewHolder(View v) {
            super(v);
            v.setOnClickListener(this);
            nombre = (TextView) v.findViewById(R.id.nombre_comida);
            precio = (TextView) v.findViewById(R.id.precio_comida);
            imagen = (ImageView) v.findViewById(R.id.miniatura_comida);
        }

        @Override
        public void onClick(View v) {
            Toast.makeText(v.getContext(), "Seleciona un item", Toast.LENGTH_LONG);

        }
    }


}