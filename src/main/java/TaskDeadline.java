public class TaskDeadline extends Task {
    protected String endDate;
    public TaskDeadline(String taskDesc, String endDate) {
        super(taskDesc);
        this.endDate = endDate;
    }
    @Override
    public String taskToString() {
        return "[DL]" + super.taskToString() + ", due " + endDate;
    }
}
