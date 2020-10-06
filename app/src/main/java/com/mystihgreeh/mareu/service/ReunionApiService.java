package com.mystihgreeh.mareu.service;

import com.mystihgreeh.mareu.model.Reunion;
import com.mystihgreeh.mareu.model.Room;


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
    List<Room> getRooms();




    /**
     * Deletes a reunion
     * @param reunion
     */
    void deleteReunion(Reunion reunion);


    /**
     * Create a reunion
     * @param reunion
     */
    void addReunion(Reunion reunion);

    List<Reunion> reunionListFilter (boolean isDateFiltered, boolean isLocationFiltered, String roomFilterSelected, Date dateFilterSelected);


}
