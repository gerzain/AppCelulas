package com.developer.celulasdepaz.modelo;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Irving on 25/11/2016.
 */
public class Pages {
    @SerializedName("id")
    private String id;
    @SerializedName("name")
    private String nombre;
    @SerializedName("category")
    private String categoria;
    @SerializedName("is_silhouette")
    private String is_silhouette;
    @SerializedName("url")
    private String url;

    public Pages(String id, String nombre, String categoria)
    {
        this.id = id;
        this.nombre = nombre;
        this.categoria = categoria;
    }

    public Pages() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getIs_silhouette() {
        return is_silhouette;
    }

    public void setIs_silhouette(String is_silhouette) {
        this.is_silhouette = is_silhouette;
    }


}
