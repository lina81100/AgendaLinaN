package agenda;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Termination {
    private LocalDate terminationDate;
    private long numberOfOccurrences;
    private boolean isDateTermination;
    private boolean isOccurrencesTermination;

    public Termination(LocalDate terminationDate) {
        this.terminationDate = terminationDate;
        this.isDateTermination = true;
    }

    public Termination(long numberOfOccurrences) {
        this.numberOfOccurrences = numberOfOccurrences;
        this.isOccurrencesTermination = true;
    }

    public Termination(LocalDate localDate, ChronoUnit chronoUnit, long numberOfOccurrences) {
    }

    public boolean isDateTermination() {
        return isDateTermination;
    }

    public boolean isOccurrencesTermination() {
        return isOccurrencesTermination;
    }

    public LocalDate getTerminationDate() {
        return terminationDate;
    }

    public long getNumberOfOccurrences() {
        return numberOfOccurrences;
    }

    public LocalDate terminationDateInclusive() {
        return terminationDate;
    }

    public Object numberOfOccurrences() {
        return numberOfOccurrences;
    }
}
