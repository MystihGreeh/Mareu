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
import static org.junit.Assert.assertTrue;

public class ReunionApiServiceTest {

    private ReunionApiService service;

    @Before
    public void setUp() {
        service = Injection.getNewInstanceApiService();
    }

    /**Test that the reunion are displayed
     * */
    @Test
    public void getReunionWithSuccess() {
        List<Reunion> reunions = service.getReunions();
        List<Reunion> expectedReunions = DummyReunionGenerator.DUMMY_REUNIONS;
        assertThat(reunions,IsIterableContainingInAnyOrder.containsInAnyOrder((expectedReunions.toArray())));
    }

    /**Test that a reunion can be deleted
     * */
    @Test
    public void deleteReunionWithSuccess() {
        Reunion reunionToDelete = service.getReunions().get(1);
        service.deleteReunion(reunionToDelete);
        assertFalse(service.getReunions().contains(reunionToDelete));
    }

    /**Test that a reunion can be added to the list
     * */
    @Test
    public void addNewReunionWithSuccess() {
        Reunion reunionToAdd = service.getReunions().get(0);
        service.addReunion(reunionToAdd);
    }

    /**Test that the reunions can be filtered
     * */
    @Test
    public void filterMeetingWithSuccess() {
        int testIndex = 1;
        Reunion reunionWanted = service.getReunions().get(testIndex);
        List<Reunion> filteredReunions = service.reunionListFilter(true, true, reunionWanted.getRoom(), reunionWanted.getDate());
        int nReunionTheSameDayAndRoom = 0;
        for (int i=0; i < service.getReunions().size(); i++) {
            if (service.getReunions().get(i).getRoom() != reunionWanted.getRoom())
                continue;
            nReunionTheSameDayAndRoom++;
        }
        assertTrue(filteredReunions.contains(reunionWanted));
        assertFalse(filteredReunions.size() > nReunionTheSameDayAndRoom);
    }

}
