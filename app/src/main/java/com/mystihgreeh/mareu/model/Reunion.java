package com.mystihgreeh.mareu.model;

import java.util.Objects;

public class Reunion {

    /** Room name */
    private String room;

    /** date */
    private String date;

    /** Time */
    private String time;

    /** Object */
    private String object;

    /** emails */
    private String emails;




    /**
     * Constructor
     * @param room
     * @param date
     * @param time
     * @param object
     * @param emails
     */
    public Reunion(String room, String date, String time, String object, String emails){

        this.room = room;
        this.date = date;
        this.time = time;
        this.object = object;
        this.emails = emails;
    }

    public String getRoom() {
        return room;
    }
    public void setRoom(String room) {
        this.room = room;
    }

    public String getDate() { return date;}
    public void setDate(String date) {this.date = date;}

    public String getTime() {
        return time;
    }
    public void setTime(String time) {
        this.time = time;
    }

    public String getId() {
        return object;
    }
    public void setId(String id) {
        this.object = id;
    }

    public String getEmails() {return emails; }
    public void setEmails(String emails) {this.emails = emails; }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reunion reunion = (Reunion) o;
        return Objects.equals(object, reunion.object);
    }

    @Override
    public int hashCode() {
        return Objects.hash(object);
    }

    public void remove(Reunion reunion) {
    }

    public void add(Reunion reunion) {
    }

}
