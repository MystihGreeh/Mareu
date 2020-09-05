package com.mystihgreeh.mareu.service;

import com.mystihgreeh.mareu.model.Reunion;
import com.mystihgreeh.mareu.model.Room;

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

}
