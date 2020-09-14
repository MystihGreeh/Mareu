package com.mystihgreeh.mareu.service;

import android.graphics.drawable.Drawable;

import com.mystihgreeh.mareu.R;
import com.mystihgreeh.mareu.model.Reunion;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public abstract class DummyReunionGenerator {

    public static List<Reunion> DUMMY_REUNIONS = Arrays.asList(
            new Reunion("Réunion A",initDate("03/08/2020"),"14h00", "Peach", "maxime@lamzone.fr, alex@lamzone.fr"),
            new Reunion("Réunion B",initDate("04/08/2020"),"15h00", "Luigi", "amandine@lamzone.fr, luc@lamzone.fr"),
            new Reunion("Réunion A",initDate("05/08/2020"),"17h00", "Bowser", "maxime@lamzone.fr, charlotte@lamzone.fr, jarvis@lamzone.fr"),
            new Reunion("Réunion D",initDate("03/08/2020"),"16h00","Toad", "amandine@lamzone.fr, viviane@lamzone.fr"));

    static List<Reunion> generateReunion() {
        return new ArrayList<>(DUMMY_REUNIONS);
    }

    private static Date initDate(String date){
        Date beginTime = null;
        String sDate = date+" ";
        try {
            beginTime = new SimpleDateFormat("dd/MM/yy HH:mm", Locale.getDefault()).parse(sDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return beginTime;
    }



}
