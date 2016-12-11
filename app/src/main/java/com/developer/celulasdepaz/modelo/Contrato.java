package com.developer.celulasdepaz.modelo;

import android.net.Uri;

import java.util.UUID;

/**
 * Created by Gerza on 20/04/2016.
 */
public class Contrato {
    public final static String AUTORIDAD = "com.developer.cultufinanzas";
    public final static Uri URI_CONTENIDO_BASE = Uri.parse("content://" + AUTORIDAD);
    public final static String RECURSO_EVENTOS = "eventos";

    interface ColumnasEvento {
        String ID_EVENTO = "idAlquiler"; // Pk
        String NOMBRE = "nombre";
        String UBICACION = "ubicacion";
        String DESCRIPCION = "descripcion";
        String TIPO = "tipo";
        String URL_IMAGEN = "urlImagen";
    }

    public static class Eventos implements ColumnasEvento {


        public static final Uri URI_CONTENIDO = URI_CONTENIDO_BASE.buildUpon().appendEncodedPath(RECURSO_EVENTOS).build();
        public final static String MIME_RECURSO = "vnd.android.cursor.item/vnd." + AUTORIDAD + "/" + RECURSO_EVENTOS;
        public final static String MIME_COLECCION =
                "vnd.android.cursor.dir/vnd." + AUTORIDAD + "/" + RECURSO_EVENTOS;


        public static Uri construirUriEvento(String idEvento) {
            return URI_CONTENIDO.buildUpon().appendPath(idEvento).build();
        }

        public static String generarIdEvento() {
            return "A-" + UUID.randomUUID();
        }

        public static String obtenerIdEvento(Uri uri) {
            return uri.getLastPathSegment();
        }

    }

}
