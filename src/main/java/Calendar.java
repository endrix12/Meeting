import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class Calendar {

  private  TimeInterval workingHours;
  private  List<TimeInterval> plannedMeeting;

    public Calendar(TimeInterval workingHours, List<TimeInterval> plannedMeeting) {
        this.workingHours = workingHours;
        this.plannedMeeting = plannedMeeting;
    }

    public TimeInterval getWorkingHours() {
        return workingHours;
    }

    public void setWorkingHours(TimeInterval workingHours) {
        this.workingHours = workingHours;
    }

    public List<TimeInterval> getPlannedMeeting() {
        return plannedMeeting;
    }

    public void setPlannedMeeting(List<TimeInterval> plannedMeeting) {
        this.plannedMeeting = plannedMeeting;
    }

    public void addMeeting(TimeInterval tm) {
        this.plannedMeeting.add(tm);
    }
//Metoda zwracająca listę przedziałów wolnego czasu o czasie trwania równym bądź dłuższym od
    //parametru duration.
    public List<TimeInterval> getFreeTimeIntervalList(Duration duration) {
        List<TimeInterval> freeTimeList = new ArrayList<>();
        TimeInterval ti = new TimeInterval(workingHours.getStart(), plannedMeeting.get(0).getStart());

        if(ti.getDuration().compareTo(duration)>=0) {
            freeTimeList.add(new TimeInterval(ti.getStart(),ti.getEnd()));
        }
        for(int i = 0; i<plannedMeeting.size()-1;i++){
            ti.setStart(plannedMeeting.get(i).getEnd());
            ti.setEnd(plannedMeeting.get(i+1).getStart());
            if(ti.getDuration().compareTo(duration)>=0){
                freeTimeList.add(new TimeInterval(ti.getStart(),ti.getEnd()));
            }
        }
        ti.setStart(plannedMeeting.get(plannedMeeting.size()-1).getEnd());
        ti.setEnd(workingHours.getEnd());
        if(ti.getDuration().compareTo(duration)>=0) {
            freeTimeList.add(new TimeInterval(ti.getStart(), ti.getEnd()));
        }
        return freeTimeList;
    }

    @Override
    public String toString() {
        return "Calendar{" +
                "workingHours=" + workingHours +
                ", plannedMeeting=" + plannedMeeting +
                '}';
    }
}
