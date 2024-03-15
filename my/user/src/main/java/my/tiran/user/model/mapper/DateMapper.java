package my.tiran.user.model.mapper;

import my.tiran.user.common.util.DateParserUtil;

import java.time.LocalDateTime;

public class DateMapper {
    public String asString(LocalDateTime date) {
        return DateParserUtil.get(date);
    }

    public LocalDateTime asDate(String date) {
        return DateParserUtil.parseToStandard(date);
    }
}
