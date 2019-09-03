package it.unisalento.tripplanner.converter;

import it.unisalento.tripplanner.dto.Location;
import it.unisalento.tripplanner.model.LocationModel;
import org.junit.Assert;
import org.junit.Test;

public class LocationConverterTest {

    @Test
    public void convertModel() {
        LocationModel model = new LocationModel();
        model.setLatitude(1.2313212);
        model.setLongitude(543.1233431);

        Location dto = LocationConverter.INSTANCE.toDto(model);

        Assert.assertEquals(model.getLatitude(), dto.getLatitude(), 0.0000001);
        Assert.assertEquals(model.getLongitude(), dto.getLongitude(), 0.0000001);
    }

    @Test
    public void convertDto() {
        Location dto = new Location();
        dto.setLatitude(1.2313212);
        dto.setLongitude(543.1233431);

        LocationModel model = LocationConverter.INSTANCE.toModel(dto);

        Assert.assertEquals(dto.getLatitude(), model.getLatitude(), 0.0000001);
        Assert.assertEquals(dto.getLongitude(), model.getLongitude(), 0.0000001);
    }

}
