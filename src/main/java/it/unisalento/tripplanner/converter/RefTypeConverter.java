package it.unisalento.tripplanner.converter;

import it.unisalento.tripplanner.dto.RefType;
import org.mapstruct.Mapper;

@Mapper
public class RefTypeConverter {

    public static RefType toDto(Integer code) {
        switch (code) {
            case 0:
                return RefType.TYPE_EVENT;
            case 1:
                return RefType.TYPE_POI;
            default:
                throw new IllegalArgumentException("Code not valid for RefType: " + code.toString());
        }
    }

    public static Integer toModel(RefType refType) {
        if (refType == RefType.TYPE_EVENT)
            return 0;
        else
            return 1;
    }

}
