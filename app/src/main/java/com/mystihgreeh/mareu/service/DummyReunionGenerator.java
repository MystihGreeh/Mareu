package com.mystihgreeh.mareu.service;

import com.mystihgreeh.mareu.model.Reunion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class DummyReunionGenerator {

    public static List<Reunion> DUMMY_REUNIONS = Arrays.asList(
            new Reunion("Salle A", "Vendredi 3 juillet 2020", "14h00", "Peach", "charlotte.achin@gmail.com"),
            new Reunion("Salle B", "Jeudi 2 juillet 2020", "15h00", "Luigi", "charlotte.achin@gmail.com"),
            new Reunion("Salle D", "Lundi 6 juillet 2020", "15h00", "Toad", "charlotte.achin@gmail.com"),
            new Reunion("Salle A", "Lundi 3 juillet 2020", "16h00", "Bowser", "charlotte.achin@gmail.com"),
            new Reunion("Salle C", "SMardi 7 juillet 2020", "10h00", "Mario", "charlotte.achin@gmail.com"));

    static List<Reunion> generateReunion() {
        return new ArrayList<>(DUMMY_REUNIONS);
    }
}
