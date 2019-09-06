package it.unisalento.tripplanner.converter;

import it.unisalento.tripplanner.dto.TripStop;
import it.unisalento.tripplanner.model.TripStopModel;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(uses = {RefTypeConverter.class, LocationConverter.class})
public abstract class TripStopConverter {

    static TripStopConverter INSTANCE = Mappers.getMapper(TripStopConverter.class);

    abstract TripStopModel toModel(TripStop dto);
    abstract TripStop toDto(TripStopModel model);
}
