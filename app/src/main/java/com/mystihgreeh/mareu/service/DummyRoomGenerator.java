package com.mystihgreeh.mareu.service;

import com.mystihgreeh.mareu.R;
import com.mystihgreeh.mareu.model.Room;

import java.util.Arrays;
import java.util.List;

public abstract class DummyRoomGenerator {

    public static Room[] ROOM_LIST = {

            new Room("Luigi", R.drawable.metting_room_one),
            new Room("Mario", R.drawable.meeting_room_two),
            new Room("Peach", R.drawable.meeting_room_three),
            new Room("Bowser", R.drawable.meeting_room_four),
            new Room("Toad", R.drawable.meeting_room_five),

    };

    public static Room[] getListRooms()
    {
        return ROOM_LIST;
    }

    static List<Room> generateRoom() {
        return Arrays.asList(ROOM_LIST);
    }

}
