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
        switch (code) {
            case 0:
                return RefType.TYPE_EVENT;
            case 1:
                return RefType.TYPE_POI;
            default:
                throw new IllegalArgumentException("Code not valid for RefType: " + code.toString());
        }
    }

    Integer toRefTypeCode(RefType refType) {
        if (refType == RefType.TYPE_EVENT)
            return 0;
        else
            return 1;
    }
}
