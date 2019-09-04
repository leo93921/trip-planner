package it.unisalento.tripplanner.converter;

import it.unisalento.tripplanner.dto.RefType;
import it.unisalento.tripplanner.dto.TripStop;
import it.unisalento.tripplanner.model.TripStopModel;
import org.junit.Assert;
import org.junit.Test;

import java.time.LocalTime;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;

public class TripStopConverterTest {

    @Test
    public void convertModelType() {
        TripStopModel model = new TripStopModel();
        model.setId("_id");
        model.setRefId("ref_id");
        model.setRefType(1);
        model.setVisitOrder(2);
        model.setVisitTime(LocalTime.of(12, 42));
        model.setWarningPresent(true);
        model.setWarningMessages(List.of("w_m_1", "w_m_2"));

        TripStop dto = TripStopConverter.INSTANCE.toDto(model);

        Assert.assertEquals(model.getId(), dto.getId());
        Assert.assertEquals(model.getRefId(), dto.getRefId());
        Assert.assertEquals(RefType.TYPE_POI, dto.getRefType());
        Assert.assertEquals(model.getVisitOrder(), dto.getVisitOrder());
        Assert.assertEquals(model.getVisitTime(), dto.getVisitTime());
        Assert.assertEquals(model.getWarningPresent(), dto.getWarningPresent());
        Assert.assertThat(model.getWarningMessages(), is(dto.getWarningMessages()));
    }

    @Test(expected = IllegalArgumentException.class)
    public void convertModelIllegalArgument() {
        TripStopModel model = new TripStopModel();
        model.setId("_id");
        model.setRefId("ref_id");
        model.setRefType(4); // Not valid code
        model.setVisitOrder(2);
        model.setVisitTime(LocalTime.of(12, 42));
        model.setWarningPresent(true);
        model.setWarningMessages(List.of("w_m_1", "w_m_2"));

        TripStop dto = TripStopConverter.INSTANCE.toDto(model);
    }


    @Test
    public void convertDtoPOI() {
        TripStop tripStop = new TripStop();
        tripStop.setId("_id");
        tripStop.setRefId("ref_id");
        tripStop.setRefType(RefType.TYPE_POI);
        tripStop.setVisitOrder(2);
        tripStop.setVisitTime(LocalTime.of(12, 42));
        tripStop.setWarningPresent(true);
        tripStop.setWarningMessages(List.of("w_m_1", "w_m_2"));

        TripStopModel model = TripStopConverter.INSTANCE.toModel(tripStop);

        Assert.assertEquals(tripStop.getId(), model.getId());
        Assert.assertEquals(tripStop.getRefId(), model.getRefId());
        Assert.assertEquals(Integer.valueOf(1), model.getRefType());
        Assert.assertEquals(tripStop.getVisitOrder(), model.getVisitOrder());
        Assert.assertEquals(tripStop.getVisitTime(), model.getVisitTime());
        Assert.assertEquals(tripStop.getWarningPresent(), model.getWarningPresent());
        Assert.assertThat(tripStop.getWarningMessages(), is(model.getWarningMessages()));
    }


}
