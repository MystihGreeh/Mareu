package com.mystihgreeh.mareu.events;

import com.mystihgreeh.mareu.model.Reunion;

/**
 * Event fired when a user deletes a Reunion
 */
public class DeleteReunionEvent {

    /**
     * Reunion to delete
     */
    public Reunion reunion;

    /**
     * Constructor.
     * @param reunion
     */
    public DeleteReunionEvent(Reunion reunion) {
        this.reunion = reunion;
    }
}
