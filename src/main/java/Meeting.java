import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Meeting {

    public static void main(String[] args) {

        Calendar c1 = new Calendar(
                new TimeInterval(LocalTime.of(9, 0), LocalTime.of(19, 55)), new ArrayList<TimeInterval>());

        c1.addMeeting(new TimeInterval(LocalTime.of(9, 0), LocalTime.of(10, 30)));
        c1.addMeeting(new TimeInterval(LocalTime.of(12, 0), LocalTime.of(13, 00)));
        c1.addMeeting(new TimeInterval(LocalTime.of(16, 0), LocalTime.of(18, 00)));


        Calendar c2 = new Calendar(
                new TimeInterval(LocalTime.of(10, 0), LocalTime.of(18, 30)), new ArrayList<TimeInterval>());
        c2.addMeeting(new TimeInterval(LocalTime.of(10, 0), LocalTime.of(11, 30)));
        c2.addMeeting(new TimeInterval(LocalTime.of(12, 30), LocalTime.of(14, 30)));
        c2.addMeeting(new TimeInterval(LocalTime.of(14, 30), LocalTime.of(15, 00)));
        c2.addMeeting(new TimeInterval(LocalTime.of(16, 00), LocalTime.of(17, 00)));

        // System.out.println(c1);
        System.out.println(c1.getFreeTimeIntervalList(Duration.ofMinutes(30L)));
        // System.out.println(c2);
        System.out.println(c2.getFreeTimeIntervalList(Duration.ofMinutes(30L)));

        System.out.println(getCommonFreeTimeIntervalList(c1, c2, Duration.ofMinutes(30L)));
    }

    //Cel zadania.
    //Metoda zwraca listę możliwych terminów spotkań na podstwawie dwóch kalendarzy i długości czasu trwania
    public static List<TimeInterval> getCommonFreeTimeIntervalList(Calendar c1, Calendar c2, Duration duration) {
        List<TimeInterval> freeCommonTimeList = new ArrayList<>();
        List<TimeInterval> c1freeTimeList = c1.getFreeTimeIntervalList(duration);
        List<TimeInterval> c2freeTimeList = c2.getFreeTimeIntervalList(duration);

        for (int i = 0; i < c1freeTimeList.size(); i++) {

            for (int j = 0; j < c2freeTimeList.size(); j++) {
                Optional<TimeInterval> o = c1freeTimeList.get(i).getCommonTimeInterval(c2freeTimeList.get(j));
                if (o.isPresent()) {
                    TimeInterval ti = o.get();
                    freeCommonTimeList.add(ti);
                }
            }
        }
        return freeCommonTimeList;
    }
}
