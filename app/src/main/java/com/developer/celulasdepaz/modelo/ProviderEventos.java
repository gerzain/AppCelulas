package com.developer.celulasdepaz.modelo;

import android.content.ContentProvider;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.text.TextUtils;

/**
 * Created by Gerza on 24/04/2016.
 */
public class ProviderEventos extends ContentProvider {

    public static final UriMatcher uriMatcher;
    public static final int EVENTOS = 100;
    public static final int EVENTOS_ID = 101;

    static {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(Contrato.AUTORIDAD, "eventos", EVENTOS);
        uriMatcher.addURI(Contrato.AUTORIDAD, "eventos/*", EVENTOS_ID);
    }

    private BDEventos bd;
    private ContentResolver resolver;

    @Override
    public boolean onCreate() {
        bd = new BDEventos(getContext());
        resolver = getContext().getContentResolver();
        return true;
    }


    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        // Obtener base de datos
        SQLiteDatabase db = bd.getWritableDatabase();
        // Comparar Uri
        int match = uriMatcher.match(uri);

        Cursor c;

        switch (match) {
            case EVENTOS:
                // Consultando todos los registros
                c = db.query(BDEventos.Tablas.EVENTO, projection,
                        selection, selectionArgs,
                        null, null, sortOrder);
                c.setNotificationUri(resolver, Contrato.Eventos.URI_CONTENIDO);
                break;
            case EVENTOS_ID:
                // Consultando un solo registro basado en el Id del Uri
                String idApartamento = Contrato.Eventos.obtenerIdEvento(uri);
                c = db.query(BDEventos.Tablas.EVENTO, projection,
                        Contrato.Eventos.ID_EVENTO + "=" + "\'" + idApartamento + "\'"
                                + (!TextUtils.isEmpty(selection) ?
                                " AND (" + selection + ')' : ""),
                        selectionArgs, null, null, sortOrder);
                c.setNotificationUri(resolver, uri);
                break;
            default:
                throw new IllegalArgumentException("URI no soportada: " + uri);
        }
        return c;
    }


    @Override
    public String getType(Uri uri) {
        switch (uriMatcher.match(uri)) {
            case EVENTOS:
                return Contrato.Eventos.MIME_COLECCION;
            case EVENTOS_ID:
                return Contrato.Eventos.MIME_RECURSO;
            default:
                throw new IllegalArgumentException("Tipo desconocido: " + uri);
        }
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues values) {
        return null;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        return 0;
    }
}
