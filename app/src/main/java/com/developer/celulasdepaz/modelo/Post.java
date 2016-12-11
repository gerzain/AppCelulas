package com.developer.celulasdepaz.modelo;
import  com.google.gson.annotations.SerializedName;
/**
 * Created by Irving on 09/12/2016.
 */
public class Post
{
    public Post()
    {

    }

    @SerializedName("message")
    private String mensaje;
    @SerializedName("id")
    private String id_publicacion;
    @SerializedName("created_time")
    private String fecha_creacion;

    public Post(String mensaje, String id_publicacion, String fecha_creacion) {
        this.mensaje = mensaje;
        this.id_publicacion = id_publicacion;
        this.fecha_creacion = fecha_creacion;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getId_publicacion() {
        return id_publicacion;
    }

    public void setId_publicacion(String id_publicacion) {
        this.id_publicacion = id_publicacion;
    }

    public String getFecha_creacion() {
        return fecha_creacion;
    }

    public void setFecha_creacion(String fecha_creacion) {
        this.fecha_creacion = fecha_creacion;
    }
}
