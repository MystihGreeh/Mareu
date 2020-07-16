package com.mystihgreeh.mareu.service;

import com.mystihgreeh.mareu.R;
import com.mystihgreeh.mareu.model.Reunion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class DummyReunionGenerator {

    public static List<Reunion> DUMMY_REUNIONS = Arrays.asList(
            new Reunion("Réunion A", "Vendredi 3 juillet 2020", "14h00", "Peach", "maxime@lamzone.fr, alex@lamzone.fr"),
            new Reunion("Réunion B", "Jeudi 2 juillet 2020", "16h00", "Mario", "paul@lamzone.fr, viviane@lamzone.fr"),
            new Reunion("Réunion C", "Lundi 6 juillet 2020", "19h00", "Luigi", "amandine@lamzone.fr, luc@lamzone.fr"),
            new Reunion("Réunion A", "Lundi 3 juillet 2020", "15h00", "Bowser", "maxime@lamzone.fr, charlotte@lamzone.fr, jarvis@lamzone.fr"),
            new Reunion("Réunion D", "SMardi 7 juillet 2020", "10h00", "Toad", "amandine@lamzone.fr, viviane@lamzone.fr"));

    static List<Reunion> generateReunion() {
        return new ArrayList<>(DUMMY_REUNIONS);
    }
}
