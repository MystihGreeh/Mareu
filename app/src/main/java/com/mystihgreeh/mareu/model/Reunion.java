package com.mystihgreeh.mareu.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class Reunion implements Serializable {


    /** Room name */
    private String room;

    /** date */
    private Date date;

    /** Time */
    private String time;

    /** Object */
    private String object;

    /** emails */
    private String emails;


    /**
     * Constructor
     * @param object
     * @param date
     * @param time
     * @param room
     * @param emails
     */
    public Reunion(String object, Date date, String time, String room, String emails){
        this.room = room;
        this.date = date;
        this.time = time;
        this.object = object;
        this.emails = emails;
    }


    public String getObject() {
        return object;
    }
    public void setObject(String object) {
        this.object = object;
    }

    public Date getDate() { return date;}
    public void setDate(Date date) {this.date = date;}

    public String getTime() {
        return time;
    }
    public void setTime(String time) {
        this.time = time;
    }

    public String getRoom() {
        return room;
    }
    public void setRoom(String room) {
        this.room = room;
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
