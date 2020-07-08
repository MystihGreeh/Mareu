package com.mystihgreeh.mareu.service;

import com.mystihgreeh.mareu.model.Reunion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class DummyReunionGenerator {

    public static List<Reunion> DUMMY_REUNIONS = Arrays.asList(
            new Reunion("Peach", "Salle A", "Vendredi 3 juillet 2020", "14h00", "charlotte.achin@gmail.com"),
            new Reunion("Mario", "Salle B", "Vendredi 3 juillet 2020", "16h00", "charlotte.achin@gmail.com"));

    static List<Reunion> generateReunion() {
        return new ArrayList<>(DUMMY_REUNIONS);
    }
}
