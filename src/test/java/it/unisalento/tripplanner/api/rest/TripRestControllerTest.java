package it.unisalento.tripplanner.api.rest;

import it.unisalento.tripplanner.dto.Trip;
import it.unisalento.tripplanner.iservice.ITripService;
import it.unisalento.tripplanner.test.utils.TestUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
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
}
