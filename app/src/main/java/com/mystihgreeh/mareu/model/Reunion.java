package com.mystihgreeh.mareu.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Objects;

public class Reunion implements Parcelable {


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
     * @param object
     * @param date
     * @param time
     * @param room
     * @param emails
     */
    public Reunion(String object, String date, String time, String room, String emails){
        this.room = room;
        this.date = date;
        this.time = time;
        this.object = object;
        this.emails = emails;
    }

    protected Reunion(Parcel in) {
        object = in.readString();
        date = in.readString();
        time = in.readString();
        room = in.readString();
        emails = in.readString();
    }

    public static final Creator<Reunion> CREATOR = new Creator<Reunion>() {
        @Override
        public Reunion createFromParcel(Parcel in) {
            return new Reunion(in);
        }

        @Override
        public Reunion[] newArray(int size) {
            return new Reunion[size];
        }
    };


    public String getObject() {
        return object;
    }
    public void setObject(String object) {
        this.object = object;
    }

    public String getDate() { return date;}
    public void setDate(String date) {this.date = date;}

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

            dest.writeString(object);
            dest.writeString(date);
            dest.writeString(time);
            dest.writeString(room);
            dest.writeString(emails);
    }

    public boolean completeReunion()
    {
        if (object != null && date != null && time !=null && room!= null && emails!= null)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

}
