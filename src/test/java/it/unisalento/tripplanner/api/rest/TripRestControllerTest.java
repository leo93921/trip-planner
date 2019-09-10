package it.unisalento.tripplanner.api.rest;

import it.unisalento.tripplanner.dto.Trip;
import it.unisalento.tripplanner.exception.TripNotFoundException;
import it.unisalento.tripplanner.iservice.ITripService;
import it.unisalento.tripplanner.test.utils.TestUtils;
import it.unisalento.tripplanner.utils.PageBuilder;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(MockitoJUnitRunner.class)
public class TripRestControllerTest {

    @Mock
    private ITripService service;

    @InjectMocks
    private TripRestController controller;

    @Captor
    private ArgumentCaptor<Trip> tripCaptor;

    private MockMvc mockMvc;
    private SimpleDateFormat formatter = new SimpleDateFormat("yyyy-mm-dd'T'hh:mm:ss.SSS");
    private PageBuilder<Trip> pageBuilder;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();

        pageBuilder = new PageBuilder<>();
    }

    @Test
    public void shouldSaveTrip() throws Exception {
        when(service.saveTrip(any())).thenReturn(getTrip());

        mockMvc.perform(
                post("/api/trip")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(TestUtils.toJson(getTrip()))
        )
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.id", is("_id")))
                .andExpect(jsonPath("$.title", is("_title")))
                .andExpect(jsonPath("$.maxBudget", is(15.76)))
                .andExpect(jsonPath("$.budgetLevel", is(4)))
                .andExpect(jsonPath("$.userId", is("user_id")))
                .andExpect(jsonPath("$.creationDate", is(1547075189143L)))
                .andExpect(jsonPath("$.updateDate", is(1547075189143L)))
                .andExpect(jsonPath("$.deleteDate", is(1547075189143L)));

        verify(service, times(1)).saveTrip(tripCaptor.capture());
        verifyNoMoreInteractions(service);
    }

    @Test
    public void shouldFindAllTrips() {
        try {
            when(service.findAll(any(), any())).thenReturn(getPage());

            mockMvc.perform(
                    get("/api/trip/{page}/{size}", 0, 25)
            )
                    .andExpect(status().isOk())
                    .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                    .andExpect(jsonPath("$.content", hasSize(1)))
                    .andExpect(jsonPath("$.content[0].id", is("_id")))
                    .andExpect(jsonPath("$.content[0].title", is("_title")))
                    .andExpect(jsonPath("$.content[0].maxBudget", is(15.76)))
                    .andExpect(jsonPath("$.content[0].budgetLevel", is(4)))
                    .andExpect(jsonPath("$.content[0].userId", is("user_id")))
                    .andExpect(jsonPath("$.content[0].creationDate", is(1547075189143L)))
                    .andExpect(jsonPath("$.content[0].updateDate", is(1547075189143L)))
                    .andExpect(jsonPath("$.content[0].deleteDate", is(1547075189143L)));
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail();
        }
    }

    @Test
    public void shouldUpdateTrips() throws Exception {
        when(service.updateTrip(any())).thenReturn(getTrip());

        mockMvc.perform(
                put("/api/trip")
                        .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                        .content(TestUtils.toJson(new Trip()))
        )
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.id", is("_id")))
                .andExpect(jsonPath("$.title", is("_title")))
                .andExpect(jsonPath("$.maxBudget", is(15.76)))
                .andExpect(jsonPath("$.budgetLevel", is(4)))
                .andExpect(jsonPath("$.userId", is("user_id")))
                .andExpect(jsonPath("$.creationDate", is(1547075189143L)))
                .andExpect(jsonPath("$.updateDate", is(1547075189143L)))
                .andExpect(jsonPath("$.deleteDate", is(1547075189143L)));

        verify(service, times(1)).updateTrip(tripCaptor.capture());
        verifyNoMoreInteractions(service);
    }

    @Test
    public void shouldNotUpdateTripWithoutID() throws Exception {
        when(service.updateTrip(any())).thenThrow(new TripNotFoundException());

        mockMvc.perform(
                put("/api/trip")
                        .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                        .content(TestUtils.toJson(new Trip()))
        )
                .andExpect(status().isNotFound());

        verify(service, times(1)).updateTrip(tripCaptor.capture());
        verifyNoMoreInteractions(service);
    }

    @Test
    public void shouldFindATrip() throws Exception {
        when(service.findByID(anyString())).thenReturn(getTrip());

        mockMvc.perform(
                get("/api/trip/{id}", "_id")
        )
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.id", is("_id")))
                .andExpect(jsonPath("$.title", is("_title")))
                .andExpect(jsonPath("$.maxBudget", is(15.76)))
                .andExpect(jsonPath("$.budgetLevel", is(4)))
                .andExpect(jsonPath("$.userId", is("user_id")))
                .andExpect(jsonPath("$.creationDate", is(1547075189143L)))
                .andExpect(jsonPath("$.updateDate", is(1547075189143L)))
                .andExpect(jsonPath("$.deleteDate", is(1547075189143L)));

        verify(service, times(1)).findByID("_id");
        verifyNoMoreInteractions(service);
    }

    @Test
    public void shouldNotFindATrip() throws Exception {
        when(service.findByID(anyString())).thenThrow(TripNotFoundException.class);
        mockMvc.perform(
                get("/api/trip/{id}", "_id")
        ).andExpect(status().isNotFound());
        verify(service, times(1)).findByID("_id");
        verifyNoMoreInteractions(service);
    }

    @Test
    public void shouldSoftDeleteTrips() throws Exception {
        when(service.deleteByID(anyString())).thenReturn(true);

        mockMvc.perform(
                delete("/api/trip/{id}", "id")
        )
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$", is(true)));

        verify(service, times(1)).deleteByID(any());
        verifyNoMoreInteractions(service);
    }

    @Test
    public void shouldNotDeleteNotFoundTrips() throws Exception {
        when(service.deleteByID(anyString())).thenThrow(TripNotFoundException.class);

        mockMvc.perform(
                delete("/api/trip/{id}", "id")
        ).andExpect(status().isNotFound());

        verify(service, times(1)).deleteByID(any());
        verifyNoMoreInteractions(service);
    }

    private Trip getTrip() throws ParseException {
        Trip trip = new Trip();
        trip.setId("_id");
        trip.setTitle("_title");
        trip.setMaxBudget(15.76f);
        trip.setBudgetLevel(4);
        Date date = formatter.parse("2019-09-10T12:06:29.143");
        trip.setCreationDate(date);
        trip.setUpdateDate(date);
        trip.setDeleteDate(date);
        trip.setUserId("user_id");
        return trip;
    }

    private Page<Trip> getPage() throws ParseException {
        List<Trip> trips = new ArrayList<>();
        trips.add(getTrip());

        return pageBuilder
                .setElements(trips)
                .setTotalElements(1)
                .setPage(PageRequest.of(0, 25))
                .build();

    }
}
