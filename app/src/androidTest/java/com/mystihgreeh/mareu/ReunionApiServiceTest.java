package com.mystihgreeh.mareu;

import com.mystihgreeh.mareu.DI.Injection;
import com.mystihgreeh.mareu.model.Reunion;
import com.mystihgreeh.mareu.service.DummyReunionGenerator;
import com.mystihgreeh.mareu.service.ReunionApiService;

import org.hamcrest.collection.IsIterableContainingInAnyOrder;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;

public class ReunionApiServiceTest {

    private ReunionApiService service;

    @Before
    public void setUp() {
        service = Injection.getNewInstanceApiService();
    }

    //Test if the reunion list display
    @Test
    public void getReunionWithSuccess() {
        List<Reunion> reunions = service.getReunions();
        List<Reunion> expectedReunions = DummyReunionGenerator.DUMMY_REUNIONS;
        assertThat(reunions,IsIterableContainingInAnyOrder.containsInAnyOrder((expectedReunions.toArray())));
    }

    //Test if a reunion can be deleted
    @Test
    public void deleteReunionWithSuccess() {
        Reunion reunionToDelete = service.getReunions().get(0);
        service.deleteReunion(reunionToDelete);
        assertFalse(service.getReunions().contains(reunionToDelete));
    }

    //Test if new reunion can be created and added to the list
    @Test
    public void createNewReunionWithSuccess() {

    }

    //Test if the filters works
    @Test
    public void filterReunionWithSuccess() {

    }
}
