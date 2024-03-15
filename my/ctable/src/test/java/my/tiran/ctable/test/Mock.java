package my.tiran.ctable.test;

import java.time.LocalDateTime;
import java.util.concurrent.ThreadLocalRandom;

public class Mock {
    public LocalDateTime startDateTime;
    public LocalDateTime endDateTime;
    public LocalDateTime generateRandomDateTime() {
        // Generate random start time between 00:00 and 23:00
        int startHour = ThreadLocalRandom.current().nextInt(0, 24);
        int startMinute = ThreadLocalRandom.current().nextInt(0, 60);
        startDateTime = LocalDateTime.of(0, 1, 1, startHour, startMinute);

        // Generate random end time greater than start time
        int endHour = ThreadLocalRandom.current().nextInt(startHour, 24);
        int endMinute = ThreadLocalRandom.current().nextInt(startMinute, 60);
        endDateTime = LocalDateTime.of(0, 1, 1, endHour, endMinute);

        return endDateTime.isBefore(startDateTime) ? generateRandomDateTime() : endDateTime;
    }
}
