package com.mystihgreeh.mareu.service;


import com.mystihgreeh.mareu.model.Reunion;
import com.mystihgreeh.mareu.model.Room;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Dummy mock for the Api
 */

public class DummyReunionApiService implements ReunionApiService{

    private List<Reunion> reunions = DummyReunionGenerator.generateReunion();
    private List<Room> rooms = DummyRoomGenerator.generateRoom();



    /**
     * {@inheritDoc}
     */
    @Override
    public List<Reunion> getReunions() {
        return reunions;
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteReunion(Reunion reunion) {
        reunions.remove(reunion);
    }

    /**
     * {@inheritDoc}
     *
     * @param reunion
     */
    @Override
    public void createReunion(Reunion reunion) {
        reunions.add(reunion);
    }

}
