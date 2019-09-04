package it.unisalento.tripplanner.converter;

import it.unisalento.tripplanner.dto.RefType;
import it.unisalento.tripplanner.dto.TripStop;
import it.unisalento.tripplanner.model.TripStopModel;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public abstract class TripStopConverter {

    static TripStopConverter INSTANCE = Mappers.getMapper(TripStopConverter.class);

    abstract TripStopModel toModel(TripStop dto);
    abstract TripStop toDto(TripStopModel model);

    RefType toRefTypeEnum(Integer code) {
        return RefTypeConverter.toDto(code);
    }

    Integer toRefTypeCode(RefType refType) {
        return RefTypeConverter.toModel(refType);
    }
}
