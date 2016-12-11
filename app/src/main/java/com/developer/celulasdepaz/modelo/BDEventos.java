package com.developer.celulasdepaz.modelo;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

/**
 * Created by Gerza on 20/04/2016.
 */
public class BDEventos extends SQLiteOpenHelper {
    static final int VERSION = 1;
    static final String NOMBRE_BD = "eventos.db";


    public BDEventos(Context context) {
        super(context, NOMBRE_BD, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(
                "CREATE TABLE " + Tablas.EVENTO + "("
                        + BaseColumns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                        + Contrato.Eventos.ID_EVENTO + " TEXT UNIQUE NOT NULL,"
                        + Contrato.Eventos.NOMBRE + " TEXT NOT NULL,"
                        + Contrato.Eventos.UBICACION + " TEXT NOT NULL,"
                        + Contrato.Eventos.DESCRIPCION + " TEXT NOT NULL,"
                        + Contrato.Eventos.TIPO + " REAL NOT NULL,"
                        + Contrato.Eventos.URL_IMAGEN + " TEXT NOT NULL)");

        ContentValues valores = new ContentValues();

        //Evento 1
        valores.put(Contrato.Eventos.ID_EVENTO, Contrato.Eventos.generarIdEvento());
        valores.put(Contrato.Eventos.NOMBRE, "Ahorro e Inversión");
        valores.put(Contrato.Eventos.UBICACION, "Quintana Roo");
        valores.put(Contrato.Eventos.DESCRIPCION, "Conferencia relacionada con temas de ahorro e inversión ");
        valores.put(Contrato.Eventos.TIPO, "Universidad del Sur");
        valores.put(Contrato.Eventos.URL_IMAGEN, "http://i.imgur.com/67Fm2NR.png");
        db.insertOrThrow(Tablas.EVENTO, null, valores);
        //Evento 2
        valores.put(Contrato.Eventos.ID_EVENTO, Contrato.Eventos.generarIdEvento());
        valores.put(Contrato.Eventos.NOMBRE, "Crédito hipotecario");
        valores.put(Contrato.Eventos.UBICACION, "CDMX");
        valores.put(Contrato.Eventos.DESCRIPCION, "Taller en donde podrás  comprender como funciona un crédito hipotecario");
        valores.put(Contrato.Eventos.TIPO, "");
        valores.put(Contrato.Eventos.URL_IMAGEN, "http://i.imgur.com/b1BdR1b.png");
        db.insertOrThrow(Tablas.EVENTO, null, valores);

        //Evento 3
        valores.put(Contrato.Eventos.ID_EVENTO, Contrato.Eventos.generarIdEvento());
        valores.put(Contrato.Eventos.NOMBRE, " Opciones para jovenes emprendedores");
        valores.put(Contrato.Eventos.UBICACION, "Baja California Sur");
        valores.put(Contrato.Eventos.DESCRIPCION, "En esta conferencia podrás conocer diversas opciones para que comienzes tu propio emprendimiento");
        valores.put(Contrato.Eventos.TIPO, "Auditorio Técnologico Nacional de México");
        valores.put(Contrato.Eventos.URL_IMAGEN, "http://i.imgur.com/DLvXEvp.jpg");
        db.insertOrThrow(Tablas.EVENTO, null, valores);

        //EVENTO 4
        valores.put(Contrato.Eventos.ID_EVENTO, Contrato.Eventos.generarIdEvento());
        valores.put(Contrato.Eventos.NOMBRE, " Manejo efeciente de las tarjetas de crédito");
        valores.put(Contrato.Eventos.UBICACION, "Tlaxcala");
        valores.put(Contrato.Eventos.DESCRIPCION, "Conferencía en la cual aprenderás la importancia de tener una manejo adecuado de las tarjetas de crédito");
        valores.put(Contrato.Eventos.TIPO, "Universidad Politénica de Tlaxcala");
        valores.put(Contrato.Eventos.URL_IMAGEN, "http://i.imgur.com/ZfXA1mV.png");
        db.insertOrThrow(Tablas.EVENTO, null, valores);


        //Evento 5
        valores.put(Contrato.Eventos.ID_EVENTO, Contrato.Eventos.generarIdEvento());
        valores.put(Contrato.Eventos.NOMBRE, " Ahorro para el retiro");
        valores.put(Contrato.Eventos.UBICACION, "Queretaro");
        valores.put(Contrato.Eventos.DESCRIPCION, "Conferencía la cual permite dar a conocer la importancia de generar un ahorro para el retiro");
        valores.put(Contrato.Eventos.TIPO, "Instalaciones de la Comisión Federal de Electricidad");
        valores.put(Contrato.Eventos.URL_IMAGEN, "http://i.imgur.com/3YWsKkQ.png");
        db.insertOrThrow(Tablas.EVENTO, null, valores);

        //Evento 6
        valores.put(Contrato.Eventos.ID_EVENTO, Contrato.Eventos.generarIdEvento());
        valores.put(Contrato.Eventos.NOMBRE, " El contrato de un seguro");
        valores.put(Contrato.Eventos.UBICACION, "Hidalgo");
        valores.put(Contrato.Eventos.DESCRIPCION, "Conferencía la cual permitirá conocer la evaluación de un plan de seguro antes de contratarlo");
        valores.put(Contrato.Eventos.TIPO, "Auditorio Jose Murillo Karam");
        valores.put(Contrato.Eventos.URL_IMAGEN, "http://i.imgur.com/zJklmZ2.jpg");
        db.insertOrThrow(Tablas.EVENTO, null, valores);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        try {
            db.execSQL("DROP TABLE IF EXISTS " + Tablas.EVENTO);
        } catch (SQLiteException e) {
            // Manejo de excepciones
        }
        onCreate(db);
    }

    interface Tablas {
        String EVENTO = "evento";
    }
}
