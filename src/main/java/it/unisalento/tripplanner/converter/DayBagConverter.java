package it.unisalento.tripplanner.converter;

import it.unisalento.tripplanner.dto.DayBag;
import it.unisalento.tripplanner.model.DayBagModel;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(uses = {
        RefTypeConverter.class,
        LocationConverter.class,
        TripStopConverter.class})
public abstract class DayBagConverter {

    static DayBagConverter INSTANCE = Mappers.getMapper(DayBagConverter.class);

    abstract DayBagModel toModel(DayBag dto);
    abstract DayBag toDto(DayBagModel model);
}
