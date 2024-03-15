package my.tiran.ctable.common.util;


import java.time.*;
import java.time.chrono.ThaiBuddhistChronology;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;
import java.util.Date;

public class DateParserUtil {
    public enum YearFormat {BC, AD}

    public enum Type {DATETIME, DATE, TIME}

    private static final class StdFormat {
        private static final String DATE = "yyyy-MM-dd";
        private static final String TIME = "HH:mm:ss";
        private static final String DATE_TIME = "yyyy-MM-dd HH:mm:ss";
    }

    private static class PrintFormat {
        private static final String DATE = "dd/MM/yyyy";
        private static final String TIME = "HH:mm:ss";
        private static final String DATE_TIME = "dd/MM/yyyy HH:mm:ss";
    }

    private static final String[] parseFormats = {
            "yyyy-MM-dd'T'HH:mm:ss.SSSSSS",
            "yyyy-MM-dd'T'HH:mm:ss.SSSX",
            "yyyy-MM-dd'T'HH:mm:ss.SSSZ",
            "yyyy-MM-dd'T'HH:mm:ss.SSS",
            "yyyy-MM-dd'T'HH:mm:ss",
            "yyyy-MM-dd HH:mm:ss.SSSSSS",
            "yyyy-MM-dd HH:mm:ss.SSSX",
            "yyyy-MM-dd HH:mm:ss.SSSZ",
            "yyyy-MM-dd HH:mm:ss.SSS",
            "yyyy-MM-dd HH:mm:ss",
            "dd-MM-yyyy HH:mm:ss",
            "dd/MM/yyyy HH:mm:ss",
            "dd/MM/yyyy",
            "yyyy-MM-dd",
            "dd/MM/yyyy",
            "dd-MM-yyyy",
            "dd-MM-yyyy",
            "yyyyMMdd",
            "HHmmssSSS",
            "HH:mm:ss",
            "HHmmss"
    };

    public interface OtherFormat {
        String DD_MM_YYYY_DAT = "dd-MM-yyyy";
        String YYMMDD = "yyMMdd";
    }

    public static LocalDateTime parse(LocalDate date) {
        return date.atStartOfDay();
    }


    public static LocalDateTime parseToStandard(String date) {
        DateTimeFormatterBuilder formatterBuilder = new DateTimeFormatterBuilder();
        for (String parseFormat : parseFormats) {
            formatterBuilder.appendOptional(DateTimeFormatter.ofPattern(parseFormat));
        }
        DateTimeFormatter formatter = formatterBuilder.toFormatter();

        try {
            return LocalDateTime.parse(date, formatter);
        } catch (Exception ignored) {
            //
        }

        LocalDate resultDate;
        try {
            resultDate = LocalDate.parse(date, formatter);
            return LocalDateTime.of(resultDate, LocalTime.of(0, 0, 0));
        } catch (Exception ignored) {
            //
        }

        LocalTime resultTime;
        try {
            resultTime = LocalTime.parse(date, formatter);
            return LocalDateTime.of(LocalDate.now() ,resultTime);
        } catch (Exception ignored) {
            //
        }

        return null;
    }

    public static OffsetDateTime offSetNow() { return OffsetDateTime.now(); }
    public static LocalDateTime now() {
        return LocalDateTime.now();
    }

    public static String get(LocalDateTime date) {
        return print(YearFormat.BC, Type.DATETIME, date);
    }

    public static String getNow() {
        return now().format(DateTimeFormatter.ofPattern(StdFormat.DATE_TIME));
    }
    public static String getNowDate() {
        return now().format(DateTimeFormatter.ofPattern(StdFormat.DATE));
    }
    public static String getNowTime() {
        return now().format(DateTimeFormatter.ofPattern(StdFormat.TIME));
    }
    private static String parse(String date, String format) {
        try {
            return parseToStandard(date).format(DateTimeFormatter.ofPattern(format));
        } catch (Exception e) {
            return "";
        }
    }

