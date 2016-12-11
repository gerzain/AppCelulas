package com.developer.celulasdepaz.Deserializador;
import  java.util.Date;

/**
 * Created by Irving on 10/12/2016.
 */
public class Fecha
{
        Date date;
        Date created_at;

    public Fecha(Date date, Date created_at) {
        this.date = date;
        this.created_at = created_at;
    }
    @Override
    public String toString()
    {
        return "Fecha [date=" + date + ", created_at=" + created_at + "]";
    }

}
