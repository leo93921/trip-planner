package it.unisalento.tripplanner.service;

import it.unisalento.tripplanner.dto.Itinerary;
import it.unisalento.tripplanner.exception.ItineraryNotFoundException;
import it.unisalento.tripplanner.model.ItineraryModel;
import it.unisalento.tripplanner.repository.ItineraryRepository;
import it.unisalento.tripplanner.utils.PageBuilder;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.Page;
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
    private PageBuilder<ItineraryModel> builder;

    @Before
    public void setUp() {
        this.builder = new PageBuilder<>();

        this.service = new ItineraryService(
                new PageBuilder<>(),
                repository
        );
    }

    @Test
    public void shouldFindAllItineraries() {
        Page<ItineraryModel> p = getItineraryPage();

        when(repository.findAll(any(Pageable.class))).thenReturn(p);

        Page<Itinerary> page = service.findAll(0, 20);
        Assert.assertEquals(1, page.getTotalElements());
        Assert.assertEquals(1, page.getTotalPages());
    }

    private Page<ItineraryModel> getItineraryPage() {
        List<ItineraryModel> mockedList = new ArrayList<>();
        ItineraryModel itinerary = new ItineraryModel();
        itinerary.setId("_id");
        itinerary.setDescription("_description");
        mockedList.add(itinerary);

        return builder
                .setElements(mockedList)
                .setPage(PageRequest.of(0,20))
                .setTotalElements(1)
                .build();
    }

    @Test
    public void shouldDeleteItinerary() {
        doNothing().when(repository).delete(any());
        when(repository.findById(anyString())).thenReturn(Optional.of(new ItineraryModel()));
        boolean result = service.deleteByID("_id");
        Assert.assertTrue(result);
    }

    @Test(expected = ItineraryNotFoundException.class)
    public void shouldThrowExceptionForNotFoundItinerary() {
        when(repository.findById(anyString())).thenReturn(Optional.empty());
        service.deleteByID("_id");
    }

    @Test
    public void shouldUpdateItinerary() {
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
    public void shouldThrowExceptionForNotUpdating() {
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

    @Test
    public void shouldFindUserItineraries() {
        //when(repository.findByUserId(anyString(), any())).thenReturn(getItineraryPage());
        when(repository.findByUserId(any(), any())).thenReturn(getItineraryPage());

        Page<Itinerary> page = service.findByUserID("userId", 0, 20);

        Assert.assertNotNull(page);
        Assert.assertEquals(1, page.getContent().size());
        Assert.assertEquals("_id", page.getContent().get(0).getId());
        Assert.assertEquals("_description", page.getContent().get(0).getDescription());
    }
}
