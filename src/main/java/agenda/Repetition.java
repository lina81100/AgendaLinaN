package agenda;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class Repetition {

    private ChronoUnit frequency;
    private LocalDate startDate;

    public Repetition(ChronoUnit frequency) {
        this.frequency = frequency;
    }

    public ChronoUnit getFrequency() {
        return frequency;
    }

    public void setStartDate(LocalDateTime start) {
        this.startDate = start.toLocalDate();
    }

    public boolean isInDay(LocalDate aDay) {
        long daysBetween = ChronoUnit.DAYS.between(startDate, aDay);
        return daysBetween % frequency.getDuration().toDays() == 0;
    }
}
