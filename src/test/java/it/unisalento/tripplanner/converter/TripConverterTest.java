package it.unisalento.tripplanner.converter;

import it.unisalento.tripplanner.dto.DayBag;
import it.unisalento.tripplanner.dto.RefType;
import it.unisalento.tripplanner.dto.Trip;
import it.unisalento.tripplanner.dto.TripStop;
import it.unisalento.tripplanner.model.DayBagModel;
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
    }

    @Test
    public void convertToModel() {
        Trip dto = new Trip();
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
    }

}
