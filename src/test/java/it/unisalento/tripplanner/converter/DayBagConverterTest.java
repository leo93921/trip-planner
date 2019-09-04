package it.unisalento.tripplanner.converter;

import it.unisalento.tripplanner.dto.DayBag;
import it.unisalento.tripplanner.dto.RefType;
import it.unisalento.tripplanner.dto.TripStop;
import it.unisalento.tripplanner.model.DayBagModel;
import it.unisalento.tripplanner.model.TripStopModel;
import org.junit.Assert;
import org.junit.Test;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DayBagConverterTest {

    @Test
    public void convertToDto() {
        DayBagModel model = new DayBagModel();
        model.setDay(new Date());
        model.setId("_id");
        List<TripStopModel> stops = new ArrayList<>();
        TripStopModel stop = new TripStopModel();
        stop.setId("_id");
        stop.setRefId("ref_id");
        stop.setRefType(1);
        stop.setVisitOrder(2);
        stop.setVisitTime(LocalTime.of(12, 42));
        stop.setWarningPresent(true);
        stop.setWarningMessages(List.of("w_m_1", "w_m_2"));
        stops.add(stop);
        model.setTripStops(stops);

        DayBag dto = DayBagConverter.INSTANCE.toDto(model);

        Assert.assertEquals(model.getId(), dto.getId());
        Assert.assertEquals(model.getDay(), dto.getDay());
        Assert.assertEquals(model.getTripStops().size(), dto.getTripStops().size());
    }

    @Test
    public void convertToModel() {
        DayBag dto = new DayBag();
        dto.setDay(new Date());
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
        dto.setTripStops(stops);

        DayBagModel model = DayBagConverter.INSTANCE.toModel(dto);

        Assert.assertEquals(dto.getId(), dto.getId());
        Assert.assertEquals(dto.getDay(), dto.getDay());
        Assert.assertEquals(dto.getTripStops().size(), dto.getTripStops().size());
    }

}
