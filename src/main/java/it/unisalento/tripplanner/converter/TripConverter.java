package it.unisalento.tripplanner.converter;

import it.unisalento.tripplanner.dto.Trip;
import it.unisalento.tripplanner.model.TripModel;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(uses = DayBagConverter.class)
public interface TripConverter {

    TripConverter INSTANCE = Mappers.getMapper(TripConverter.class);

    Trip toDto(TripModel model);
    TripModel toModel(Trip trip);

}
