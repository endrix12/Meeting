import java.time.Duration;
import java.time.LocalTime;
import java.util.Optional;

public class TimeInterval {

   private LocalTime start;
   private LocalTime end;

    public TimeInterval(LocalTime start, LocalTime end) {
        this.start = start;
        this.end = end;
    }

    public LocalTime getStart() {
        return start;
    }

    public void setStart(LocalTime start) {
        this.start = start;
    }

    public LocalTime getEnd() {
        return end;
    }

    public void setEnd(LocalTime end) {
        this.end = end;
    }

    public Duration getDuration() {
        return Duration.between(start, end);
    }

    public Optional<TimeInterval> getCommonTimeInterval(TimeInterval timeInterval) {
        LocalTime latestStart;
        LocalTime earliestEnd;
        if (this.getStart().compareTo(timeInterval.getStart()) > 0) {
            latestStart = this.getStart();
        } else {
            latestStart = timeInterval.getStart();
        }
        if (this.getEnd().compareTo(timeInterval.getEnd()) <0) {
            earliestEnd = this.getEnd();
        } else {
            earliestEnd = timeInterval.getEnd();
        }
        if(latestStart.isAfter(earliestEnd)){
            return Optional.empty();
        }
        return Optional.of(new TimeInterval(latestStart,earliestEnd));
    }

    @Override
    public String toString() {
        return "[" +
                "start: " + start +
                ", end: " + end +
                ']';
    }
}
