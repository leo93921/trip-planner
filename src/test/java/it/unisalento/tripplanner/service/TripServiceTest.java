package it.unisalento.tripplanner.service;

import it.unisalento.tripplanner.dto.Trip;
import it.unisalento.tripplanner.exception.TripNotFoundException;
import it.unisalento.tripplanner.model.TripModel;
import it.unisalento.tripplanner.repository.TripRepository;
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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class TripServiceTest {

    @Mock TripRepository repository;
    @InjectMocks TripService service;
    @Captor ArgumentCaptor<TripModel> modelsCaptor;
    @Captor ArgumentCaptor<Pageable> pageCaptor;
    private SimpleDateFormat formatter = new SimpleDateFormat("yyyy-mm-dd'T'hh:mm:ss.SSS");
    private PageBuilder<TripModel> pageBuilder;

    @Before
    public void setUp() {
        this.pageBuilder = new PageBuilder<>();
        this.service = new TripService(
                repository,
                new PageBuilder<>()
        );
    }

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

    @Test
    public void shouldFindAllTrips() throws ParseException {
        when(repository.findAll(any(Pageable.class))).thenReturn(getPage());

        Date expectedDate = formatter.parse("2019-09-10T12:06:29.143");

        Page<Trip> tripPage = service.findAll(0, 25);
        Assert.assertEquals(1, tripPage.getContent().size());
        Assert.assertTrue(tripPage.isLast());
        Assert.assertEquals("_id", tripPage.getContent().get(0).getId());
        Assert.assertEquals("_title", tripPage.getContent().get(0).getTitle());
        Assert.assertEquals(15.76f, tripPage.getContent().get(0).getMaxBudget(), 1e-7);
        Assert.assertEquals(4, tripPage.getContent().get(0).getBudgetLevel(), 1);
        Assert.assertEquals(expectedDate, tripPage.getContent().get(0).getCreationDate());
        Assert.assertEquals(expectedDate, tripPage.getContent().get(0).getUpdateDate());
        Assert.assertEquals(expectedDate, tripPage.getContent().get(0).getDeleteDate());
        Assert.assertEquals("user_id", tripPage.getContent().get(0).getUserId());

        verify(repository, times(1)).findAll(pageCaptor.capture());
        verifyNoMoreInteractions(repository);
    }

    @Test
    public void shouldUpdateTrips() throws ParseException {
        when(repository.findById(anyString())).thenReturn(Optional.of(getModel()));
        when(repository.save(any())).thenReturn(getModel());

        Trip toUpdate = new Trip();
        toUpdate.setId("id");
        Trip saved = service.updateTrip(toUpdate);

        Date expectedDate = formatter.parse("2019-09-10T12:06:29.143");

        Assert.assertEquals("_id", saved.getId());
        Assert.assertEquals("_title", saved.getTitle());
        Assert.assertEquals(15.76f, saved.getMaxBudget(), 1e-7);
        Assert.assertEquals(4, saved.getBudgetLevel(), 1);
        Assert.assertEquals(expectedDate, saved.getCreationDate());
        Assert.assertEquals(expectedDate, saved.getUpdateDate());
        Assert.assertEquals(expectedDate, saved.getDeleteDate());
        Assert.assertEquals("user_id", saved.getUserId());

        verify(repository, times(1)).findById("id");
        verify(repository, times(1)).save(modelsCaptor.capture());
        verifyNoMoreInteractions(repository);
    }

    @Test(expected = TripNotFoundException.class)
    public void shouldNotUpdateTripsWithoutID() {
        service.updateTrip(new Trip());
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

    private Page<TripModel> getPage() throws ParseException {
        List<TripModel> models = new ArrayList<>();
        models.add(getModel());

        return pageBuilder
                .setElements(models)
                .setTotalElements(1)
                .setPage(PageRequest.of(0, 25))
                .build();
    }
}
