package it.unisalento.tripplanner.service;

import it.unisalento.tripplanner.dto.Trip;
import it.unisalento.tripplanner.model.TripModel;
import it.unisalento.tripplanner.repository.TripRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class TripSeriveTest {

    @Mock TripRepository repository;
    @InjectMocks TripService service;
    @Captor ArgumentCaptor<TripModel> modelsCaptor;
    private SimpleDateFormat formatter = new SimpleDateFormat("yyyy-mm-dd'T'hh:mm:ss.SSS");


    @Test
    public void shouldSaveTrip() throws ParseException {
        when(repository.save(any())).thenReturn(getModel());

        Trip saved = service.saveTrip(new Trip());

        Date expectedDate = formatter.parse("2019-09-10T12:06:29.143");

        Assert.assertEquals("_id", saved.getId());
        Assert.assertEquals("_title", saved.getTitle());
        Assert.assertEquals(15.76f, saved.getMaxBudget(), 1e-7);
        Assert.assertEquals(4, saved.getBudgetLevel(), 1);
        Assert.assertEquals(expectedDate, saved.getCreationDate());
        Assert.assertEquals(expectedDate, saved.getUpdateDate());
        Assert.assertEquals(expectedDate, saved.getDeleteDate());
        Assert.assertEquals("user_id", saved.getUserId());

        verify(repository, times(1)).save(modelsCaptor.capture());
        verifyNoMoreInteractions(repository);
    }

    private TripModel getModel() throws ParseException {
        TripModel model = new TripModel();
        model.setId("_id");
        model.setTitle("_title");
        model.setMaxBudget(15.76f);
        model.setBudgetLevel(4);
        Date date = formatter.parse("2019-09-10T12:06:29.143");
        model.setCreationDate(date);
        model.setUpdateDate(date);
        model.setDeleteDate(date);
        model.setUserId("user_id");

        return model;
    }

}
