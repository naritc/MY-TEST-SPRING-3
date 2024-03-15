package my.tiran.ctable.model.mapper;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import my.tiran.ctable.common.util.DateParserUtil;
import my.tiran.ctable.model.dto.Reserve;
import my.tiran.ctable.model.dto.ReserveDetail;
import my.tiran.ctable.model.entity.ReserveDetailEntity;
import my.tiran.ctable.model.entity.ReserveEntity;
import org.mapstruct.*;

@Mapper(
    componentModel = "spring",
    uses = DateMapper.class
)
public interface ReserveMapper {
    @Mapping(target = "reserveDate", source = "reserveDate", qualifiedByName = "toDate")
    ReserveEntity toReserveEntity(Reserve reserve);

    ReserveDetailEntity toReserveDetailEntity(ReserveDetail reserveDetail);
    List<ReserveDetailEntity> toReserveDetailEntities(List<ReserveDetail> reserveDetails);

    @Named("toDate")
    default LocalDateTime toDateTime(LocalDate localDate) {
        return DateParserUtil.parse(localDate);

    }
}
