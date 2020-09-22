package com.mystihgreeh.mareu.service;


import com.mystihgreeh.mareu.model.Reunion;
import com.mystihgreeh.mareu.model.Room;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Dummy mock for the Api
 */

public class DummyReunionApiService implements ReunionApiService {

    private List<Reunion> reunions = DummyReunionGenerator.generateReunion();
    private List<Room> rooms = DummyReunionGenerator.generateRoom();


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
    public void addReunion(Reunion reunion) {
        reunions.add(reunion);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Room> getRooms() {
        return rooms;
    }


    @Override
    public List<Reunion> reunionListFilter (boolean isDateFiltered, boolean isLocationFiltered, String roomFilterSelected, Date dateFilterSelected) {

        List<Reunion> reunionArrayList = new ArrayList<>();

        // Filter (if either room, date or both are filtered)
        if (isDateFiltered || isLocationFiltered){
            for (int i = 0; i < getReunions().size(); i++) {

                // Difference of rooms
                String sReunionLocation = getReunions().get(i).getRoom();
                boolean booleanLocation = sReunionLocation.equals(roomFilterSelected);

                // difference of dates (with calendars)
                Calendar cal1 = Calendar.getInstance();
                Calendar cal2 = Calendar.getInstance();
                cal1.setTime(getReunions().get(i).getDate());
                if (dateFilterSelected != null){
                    cal2.setTime(dateFilterSelected);
                }

                boolean booleanDate = cal1.get(Calendar.DAY_OF_YEAR) == cal2.get(Calendar.DAY_OF_YEAR) &&
                        cal1.get(Calendar.MONTH) == cal2.get(Calendar.MONTH) &&
                        cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR);

                // If both are filtered and match filter, add the reunion to the list
                if (isLocationFiltered && isDateFiltered) {
                    if (booleanLocation && booleanDate) { reunionArrayList.add(getReunions().get(i)); }

                    // If only the room is filtered and match filter, add the reunion to the list
                } else if (isLocationFiltered) {
                    if (booleanLocation) { reunionArrayList.add(getReunions().get(i)); }

                    // If only the date is filtered and match filter, add the reunion to the list
                } else {
                    if (booleanDate) { reunionArrayList.add(getReunions().get(i)); }
                }
            }
            // Without filter, show all reunions
        } else {
            reunionArrayList = getReunions();
        }
        return reunionArrayList;
    }

}
