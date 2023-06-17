package com.kosmos.citas.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class Utils {

    public static Date getDate(String fecha) throws ParseException {
        Date date1=new SimpleDateFormat("dd/MM/yyyy").parse(fecha);

        return date1;
    }

    public static Date getHora(String hora) throws ParseException {
        Date date1=new SimpleDateFormat("HH:mm").parse(hora);

        return date1;
    }

    public static ZonedDateTime getZoneDateTime(String fecha){
        String dateString = fecha;
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        LocalDateTime dateTime = LocalDateTime.parse(dateString, dtf);
        ZonedDateTime date = dateTime.atZone(ZoneId.systemDefault());

        return date;
    }

}
