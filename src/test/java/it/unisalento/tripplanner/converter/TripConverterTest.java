package it.unisalento.tripplanner.converter;

import it.unisalento.tripplanner.dto.*;
import it.unisalento.tripplanner.model.DayBagModel;
import it.unisalento.tripplanner.model.LocationModel;
import it.unisalento.tripplanner.model.TripModel;
import it.unisalento.tripplanner.model.TripStopModel;
import org.glassfish.jersey.internal.util.collection.Ref;
import org.junit.Assert;
import org.junit.Test;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TripConverterTest {

    @Test
    public void convertToDto() {
        TripModel model = new TripModel();
        model.setId("__id");
        model.setTitle("_title");
        model.setMaxBudget(100f);
        model.setBudgetLevel(3);
        model.setCreationDate(new Date());
        model.setUpdateDate(new Date());
        model.setDeleteDate(new Date());
        model.setStartPoint(new LocationModel(1.23, 4.54));
        model.setEndPoint(new LocationModel(32.12, 43.222));
        model.setUserId("_user_id");

        List<DayBagModel> dayBags = new ArrayList<>();
        DayBagModel bag = new DayBagModel();
        bag.setDay(new Date());
        bag.setId("_id");
        List<TripStopModel> stops = new ArrayList<>();
        TripStopModel stop = new TripStopModel();
        stop.setId("_id");
        stop.setRefId("ref_id");
        stop.setRefType(0);
        stop.setVisitOrder(2);
        stop.setVisitTime(LocalTime.of(12, 42));
        stop.setWarningPresent(true);
        stop.setWarningMessages(List.of("w_m_1", "w_m_2"));
        stops.add(stop);
        bag.setTripStops(stops);
        model.setDayBags(dayBags);

        Trip dto = TripConverter.INSTANCE.toDto(model);

        Assert.assertEquals(model.getDayBags().size(), dto.getDayBags().size());
        Assert.assertEquals(model.getId(), dto.getId());
        Assert.assertEquals(model.getTitle(), dto.getTitle());
        Assert.assertEquals(model.getMaxBudget(), dto.getMaxBudget());
        Assert.assertEquals(model.getBudgetLevel(), dto.getBudgetLevel());
        Assert.assertEquals(model.getCreationDate(), dto.getCreationDate());
        Assert.assertEquals(model.getUpdateDate(), dto.getUpdateDate());
        Assert.assertEquals(model.getDeleteDate(), dto.getDeleteDate());
        Assert.assertEquals(model.getStartPoint().getLongitude(), dto.getStartPoint().getLongitude(), 1e-7);
        Assert.assertEquals(model.getStartPoint().getLatitude(), dto.getStartPoint().getLatitude(), 1e-7);
        Assert.assertEquals(model.getEndPoint().getLongitude(), dto.getEndPoint().getLongitude(), 1e-7);
        Assert.assertEquals(model.getEndPoint().getLatitude(), dto.getEndPoint().getLatitude(), 1e-7);
        Assert.assertEquals(model.getUserId(), dto.getUserId());

    }

    @Test
    public void convertToModel() {
        Trip dto = new Trip();
        dto.setId("__id");
        dto.setTitle("_title");
        dto.setMaxBudget(100f);
        dto.setBudgetLevel(3);
        dto.setCreationDate(new Date());
        dto.setUpdateDate(new Date());
        dto.setDeleteDate(new Date());
        dto.setStartPoint(new Location(1.23, 4.54));
        dto.setEndPoint(new Location(32.12, 43.222));
        dto.setUserId("_user_id");

        List<DayBag> dayBags = new ArrayList<>();
        DayBag bag = new DayBag();
        bag.setDay(new Date());
        bag.setId("_id");
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
        bag.setTripStops(stops);
        dto.setDayBags(dayBags);

        TripModel model = TripConverter.INSTANCE.toModel(dto);

        Assert.assertEquals(dto.getDayBags().size(), model.getDayBags().size());
        Assert.assertEquals(dto.getId(), model.getId());
        Assert.assertEquals(dto.getTitle(), model.getTitle());
        Assert.assertEquals(dto.getMaxBudget(), model.getMaxBudget());
        Assert.assertEquals(dto.getBudgetLevel(), model.getBudgetLevel());
        Assert.assertEquals(dto.getCreationDate(), model.getCreationDate());
        Assert.assertEquals(dto.getUpdateDate(), model.getUpdateDate());
        Assert.assertEquals(dto.getDeleteDate(), model.getDeleteDate());
        Assert.assertEquals(dto.getStartPoint().getLongitude(), model.getStartPoint().getLongitude(), 1e-7);
        Assert.assertEquals(dto.getStartPoint().getLatitude(), model.getStartPoint().getLatitude(), 1e-7);
        Assert.assertEquals(dto.getEndPoint().getLongitude(), model.getEndPoint().getLongitude(), 1e-7);
        Assert.assertEquals(dto.getEndPoint().getLatitude(), model.getEndPoint().getLatitude(), 1e-7);
        Assert.assertEquals(dto.getUserId(), model.getUserId());
    }

}
