package it.unisalento.tripplanner.api.rest;

import it.unisalento.tripplanner.dto.Itinerary;
import it.unisalento.tripplanner.iservice.IItineraryService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(MockitoJUnitRunner.class)
public class ItineraryRestControllerTest {

    @Mock
    IItineraryService service;

    @InjectMocks
    ItineraryRestController controller;

    private MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    public void testFindAll() throws Exception {
        List<Itinerary> mockedList = new ArrayList<>();
        Itinerary itinerary = new Itinerary();
        itinerary.setId("_id");
        itinerary.setDescription("_description");
        mockedList.add(itinerary);
        Page<Itinerary> p = new PageImpl<>(mockedList, PageRequest.of(0,20), 1);
        when(service.findAll(ArgumentMatchers.any(Integer.class), ArgumentMatchers.any(Integer.class)))
                .thenReturn(p);

        mockMvc.perform(get("/api/itinerary/{page}/{size}",0, 20))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content", hasSize(1)))
                .andExpect(jsonPath("$.content[0].id", is("_id")))
                .andExpect(jsonPath("$.totalElements", is(1)))
                .andExpect(jsonPath("$.totalPages", is(1)))
                .andExpect(jsonPath("$.last", is(true)));

        verify(service, times(1)).findAll(0, 20);
        verifyNoMoreInteractions(service);
    }
}
