package com.developer.celulasdepaz.Interfaces;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.developer.celulasdepaz.R;
import com.developer.celulasdepaz.modelo.GridBoton;

/**
 * Created by lenovo on 25/08/2016.
 */
public class AdaptadorQueHacer extends BaseAdapter {
    private Context context;

    public AdaptadorQueHacer(Context context) {
        this.context = context;
    }


    @Override
    public int getCount() {
        return GridBoton.ITEMS.length;
    }

    @Override
    public GridBoton getItem(int position) {
        return GridBoton.ITEMS[position];
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
            convertView = inflater.inflate(R.layout.grid_botones, parent, false);
        }

        Button imagenCoche = (Button) convertView.findViewById(R.id.imagen_coche);
        TextView nombreCoche = (TextView) convertView.findViewById(R.id.nombre_coche);

        final GridBoton item = getItem(position);
       /* Glide.with(imagenCoche.getContext())
                .load(item.getIdDrawable())
                .into(imagenCoche);*/
        imagenCoche.animate();

        nombreCoche.setText(item.getNombre());

        return convertView;
    }
}
