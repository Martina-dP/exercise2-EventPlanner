public class DelPinoMartinaEventTask {
    private String text;
    private Boolean isCompleted;

    public DelPinoMartinaEventTask(Boolean isCompleted, String text) {
        this.isCompleted = isCompleted;
        this.text = text;
    }

    public String getText() {
        return text;
    }
    public Boolean getCompleted() {
        return isCompleted;
    }

    public void setCompleted(Boolean completed) {
        isCompleted = completed;
    }

    @Override
    public String toString() {
        return "DelPinoMartinaEventTask{" +
                "text='" + text + ":\n" +
                "isCompleted=" + isCompleted +
                '}';
    }
}
