package com.mystihgreeh.mareu.controller;

import com.mystihgreeh.mareu.service.DummyReunionApiService;
import com.mystihgreeh.mareu.service.ReunionApiService;

public class DI {

    private static ReunionApiService service = new DummyReunionApiService();

    /**
     * Get an instance on @{@link ReunionApiService}
     * @return
     */
    public static ReunionApiService getReunionApiService() {
        return service;
    }

    /**
     * Get always a new instance on @{@link ReunionApiService}. Useful for tests, so we ensure the context is clean.
     * @return
     */
    public static ReunionApiService getNewInstanceApiService() {
        return new DummyReunionApiService();
    }
}
