package it.unisalento.tripplanner.api.rest;

import it.unisalento.tripplanner.dto.Itinerary;
import it.unisalento.tripplanner.dto.RefType;
import it.unisalento.tripplanner.dto.TripStop;
import it.unisalento.tripplanner.exception.ExceptionMessage;
import it.unisalento.tripplanner.exception.ItineraryNotFoundException;
import it.unisalento.tripplanner.iservice.IItineraryService;
import it.unisalento.tripplanner.test.utils.TestUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalTime;
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

    @Captor
    private ArgumentCaptor<Itinerary> capturedItineraries;

    private MockMvc mockMvc;

    @Before
    public void setUp() {
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

    @Test
    public void testSave() throws Exception {
        Itinerary dto = new Itinerary();
        dto.setDescription("_description");

        List<TripStop> stops = new ArrayList<>();
        TripStop stop = new TripStop();
        stop.setId("_id");
        stop.setRefId("ref_id");
        stop.setRefType(RefType.TYPE_EVENT);
        stop.setVisitOrder(2);
        stop.setVisitTime(LocalTime.of(12, 42));
        stop.setWarningPresent(true);
        stop.setWarningMessages(List.of("w_m_1", "w_m_2"));
        stops.add(stop);
        dto.setStops(stops);

        when(service.save(ArgumentMatchers.any(Itinerary.class))).thenReturn(dto);

        Itinerary toSend = new Itinerary();
        mockMvc.perform(post("/api/itinerary")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(TestUtils.toJson(toSend)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.description", is("_description")))
                .andExpect(jsonPath("$.stops", hasSize(1)))
                .andExpect(jsonPath("$.stops[0].id", is("_id")))
                .andExpect(jsonPath("$.stops[0].refId", is("ref_id")))
                .andExpect(jsonPath("$.stops[0].refType", is("TYPE_EVENT")))
                .andExpect(jsonPath("$.stops[0].visitOrder", is(2)))
                .andExpect(jsonPath("$.stops[0].warningPresent", is(true)))
                .andExpect(jsonPath("$.stops[0].warningMessages", hasSize(2)))
                .andExpect(jsonPath("$.stops[0].warningMessages[0]", is("w_m_1")))
                .andExpect(jsonPath("$.stops[0].warningMessages[1]", is("w_m_2")))
                .andExpect(jsonPath("$.stops[0].warningMessages[0]", is("w_m_1")));

        verify(service, times(1)).save(capturedItineraries.capture());
        verifyNoMoreInteractions(service);
    }

    @Test
    public void testDeleteOK() throws Exception {
        when(service.deleteByID(anyString())).thenReturn(true);

        mockMvc.perform(delete("/api/itinerary/{id}", "_id"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", is(true)));
        verify(service, times(1)).deleteByID("_id");
        verifyNoMoreInteractions(service);
    }

    @Test
    public void testDelete404() throws Exception {
        when(service.deleteByID(anyString())).thenThrow(ItineraryNotFoundException.class);

        mockMvc.perform(delete("/api/itinerary/{id}", "_id"))
                .andExpect(status().isNotFound());

        verify(service, times(1)).deleteByID("_id");
        verifyNoMoreInteractions(service);
    }

}
