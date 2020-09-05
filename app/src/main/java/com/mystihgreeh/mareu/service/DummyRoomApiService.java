package com.mystihgreeh.mareu.service;


import com.mystihgreeh.mareu.model.Room;

import java.util.Date;
import java.util.List;

/**
 * Dummy mock for the Api
 */

public class DummyRoomApiService implements RoomApiService{

    private List<Room> rooms = DummyRoomGenerator.generateRoom();



    /**
     * {@inheritDoc}
     */
    @Override
    public List<Room> getRooms() {
        return rooms;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteRoom(Room room) {
        rooms.remove(room);
    }

    /**
     * {@inheritDoc}
     *
     * @param room
     */
    @Override
    public void createRoom(Room room) {
        rooms.add(room);
    }

}