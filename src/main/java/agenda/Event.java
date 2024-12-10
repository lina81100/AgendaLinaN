package agenda;

import java.time.*;
import java.time.temporal.ChronoUnit;
import java.util.*;

public class Event {

    private String myTitle;
    private LocalDateTime myStart;
    private Duration myDuration;
    private Repetition repetition;
    private Termination termination;
    private List<LocalDate> exceptionList = new ArrayList<>();

    public Event(String title, LocalDateTime start, Duration duration) {
        this.myTitle = title;
        this.myStart = start;
        this.myDuration = duration;
    }

    public void setRepetition(ChronoUnit frequency) {
        this.repetition = new Repetition(frequency);
        repetition.setStartDate(myStart);
    }

    public void addException(LocalDate date) {
        exceptionList.add(date);
    }

    public void setTermination(LocalDate terminationInclusive) {
        this.termination = new Termination(terminationInclusive);
    }

    public void setTermination(long numberOfOccurrences) {
        this.termination = new Termination(numberOfOccurrences);
    }

    public int getNumberOfOccurrences() {
        if (termination == null) {
            return 1;
        }

        if (termination.isDateTermination()) {
            long daysBetween = ChronoUnit.DAYS.between(myStart.toLocalDate(), termination.getTerminationDate());
            return (int) (daysBetween / repetition.getFrequency().getDuration().toDays()) + 1;
        } else if (termination.isOccurrencesTermination()) {
            return (int) termination.getNumberOfOccurrences();
        }

        return 1;
    }

    public LocalDate getTerminationDate() {
        if (termination == null) {
            return null;
        }

        if (termination.isOccurrencesTermination()) {
            long occurrences = termination.getNumberOfOccurrences();
            long daysBetween = occurrences * repetition.getFrequency().getDuration().toDays();
            return myStart.toLocalDate().plusDays(daysBetween);
        }

        return termination.getTerminationDate();
    }

    public boolean isInDay(LocalDate aDay) {
        if (exceptionList.contains(aDay)) {
            return false;
        }

        if (aDay.isBefore(myStart.toLocalDate())) {
            return false;
        }

        LocalDate eventEndDate = myStart.toLocalDate().plusDays(myDuration.toDays());

        if ((aDay.isEqual(myStart.toLocalDate()) || aDay.isAfter(myStart.toLocalDate()))
                && aDay.isBefore(eventEndDate.plusDays(1))) {
            return true;
        }

        if (repetition != null && repetition.isInDay(aDay)) {
            return true;
        }

        if (termination != null && !aDay.isAfter(termination.getTerminationDate())) {
            return true;
        }

        return false;
    }


    public String getTitle() {
        return myTitle;
    }

    public LocalDateTime getStart() {
        return myStart;
    }

    public Duration getDuration() {
        return myDuration;
    }

    @Override
    public String toString() {
        return "Event{title='%s', start=%s, duration=%s}".formatted(myTitle, myStart, myDuration);
    }
}
