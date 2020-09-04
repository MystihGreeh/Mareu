package com.mystihgreeh.mareu.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Objects;

public class Room {
    /**
     * Room's name
     */
    private String mName;

    /**
     * Color
     */
    private int mColor;

    public Room(String mName, int mColor) {
        this.mName = mName;
        this.mColor = mColor;
    }


    public String getmName() {
        return mName;
    }
    public void setmName(String mName) {
        this.mName = mName;
    }

    public int getmColor() {
        return mColor;
    }
    public void setmColor(int mColor) {
        this.mColor = mColor;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Room room = (Room) o;
        return Objects.equals(getmName(), room.getmName());
    }

    @Override
    public String toString(){
        return mName;
    }

}
