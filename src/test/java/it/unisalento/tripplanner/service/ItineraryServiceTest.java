package it.unisalento.tripplanner.service;

import it.unisalento.tripplanner.dto.Itinerary;
import it.unisalento.tripplanner.iservice.IItineraryService;
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

import static org.mockito.ArgumentMatchers.any;
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
}