package su.tsc.reports.util;

import java.time.*;
import java.time.format.DateTimeFormatter;

public class DateTimeUtil {

    public static OffsetDateTime StringToOffsetDateTime(String inputDate) {
        if (inputDate.isEmpty()) {
            return null;
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.parse(inputDate, formatter);
        LocalDateTime datetime = LocalDateTime.of(date, LocalTime.of(0 ,0));
        ZonedDateTime zoned = datetime.atZone(ZoneId.of("Europe/Moscow"));
        return zoned.toOffsetDateTime();
    }

    public static String offsetDateTimeToString(OffsetDateTime dateTime) {
        if (dateTime == null) {
            return "";
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        return dateTime.atZoneSameInstant(ZoneId.of("Europe/Moscow")).format(formatter);
    }
}
