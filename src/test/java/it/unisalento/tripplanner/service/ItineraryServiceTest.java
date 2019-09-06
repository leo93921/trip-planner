package it.unisalento.tripplanner.service;

import it.unisalento.tripplanner.dto.Itinerary;
import it.unisalento.tripplanner.exception.ItineraryNotFoundException;
import it.unisalento.tripplanner.model.ItineraryModel;
import it.unisalento.tripplanner.repository.ItineraryRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ItineraryServiceTest {

    @Mock
    private ItineraryRepository repository;
    @InjectMocks
    private ItineraryService service;

    @Test
    public void findAll() {
        List<ItineraryModel> mockedList = new ArrayList<>();
        ItineraryModel itinerary = new ItineraryModel();
        itinerary.setId("_id");
        itinerary.setDescription("_description");
        mockedList.add(itinerary);
        Page<ItineraryModel> p = new PageImpl<>(mockedList, PageRequest.of(0,20), 1);

        when(repository.findAll(any(Pageable.class))).thenReturn(p);

        Page<Itinerary> page = service.findAll(0, 20);
        Assert.assertEquals(1, page.getTotalElements());
        Assert.assertEquals(1, page.getTotalPages());
    }

    @Test
    public void deleteOK() {
        doNothing().when(repository).delete(any());
        when(repository.findById(anyString())).thenReturn(Optional.of(new ItineraryModel()));
        boolean result = service.deleteByID("_id");
        Assert.assertTrue(result);
    }

    @Test(expected = ItineraryNotFoundException.class)
    public void deleteNotFound() {
        when(repository.findById(anyString())).thenReturn(Optional.empty());
        service.deleteByID("_id");
    }

    @Test
    public void updateOK() {
        ItineraryModel itinerary = new ItineraryModel();
        itinerary.setId("_id");
        itinerary.setDescription("_description");

        when(repository.findById(anyString())).thenReturn(Optional.of(new ItineraryModel()));
        when(repository.save(any())).thenReturn(itinerary);

        Itinerary toSend = new Itinerary();
        toSend.setId("abs");
        Itinerary updated = service.update(toSend);

        Assert.assertEquals(itinerary.getId(), updated.getId());
        Assert.assertEquals(itinerary.getDescription(), updated.getDescription());
    }

    @Test(expected = ItineraryNotFoundException.class)
    public void updateNotFound() {
        when(repository.findById(any())).thenReturn(Optional.empty());
        service.update(new Itinerary());
    }

    @Test
    public void shouldFindItineraryByID() {
        ItineraryModel itinerary = new ItineraryModel();
        itinerary.setId("_id");
        itinerary.setDescription("_description");

        when(repository.findById("_id")).thenReturn(Optional.of(itinerary));

        Itinerary found = service.findByID("_id");

        Assert.assertNotNull(found);
        Assert.assertEquals(itinerary.getId(), found.getId());
        Assert.assertEquals(itinerary.getDescription(), found.getDescription());
    }

    @Test(expected = ItineraryNotFoundException.class)
    public void shouldThrowExceptionForNotExistingItinerary(){
        when(repository.findById(anyString())).thenReturn(Optional.empty());

        service.findByID("_id");
    }
}
