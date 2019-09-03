package it.unisalento.tripplanner.converter;

import it.unisalento.tripplanner.dto.Location;
import it.unisalento.tripplanner.model.LocationModel;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface LocationConverter {

    LocationConverter INSTANCE = Mappers.getMapper( LocationConverter.class );

    LocationModel locationToLocationModel(Location location);
    Location locationModelToLocation(LocationModel locationModel);

}
