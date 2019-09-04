package it.unisalento.tripplanner.converter;

import it.unisalento.tripplanner.dto.Itinerary;
import it.unisalento.tripplanner.dto.RefType;
import it.unisalento.tripplanner.dto.TripStop;
import it.unisalento.tripplanner.model.ItineraryModel;
import it.unisalento.tripplanner.model.TripStopModel;
import org.junit.Assert;
import org.junit.Test;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class ItineraryConverterTest {

    @Test
    public void convertToDto() {
        ItineraryModel model = new ItineraryModel();
        model.setDescription("_description");

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
        model.setStops(stops);

        Itinerary dto = ItineraryConverter.INSTANCE.toDto(model);

        Assert.assertEquals(model.getDescription(), dto.getDescription());
        Assert.assertEquals(model.getStops().size(), dto.getStops().size());
    }

    @Test
    public void convertToModel() {
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

        ItineraryModel model = ItineraryConverter.INSTANCE.toModel(dto);

        Assert.assertEquals(dto.getDescription(), model.getDescription());
        Assert.assertEquals(dto.getStops().size(), model.getStops().size());
    }
}
