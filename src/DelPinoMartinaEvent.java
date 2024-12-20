import java.time.LocalDate;
import java.util.ArrayList;

public class DelPinoMartinaEvent {
    public enum Priority {
        HIGH,
        MEDIUM,
        LOW
    }

    private String title;
    private LocalDate date;
    private Priority priority;
    private ArrayList<DelPinoMartinaEventTask> tasks;

    public DelPinoMartinaEvent(String title, LocalDate date, Priority priority) {
        this.title = title;
        this.date = date;
        this.priority = priority;
        this.tasks = new ArrayList<>();
    }

    public void addTask(DelPinoMartinaEventTask task) {
        this.tasks.add(task);
    }

    public String getTitle() {
        return title;
    }
    public LocalDate getDate() {
        return date;
    }
    public ArrayList<DelPinoMartinaEventTask> getTasks() {
        return tasks;
    }

    @Override
    public String toString() {
        int totalDone = 0;
        for (DelPinoMartinaEventTask task : tasks) {
            if (task.getCompleted()){
                totalDone++;
            }
        }
        return "DelPinoMartinaEvent{" +
                "title: " + title + "," +
                "date: " + date + "," +
                "priority: " + priority + "," +
                "tasks completed: " + totalDone + " " + "total tasks: " + tasks.size() +
                '}';
    }
}
