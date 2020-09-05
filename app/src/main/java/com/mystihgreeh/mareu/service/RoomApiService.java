package com.mystihgreeh.mareu.service;

import com.mystihgreeh.mareu.model.Reunion;
import com.mystihgreeh.mareu.model.Room;

import java.util.Date;
import java.util.List;


/**
 * Room API client
 */
public interface RoomApiService {

    /**
     * Get all the room
     * @return {@link List}
     */
    List<Room> getRooms();




    /**
     * Deletes a room
     * @param room
     */
    void deleteRoom(Room room);

    /**
     * Create a room
     * @param room
     */
    void createRoom(Room room);


}