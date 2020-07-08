package com.mystihgreeh.mareu.model;

import android.widget.EditText;
import android.widget.Spinner;

import com.google.android.material.textfield.TextInputEditText;

import java.util.Objects;

public class Reunion {

    /** Identifier */
    private String id;

    /** Room name */
    private String room;

    /** date */
    private String date;

    /** Time */
    private String time;

    /** emails */
    private String emails;




    /**
     * Constructor
     * @param id
     * @param room
     * @param date
     * @param time
     * @param emails
     */
    public Reunion(String id, String room, String date, String time, String emails){
        this.id = id;
        this.room = room;
        this.date = date;
        this.time = time;
        this.emails = emails;
    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
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

    public String getEmails() {return emails; }
    public void setEmails(String emails) {this.emails = emails; }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reunion reunion = (Reunion) o;
        return Objects.equals(id, reunion.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public void remove(Reunion reunion) {
    }

    public void add(Reunion reunion) {
    }

}
