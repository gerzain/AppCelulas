package com.developer.celulasdepaz.Adaptadores;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.developer.celulasdepaz.R;
import com.developer.celulasdepaz.modelo.Post;

import java.util.List;

/**
 * Created by Irving on 09/12/2016.
 */
public class AdaptadorPost extends RecyclerView.Adapter<AdaptadorPost.ViewHolder>
{
    private final List<Post> items;

    public AdaptadorPost(List<Post> items)
    {
        this.items = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {

        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_lista_feed, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position)
    {
        items.get(position);
        holder.mensaje.setText(items.get(position).getMensaje());
        holder.precio.setText(items.get(position).getId_publicacion());
        holder.fecha.setText(items.get(position).getFecha_creacion());

    }

    @Override
    public int getItemCount()
    {
        return items.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder
    {
        public TextView mensaje;
        public TextView precio;
        public TextView fecha;

        public ViewHolder(View itemView)
        {
            super(itemView);
            mensaje=(TextView)itemView.findViewById(R.id.tvDescriptionn);
            precio=(TextView)itemView.findViewById(R.id.tvSpouse);
            fecha=(TextView)itemView.findViewById(R.id.tvChildren);


        }
    }





}
