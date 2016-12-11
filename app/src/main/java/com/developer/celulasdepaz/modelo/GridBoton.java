package com.developer.celulasdepaz.modelo;

import com.developer.celulasdepaz.R;

/**
 * Created by lenovo on 26/08/2016.
 */
public class GridBoton {
    public static GridBoton[] ITEMS = {
            new GridBoton("Embarazo", R.drawable.abono),
            new GridBoton("Drogas", R.drawable.aguinaldo),
            new GridBoton("Ambientes", R.drawable.comenzar),
            new GridBoton("Deserción", R.drawable.pagominimo),
            new GridBoton("Oportunidades", R.drawable.presupuesto),
            new GridBoton("Social", R.drawable.banco),
            new GridBoton("Espacios", R.drawable.comenzar),
            new GridBoton("Marginación", R.drawable.pagominimo),

    };
    private String nombre;
    private int idDrawable;

    public GridBoton(String nombre, int idDrawable) {
        this.nombre = nombre;
        this.idDrawable = idDrawable;
    }

    public static GridBoton getItem(int id) {

        {
            for (GridBoton item : ITEMS) {
                if (item.getId() == id) {
                    return item;
                }
            }
            return null;
        }
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