    private static String parse(String date, Type format, YearFormat yearFormat) {
        if (date == null) return "";
        try {
            String parseFormat = switch (format) {
                case DATE -> StdFormat.DATE;
                case TIME -> StdFormat.TIME;
                default -> StdFormat.DATE_TIME;
            };

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(parseFormat);
            if (YearFormat.BC == yearFormat) {
                formatter = formatter.withChronology(ThaiBuddhistChronology.INSTANCE);
            }

            return parseToStandard(date).format(formatter);
        } catch (DateTimeParseException e) {
            return "";
        }
    }

    private static String parse(LocalDateTime date, String format) {
        try {
            return date.format(DateTimeFormatter.ofPattern(format));
        } catch (Exception e) {
            return "";
        }
    }

    public static String parse(LocalDateTime date) {
        return parse(date, StdFormat.DATE_TIME);
    }

    public static String parse(String date) {
        return parse(date, StdFormat.DATE_TIME);
    }
    public static LocalDateTime parse(Date date) {
        if (date == null) return null;
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
    }
    public static String parseDate(String date) {
        return parse(date, StdFormat.DATE);
    }

    public static String parseDate(String date, YearFormat yearFormat) {
        return parse(date, Type.DATE, yearFormat);
    }

    public static String parseTime(String date) {
        return parse(date, StdFormat.TIME);
    }

    private static String print(YearFormat yearFormat, Type format, LocalDateTime date) {
        if (date == null) return "";
        try {
            String parseFormat = switch (format) {
                case DATE -> PrintFormat.DATE;
                case TIME -> PrintFormat.TIME;
                default -> PrintFormat.DATE_TIME;
            };

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(parseFormat);
            if (YearFormat.BC == yearFormat) {
                formatter = formatter.withChronology(ThaiBuddhistChronology.INSTANCE);
            }

            return date.format(formatter);
        } catch (DateTimeParseException e) {
            return "";
        }
    }
    private static String print(YearFormat yearFormat, String format, LocalDateTime date) {
        if (date == null) return "";
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
            if (YearFormat.BC == yearFormat) {
                formatter = formatter.withChronology(ThaiBuddhistChronology.INSTANCE);
            }
            return date.format(formatter);
        } catch (DateTimeParseException e) {
            return "";
        }
    }
    public static String print(LocalDateTime date) {
        return print(YearFormat.AD, Type.DATETIME, date);
    }
    private static String print(YearFormat yearFormat, Type format, String date) {
        return print(yearFormat, format, parseToStandard(date));
    }

    public static String printNow() {
        return print(YearFormat.AD, Type.DATETIME, now());
    }
    public static String printNowDate() {
        return print(YearFormat.AD, Type.DATE, now());
    }
    public static String printNowTime() {
        return print(YearFormat.AD, Type.TIME, now());
    }
    public static String printNow(YearFormat yearFormat) {
        return print(yearFormat, Type.DATETIME, now());
    }
    public static String printNowDate(YearFormat yearFormat) {
        return print(yearFormat, Type.DATE, now());
    }
    public static String printNowTime(YearFormat yearFormat) {
        return print(yearFormat, Type.TIME, now());
    }
    public static String parsePrint(String date) {
        return print(YearFormat.AD, Type.DATETIME, date);
    }
    public static String parsePrint(String date, YearFormat format) {
        return print(format, Type.DATETIME, date);
    }
    public static String parsePrintDate(String date) {
        return print(YearFormat.AD, Type.DATE, date);
    }
    public static String parsePrintDate(String date, YearFormat format) {
        return print(format, Type.DATE, date);
    }
    public static String parsePrintTime(String date) {
        return print(YearFormat.AD, Type.TIME, date);
    }
    public static String parsePrintTime(String date, YearFormat format) {
        return print(format, Type.TIME, date);
    }
    public static String parsePrintFormat(String date, String otherFormat, YearFormat format) {
        return print(format, otherFormat, parseToStandard(date));
    }

    public static String parsePrintFormat(String date, String otherFormat) {
        return print(YearFormat.AD, otherFormat, parseToStandard(date));
    }
}
