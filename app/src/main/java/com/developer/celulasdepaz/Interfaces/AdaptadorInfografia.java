package com.developer.celulasdepaz.Interfaces;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.developer.celulasdepaz.R;

/**
 * Created by lenovo on 25/08/2016.
 */
public class AdaptadorInfografia extends ArrayAdapter {
    private String[] nombres;
    private String[] desc;
    private Integer[] imageid;
    private Activity context;
    private int ultima_posicion = -1;

    public AdaptadorInfografia(Activity context, String[] nombres, String[] desc, Integer[] imageid) {
        super(context, R.layout.item_lista_infografia, nombres);
        this.context = context;
        this.nombres = nombres;
        this.desc = desc;
        this.imageid = imageid;

    }

    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();

        View listViewItem = inflater.inflate(R.layout.item_lista_infografia, null, true);
        TextView textViewName = (TextView) listViewItem.findViewById(R.id.textViewName);
        TextView textViewDesc = (TextView) listViewItem.findViewById(R.id.textViewDesc);
        ImageView image = (ImageView) listViewItem.findViewById(R.id.imageView);
        textViewName.setText(nombres[position]);
        textViewDesc.setText(desc[position]);
        image.setImageResource(imageid[position]);
        Animation animation = AnimationUtils.loadAnimation(getContext(), (position > ultima_posicion) ? R.anim.up_from_bottom : R.anim.down_from_top);
        listViewItem.setAnimation(animation);
        ultima_posicion = position;

        return listViewItem;
    }


}
