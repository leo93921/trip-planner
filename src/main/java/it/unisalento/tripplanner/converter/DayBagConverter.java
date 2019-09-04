package it.unisalento.tripplanner.converter;

import it.unisalento.tripplanner.dto.DayBag;
import it.unisalento.tripplanner.dto.RefType;
import it.unisalento.tripplanner.model.DayBagModel;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public abstract class DayBagConverter {

    static DayBagConverter INSTANCE = Mappers.getMapper(DayBagConverter.class);

    abstract DayBagModel toModel(DayBag dto);
    abstract DayBag toDto(DayBagModel model);

    RefType toRefTypeEnum(Integer code) {
        return RefTypeConverter.toDto(code);
    }

    Integer toRefTypeCode(RefType refType) {
        return RefTypeConverter.toModel(refType);
    }

}
