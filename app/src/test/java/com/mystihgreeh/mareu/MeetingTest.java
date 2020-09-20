package com.mystihgreeh.mareu;

import com.mystihgreeh.mareu.DI.Injection;
import com.mystihgreeh.mareu.model.Reunion;
import com.mystihgreeh.mareu.service.DummyReunionGenerator;
import com.mystihgreeh.mareu.service.ReunionApiService;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;

public class MeetingTest {
    private ReunionApiService service;

    @Before
    public void setup() {
        service = Injection.getNewInstanceApiService();
    }

    @Test
    public void getNeighboursWithSuccess() {
        List<Reunion> neighbours = service.getReunions();
        List<Reunion> expectedNeighbours = DummyReunionGenerator.DUMMY_REUNIONS;
        assertThat(neighbours, IsIterableContainingInAnyOrder.containsInAnyOrder(expectedNeighbours.toArray()));
    }

    @Test
    public void deleteNeighbourWithSuccess() {
        Reunion neighbourToDelete = service.getReunions().get(0);
        service.deleteReunion(neighbourToDelete);
        assertFalse(service.getReunions().contains(neighbourToDelete));
    }
}
