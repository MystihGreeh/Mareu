package com.mystihgreeh.mareu.DI;

import com.mystihgreeh.mareu.service.DummyReunionApiService;
import com.mystihgreeh.mareu.service.DummyRoomApiService;
import com.mystihgreeh.mareu.service.ReunionApiService;
import com.mystihgreeh.mareu.service.RoomApiService;

public class Injection {

    private static ReunionApiService service = new DummyReunionApiService();
    private static RoomApiService roomService = new DummyRoomApiService();

    /**
     * Get an instance on @{@link ReunionApiService}
     * @return
     */
    public static ReunionApiService getReunionApiService() {
        return service;
    }

    /**
     * Get an instance on @{@link RoomApiService}
     * @return
     */
    public static RoomApiService getRoomApiService() {
        return roomService;
    }

    /**
     * Get always a new instance on @{@link ReunionApiService}. Useful for tests, so we ensure the context is clean.
     * @return
     */
    public static ReunionApiService getNewInstanceApiService() {
        return new DummyReunionApiService();
    }
}
