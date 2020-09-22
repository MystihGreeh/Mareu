package com.mystihgreeh.mareu.service;

import com.mystihgreeh.mareu.R;
import com.mystihgreeh.mareu.model.Reunion;
import com.mystihgreeh.mareu.model.Room;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public abstract class DummyReunionGenerator {

    public static List<Reunion> DUMMY_REUNIONS = Arrays.asList(
            new Reunion("Réunion A", new Date(1600779600), "14:00", "Peach", "maxime@lamzone.fr, alex@lamzone.fr"),
            new Reunion("Réunion B", new Date(1600709618), "15:00", "Luigi", "amandine@lamzone.fr, luc@lamzone.fr"),
            new Reunion("Réunion A", new Date(1600623218), "17:00", "Bowser", "maxime@lamzone.fr, charlotte@lamzone.fr, jarvis@lamzone.fr"),
            new Reunion("Réunion D", new Date(1600623218), "16:00", "Toad", "amandine@lamzone.fr, viviane@lamzone.fr"));

    static List<Reunion> generateReunion() {
        return new ArrayList<>(DUMMY_REUNIONS);
    }

    public static Room[] ROOM_LIST = {

            new Room("Luigi", R.drawable.metting_room_one),
            new Room("Mario", R.drawable.meeting_room_two),
            new Room("Peach", R.drawable.meeting_room_three),
            new Room("Bowser", R.drawable.meeting_room_four),
            new Room("Toad", R.drawable.meeting_room_five),

    };

    public static Room[] getListRooms() {
        return ROOM_LIST;
    }

    static List<Room> generateRoom() {
        return Arrays.asList(ROOM_LIST);
    }




}
