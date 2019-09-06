package it.unisalento.tripplanner.api.rest;

import it.unisalento.tripplanner.dto.Itinerary;
import it.unisalento.tripplanner.dto.RefType;
import it.unisalento.tripplanner.dto.TripStop;
import it.unisalento.tripplanner.exception.ItineraryNotFoundException;
import it.unisalento.tripplanner.iservice.IItineraryService;
import it.unisalento.tripplanner.test.utils.TestUtils;
import it.unisalento.tripplanner.utils.PageBuilder;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.Page;
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
    private PageBuilder<Itinerary> builder;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
        this.builder = new PageBuilder<>();
    }

    @Test
    public void shouldFindAll() throws Exception {
        when(service.findAll(ArgumentMatchers.any(Integer.class), ArgumentMatchers.any(Integer.class)))
                .thenReturn(getItineraryPage());

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

    private Page<Itinerary> getItineraryPage() {
        List<Itinerary> mockedList = new ArrayList<>();
        Itinerary itinerary = new Itinerary();
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
    public void shouldSave() throws Exception {
        Itinerary dto = createDto();

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
    public void shouldDelete() throws Exception {
        when(service.deleteByID(anyString())).thenReturn(true);

        mockMvc.perform(delete("/api/itinerary/{id}", "_id"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", is(true)));
        verify(service, times(1)).deleteByID("_id");
        verifyNoMoreInteractions(service);
    }

    @Test
    public void shouldNotDeleteNotFoundItinerary() throws Exception {
        when(service.deleteByID(anyString())).thenThrow(ItineraryNotFoundException.class);

        mockMvc.perform(delete("/api/itinerary/{id}", "_id"))
                .andExpect(status().isNotFound());

        verify(service, times(1)).deleteByID("_id");
        verifyNoMoreInteractions(service);
    }

    @Test
    public void shouldUpdate() throws Exception {
        Itinerary dto = createDto();

        when(service.update(any())).thenReturn(dto);

        Itinerary toSend = new Itinerary();
        mockMvc.perform(put("/api/itinerary")
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

        verify(service, times(1)).update(capturedItineraries.capture());
        verifyNoMoreInteractions(service);
    }

    @Test
    public void shouldNotUpdateNotFoundItinerary() throws Exception {
        when(service.update(any())).thenThrow(ItineraryNotFoundException.class);

        mockMvc.perform(put("/api/itinerary")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(TestUtils.toJson(new Itinerary())))
                .andExpect(status().isNotFound());
        verify(service, times(1)).update(capturedItineraries.capture());
        verifyNoMoreInteractions(service);
    }

    private Itinerary createDto() {
        Itinerary dto = new Itinerary();
        dto.setDescription("_description");
        dto.setId("_id");

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
        return dto;
    }

    @Test
    public void shouldFindItinerary() throws Exception {
        when(service.findByID(anyString())).thenReturn(createDto());

        mockMvc.perform(get("/api/itinerary/{id}", "_id"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.id", is("_id")))
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

        verify(service, times(1)).findByID("_id");
        verifyNoMoreInteractions(service);
    }

    @Test
    public void shouldNotFindItinerary() throws Exception {
        when(service.findByID(anyString())).thenThrow(ItineraryNotFoundException.class);

        mockMvc.perform(get("/api/itinerary/{id}", "_id"))
                .andExpect(status().isNotFound());

        verify(service, times(1)).findByID("_id");
        verifyNoMoreInteractions(service);
    }

    @Test
    public void shouldFindUserItineraries() throws Exception {
        when(service.findByUserID(anyString(), anyInt(), anyInt()))
                .thenReturn(getItineraryPage());

        mockMvc
                .perform(get("/api/itinerary/by-user/{id}/{page}/{size}", "userId", 0, 20))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.content", hasSize(1)))
                .andExpect(jsonPath("$.content[0].id", is("_id")))
                .andExpect(jsonPath("$.totalElements", is(1)))
                .andExpect(jsonPath("$.totalPages", is(1)))
                .andExpect(jsonPath("$.last", is(true)));

        verify(service, times(1)).findByUserID("userId", 0, 20);
        verifyNoMoreInteractions(service);
    }

}
