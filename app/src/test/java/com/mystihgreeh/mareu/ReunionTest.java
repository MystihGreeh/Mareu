package com.mystihgreeh.mareu;

import com.mystihgreeh.mareu.DI.Injection;
import com.mystihgreeh.mareu.model.Reunion;
import com.mystihgreeh.mareu.service.DummyReunionGenerator;
import com.mystihgreeh.mareu.service.ReunionApiService;

import org.hamcrest.collection.IsIterableContainingInAnyOrder;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.Objects;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

;

public class ReunionTest {

    private ReunionApiService service;
    @Before
    public void setup() {
        service = Injection.getNewInstanceApiService();
    }

    @Test
    public void getReunionWithSuccess() {
        List<Reunion> reunion = service.getReunions();
        List<Reunion> expectedReunion = DummyReunionGenerator.DUMMY_REUNIONS;
        assertThat(reunion, IsIterableContainingInAnyOrder.containsInAnyOrder(Objects.requireNonNull(expectedReunion.toArray())));
    }

    @Test
    public void deleteReunionWithSuccess() {
        Reunion reunionToDelete = service.getReunions().get(0);
        service.deleteReunion(reunionToDelete);
        assertFalse(service.getReunions().contains(reunionToDelete));
    }

    @Test
    public void addReunionWithSuccess() {
        Reunion reunionToAdd = service.getReunions().get(0);
        service.addReunion(reunionToAdd);
        assertTrue(service.getReunions().contains(reunionToAdd));
    }


}
