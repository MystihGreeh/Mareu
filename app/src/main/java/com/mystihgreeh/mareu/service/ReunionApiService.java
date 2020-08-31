package com.mystihgreeh.mareu.service;

import com.mystihgreeh.mareu.model.Reunion;

import java.util.Calendar;
import java.util.Date;
import java.util.List;


/**
 * Reunion API client
 */
public interface ReunionApiService {

    /**
     * Get all the reunion
     * @return {@link List}
     */
    List<Reunion> getReunions();
    List<Reunion> filteredByDate();
    List<Reunion> filteredByRoom();



    /**
     * Deletes a reunion
     * @param reunion
     */
    void deleteReunion(Reunion reunion);

    /**
     * Create a reunion
     * @param reunion
     */
    void createReunion(Reunion reunion);

    /**
     * Filter the list of Reunions
     * @return {@link List}
     */
    List<Reunion> reunionListFilter (boolean isDateFiltered, boolean isLocationFiltered, String roomFilterSelected, Date dateFilterSelected);

    void filteredByDate (Reunion reunion);

    void filteredByRoom (Reunion reunion);
}
