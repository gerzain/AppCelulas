package com.developer.celulasdepaz.Deserializador;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Irving on 10/12/2016.
 */
public class FooDeserializer implements JsonDeserializer<Fecha>
{

    @Override
    public Fecha deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException
    {
        String a = json.getAsJsonObject().get("date").getAsString();
        String b = json.getAsJsonObject().get("created_at").getAsString();

        SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat sdfDateWithTime = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");

        Date date, created;
        try {
            date = sdfDate.parse(a);
            created = sdfDateWithTime.parse(b);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        return new Fecha(date, created);


    }// Termina el  metodo de seralizar


}// Termina la clase
