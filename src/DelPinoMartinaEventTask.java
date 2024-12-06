public class DelPinoMartinaEventTask {
    private String text;
    private Boolean isCompleted;

    public DelPinoMartinaEventTask(String text) {
        this.isCompleted = false;
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
