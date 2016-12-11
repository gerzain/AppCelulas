package com.developer.celulasdepaz.Interfaces;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.developer.celulasdepaz.modelo.Noticia;
import com.developer.celulasdepaz.R;

/**
 * Created by lenovo on 25/08/2016.
 */
public class AdaptadorNoticias extends BaseAdapter {
    private Context context;

    public AdaptadorNoticias(Context context) {
        this.context = context;
    }


    @Override
    public int getCount() {
        return Noticia.ITEMS.length;
    }

    @Override
    public Noticia getItem(int position) {
        return Noticia.ITEMS[position];
    }

    @Override
    public long getItemId(int position) {
        return getItem(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.grid_noticias, parent, false);
        }

        ImageView imagenCoche = (ImageView) convertView.findViewById(R.id.imagen_coche);
        TextView nombreCoche = (TextView) convertView.findViewById(R.id.nombre_coche);

        final Noticia item = getItem(position);
        Glide.with(imagenCoche.getContext())
                .load(item.getIdDrawable())
                .into(imagenCoche);

        nombreCoche.setText(item.getNombre());


        return convertView;
    }
}
