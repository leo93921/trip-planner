package it.unisalento.tripplanner.converter;

import it.unisalento.tripplanner.dto.Itinerary;
import it.unisalento.tripplanner.model.ItineraryModel;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(uses = TripStopConverter.class)
public interface ItineraryConverter {

    ItineraryConverter INSTANCE = Mappers.getMapper(ItineraryConverter.class);

    Itinerary toDto(ItineraryModel model);
    ItineraryModel toModel(Itinerary dto);

}
