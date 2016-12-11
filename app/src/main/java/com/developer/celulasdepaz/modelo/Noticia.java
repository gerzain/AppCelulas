package com.developer.celulasdepaz.modelo;

import com.developer.celulasdepaz.R;

/**
 * Created by Irving on 18/09/2016.
 */
public class Noticia {
    public static Noticia[] ITEMS = {
            new Noticia("Embarazo", R.drawable.abono),
            new Noticia("Drogas", R.drawable.aguinaldo),
            new Noticia("Ambientes", R.drawable.comenzar),
            new Noticia("Deserción", R.drawable.pagominimo),
            new Noticia("Oportunidades", R.drawable.presupuesto),
            new Noticia("Social", R.drawable.banco),
            new Noticia("Espacios", R.drawable.comenzar),
            new Noticia("Marginación", R.drawable.pagominimo),

    };
    private String nombre;
    private int idDrawable;


    public Noticia(String nombre, int idDrawable) {
        this.nombre = nombre;
        this.idDrawable = idDrawable;
    }

    public static Noticia getItem(int id) {
        for (Noticia item : ITEMS) {
            if (item.getId() == id) {
                return item;
            }
        }
        return null;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getIdDrawable() {
        return idDrawable;
    }

    public void setIdDrawable(int idDrawable) {
        this.idDrawable = idDrawable;
    }

    public int getId() {
        return nombre.hashCode();
    }
}
