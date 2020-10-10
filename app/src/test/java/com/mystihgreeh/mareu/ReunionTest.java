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
        Reunion reunionToDelete = service.getReunions().get(1);
        service.deleteReunion(reunionToDelete);
        assertFalse(service.getReunions().contains(reunionToDelete));
    }

    @Test
    public void addReunionWithSuccess() {
        Reunion reunionToAdd = service.getReunions().get(0);
        service.addReunion(reunionToAdd);
        assertTrue(service.getReunions().contains(reunionToAdd));
    }


    @Test
    public void filterByRoomWithSuccess() {
        int testIndex = 1;
        Reunion reunionWanted = service.getReunions().get(testIndex);
        List<Reunion> filteredReunions = service.reunionListFilter(false, true, reunionWanted.getRoom(), reunionWanted.getDate());
        int nReunionTheSameRoom = 0;
        for (int i=0; i < service.getReunions().size(); i++) {
            if (!service.getReunions().get(i).getRoom().equals(reunionWanted.getRoom()))
                continue;
            nReunionTheSameRoom++;
        }
        assertTrue(filteredReunions.contains(reunionWanted));
        assertFalse(filteredReunions.size() > nReunionTheSameRoom);
    }

    @Test
    public void filterByDateWithSuccess() {
        int testIndex = 1;
        Reunion reunionWanted = service.getReunions().get(testIndex);
        List<Reunion> filteredReunions = service.reunionListFilter(true, false, reunionWanted.getRoom(), reunionWanted.getDate());
        int nReunionTheSameDate = 0;
        for (int i=0; i < service.getReunions().size(); i++) {
            if (!service.getReunions().get(i).getRoom().equals(reunionWanted.getRoom()))
                continue;
            nReunionTheSameDate++;
        }
        assertTrue(filteredReunions.contains(reunionWanted));
        assertFalse(filteredReunions.size() > nReunionTheSameDate);
    }

    @Test
    public void filterMeetingWithSuccess() {
        int testIndex = 1;
        Reunion reunionWanted = service.getReunions().get(testIndex);
        List<Reunion> filteredReunions = service.reunionListFilter(true, true, reunionWanted.getRoom(), reunionWanted.getDate());
        int nReunionTheSameDayAndRoom = 0;
        for (int i=0; i < service.getReunions().size(); i++) {
            if (!service.getReunions().get(i).getRoom().equals(reunionWanted.getRoom()))
                continue;
            nReunionTheSameDayAndRoom++;
        }
        assertTrue(filteredReunions.contains(reunionWanted));
        assertFalse(filteredReunions.size() > nReunionTheSameDayAndRoom);
    }


}
